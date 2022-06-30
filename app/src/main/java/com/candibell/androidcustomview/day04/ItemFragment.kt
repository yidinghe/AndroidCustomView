package com.candibell.androidcustomview.day04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.candibell.androidcustomview.R

class ItemFragment : Fragment() {

    companion object {
        fun newInstance(title: String?):ItemFragment {
            val fragment = ItemFragment()
            val bundle = Bundle()
            title?.let {
                bundle.putString("title", it)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, null)
        val text = arguments?.getString("title") ?: ""
        view.findViewById<TextView>(R.id.text_view).setText(text)
        return view
    }
}