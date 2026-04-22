package com.example.sticktogether.Navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.sticktogether.Resources.Components.Colors

@Composable

fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        containerColor = Colors.BackgroundColor
    ) { innerPadding ->
        NavRoute(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}