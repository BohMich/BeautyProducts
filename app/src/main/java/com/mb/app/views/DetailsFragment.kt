package com.mb.app.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mb.app.R
import com.mb.app.constants.NetworkConstants.Companion.ALTERNATIVE_IMAGE
import com.mb.app.constants.StringConstants
import com.mb.app.dependencyinjection.BeautyProductsApp
import com.mb.app.utils.StringFormatter
import kotlinx.android.synthetic.main.product_view.*

class DetailsFragment : Fragment(){

    init {
        BeautyProductsApp.mainComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.product_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        // Setup Cross Button
        val closingOnClickListener = View.OnClickListener{ activity?.onBackPressed() }

        // Setup closing on the grey fields' click
        spacing_top.setOnClickListener(closingOnClickListener)
        spacing_bottom.setOnClickListener(closingOnClickListener)

        setupContentLoadedView()
    }

    // UI setup methods

    private fun setupContentLoadedView() {

        imageView_picture.let {
            Glide.with(this)
                .load(ALTERNATIVE_IMAGE)
                .into(it)
        }
        textView_id.text = this.arguments?.getString("productId")
        textView_name.text = this.arguments?.getString("productName")

        val price: String? = this.arguments?.getString("productPrice")
        textView_price.text = StringFormatter.formatCurrency(StringConstants.DEFAULT_CURRNECY, price)
        textView_description.text = this.arguments?.getString("productDescription")
    }
}