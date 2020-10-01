package com.example.systemtask.di.component


import com.example.systemtask.di.module.ActivityModule
import com.example.systemtask.di.module.ViewModelModule
import com.example.systemtask.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(
        dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class, ViewModelModule::class]
)
interface ActivityComponent {

}