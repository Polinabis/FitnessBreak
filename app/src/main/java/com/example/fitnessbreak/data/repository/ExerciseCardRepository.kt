package com.example.fitnessbreak.data.repository

import com.example.fitnessbreak.data.local.dao.ExerciseCardDao
import com.example.fitnessbreak.data.mapper.toEntity
import com.example.fitnessbreak.data.mapper.toModel
import com.example.fitnessbreak.data.local.model.ExerciseCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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

//    fun getTurnedOnCards(): Flow<List<ExerciseCard>> = dao.getTurnedOnCards()
//        .map { entities ->
//            entities.map { entity -> entity.toModel() }
//        }
//    }

    suspend fun updateCardsSelection(selectedIds: Set<Int>) {
        val allCards = getAllCards().first()
        val updatedCards = allCards.map { card ->
            card.copy(isSelected = card.id in selectedIds)
        }
        // Обновляем ВСЕ карточки через @Update
        updatedCards.forEach { updateCard(it) }
    }
}