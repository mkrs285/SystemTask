package com.example.systemtask.di.component

import android.content.Context
import com.example.systemtask.di.module.ApiModule
import com.example.systemtask.di.module.AppModule
import com.example.systemtask.di.module.HttpClientModule
import com.example.systemtask.di.scope.ApplicationContext
import com.example.systemtask.network.ApiHelper
import dagger.Component
import javax.inject.Singleton

/**
 *  @Component Definition of the Application graph
 * @Singleton Single Instance
 **/
@Singleton
@Component(modules = [AppModule::class, HttpClientModule::class, ApiModule::class])
interface ApplicationComponent {


    //A Builder here is basically a more versatile (and less verbose!) builder.
    //compile time safety
    //For testing

    fun api(): ApiHelper
}