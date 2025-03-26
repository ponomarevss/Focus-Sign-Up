package ru.sspo.focussignup.domain.usecase

import android.util.Patterns
import ru.sspo.focussignup.domain.model.SignUpResult
import ru.sspo.focussignup.ui.viewmodel.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val stringProvider: StringProvider
) : SignUpUseCase {

//    companion object {
//        const val USERNAME_EMPTY_ERROR = "Username cannot be empty"
//        const val USERNAME_LENGTH_ERROR = "Username must be between 1 and 35 characters"
//        const val EMAIL_FORMAT_ERROR = "Invalid email format"
//        const val EMAIL_LENGTH_ERROR = "Email must be 50 characters or less"
//        const val PASSWORD_LENGTH_ERROR = "Password must be between 5 and 64 characters"
//        const val PASSWORD_MISMATCH_ERROR = "Passwords do not match"
//        const val USER_EXISTS_ERROR = "User already exists"
//    }

    override suspend fun signUpUser(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): SignUpResult {
        validateUsername(username)?.let { return SignUpResult.Error(it) }
        validateEmail(email)?.let { return SignUpResult.Error(it) }
        validatePassword(password)?.let { return SignUpResult.Error(it) }
        validateConfirmPassword(password, confirmPassword)?.let { return SignUpResult.Error(it) }
        checkIfUserExists(email)?.let { return SignUpResult.Error(it) }

        registerUser(username, email, password)
        return SignUpResult.Success
    }

    private fun validateUsername(username: String): String? = when {
        username.isBlank() -> stringProvider.getUsernameEmptyError()
        username.length !in 1..35 -> stringProvider.getUsernameLengthError()
        else -> null
    }

    private fun validateEmail(email: String): String? = when {
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> stringProvider.getEmailFormatError()
        email.length > 50 -> stringProvider.getEmailLengthError()
        else -> null
    }

    private fun validatePassword(password: String): String? = when {
        password.length !in 5..64 -> stringProvider.getPasswordLengthError()
        else -> null
    }

    private fun validateConfirmPassword(password: String, confirmPassword: String): String? =
        if (password != confirmPassword) {
            stringProvider.getPasswordMismatchError()
        } else {
            null
        }

    private suspend fun checkIfUserExists(email: String): String? =
        userRepository.getUserByEmail(email)?.let { stringProvider.getUserExistsError() }

    private suspend fun registerUser(username: String, email: String, password: String) {
        userRepository.insertUser(username, email, password)
    }
}