package com.psutools.reminder.utils.validator

import com.psutools.reminder.utils.isNullOrEmptyOrBlank

class EmptyFieldValidationRule(errorMessage: String = "Поле не может быть пустым") : ValidationRule(errorMessage) {

    override fun validate(value: String): ValidationUnit {
        return ValidationUnit(
            validation = !value.isNullOrEmptyOrBlank(),
            errorMessage = errorMessage
        )
    }
}
