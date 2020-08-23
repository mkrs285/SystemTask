package com.example.systemtask.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.systemtask.di.scope.WiproViewModelKey
import com.example.systemtask.fragment.MenuViewModel
import com.example.systemtask.viewModelFactory.SystemTaskViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @WiproViewModelKey(MenuViewModel::class)
    internal abstract fun bindFactsViewModels(factsViewModel: MenuViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: SystemTaskViewModelFactory): ViewModelProvider.Factory




}

