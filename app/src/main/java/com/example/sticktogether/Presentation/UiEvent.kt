package com.example.sticktogether.Presentation

sealed interface UiEvent {
    data class ShowToast(val message: String) : UiEvent
    data object NavigateBack : UiEvent
}