package com.psutools.reminder.utils.validator

sealed class ValidationResult {

    data object Success : ValidationResult()

    data class Error(val messages: List<String>) : ValidationResult()
}
