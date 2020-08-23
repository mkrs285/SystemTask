package com.example.systemtask.di.scope

import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@MapKey
internal annotation class FragmentKey(val clazz: KClass<out Fragment>)