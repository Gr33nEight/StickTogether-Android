package com.example.sticktogether.Features

sealed interface UiEvent {
    data class ShowToast(val message: String) : UiEvent
    data object NavigateBack : UiEvent
}