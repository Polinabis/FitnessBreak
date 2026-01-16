package com.example.fitnessbreak.data.mapper

import com.example.fitnessbreak.data.local.entity.ExerciseCardEntity
import com.example.fitnessbreak.domain.model.ExerciseCard
import com.example.fitnessbreak.domain.model.ImageSource

// Из Entity → Model
fun ExerciseCardEntity.toModel(): ExerciseCard {
    val imageSource = if (imageResId != null) {
        ImageSource.Resource(imageResId)
    } else {
        ImageSource.FileUri(imageFilePath ?: "")
    }
    return ExerciseCard(id, title, isSelected, imageSource)
}

// Из Model → Entity
fun ExerciseCard.toEntity(): ExerciseCardEntity {
    return ExerciseCardEntity(
        id = id,
        title = title,
        isSelected = isSelected,
        imageResId = if (imageSource is ImageSource.Resource) imageSource.resId else null,
        imageFilePath = if (imageSource is ImageSource.FileUri) imageSource.uri else null
    )

}