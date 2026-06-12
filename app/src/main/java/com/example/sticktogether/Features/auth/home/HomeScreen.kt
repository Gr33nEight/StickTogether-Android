package com.example.sticktogether.Features.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.sticktogether.Features.auth.home.scheduleHabitAlarm
import com.example.sticktogether.Features.auth.home.view.HomeTopBar
import com.example.sticktogether.Features.auth.login.LoginViewModel
import com.example.sticktogether.Features.home.view.*
import com.example.sticktogether.Navigation.Screen
import com.example.sticktogether.Resources.Components.Colors
import com.example.sticktogether.Resources.Components.CustomBottomBar
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Habit(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val time: String,
    val startDate: LocalDate,
    val frequency: String,
    var isCompleted: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (Any) -> Unit){
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf(0) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val allHabits = remember { mutableStateListOf<Habit>() }

    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted -> hasNotificationPermission = isGranted }
    )

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    val filteredHabits = allHabits.filter { habit ->
        if (selectedDate.isBefore(habit.startDate)) {
            false
        } else {
            when (habit.frequency) {
                "Daily" -> true
                "Weekly" -> selectedDate.dayOfWeek == habit.startDate.dayOfWeek
                "Monthly" -> selectedDate.dayOfMonth == habit.startDate.dayOfMonth
                else -> habit.startDate == selectedDate
            }
        }
    }

    val daysOffset = ChronoUnit.DAYS.between(LocalDate.now(), selectedDate).toInt()

    Scaffold(
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
                    HomeTopBar(userName = "Natanael", onNotificationClick = {})
                }

                item {
                    DateSelector(
                        selectedDateIndex = if (daysOffset >= 0) daysOffset else 0,
                        onDateSelected = { index ->
                            selectedDate = LocalDate.now().plusDays(index.toLong())
                        }
                    )
                }

                item {
                    HabitsHeader(onAddClick = { showBottomSheet = true })
                }

                items(filteredHabits, key = { it.id }) { habit ->
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        HabitCard(
                            title = habit.name,
                            time = habit.time,
                            isCompleted = habit.isCompleted,
                            onToggleCompleted = {
                                val index = allHabits.indexOf(habit)
                                if (index != -1) {
                                    allHabits[index] = habit.copy(isCompleted = !habit.isCompleted)
                                }
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
                        onHabitCreated = { name, time, frequency, startDate ->
                            allHabits.add(
                                Habit(
                                    name = name,
                                    time = time,
                                    startDate = startDate,
                                    frequency = frequency
                                )
                            )
                            if (hasNotificationPermission) {
                                scheduleHabitAlarm(context, name, startDate, time)
                            }
                            showBottomSheet = false
                        },
                        onCancel = { showBottomSheet = false }
                    )
                }
            }
        }
    }
}
