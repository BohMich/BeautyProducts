package com.mb.app.dependencyinjection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mb.app.viewmodels.ListFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    internal abstract fun bindListFragmentViewModel(ListFragmentViewModel: ListFragmentViewModel)
            : ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory)
              : ViewModelProvider.Factory

}