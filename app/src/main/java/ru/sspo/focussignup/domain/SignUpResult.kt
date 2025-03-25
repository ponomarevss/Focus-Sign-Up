package ru.sspo.focussignup.domain

sealed class SignUpResult {
    data object Success: SignUpResult()
    data class Error(val message: String): SignUpResult()
}