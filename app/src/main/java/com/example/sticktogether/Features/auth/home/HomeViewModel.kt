package com.example.sticktogether.Features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sticktogether.Domain.Habit
import com.example.sticktogether.Domain.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val habitRepository: HabitRepository
) : ViewModel() {

    private val _habits = MutableStateFlow<List<Habit>>(emptyList())
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    init {
        viewModelScope.launch {
            habitRepository.getUserHabits().collect { habitsList ->
                _habits.value = habitsList
            }
        }
    }

    fun addHabit(name: String, time: String, startDate: String, frequency: String) {
        val newHabit = Habit(
            name = name,
            time = time,
            startDate = startDate,
            frequency = frequency
        )
        habitRepository.addHabit(newHabit)
    }

    fun toggleHabitCompletion(habit: Habit) {
        habitRepository.updateHabitCompletion(habit.id, !habit.isCompleted)
    }
}