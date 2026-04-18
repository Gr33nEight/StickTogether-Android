package com.example.sticktogether.Resources.Enums

import androidx.compose.runtime.Composable

enum class PasswordError {
    NONE,
    TOO_SHORT,
    MISSING_UPPERCASE,
    EMPTY
}

@Composable
fun PasswordError.asString(): String {
    return when (this) {
        PasswordError.TOO_SHORT -> "Hasło musi mieć minimum 6 znaków"
        PasswordError.MISSING_UPPERCASE -> "Hasło musi posiadać przynajmniej jedną dużą literę"
        PasswordError.EMPTY -> "Pole hasła nie może być puste"
        PasswordError.NONE -> ""
    }
}