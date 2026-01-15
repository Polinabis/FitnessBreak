package com.example.fitnessbreak.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class ExerciseCardEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val isSelected: Boolean,
    // Для ImageSource нужно преобразовать в простые типы:
    val imageResId: Int?,      // null если FileUri
    val imageFilePath: String? // null если Resource
)
