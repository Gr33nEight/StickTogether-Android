package com.example.sticktogether.Features.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sticktogether.Domain.AuthRepository
import com.example.sticktogether.Resources.Enums.PasswordError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class RegisterUIState(
    val emailorusername: String = "",
    val password: String = "",
    val nickname: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRegisterSuccessful: Boolean = false,
    val passwordError: PasswordError = PasswordError.NONE,
    val confirmPasswordError: PasswordError = PasswordError.NONE,
    val showErrors: Boolean = false
)
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())

    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    fun onEmailChange(newValue: String) {
        _uiState.value = _uiState.value.copy(emailorusername = newValue, error = null)
    }

    fun onNicknameChange(newValue: String) {
        _uiState.value = _uiState.value.copy(nickname = newValue, error = null)
    }

    fun onPasswordChange(newValue: String) {

        val validationError = validatePassword(newValue)

        _uiState.value = _uiState.value.copy(
            password = newValue,
            passwordError = validationError,
            confirmPasswordError = validateConfirmPassword(newValue, _uiState.value.confirmPassword),
            showErrors = false,
        )
    }

    fun onConfirmPasswordChange(newValue: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = newValue,
            confirmPasswordError = validateConfirmPassword(_uiState.value.password, newValue),
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

    private fun validateConfirmPassword(password: String, confirm: String,): PasswordError {
        return when {
            confirm.isEmpty() -> PasswordError.EMPTY
            password != confirm -> PasswordError.PASSWORDS_DO_NOT_MATCH
            else -> PasswordError.NONE
        }
    }

    fun RegisterSignIn() {

        val validationError = validatePassword(_uiState.value.password)

        val validationConfirmError = validateConfirmPassword(_uiState.value.password, _uiState.value.confirmPassword)


        if (validationError != PasswordError.NONE || validationConfirmError != PasswordError.NONE) {
            _uiState.value = _uiState.value.copy(
                passwordError = validationError,
                confirmPasswordError = validationConfirmError,
                showErrors = true,
            )
            return
        }

        val currentState = _uiState.value

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            val result = authRepository.registerWithEmail(
                email = _uiState.value.emailorusername,
                password = _uiState.value.password
            )

            result.onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false, isRegisterSuccessful = true)
            }.onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = exception.localizedMessage ?: "Błąd rejestracji"
                )
            }
        }
    }
}