package ru.sspo.focussignup.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sspo.focussignup.room.User
import ru.sspo.focussignup.room.UserDao
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val userDao: UserDao) : ViewModel() {

    private val _signUpResult = MutableLiveData<String>()
    val signUpResult: LiveData<String> get() = _signUpResult

    fun onSignUpButtonClicked(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {

            if (validateUsername(username) ||
                validateEmail(email) ||
                validatePassword(password) ||
                validateConfirmPassword(password, confirmPassword) ||
                ifExistingUser(email)
            ) return@launch
            signUpUser(username, email, password)
        }
    }

    private suspend fun signUpUser(
        username: String,
        email: String,
        password: String
    ) {
        userDao.insert(User(username = username, email = email, password = password))
        _signUpResult.postValue("Registration Successful!")
    }

    private suspend fun ifExistingUser(email: String): Boolean {
        val existingUser = userDao.getUserByEmail(email)
        if (existingUser != null) {
            _signUpResult.postValue("User already exists with this email.")
            return true
        }
        return false
    }

    private fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        if (password != confirmPassword) {
            _signUpResult.postValue("Passwords do not match")
            return true
        }
        return false
    }

    private fun validatePassword(password: String): Boolean {
        if (password.length !in 5..64) {
            _signUpResult.postValue("Password must be between 5 and 64 characters")
            return true
        }
        return false
    }

    private fun validateEmail(email: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.length > 50) {
            _signUpResult.postValue("Invalid email address (max 50 characters)")
            return true
        }
        return false
    }

    private fun validateUsername(username: String): Boolean {
        if (username.isBlank() || username.length !in 1..35) {
            _signUpResult.postValue("Name must be between 1 and 35 characters")
            return true
        }
        return false
    }
}