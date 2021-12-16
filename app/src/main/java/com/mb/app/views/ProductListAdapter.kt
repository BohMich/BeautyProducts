package com.mb.app.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mb.app.R
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mb.app.constants.NetworkConstants.Companion.ALTERNATIVE_IMAGE
import com.mb.app.constants.StringConstants.Companion.DEFAULT_CURRNECY
import com.mb.app.models.Product
import com.mb.app.utils.StringFormatter
import kotlinx.android.synthetic.main.item_view_products.view.*

// Main adapter used for managing main feed list within the main Recycler View
class ProductListAdapter(
    val context: Context?,
    val clickListener: (Product) -> Unit
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private var items: List<Product> = ArrayList()

    fun setItems(items: List<Product>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view_products, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Prepare fetched data
        val id = items[position].id
        val name = items[position].name
        val price = items[position].price
        val image = items[position].image

        // Set the data within the view
        //(holder as? ItemViewHolder)?.productId?.text = id
        (holder as? ItemViewHolder)?.productName?.text = name
        (holder as? ItemViewHolder)?.productPrice?.text =
            StringFormatter.formatCurrency(DEFAULT_CURRNECY, price)

        // Load the picture
        // **************** Developer's Comment ****************
        // The image urls contained in the products.json do not point to valid images
        // The image url is still available, but for the purpose of this demo the url has been
        // replaced with a random .ico file found online.

        // If you wish to inspect the issue, please replace the constant in line 63 with "image" val (line 45)

        (holder as? ItemViewHolder)?.let {
            if (context != null) {
                Glide.with(context)
                    .load(ALTERNATIVE_IMAGE)
                    .into(it.productImage)
            }
        }

        // Set the onClickListener
        (holder as? ItemViewHolder)?.container?.setOnClickListener{
            val itemId = items[position].name
            clickListener(items[position])
        }
    }

    abstract class ViewHolder (view: View) : RecyclerView.ViewHolder(view)

    inner class ItemViewHolder (view: View) : ViewHolder(view) {
        val container = view.row_container
        //val productId = view.product_id_label
        val productName = view.product_name_label
        val productPrice = view.product_price_label
        val productImage = view.product_image
    }
}

