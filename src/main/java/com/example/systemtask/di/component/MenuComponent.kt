package com.example.systemtask.di.component

import com.example.systemtask.di.module.MenuModule
import com.example.systemtask.di.scope.MenuScope
import com.example.systemtask.fragment.MenuFragment
import com.example.systemtask.fragment.secondFragment.CartFragment
import dagger.Component

@MenuScope
@Component(dependencies = [ApplicationComponent::class], modules = [MenuModule::class])
interface MenuComponent {

    fun inject(fragment: MenuFragment)

    fun inject(fragment: CartFragment)


}