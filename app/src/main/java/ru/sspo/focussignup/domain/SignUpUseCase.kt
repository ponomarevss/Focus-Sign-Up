package ru.sspo.focussignup.domain

import android.util.Patterns

class SignUpUseCase {
    fun validateUsername(username: String): Boolean {
        return username.isNotBlank() && username.length in 1..35
    }

    fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length <= 50
    }

    fun validatePassword(password: String): Boolean {
        return password.length in 5..64
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}