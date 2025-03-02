package com.psutools.reminder.utils.validator

import android.util.Patterns

class EmailValidationRule(errorMessage: String = "Некорректный формат электронной почты") : ValidationRule(errorMessage) {

    override fun validate(value: String): ValidationUnit {
        return ValidationUnit(
            validation = Patterns.EMAIL_ADDRESS.matcher(value).matches(),
            errorMessage = errorMessage
        )
    }
}
