package com.psutools.reminder.base.arch

sealed class ScreenState<out T> {
    data class Content<out T>(val data: T) : ScreenState<T>()
    data object Loading : ScreenState<Nothing>()  // Nothing can still be used here to indicate no data
}
