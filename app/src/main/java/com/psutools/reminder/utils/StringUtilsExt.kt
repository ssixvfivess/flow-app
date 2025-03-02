package com.psutools.reminder.utils

fun String?.isNullOrEmptyOrBlank(): Boolean {
    return this.isNullOrEmpty() || this.isBlank()
}

fun String.hasSymbols(): Boolean {
    return this.find { !it.isLetterOrDigit() } != null
}

fun String.hasUpperCaseLetter(): Boolean {
    return this.find { it.isUpperCase() } != null
}

fun String.hasLowerCaseLetter(): Boolean {
    return this.find { it.isLowerCase() } != null
}

fun String.hasDigit(): Boolean {
    return this.find { it.isDigit() } != null
}
