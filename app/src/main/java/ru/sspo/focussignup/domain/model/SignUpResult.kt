package ru.sspo.focussignup.domain.model

sealed class SignUpResult {
    data object Success: SignUpResult()
    data class Error(val message: String): SignUpResult()
}