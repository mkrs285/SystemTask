package com.example.systemtask.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.systemtask.viewModelFactory.SystemTaskViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: SystemTaskViewModelFactory): ViewModelProvider.Factory


}

