package com.empty.jinux.baselibaray.view.recycleview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.empty.jinux.baselibaray.utils.layoutHeight

class SpaceItem(val size: Int) : Item {
    override val controller: ItemController
        get() = Controller

    private companion object Controller : ItemController {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            return ViewHolder(View(parent.context).also {
                it.layoutParams = RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
            })
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item) {
            item as SpaceItem
            holder.itemView.layoutHeight = item.size
        }
    }

    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}