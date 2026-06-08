package com.example.sticktogether.Features.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sticktogether.Features.auth.home.view.HomeTopBar
import com.example.sticktogether.Features.home.view.DateSelector
import com.example.sticktogether.Features.home.view.HabitCard
import com.example.sticktogether.Features.home.view.HabitsHeader
import com.example.sticktogether.Resources.Components.Colors
import com.example.sticktogether.Resources.Components.CustomBottomBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    var selectedDateIndex by remember { mutableStateOf(0) }

    val habits = listOf(
        Pair("Morning Run", "07:00 AM"),
        Pair("Read a Book", "08:00 PM"),
        Pair("Drink Water", "12:00 PM")
    )

    Scaffold(
        bottomBar = {
            CustomBottomBar(
                selectedTab = selectedTab,
                onHomeClick = { selectedTab = 0 },
                onStatsClick = { selectedTab = 1 },
                onSettingsClick = { selectedTab = 2 }
            )
        },
        containerColor = Colors.BackgroundColor
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Colors.BackgroundColor),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                HomeTopBar(
                    userName = "Natanael",
                    onNotificationClick = {}
                )
            }

            item {
                DateSelector(
                    selectedDateIndex = selectedDateIndex,
                    onDateSelected = { selectedDateIndex = it }
                )
            }

            item {
                HabitsHeader()
            }

            items(habits.size) { index ->
                val habit = habits[index]
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    HabitCard(
                        title = habit.first,
                        time = habit.second,
                        isCompleted = false,
                        onToggleCompleted = {}
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}