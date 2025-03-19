package ru.sspo.focussignup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _signUpResult = MutableLiveData<String>()
    val signUpResult: LiveData<String> get() = _signUpResult

    fun onSignUpButtonClicked(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            if (validateUsername(username)) return@launch
            if (validateEmail(email)) return@launch
            if (validatePassword(password)) return@launch
            if (validateConfirmPassword(password, confirmPassword)) return@launch

//            val existingUser = userDao.getUserByEmail(email)
//            if (existingUser != null) {
//                _signUpResult.value = "User already exists with this email."
//                return@launch
//            }

//            userDao.insert(User(username = username, email = email, password = password))
//            _signUpResult.value = "Registration Successful!"
        }

        // Если все проверки пройдены
//        _signUpResult.value = "Registration Successful!"
        // Здесь можно добавить логику для отправки данных на сервер или сохранения в базу данных
    }

    private fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        if (password != confirmPassword) {
            _signUpResult.value = "Passwords do not match"
            return true
        }
        return false
    }

    private fun validatePassword(password: String): Boolean {
        if (password.length !in 5..64) {
            _signUpResult.value = "Password must be between 5 and 64 characters"
            return true
        }
        return false
    }

    private fun validateEmail(email: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.length > 50) {
            _signUpResult.value = "Invalid email address (max 50 characters)"
            return true
        }
        return false
    }

    private fun validateUsername(username: String): Boolean {
        if (username.isBlank() || username.length !in 1..35) {
            _signUpResult.value = "Name must be between 1 and 35 characters"
            return true
        }
        return false
    }
}