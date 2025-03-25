package ru.sspo.focussignup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sspo.focussignup.domain.SignUpResult
import ru.sspo.focussignup.ui.SignUpScreenState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpScreenState = MutableStateFlow<SignUpScreenState>(SignUpScreenState.Success)
    val signUpScreenState: StateFlow<SignUpScreenState> get() = _signUpScreenState

    fun handleSignUp(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            val signUpResult = signUpUseCase.signUpUser(username, email, password, confirmPassword)
            when (signUpResult) {
                is SignUpResult.Error -> {
                    _signUpScreenState.value = SignUpScreenState.Error(signUpResult.message)
                }
                SignUpResult.Success -> {
                    _signUpScreenState.value = SignUpScreenState.Success
                }
            }
        }
    }
}