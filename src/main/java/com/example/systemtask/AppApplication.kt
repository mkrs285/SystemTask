package com.example.systemtask

import android.app.Application
import com.example.systemtask.di.component.ApplicationComponent
import com.example.systemtask.di.component.DaggerApplicationComponent
import com.example.systemtask.di.module.AppModule


// appComponent lives in the Application class to share its lifecycle
class AppApplication : Application() {

    // Reference to the application graph that is used across the whole app
    private lateinit var mApplicationComponent: ApplicationComponent


    fun getComponent(): ApplicationComponent {
        return mApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        mApplicationComponent =
                DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
    }


}