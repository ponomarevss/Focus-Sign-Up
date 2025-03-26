package ru.sspo.focussignup.domain.usecase

interface StringProvider {
    fun getUsernameEmptyError(): String
    fun getUsernameLengthError(): String
    fun getEmailFormatError(): String
    fun getEmailLengthError(): String
    fun getPasswordLengthError(): String
    fun getPasswordMismatchError(): String
    fun getUserExistsError(): String
}