package com.venkatesh.dynamiclistdemo.items.adapter

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.internal.LinkedTreeMap
import com.venkatesh.dynamiclistdemo.items.repository.model.Item
import com.venkatesh.dynamiclistdemo.items.view.ItemListingFragment
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.venkatesh.dynamiclistdemo.items.view.CriteriaListFragment


class CriteriaRecyclerViewAdapter(
    var mItems: MutableList<Item.Criteria>,
    var listener: CriteriaListFragment.OnListItemListener?,
    var defaultListener: CriteriaListFragment.OnDefaultListItemListener
) : RecyclerView.Adapter<CriteriaViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CriteriaViewHolder =
        CriteriaViewHolder(
            LayoutInflater.from(p0.context).inflate(
                com.venkatesh.dynamiclistdemo.R.layout.trending_card_view,
                p0,
                false
            ), listener, defaultListener
        )

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(p0: CriteriaViewHolder, p1: Int){
        p0.setItem(mItems[p1])
    }

}

/**
 * Class extends [RecyclerView.ViewHolder]
 * @param itemView is the instance of view
 * @param listener is [ItemListingFragment.OnWatchlistItemListener] listener
 */
class CriteriaViewHolder(itemView: View, private val listener: CriteriaListFragment.OnListItemListener?, private val defaultListener: CriteriaListFragment.OnDefaultListItemListener): RecyclerView.ViewHolder(itemView) {

    private var item: Item.Criteria? = null
    private var titleTextView: AppCompatTextView = itemView.findViewById(com.venkatesh.dynamiclistdemo.R.id.title_text_view)
    /**
     * In this method, the views are updated with data
     * @param item is the instance of [Item]
     */
    fun setItem(item: Item.Criteria) {
        this.item = item
        val split = item.text.split(" ")
        titleTextView.text = ""
        split.forEach {
            if(it.startsWith("$")) {
                val variable = item.variable
                val value = variable?.get(it) as LinkedTreeMap<String, Any>
                val type = value["type"]
                if(type == "indicator"){
                    val defaultValue  = value["default_value"] as Double
                    titleTextView.append(makeLinkSpan("(" + defaultValue.toInt().toString() + ") ", View.OnClickListener {
                        defaultListener.onListClickInteraction(value, "")
                    }))
                }else if(type == "value"){
                    val valuesOfValue = value["values"] as ArrayList<Double>
                    titleTextView.append(makeLinkSpan("("+valuesOfValue[0].toInt().toString()+") ", View.OnClickListener {
                        listener?.onListClickInteraction(getFloatArrayFromList(valuesOfValue))
                    }))
                }
            }else{
                titleTextView.append("$it ")
            }
        }
        makeLinksFocusable(titleTextView)
    }

    private fun getFloatArrayFromList(floatList : ArrayList<Double>):FloatArray{
        val floatArray = FloatArray(floatList.size)
        var i = 0

        for (f in floatList) {
            floatArray[i++] = f.toFloat()
        }
        return floatArray
    }
    private fun makeLinksFocusable(tv: TextView) {
        val m = tv.movementMethod
        if ((m == null || m !is LinkMovementMethod) && tv.linksClickable) {
            tv.movementMethod = LinkMovementMethod.getInstance()
        }
    }
    private fun makeLinkSpan(text: CharSequence, listener: View.OnClickListener): SpannableString {
        val link = SpannableString(text)
        link.setSpan(
            ClickableString(listener), 0, text.length,
            SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return link
    }
    private class ClickableString(private val mListener: View.OnClickListener) : ClickableSpan() {
        override fun onClick(v: View) {
            mListener.onClick(v)
        }
    }

}