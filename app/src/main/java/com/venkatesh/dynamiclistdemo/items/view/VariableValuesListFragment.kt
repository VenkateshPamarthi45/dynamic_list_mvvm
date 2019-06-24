package com.venkatesh.dynamiclistdemo.items.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.venkatesh.dynamiclistdemo.R
import com.venkatesh.dynamiclistdemo.databinding.ErrorCaseBinding
import com.venkatesh.dynamiclistdemo.databinding.FragmentItemListBinding
import com.venkatesh.dynamiclistdemo.items.adapter.VariableValuesRecyclerViewAdapter

private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [VariableValuesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class VariableValuesListFragment : Fragment() {
    private var items: FloatArray? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            items = it.getFloatArray(ARG_PARAM1)
        }
    }

    private lateinit var listItemBinding: FragmentItemListBinding
    private lateinit var errorCaseBinding: ErrorCaseBinding
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var variableValuesRecyclerViewAdapter : VariableValuesRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        listItemBinding = FragmentItemListBinding.inflate(layoutInflater, container, false)
        errorCaseBinding = ErrorCaseBinding.inflate(layoutInflater, container, false)
        gridLayoutManager = GridLayoutManager(view.context, resources.getInteger(R.integer.items_columns), LinearLayoutManager.VERTICAL, false)
        listItemBinding.recyclerView.layoutManager = gridLayoutManager
        renderItems()
        return listItemBinding.root
    }
    private fun renderItems() {
        items?.let {
            variableValuesRecyclerViewAdapter = VariableValuesRecyclerViewAdapter(it, null)
            listItemBinding.recyclerView.adapter =
                variableValuesRecyclerViewAdapter
        }
    }

    companion object {
        var TAG = "VariableValuesListFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param item Parameter 1.
         * @return A new instance of fragment VariableValuesListFragment.
         */
        @JvmStatic
        fun newInstance(item: FloatArray) =
            VariableValuesListFragment().apply {
                arguments = Bundle().apply {
                    putFloatArray(ARG_PARAM1,item)
                }
            }
    }
}
