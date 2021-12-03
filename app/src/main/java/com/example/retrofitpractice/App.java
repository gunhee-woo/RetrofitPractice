////////////////////////////////////////////////////////////////////////////////////////////////////
// CurrentMethodName Class
// Update: 2011-12-12

package com.example.retrofitpractice;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class App
{
	public static String TAG = "";
	public static String Name = "";
	public static String OwnNumer = "unknown";
	public static String OperatorName = "";

	public static String Function() {
		StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
		TextUtils.StringSplitter splitter = new TextUtils.SimpleStringSplitter('.');
		splitter.setString(stackTraceElements[1].getFileName());
		return splitter.iterator().next() + "." + stackTraceElements[1].getMethodName() + "()";
	}

	public static String Function(String paramDesc) {
		String methodName = Function();
		return methodName.replace("()", "(" + paramDesc + ")");
	}

	public static String getTopActivity(ActivityManager manager) {
		if (Build.VERSION.SDK_INT < 21) {
			return getPreLollipop(manager);
		}
		else {
			return getLollipop(manager);
		}
	}

	private static String getPreLollipop(ActivityManager manager) {
		@SuppressWarnings("deprecation")
		List<ActivityManager.RunningTaskInfo> tasks = manager.getRunningTasks(1);
		ActivityManager.RunningTaskInfo currentTask = tasks.get(0);
		ComponentName currentActivity = currentTask.topActivity;
		return currentActivity.getPackageName();
	}

	private static String getLollipop(ActivityManager manager) {
		final int PROCESS_STATE_TOP = 2;

		try {
			Field processStateField = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
			List<ActivityManager.RunningAppProcessInfo> processes = manager.getRunningAppProcesses();

			for (ActivityManager.RunningAppProcessInfo process : processes) {
				if (// Filters out most non-activity processes
					process.importance <= ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
					// Filters out processes that are just being
					// _used_ by the process with the activity
					process.importanceReasonCode == 0)
				{
					int state = processStateField.getInt(process);

					if (state == PROCESS_STATE_TOP)
                        /*
                         If multiple candidate processes can get here,
                         it's most likely that apps are being switched.
                         The first one provided by the OS seems to be
                         the one being switched to, so we stop here.
                         */ {
						return process.pkgList[0];
					}
				}
			}
		}
		catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		return "";
	}

	public static boolean isPackageInstalled(String packagename, Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
			return true;
		}
		catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	public static boolean isPackageEnabled(String packagename, Context context) {
		ApplicationInfo ai = null;
		try {
			ai = context.getPackageManager().getApplicationInfo(packagename, 0);

			if (ai != null) {
				return ai.enabled;
			}
		}
		catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String getApkPackageName(Context context, String apkName) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkName, 0);

		if (info == null) {
			return "";
		}

		return info.packageName;
	}

	public static String getApkVersion(Context context, String apkName) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkName, 0);

		if (info == null) {
			return "";
		}

		return info.versionName;
	}

	public static String getPackageVersion(Context context, String packageName) {
		PackageInfo pinfo = null;

		try {
			pinfo = context.getPackageManager().getPackageInfo(packageName, 0);
		}
		catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		if (pinfo == null) {
			return "";
		}

		return pinfo.versionName;
	}

	public static long getBuildDate(Context context) {
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
			ZipFile zf = new ZipFile(ai.sourceDir);
			ZipEntry ze = zf.getEntry("classes.dex");
			return ze.getTime();
		}
		catch (Exception e)
		{} // Ignore

		return 0l;
	}

	public static void getHashKey(Context context) {
		PackageInfo packageInfo = null;

		try {
			packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
		}
		catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		if (packageInfo == null) {
			Log.e("KeyHash", "KeyHash:null");
		}

		for (Signature signature : packageInfo.signatures) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
			catch (NoSuchAlgorithmException e) {
				Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
			}
		}
	}

	public static int getSDKVersion() {
		return Build.VERSION.SDK_INT;
	}

	public static void installApk(Context context, File apkFile) {
		Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
		Uri fileUri;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", apkFile);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
			intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		}
		else {
			fileUri = Uri.fromFile(apkFile);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		}

		intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	public static void installApk(Activity activity, File apkFile, int requestCode) {
		Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
		Uri fileUri;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			fileUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", apkFile);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
			intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		}
		else {
			fileUri = Uri.fromFile(apkFile);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		}

		intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
		activity.startActivityForResult(intent, requestCode);
	}
}
