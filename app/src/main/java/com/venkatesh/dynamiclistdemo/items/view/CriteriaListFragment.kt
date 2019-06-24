package com.venkatesh.dynamiclistdemo.items.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.internal.LinkedTreeMap
import com.venkatesh.dynamiclistdemo.R
import com.venkatesh.dynamiclistdemo.databinding.ErrorCaseBinding
import com.venkatesh.dynamiclistdemo.databinding.FragmentItemListBinding
import com.venkatesh.dynamiclistdemo.items.adapter.CriteriaRecyclerViewAdapter
import com.venkatesh.dynamiclistdemo.items.repository.model.Item

private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [CriteriaListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CriteriaListFragment : Fragment() {
    private var item: Item? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable<Item>(ARG_PARAM1)
        }
    }

    private lateinit var listItemBinding: FragmentItemListBinding
    private lateinit var errorCaseBinding: ErrorCaseBinding
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var criteriaRecyclerViewAdapter : CriteriaRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        listItemBinding = FragmentItemListBinding.inflate(layoutInflater, container, false)
        errorCaseBinding = ErrorCaseBinding.inflate(layoutInflater, container, false)
        gridLayoutManager = GridLayoutManager(view.context, resources.getInteger(R.integer.items_columns), LinearLayoutManager.VERTICAL, false)
        listItemBinding.recyclerView.layoutManager = gridLayoutManager as RecyclerView.LayoutManager?
        renderItems()
        return listItemBinding.root
    }
    private fun renderItems() {
        criteriaRecyclerViewAdapter = CriteriaRecyclerViewAdapter(item?.criteria as MutableList<Item.Criteria>, object :
            OnListItemListener{
            override fun onListClickInteraction(items: FloatArray) {
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.container, VariableValuesListFragment.newInstance(items), VariableValuesListFragment::class.java.canonicalName)?.addToBackStack(VariableValuesListFragment::class.java.canonicalName)?.commit()
            }
        }, object : OnDefaultListItemListener{
            override fun onListClickInteraction(item: LinkedTreeMap<String, Any>, tag: String) {
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.container, IndicatorValueFragment.newInstance(item), IndicatorValueFragment::class.java.canonicalName)?.addToBackStack(VariableValuesListFragment::class.java.canonicalName)?.commit()
            }
        })
        listItemBinding.recyclerView.adapter = criteriaRecyclerViewAdapter
    }

    interface OnListItemListener {
        fun onListClickInteraction(items: FloatArray)
    }
    interface OnDefaultListItemListener {
        fun onListClickInteraction(item: LinkedTreeMap<String, Any>, tag: String)
    }

    companion object {
        var TAG = "CriteriaListFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param item Parameter 1.
         * @return A new instance of fragment CriteriaListFragment.
         */
        @JvmStatic
        fun newInstance(item: Item) =
            CriteriaListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, item)
                }
            }
    }
}
