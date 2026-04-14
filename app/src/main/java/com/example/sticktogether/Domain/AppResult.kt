package com.example.sticktogether.Domain

import com.example.sticktogether.Domain.AppError

sealed class AppResult<out T> {
    data class Success<out T>(val data: T) : AppResult<T>()
    data class Error(val error: AppError) : AppResult<Nothing>()
}