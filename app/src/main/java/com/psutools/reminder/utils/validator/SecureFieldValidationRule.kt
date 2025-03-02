package com.psutools.reminder.utils.validator

import com.psutools.reminder.utils.hasLowerCaseLetter
import com.psutools.reminder.utils.hasSymbols
import com.psutools.reminder.utils.hasUpperCaseLetter

class SecureFieldValidationRule(
    private val minLength: Int,
    errorMessage: String = "Поле должно содержать как минимум $minLength символов, иметь одну заглавную букву, одну строчную букву и одну цифру"
) : ValidationRule(errorMessage) {

    override fun validate(value: String): ValidationUnit {
        return ValidationUnit(
            validation = value.length >= minLength &&
                    value.hasSymbols() &&
                    value.hasLowerCaseLetter() &&
                    value.hasUpperCaseLetter(),
            errorMessage = errorMessage
        )
    }
}
