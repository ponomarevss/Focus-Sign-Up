package ru.sspo.focussignup.domain.usecase

interface SignUpValidator {
    fun validateUsername(username: String): String?
    fun validateEmail(email: String): String?
    fun validatePassword(password: String): String?
    fun validateConfirmPassword(password: String, confirmPassword: String): String?
}