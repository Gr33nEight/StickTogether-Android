package com.example.sticktogether.Domain


data class Habit(
    val id: String = java.util.UUID.randomUUID().toString(),
    val userId: String = "",
    val name: String = "",
    val time: String = "",
    val startDate: String = "",
    val frequency: String = "",
    var isCompleted: Boolean = false
)