package com.example.sticktogether.Features.auth.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _showCustomSplash = MutableStateFlow(true)

    val showCustomSplash = _showCustomSplash.asStateFlow()

    init {
        viewModelScope.launch {
            delay(300)
            _isLoading.value = false

            delay(2500)
            _showCustomSplash.value = false
        }
    }
}