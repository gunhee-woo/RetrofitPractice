package com.example.retrofitpractice.base

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.retrofitpractice.R
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Owner on 2017-06-22.
 */

abstract class BaseDataBindingDialogFragment<BINDING : ViewDataBinding> : BaseDialogFragment() {
    protected abstract val layoutId: Int
    protected lateinit var binding: BINDING

    protected abstract val disposables: CompositeDisposable

    var builder: AlertDialog.Builder ?= null

    override fun onDestroyView() {
        unbind()
        super.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomDialog)
        //        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomDialog);
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        Log.d(TAG, App.Function())
//
//        builder = AlertDialog.Builder(activity)
//        val view = activity!!.layoutInflater.inflate(layout, null)
//        binding = DataBindingUtil.setContentView(activity!!, layout)
//        setEvent()
//        builder.setView(view)
//        return builder.create()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layoutId, container, false)
        dialogView = binding.root

        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        initView()
        return dialogView
    }

    abstract fun initView()

    abstract fun bind()

    abstract fun unbind()
}
