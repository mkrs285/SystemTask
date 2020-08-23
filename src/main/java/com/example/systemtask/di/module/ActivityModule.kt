package com.example.systemtask.di.module

import com.example.systemtask.activity.MainActivity
import com.example.systemtask.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    //Alive until activity lives
    @ActivityScope
    @ContributesAndroidInjector(modules = [MenuModule::class,FragmentFactoryModule::class])
    internal abstract fun contributeMainActivity(): MainActivity


}