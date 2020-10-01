package com.example.systemtask.di.module

import android.app.Application
import com.example.systemtask.BuildConfig
import com.example.systemtask.constants.NetworkConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private var mApplication: Application) {


    @Singleton
    @Provides
    fun provideIsDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    @Named("connectionTimeoutInSeconds")
    fun provideConnectionTimeOutInSeconds(): Int {
        return NetworkConstants.CONNECTION_TIME_OUT
    }

    @Singleton
    @Provides
    @Named("readTimeoutInSeconds")
    fun provideReadTimeOutInSeconds(): Int {
        return NetworkConstants.READ_TIME_OUT
    }

}