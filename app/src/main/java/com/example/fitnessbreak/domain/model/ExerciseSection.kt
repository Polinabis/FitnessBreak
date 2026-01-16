package com.example.fitnessbreak.domain.model

data class ExerciseSection(
    val id: Int,
    val title: String,
    val cards: List<ExerciseCard>
)