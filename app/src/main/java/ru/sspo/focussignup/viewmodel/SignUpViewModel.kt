package ru.sspo.focussignup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sspo.focussignup.domain.SignUpUseCase
import ru.sspo.focussignup.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _signUpResult = MutableLiveData<String>()
    val signUpResult: LiveData<String> get() = _signUpResult

    fun onSignUpButtonClicked(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {

            if (!signUpUseCase.validateUsername(username)) {
                _signUpResult.postValue("Invalid username")
                return@launch
            }
            if (!signUpUseCase.validateEmail(email)) {
                _signUpResult.postValue("Invalid email")
                return@launch
            }
            if (!signUpUseCase.validatePassword(password)) {
                _signUpResult.postValue("Invalid password")
                return@launch
            }
            if (!signUpUseCase.validateConfirmPassword(password, confirmPassword)) {
                _signUpResult.postValue("Passwords do not match")
                return@launch
            }

            val existingUser = userRepository.getUserByEmail(email)
            if (existingUser != null) {
                _signUpResult.postValue("User already exists")
                return@launch
            }

            userRepository.insertUser(username, email, password)
            _signUpResult.postValue("Registration successful!")
        }
    }
}