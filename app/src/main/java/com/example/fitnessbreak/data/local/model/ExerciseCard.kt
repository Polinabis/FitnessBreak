package com.example.fitnessbreak.data.local.model

sealed interface ImageSource {
    data class Resource(val resId: Int) : ImageSource
    data class FileUri(val uri: String) : ImageSource
}

data class ExerciseCard(
    val id: Int,
    val title: String,
    val isSelected: Boolean,
    val imageSource: ImageSource
)
