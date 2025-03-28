package ru.sspo.focussignup.ui.viewmodel

import ru.sspo.focussignup.domain.model.SignUpResult

interface SignUpUseCase {
    suspend fun signUpUser(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): SignUpResult
}