package com.example.fitnessbreak.data.repository

import com.example.fitnessbreak.data.local.dao.ExerciseCardDao
import com.example.fitnessbreak.data.mapper.toEntity
import com.example.fitnessbreak.data.mapper.toModel
import com.example.fitnessbreak.data.model.ExerciseCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ExerciseCardRepository(private val dao: ExerciseCardDao) {

    fun getAllCards(): Flow<List<ExerciseCard>> = dao.getAllCards()
        .map { entities ->
            entities.map { entity -> entity.toModel() }
        }

    suspend fun insertCard(card: ExerciseCard) {
        dao.insertCard(card.toEntity())
    }

    suspend fun updateCard(card: ExerciseCard) {
        dao.updateCard(card.toEntity())
    }
}