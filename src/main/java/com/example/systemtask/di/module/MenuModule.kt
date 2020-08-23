package com.example.systemtask.di.module

import com.example.systemtask.di.scope.MenuScope
import com.example.systemtask.fragment.MenuFragment
import com.example.systemtask.fragment.secondFragment.CartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MenuModule {

    @MenuScope
    @ContributesAndroidInjector
    abstract fun contributeFactsFragment(): MenuFragment

    @MenuScope
    @ContributesAndroidInjector
    abstract fun contributeCartFragment(): CartFragment
}