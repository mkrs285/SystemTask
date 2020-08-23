package com.example.systemtask

import com.example.systemtask.di.component.ApplicationComponent
import com.example.systemtask.di.component.DaggerApplicationComponent
import dagger.android.DaggerApplication

// appComponent lives in the Application class to share its lifecycle
class AppApplication : DaggerApplication() {

    // Reference to the application graph that is used across the whole app
    private val appComponent = DaggerApplicationComponent.builder().create(this).build()


    fun getComponent(): ApplicationComponent {
        return appComponent
    }

    override fun applicationInjector() = appComponent
}