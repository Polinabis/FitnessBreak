package com.example.fitnessbreak.data.local.model

data class ExerciseSection(
    val id: Int,
    val title: String,
    val cards: List<ExerciseCard>
)