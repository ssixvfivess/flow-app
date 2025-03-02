package com.psutools.reminder.utils.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.tryLaunch(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    doOnLaunch: suspend () -> Unit,
    doOnError: (Throwable) -> Unit = {},
    doOnComplete: () -> Unit = {},
): Job {
    val job = try {
        coroutineContext.job
    } catch (e: IllegalStateException) {
        Job()
    }
    val wrapperJob = SupervisorJob(job)
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, error -> doOnError(error) }

    return launch(coroutineContext + wrapperJob + coroutineExceptionHandler) {
        doOnLaunch()
    }.apply {
        invokeOnCompletion {
            wrapperJob.complete()
            doOnComplete()
        }
    }
}

inline fun <T> runSuspendCatching(block: () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
