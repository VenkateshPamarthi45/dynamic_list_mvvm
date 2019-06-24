package com.venkatesh.dynamiclistdemo.items.adapter

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.venkatesh.dynamiclistdemo.items.repository.model.Item
import com.venkatesh.dynamiclistdemo.items.view.ItemListingFragment

class VariableValuesRecyclerViewAdapter(var mItems: FloatArray, var listener: ItemListingFragment.OnWatchlistItemListener?) : RecyclerView.Adapter<VariableValuesViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VariableValuesViewHolder =
        VariableValuesViewHolder(
            LayoutInflater.from(p0.context).inflate(
                com.venkatesh.dynamiclistdemo.R.layout.trending_card_view,
                p0,
                false
            ), listener
        )

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(p0: VariableValuesViewHolder, p1: Int){
        p0.setItem(mItems[p1])
    }

}

/**
 * Class extends [RecyclerView.ViewHolder]
 * @param itemView is the instance of view
 * @param listener is [ItemListingFragment.OnWatchlistItemListener] listener
 */
class VariableValuesViewHolder(itemView: View, listener: ItemListingFragment.OnWatchlistItemListener?): RecyclerView.ViewHolder(itemView) {

    private var item: Float? = null
    private var titleTextView: AppCompatTextView = itemView.findViewById(com.venkatesh.dynamiclistdemo.R.id.title_text_view)
    init {
        titleTextView.setOnClickListener {
            listener?.onListClickInteraction(
                item!!, ""
            )
        }
    }

    /**
     * In this method, the views are updated with data
     * @param item is the instance of [Item]
     */
    fun setItem(item: Float) {
        this.item = item
        titleTextView.text = item.toString()
    }

}