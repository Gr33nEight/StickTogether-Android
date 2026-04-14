package com.example.sticktogether.Presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ViewModel.launchSafe(
    onError: (Exception) -> Unit = {},
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch {
        try {
            block()

        } catch (e: CancellationException) {
            throw e

        } catch (e: Exception) {
            onError(e)
        }
    }
}