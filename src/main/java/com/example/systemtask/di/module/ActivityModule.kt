package com.example.systemtask.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.systemtask.di.scope.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: AppCompatActivity?) {

    @Provides
    @ActivityContext
    fun provideContext(): Context? {
        return activity
    }

    @Provides
    fun provideActivity(): AppCompatActivity? {
        return activity
    }
}