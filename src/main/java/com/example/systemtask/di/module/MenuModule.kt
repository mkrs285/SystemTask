package com.example.systemtask.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.systemtask.di.scope.MenuScope
import com.example.systemtask.di.scope.SystemViewModelKey
import com.example.systemtask.fragment.MenuViewModel
import com.example.systemtask.viewModelFactory.SystemTaskViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MenuModule {

    @MenuScope
    @Binds
    @IntoMap
    @SystemViewModelKey(MenuViewModel::class)
    abstract fun bindLoginViewModels(loginViewModel: MenuViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: SystemTaskViewModelFactory): ViewModelProvider.Factory
}