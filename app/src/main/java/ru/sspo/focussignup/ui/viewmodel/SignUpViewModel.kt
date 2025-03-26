package ru.sspo.focussignup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.sspo.focussignup.domain.model.SignUpResult
import ru.sspo.focussignup.ui.SignUpScreenState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpScreenState = MutableSharedFlow<SignUpScreenState>(replay = 1)
    val signUpScreenState: SharedFlow<SignUpScreenState> get() = _signUpScreenState

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
                    updateState(SignUpScreenState.Error(signUpResult.message))
                }
                SignUpResult.Success -> {
                    updateState(SignUpScreenState.Success)
                }
            }
        }
    }

    private fun updateState(newState: SignUpScreenState) {
        _signUpScreenState.tryEmit(newState)
    }
}