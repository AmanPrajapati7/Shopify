package com.example.fragmentapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class ProductAdapter(val listener: (Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var productData = arrayOf<Product>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        val productName = containerView.findViewById<TextView>(R.id.product_name)
        val productPrice = containerView.findViewById<TextView>(R.id.product_price)
        val productImage = containerView.findViewById<ImageView>(R.id.product_image)
        val shortDes = containerView.findViewById<TextView>(R.id.product_shortDes)

        init {
            itemView.setOnClickListener {
                listener.invoke(productData[adapterPosition])
            }
        }

    }

    override fun getItemCount() = productData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val itemLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ProductViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        with(holder) {
            productName.text = products[position].name
            productPrice.text = "Price: ${productData[position].price}"
            productImage.setImageResource(products[position].imageId)
            shortDes.text = products[position].shortDescription

        }

    }

    override fun getItemId(position: Int) = productData[position].id.hashCode().toLong()

}