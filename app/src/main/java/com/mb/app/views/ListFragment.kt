package com.mb.app.views

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mb.app.R
import com.mb.app.dependencyinjection.BeautyProductsApp
import com.mb.app.utils.DataFetchingCallback
import com.mb.app.viewmodels.ListFragmentViewModel
import androidx.lifecycle.ViewModelProviders
import com.mb.app.models.Product
import com.mb.app.models.ProductSet
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.loading_badge.*
import javax.inject.Inject

class ListFragment : Fragment(), DataFetchingCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ListFragmentViewModel

    private val STATE_LOADING_ERROR = "STATE_LOADING_ERROR"
    private val STATE_CONTENT_LOADED = "STATE_CONTENT_LOADED"

    init {
        BeautyProductsApp.mainComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListFragmentViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        // Initialize RecyclerView
        setupRecyclerView()

        // Fetch Products
        fetchProducts()
    }

    // UI setup methods

    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(context)
        product_list_recycler.layoutManager = layoutManager
        product_list_recycler.adapter = ProductListAdapter(context) {  selectedProduct: Product ->
            displayProductDetails(selectedProduct)
        }
    }

    // UI management methods

    private fun displayProductDetails(selectedProduct: Product) {

        val fragment = DetailsFragment()
        val bundle = Bundle()

        bundle.putString("productId", selectedProduct.id)
        bundle.putString("productName", selectedProduct.name)
        bundle.putString("productPrice", selectedProduct.price)
        bundle.putString("productDescription", selectedProduct.description)

        fragment.arguments = bundle

        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_content_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun loadItems(products: List<Product>) {

        (product_list_recycler.adapter as ProductListAdapter).setItems(products)
    }

    private fun displayErrorDialog(tryAgainAction: () -> Unit) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.loading_problem_check_the_internet_connection)
        builder.setPositiveButton(R.string.try_again) { _, _ ->
            tryAgainAction()
        }
        builder.create().show()
    }

    private fun setViewState(state: String) {

        when(state) {
            STATE_LOADING_ERROR -> setupLoadingErrorView()
            STATE_CONTENT_LOADED -> setupContentLoadedView()
        }
    }

    private fun setupLoadingErrorView() {

        displayErrorDialog {
            fetchProducts()
        }
    }

    private fun setupContentLoadedView() {

        loading_container.visibility = View.GONE
    }

    // Data fetching methods

    private fun fetchProducts() {
        viewModel.getProducts(this)
    }

    // Data Fetching Callback interface methods

    override fun fetchingDone(payload: Any) {

        if ((payload as? ProductSet) != null) {

            loadItems(payload.products)
            setViewState(STATE_CONTENT_LOADED)

        } else if ((payload as? List<Product>) != null) {

            loadItems(payload)
            setViewState(STATE_CONTENT_LOADED)

        } else{

            setViewState(STATE_LOADING_ERROR)

        }
    }

    override fun fetchingError() {

        setViewState(STATE_LOADING_ERROR)
    }
}