package com.example.sticktogether.Features.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sticktogether.Resources.Enums.PasswordError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class RegisterUIState(
    val emailorusername: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRegisterSuccessful: Boolean = false,
    val passwordError: PasswordError = PasswordError.NONE,
    val confirmPasswordError: PasswordError = PasswordError.NONE,
    val showErrors: Boolean = false
)
class RegisterViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())

    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    fun onEmailChange(newValue: String) {
        _uiState.value = _uiState.value.copy(emailorusername = newValue, error = null)
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

            _uiState.value = currentState.copy(isLoading = true, error = null)

            delay(1500)

            _uiState.value = _uiState.value.copy(isLoading = false, isRegisterSuccessful = true)
        }
    }
}