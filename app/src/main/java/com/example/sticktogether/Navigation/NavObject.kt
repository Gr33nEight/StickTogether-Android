package com.example.sticktogether.Navigation

import kotlinx.serialization.Serializable

@Serializable sealed class Screen {
    @Serializable object Login: Screen()
    @Serializable object Register: Screen()
}
