package ru.sspo.focussignup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sspo.focussignup.domain.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _signUpResult = MutableLiveData<String>()
    val signUpResult: LiveData<String> get() = _signUpResult

    fun onSignUpButtonClicked(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {

            signUpUseCase.validateUsername(username)?.let {
                _signUpResult.postValue(it)
                return@launch
            }
            signUpUseCase.validateEmail(email)?.let {
                _signUpResult.postValue(it)
                return@launch
            }
            signUpUseCase.validatePassword(password)?.let {
                _signUpResult.postValue(it)
                return@launch
            }
            signUpUseCase.validateConfirmPassword(password, confirmPassword)?.let {
                _signUpResult.postValue(it)
                return@launch
            }

            signUpUseCase.isExistingUser(email)?.let {
                _signUpResult.postValue(it)
                return@launch
            }

            _signUpResult.postValue(signUpUseCase.registerUser(username, email, password))
        }
    }
}