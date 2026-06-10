package com.example.sticktogether.Features.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sticktogether.Features.auth.home.view.HomeTopBar
import com.example.sticktogether.Features.home.view.*
import com.example.sticktogether.Resources.Components.Colors
import com.example.sticktogether.Resources.Components.CustomBottomBar
import java.time.LocalDate

data class Habit(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val time: String,
    val startDate: LocalDate,
    var isCompleted: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val allHabits = remember { mutableStateListOf<Habit>() }

    val filteredHabits = allHabits.filter {
        it.startDate.isBefore(selectedDate.plusDays(1))
    }

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
        Box(modifier = Modifier.fillMaxSize()) {

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
                        selectedDateIndex = 0,
                        onDateSelected = { index ->
                            selectedDate = LocalDate.now().plusDays(index.toLong())
                        }
                    )
                }

                item {
                    HabitsHeader(
                        onAddClick = { showBottomSheet = true }
                    )
                }

                items(filteredHabits) { habit ->
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        HabitCard(
                            title = habit.name,
                            time = habit.time,
                            isCompleted = habit.isCompleted,
                            onToggleCompleted = {
                                habit.isCompleted = !habit.isCompleted
                            }
                        )
                    }
                }
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    containerColor = Colors.CardBackground,
                    dragHandle = { BottomSheetDefaults.DragHandle(color = Colors.TextGray) }
                ) {
                    CreateHabitContent(
                        onHabitCreated = { name, time, frequency ->
                            allHabits.add(
                                Habit(
                                    name = name,
                                    time = time,
                                    startDate = selectedDate
                                )
                            )
                            showBottomSheet = false
                        },
                        onCancel = {
                            showBottomSheet = false
                        }
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