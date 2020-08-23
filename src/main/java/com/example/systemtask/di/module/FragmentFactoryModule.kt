package com.example.systemtask.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.systemtask.di.scope.FragmentKey
import com.example.systemtask.fragment.MenuFragment
import com.example.systemtask.utils.MyFragmentFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class FragmentFactoryModule {

    @Binds
    internal abstract fun bindFragmentFactroy(factory: MyFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(MenuFragment::class)
    internal abstract fun bindMainFragment(fragment: MenuFragment): Fragment
}