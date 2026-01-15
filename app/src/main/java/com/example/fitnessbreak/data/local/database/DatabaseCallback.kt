package com.example.fitnessbreak.data.local.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fitnessbreak.data.local.dao.ExerciseCardDao
import com.example.fitnessbreak.data.local.dao.SectionDao
import com.example.fitnessbreak.data.local.entity.ExerciseCardEntity
import com.example.fitnessbreak.data.local.entity.ExerciseSectionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.fitnessbreak.R

class DatabaseCallback(
    private val sectionDao: SectionDao,
    private val exerciseCardDao: ExerciseCardDao
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        // Запускаем в фоне, потому что DAO-методы suspend
        CoroutineScope(Dispatchers.IO).launch {
            insertDefaultData()
        }
    }

    private suspend fun insertDefaultData() {
        // Сначала вставляем карточки
        val warmupNeck = ExerciseCardEntity(
            id = 1,
            title = "Мягкие повороты шеи сидя",
            isSelected = false,
            imageResId = R.drawable.card_image2,
            imageFilePath = null
        )
        val warmupShoulders = ExerciseCardEntity(
            id = 2,
            title = "Потягивание за руками вверх",
            isSelected = false,
            imageResId = R.drawable.card_image1,
            imageFilePath = null
        )



        exerciseCardDao.insertCard(warmupShoulders)
        exerciseCardDao.insertCard(warmupNeck)

        // Теперь секции (ссылаются на ID карточек)
        val section1 = ExerciseSectionEntity(
            id = 1,
            title = "Шея",
            cards = listOf(1) // ID карточек
        )
        val section2 = ExerciseSectionEntity(
            id = 2,
            title = "Плечи",
            cards = listOf(2)
        )

        sectionDao.insertSection(section1)
        sectionDao.insertSection(section2)
    }
}