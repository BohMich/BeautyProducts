package com.mb.app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mb.app.R
import com.mb.app.dependencyinjection.BeautyProductsApp
import com.mb.app.viewmodels.ListFragmentViewModel
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ListFragmentViewModel

    init {
        BeautyProductsApp.mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListFragmentViewModel::class.java)

        // Show List Fragment
        displayListFragment()
    }

    private fun displayListFragment() {
        val fragment = ListFragment()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_content_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}