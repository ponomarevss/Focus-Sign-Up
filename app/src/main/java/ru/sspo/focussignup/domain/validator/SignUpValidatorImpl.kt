package ru.sspo.focussignup.domain.validator

import android.util.Patterns
import ru.sspo.focussignup.domain.StringProvider
import ru.sspo.focussignup.domain.usecase.SignUpValidator
import javax.inject.Inject

class SignUpValidatorImpl @Inject constructor(
    private val stringProvider: StringProvider
) : SignUpValidator {

    override fun validateUsername(username: String): String? = when {
        username.isBlank() -> stringProvider.getUsernameEmptyError()
        username.length !in 1..35 -> stringProvider.getUsernameLengthError()
        else -> null
    }

    override fun validateEmail(email: String): String? = when {
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> stringProvider.getEmailFormatError()
        email.length > 50 -> stringProvider.getEmailLengthError()
        else -> null
    }

    override fun validatePassword(password: String): String? = when {
        password.length !in 5..64 -> stringProvider.getPasswordLengthError()
        else -> null
    }

    override fun validateConfirmPassword(password: String, confirmPassword: String): String? =
        if (password != confirmPassword) {
            stringProvider.getPasswordMismatchError()
        } else {
            null
        }
}