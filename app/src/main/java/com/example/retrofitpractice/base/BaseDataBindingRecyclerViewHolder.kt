package com.example.retrofitpractice.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDataBindingRecyclerViewHolder<BINDING: ViewDataBinding, ITEM>(itemView: View): BaseRecyclerViewHolder<ITEM>(itemView) {
    protected var binding: BINDING = DataBindingUtil.bind(itemView)!!
}