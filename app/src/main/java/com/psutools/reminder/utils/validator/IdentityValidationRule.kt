package com.psutools.reminder.utils.validator

class IdentityValidationRule(
    private val identityString: String,
    private val identityMode: IdentityMode = IdentityMode.MATCH,
    errorMessage: String = ""
) : ValidationRule(errorMessage) {

    override fun validate(value: String): ValidationUnit {
        return ValidationUnit(
            validation = when (identityMode) {
                IdentityMode.MATCH -> value == identityString
                IdentityMode.NOT_MATCH -> value != identityString
            },
            errorMessage = makeErrorMessage()
        )
    }

    private fun makeErrorMessage(): String {
        return when {
            errorMessage.isNotEmpty() -> errorMessage
            identityMode == IdentityMode.MATCH -> "Пароли должны совпадать"
            identityMode == IdentityMode.NOT_MATCH -> "Пароли не должны совпадать"
            else -> throw IllegalStateException("Error message is empty")
        }
    }
}

enum class IdentityMode {
    MATCH,
    NOT_MATCH
}
