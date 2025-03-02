package com.psutools.reminder.utils.validator

abstract class ValidationRule(protected val errorMessage: String) {

    abstract fun validate(value: String): ValidationUnit

    class ValidationUnit(val validation: Boolean, val errorMessage: String)
}
