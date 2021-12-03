package com.example.retrofitpractice.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<ITEM>(itemView: View): RecyclerView.ViewHolder(itemView) {
    var onItemClick: ((Int) -> Unit?)? = null
    abstract fun onBindViewHolder(item: ITEM, position: Int)
}