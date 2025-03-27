package ru.sspo.focussignup.domain.usecase

import ru.sspo.focussignup.domain.StringProvider
import ru.sspo.focussignup.domain.model.SignUpResult
import ru.sspo.focussignup.ui.viewmodel.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val signUpValidator: SignUpValidator,
    private val stringProvider: StringProvider
) : SignUpUseCase {

    override suspend fun signUpUser(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): SignUpResult {
        with(signUpValidator) {
            validateUsername(username)?.let { return SignUpResult.Error(it) }
            validateEmail(email)?.let { return SignUpResult.Error(it) }
            validatePassword(password)?.let { return SignUpResult.Error(it) }
            validateConfirmPassword(
                password,
                confirmPassword
            )?.let { return SignUpResult.Error(it) }
        }
        checkIfUserExists(email)?.let { return SignUpResult.Error(it) }

        registerUser(username, email, password)
        return SignUpResult.Success
    }

    private suspend fun checkIfUserExists(email: String): String? =
        userRepository.getUserByEmail(email)?.let { stringProvider.getUserExistsError() }

    private suspend fun registerUser(username: String, email: String, password: String) {
        userRepository.insertUser(username, email, password)
    }
}