package com.psutools.reminder.base.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {

    fun main(): CoroutineDispatcher

    fun mainImmediate(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun default(): CoroutineDispatcher

    fun unconfined(): CoroutineDispatcher
}
