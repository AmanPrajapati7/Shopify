package com.example.fragmentapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        product_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductAdapter {
                findNavController().navigate(ListFragmentDirections.actionHomeToDetail(it.id))
            }.apply {
                setHasStableIds(true)
            }
            setHasFixedSize(true)
        }

        (product_list.adapter as ProductAdapter).productData = products
    }

}