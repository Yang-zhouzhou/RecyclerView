package com.example.recyclerviewtest.ui.theme

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewtest.R
import com.example.recyclerviewtest.TAG

/**
 * Created by liuxiaojuan.alpha on 2024/8/9
 * @author liuxiaojuan.alpha@bytedance.com
 */
data class Item(
    val id: Int,
    val text: String
)

object Diff: DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.text == newItem.text
    }
}

class Adapter(private var data: List<Item> = emptyList()): ListAdapter<Item, Adapter.ViewHolder>(Diff) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val text = view.findViewById<TextView>(R.id.text)

        fun bind(data: String) {
            text.text = data
            text.isVisible = true
        }
    }

    init {
        registerAdapterDataObserver(
            object : RecyclerView.AdapterDataObserver() {
                override fun onChanged() {
                    Log.d(TAG, "onChanged()")
                }

                override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                    Log.d(
                        TAG,
                        "onItemRangeChanged() positionStart:$positionStart itemCount:$itemCount",
                    )
                }

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    Log.d(
                        TAG,
                        "onItemRangeInserted() positionStart:$positionStart itemCount:$itemCount",
                    )
                }

                override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                    Log.d(
                        TAG,
                        "onItemRangeRemoved() positionStart:$positionStart itemCount:$itemCount",
                    )
                }

                override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                    Log.d(
                        TAG,
                        "onItemRangeMoved() fromPosition:$fromPosition toPosition:$toPosition itemCount:$itemCount",
                    )
                }

                override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                    Log.d(
                        TAG,
                        "onItemRangeChanged() positionStart:$positionStart itemCount:$itemCount payload:$payload",
                    )
                }
            }
        )
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d(
            TAG,
            "onViewAttachedToWindow position = ${holder.bindingAdapterPosition} view is ${holder.itemView.hashCode()}",
        )
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.d(TAG, "onDetachedFromRecyclerView position = ${holder.bindingAdapterPosition}")
    }


    internal fun setData(data: List<Item>) {
        Log.d(TAG, "setData data = $data")
        this.data = data
        submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position].text)
    }
}