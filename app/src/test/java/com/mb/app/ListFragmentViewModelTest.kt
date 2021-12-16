package com.mb.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mb.app.data.network.ApiClient
import com.mb.app.models.Product
import com.mb.app.models.ProductSet
import com.mb.app.utils.CacheReader
import com.mb.app.utils.DataFetchingCallback
import com.mb.app.viewmodels.ListFragmentViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.mock.Calls


class ListFragmentViewModelTest {

    private var viewModel: ListFragmentViewModel? = null
    private var testProductSet: ProductSet? = null

    @Mock
    private val apiClient: ApiClient? = null

    @Mock
    private val cacheReader: CacheReader? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Inject Mocks
        MockitoAnnotations.openMocks(this)

        // Initialise the List Fragment ViewModel
        viewModel = ListFragmentViewModel(apiClient!!, cacheReader!!)

        // Setup test product set
        val testSet: List<Product> = listOf(
            Product("Product ID 1", "Beauty Product 1", "N/A", "This is a test description 1", "10"),
            Product("Product ID 2", "Beauty Product 2", "N/A", "This is a test description 2", "20"),
            Product("Product ID 3", "Beauty Product 3", "N/A", "This is a test description 3", "30")
        )

        testProductSet = ProductSet(testSet)
    }

    @Test
    fun test_api_callback() {

        // API response
        val getResponseObject = Calls.response(testProductSet)

        // Testing conditions
        Mockito.`when`(apiClient?.getAllProducts()).thenReturn(getResponseObject)

        // Fake callback
        val fakeCallback: DataFetchingCallback = Mockito.mock(DataFetchingCallback::class.java)

        // Run getProducts method
        viewModel!!.getProducts(fakeCallback)

        // Verify callback method
        Mockito.verify(fakeCallback).fetchingDone(testProductSet!!)

    }
}