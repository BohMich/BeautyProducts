package com.mb.app.dependencyinjection

import com.mb.app.data.offline.AppDatabase
import com.mb.app.data.offline.ProductDao
import com.mb.app.utils.CacheReader
import com.mb.app.viewmodels.ListFragmentViewModel
import com.mb.app.views.MainActivity
import com.mb.app.views.DetailsFragment
import com.mb.app.views.ListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, MainModule::class, RoomModule::class, ViewModelModule::class))
interface MainComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(listFragment: ListFragment)
    fun inject(listViewModel: ListFragmentViewModel)
    fun inject(productDetailsFragment: DetailsFragment)
    fun inject(cacheReader: CacheReader)
    fun inject(appDatabase: AppDatabase)
    fun inject(productDao: ProductDao)

}