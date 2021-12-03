package com.example.retrofitpractice.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.retrofitpractice.models.Lecture

class DiffUtilCallBack(private val oldData: List<Lecture>, private val newData: List<Lecture>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = newData[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }

}