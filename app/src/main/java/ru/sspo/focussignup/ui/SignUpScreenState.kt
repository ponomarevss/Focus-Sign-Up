package ru.sspo.focussignup.ui

sealed class SignUpScreenState {
    data object Success: SignUpScreenState()
    data class Error(val error: String): SignUpScreenState()
}