package com.example.sticktogether.Domain

sealed class AppError {
    data class Unknown(val exception: Throwable? = null) : AppError()
    data class Network(val exception: Throwable? = null) : AppError()
    data class Firestore(val exception: Throwable? = null) : AppError()
}