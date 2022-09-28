package com.example.fragmentapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.list_item.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var product: Product? = null

        arguments?.let {
            val args = DetailFragmentArgs.fromBundle(it)
            product = products.find { args.id == it.id }
        }

        product?.let {
            with(it) {
                product_name.text = name
                product_image.setImageResource(imageId)
                product_shortDes.text = shortDescription
                product_longDes.text = longDescription
                product_price.text = "Price: Rs. $price "

                buy_button.setOnClickListener {
                    findNavController().navigate(DetailFragmentDirections.actionDetailToCheckoutFragment(this.id))
                }
            }
        }

    }
}