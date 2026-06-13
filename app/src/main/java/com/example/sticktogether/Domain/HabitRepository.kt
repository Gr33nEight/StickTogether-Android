package com.example.sticktogether.Domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class HabitRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {
    private val habitsCollection = firestore.collection("habits")

    fun getUserHabits(): Flow<List<Habit>> = callbackFlow {
        val userId = auth.currentUser?.uid
        if (userId == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val subscription = habitsCollection
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val habits = snapshot.toObjects(Habit::class.java)
                    trySend(habits)
                }
            }

        awaitClose { subscription.remove() }
    }

    fun addHabit(habit: Habit) {
        val userId = auth.currentUser?.uid ?: return
        val newHabit = habit.copy(userId = userId)
        habitsCollection.document(newHabit.id).set(newHabit)
    }

    fun updateHabitCompletion(habitId: String, isCompleted: Boolean) {
        habitsCollection.document(habitId).update("completed", isCompleted)
    }
}