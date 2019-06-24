package com.venkatesh.dynamiclistdemo.items.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.gson.internal.LinkedTreeMap

import com.venkatesh.dynamiclistdemo.R

private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [IndicatorValueFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class IndicatorValueFragment : Fragment() {
    private var param1: LinkedTreeMap<String, Any>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as LinkedTreeMap<String, Any>?
        }
    }

    private lateinit var titleTextView: AppCompatTextView
    private lateinit var parameterTextView: AppCompatTextView
    private lateinit var parameterEditText: AppCompatEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_indicator_value, container, false)
        titleTextView = view.findViewById(R.id.title_text_view)
        parameterTextView = view.findViewById(R.id.parameter_label)
        parameterEditText = view.findViewById(R.id.parameter_edit_text)
        titleTextView.text = param1?.get(getString(R.string.study_type)).toString().toUpperCase()
        parameterTextView.text = param1?.get(getString(R.string.parameter_name)).toString().capitalize()
        parameterEditText.setText(param1?.get(getString(R.string.default_value)).toString())
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment IndicatorValueFragment.
         */
        @JvmStatic
        fun newInstance(param1: LinkedTreeMap<String, Any>) =
            IndicatorValueFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}
