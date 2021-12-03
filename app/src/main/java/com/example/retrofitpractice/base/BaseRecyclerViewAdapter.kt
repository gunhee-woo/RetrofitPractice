package com.example.retrofitpractice.base

import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerViewAdapter<VIEW_HOLDER: RecyclerView.ViewHolder, ITEM>: RecyclerView.Adapter<VIEW_HOLDER>() {
    var items = ArrayList<ITEM>()

    override fun getItemCount(): Int = items.size

    fun addItem(item: ITEM) = items.add(item)
    fun addAllItems(elements: Collection<ITEM>) = items.addAll(elements)
    fun getItem(position: Int) = items[position]
    fun deleteAllItems() = items.clear()
}
