package com.example.fitnessbreak.data.model

data class ExerciseSection(
    val id: Int,
    val title: String,
    val cards: List<ExerciseCard>
)