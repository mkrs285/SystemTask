package com.example.systemtask.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.systemtask.AppApplication
import com.example.systemtask.di.component.ActivityComponent
import com.example.systemtask.di.component.DaggerActivityComponent
import com.example.systemtask.di.component.DaggerMenuComponent
import com.example.systemtask.di.component.MenuComponent
import com.example.systemtask.di.module.ActivityModule
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    private lateinit var mActivityComponent: ActivityComponent
    private lateinit var menuComponent: MenuComponent

    //creating instance
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as AppApplication).getComponent())
                .build()
    }

    //ktx co-routine to accessing viewModels
    protected inline fun <reified VM : ViewModel>
            injectViewModels(): Lazy<VM> = viewModels { viewModelFactory }


    /**
     * For Initializing Caf Component
     */
    fun createMenuComponent() {
        menuComponent = DaggerMenuComponent.builder()
                .applicationComponent((application as AppApplication).getComponent())
                .build()
    }

    fun getMenuComponent() = menuComponent


}