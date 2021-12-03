package com.example.retrofitpractice.base

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.retrofitpractice.MainActivity

abstract class BaseDialogFragment: AppCompatDialogFragment() {
    protected var TAG = "/" + this::class.simpleName
    protected var dialogView: View? = null

    fun show(context: Context) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
                .add(this, TAG)
                .commitAllowingStateLoss()
    }
}