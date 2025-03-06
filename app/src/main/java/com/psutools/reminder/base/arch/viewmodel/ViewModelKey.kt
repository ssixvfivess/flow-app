package com.psutools.reminder.base.arch.viewmodel
//Ключ — это класс ViewModel (например, UserViewModel::class).
//Значение — это Provider<ViewModel>, который может создать экземпляр ViewModel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)