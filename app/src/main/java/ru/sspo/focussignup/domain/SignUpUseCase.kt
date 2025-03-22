package ru.sspo.focussignup.domain

interface SignUpUseCase {
    fun validateUsername(username: String): String?

    fun validateEmail(email: String): String?

    fun validatePassword(password: String): String?

    fun validateConfirmPassword(password: String, confirmPassword: String): String?

    suspend fun isExistingUser(email: String): String?

    suspend fun registerUser(username: String, email: String, password: String): String
}