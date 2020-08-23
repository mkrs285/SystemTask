package com.example.systemtask.di.scope

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@MapKey
internal annotation class WiproViewModelKey(
    val value: KClass<out ViewModel>
)