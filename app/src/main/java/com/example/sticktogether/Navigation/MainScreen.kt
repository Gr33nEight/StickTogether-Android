package com.example.sticktogether.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sticktogether.Resources.Components.Colors
import com.example.sticktogether.Resources.Components.CustomBottomBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun MainScreen() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = currentDestination?.hasRoute(Screen.Home::class) == true ||
            currentDestination?.hasRoute(Screen.Settings::class) == true

    val selectedTab = when {
        currentDestination?.hasRoute(Screen.Home::class) == true -> 0
        currentDestination?.hasRoute(Screen.Settings::class) == true -> 2
        else -> 0
    }

    Scaffold(
        containerColor = Colors.BackgroundColor,
        bottomBar = {
            if (showBottomBar) {
                CustomBottomBar(
                    selectedTab = selectedTab,
                    onHomeClick = {
                        navController.navigate(Screen.Home) { popUpTo(0) }
                    },
                    onStatsClick = {
                    },
                    onSettingsClick = {
                        navController.navigate(Screen.Settings) { popUpTo(0) }
                    }
                )
            }
        }
    ) { innerPadding ->

        NavRoute(
            navController = navController,
            modifier = Modifier.padding(paddingValues = innerPadding)
        )

    }
}