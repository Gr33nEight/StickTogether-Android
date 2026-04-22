package com.example.sticktogether.Resources.Enums

import androidx.compose.runtime.Composable

enum class PasswordError {
    NONE,
    TOO_SHORT,
    MISSING_UPPERCASE,
    EMPTY,
    PASSWORDS_DO_NOT_MATCH,
}

@Composable
fun PasswordError.asString(): String {
    return when (this) {
        PasswordError.TOO_SHORT -> "Password must contain at least 6 characters!"
        PasswordError.MISSING_UPPERCASE -> "Password is missing uppercase! "
        PasswordError.EMPTY -> "Password field cannot be empty!"
        PasswordError.PASSWORDS_DO_NOT_MATCH -> "Passwords are not identical!"
        PasswordError.NONE -> ""
    }
}