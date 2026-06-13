package com.example.sticktogether.Features.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sticktogether.Resources.Enums.PasswordError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.sticktogether.Domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


data class LoginUIState(
    val emailorusername: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoginSuccessful: Boolean = false,
    val passwordError: PasswordError = PasswordError.NONE,
    val showErrors: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUIState())

    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun onEmailChange(newValue: String) {
        _uiState.value = _uiState.value.copy(emailorusername = newValue, error = null)
    }

    fun onPasswordChange(newValue: String) {

        val validationError = validatePassword(newValue)

        _uiState.value = _uiState.value.copy(
            password = newValue,
            passwordError = validationError,
            showErrors = false,
        )
    }

    private fun validatePassword(password: String): PasswordError {
        return when {
            password.isEmpty() -> PasswordError.EMPTY
            password.length < 6 -> PasswordError.TOO_SHORT
            !password.any { it.isUpperCase() } -> PasswordError.MISSING_UPPERCASE
            else -> PasswordError.NONE
        }
    }

    fun LoginSignIn() {

        val validationError = validatePassword(_uiState.value.password)

        if (validationError != PasswordError.NONE) {
            _uiState.value = _uiState.value.copy(
                passwordError = validationError,
                showErrors = true
            )

            return
        }

        val currentState = _uiState.value

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            val result = authRepository.loginWithEmail(
                email = _uiState.value.emailorusername,
                password = _uiState.value.password
            )

            result.onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false, isLoginSuccessful = true)
            }.onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = exception.localizedMessage ?: "Błąd logowania"
                )
            }
        }
    }
}