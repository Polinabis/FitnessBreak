package com.example.fitnessbreak.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sections")
data class ExerciseSectionEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val cards: List<Int>
)
