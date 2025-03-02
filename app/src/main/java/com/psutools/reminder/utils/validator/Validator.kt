package com.psutools.reminder.utils.validator


/**
 * A simple validator for input string
 *
 * Usage:
 *
 * val validator = buildValidator {
 *      addRule(EmailValidationRule())
 *      addRule(EmptyFieldValidationRule())
 * }
 *
 * val result = validator.validate(editText.text.toString())
 *
 * when(result){
 *      ValidationResult.Success -> showOk()
 *      ValidationResult.Error -> showErrors(result.messages)
 * }
 */
class Validator private constructor(private val rules: List<ValidationRule>) {

    fun validate(input: String): ValidationResult {
        return rules
            .map { rule -> rule.validate(input) }
            .filter { unit -> !unit.validation }
            .map { unit -> unit.errorMessage }
            .takeIf { errors -> errors.isEmpty() }
            ?.let { errors -> ValidationResult.Error(errors) }
            ?: ValidationResult.Success
    }

    class ValidatorBuilder {
        private val rules: MutableList<ValidationRule> = mutableListOf()

        fun addRule(validationRule: ValidationRule) = rules.add(validationRule)

        fun build(): Validator = Validator(rules)
    }
}

fun buildValidator(builder: Validator.ValidatorBuilder.() -> Unit): Validator {
    return Validator.ValidatorBuilder().apply(builder).build()
}
