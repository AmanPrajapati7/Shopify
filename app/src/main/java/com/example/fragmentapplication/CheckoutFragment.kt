package com.example.fragmentapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.BoringLayout.make
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_checkout.product_image
import kotlinx.android.synthetic.main.fragment_checkout.product_name
import kotlinx.android.synthetic.main.list_item.*
import org.json.JSONObject
import java.lang.Exception

class CheckoutFragment : Fragment() {

    private var quantity = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_checkout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Checkout.preload(context)

        var product: Product? = null

        arguments?.let {
            val args = CheckoutFragmentArgs.fromBundle(it)
            product = products.find { args.id == it.id }
        }

        product?.let {
            with(it) {
                product_image.setImageResource(imageId)
                product_name.text = name
                total_price.text = "Order total: Rs. ${price}"
                checkout_price.text = "Price: Rs. ${price}"
                product_quantity.text = "Quantity: $quantity"

                sub_button.setOnClickListener {
                    if (quantity > 1) {
                        quantity--
                        product_quantity.text = "Quantity: $quantity"
                        total_price.text = "Order total: Rs. ${price * quantity}"
                    }
                }

                add_button.setOnClickListener {
                    quantity++
                    if (quantity > 10) {
                        quantity = 10
                        Toast.makeText(
                            requireActivity(),
                            "Cannot place more than 10 items in 1 order",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    product_quantity.text = "Quantity: $quantity"
                    total_price.text = "Order total: Rs. ${price * quantity}"
                }

                pay_button.setOnClickListener {
                    startPayment(this)
                }
            }
        }
    }

    private fun startPayment(product: Product) {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_o57X5qeswStRoM")
        checkout.setImage(R.drawable.rzp_logo)

        try {
            val value = product.price * quantity
            val options = JSONObject()
            options.put("currency", "INR")
            options.put("amount", value*100)
            options.put("description", "${product.name}")

            checkout.open(requireActivity(), options)
            productId = product.id

        } catch (e: Exception) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e)
        }
    }
}