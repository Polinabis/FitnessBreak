package com.example.fitnessbreak.data.repository

import com.example.fitnessbreak.data.local.dao.ExerciseCardDao
import com.example.fitnessbreak.data.local.dao.SectionDao
import com.example.fitnessbreak.data.mapper.toModel
import com.example.fitnessbreak.data.local.model.ExerciseSection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class SectionRepository @Inject constructor(
    private val sectionDao: SectionDao,
    private val exerciseCardDao: ExerciseCardDao
) {

    suspend fun getSection(id: Int): ExerciseSection? {
        val sectionEntity = sectionDao.getSectionEntity(id) ?: return null
        val cardEntities = exerciseCardDao.getCardsByIds(sectionEntity.cards)
        val cards = cardEntities.map { it.toModel() }
        return ExerciseSection(
            id = sectionEntity.id,
            title = sectionEntity.title,
            cards = cards
        )
    }

    // Реактивная загрузка всех секций с карточками
    fun getAllSections(): Flow<List<ExerciseSection>> =
        sectionDao.getAllSections()
            .combine(exerciseCardDao.getAllCards()) { sections, cards ->
                val cardMap = cards.associateBy { it.id }
                sections.map { sectionEntity ->
                    val cardsInSection = sectionEntity.cards.mapNotNull { cardId ->
                        cardMap[cardId]?.toModel()
                    }
                    ExerciseSection(
                        id = sectionEntity.id,
                        title = sectionEntity.title,
                        cards = cardsInSection
                    )
                }
            }
}