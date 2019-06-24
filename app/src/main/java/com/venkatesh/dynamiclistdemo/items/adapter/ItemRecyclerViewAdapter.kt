package com.venkatesh.dynamiclistdemo.items.adapter

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.venkatesh.dynamiclistdemo.R
import com.venkatesh.dynamiclistdemo.items.repository.model.Item
import com.venkatesh.dynamiclistdemo.items.view.ItemListingFragment
import com.venkatesh.networklibrary.model.ModelManager


class ItemRecyclerViewAdapter(var mItems: MutableList<Item>, var listener: ItemListingFragment.OnWatchlistItemListener) : RecyclerView.Adapter<ItemViewHolder>() {

    fun addAllItems(items: List<Item>){
        mItems.addAll(items)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.trending_card_view,
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

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int){
        p0.setItem(mItems[p1])
    }
}

/**
 * Class extends [RecyclerView.ViewHolder]
 * @param itemView is the instance of view
 * @param listener is [ItemListingFragment.OnWatchlistItemListener] listener
 */
class ItemViewHolder(itemView: View, listener: ItemListingFragment.OnWatchlistItemListener): RecyclerView.ViewHolder(itemView) {

    private var item: Item? = null
    private var titleTextView: AppCompatTextView = itemView.findViewById(R.id.title_text_view)
    private var tagTextView: AppCompatTextView = itemView.findViewById(R.id.tag_text_view)
    init {
        titleTextView.setOnClickListener {
            listener.onListClickInteraction(
                item!!, ""
            )
        }
    }

    /**
     * In this method, the views are updated with data
     * @param item is the instance of [Item]
     */
    fun setItem(item: Item ) {
        this.item = item
        titleTextView.text = item.name
        tagTextView.text = item.tag
    }

}