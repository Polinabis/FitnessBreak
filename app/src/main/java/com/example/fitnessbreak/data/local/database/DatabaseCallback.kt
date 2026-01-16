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

//class DatabaseCallback(
//    private val database: AppDatabase
//) : RoomDatabase.Callback() {
//
//    override fun onCreate(db: SupportSQLiteDatabase) {
//        super.onCreate(db)
//        CoroutineScope(Dispatchers.IO).launch {
//            insertDefaultData()
//        }
//    }
//
//    private suspend fun insertDefaultData() {
//        println("Внедрение данных в БД запущено")
//
//        val cardDao = database.exerciseCardDao()
//        val sectionDao = database.sectionDao()
//
//        // Вставляем карточки
//        cardDao.insertCard(
//            ExerciseCardEntity(
//                id = 1,
//                title = "Мягкие повороты шеи сидя",
//                isSelected = false,
//                imageResId = R.drawable.card_image2,
//                imageFilePath = null
//            )
//        )
//        cardDao.insertCard(
//            ExerciseCardEntity(
//                id = 2,
//                title = "Потягивание за руками вверх",
//                isSelected = false,
//                imageResId = R.drawable.card_image1,
//                imageFilePath = null
//            )
//        )
//
//        // Вставляем секции
//        sectionDao.insertSection(
//            ExerciseSectionEntity(
//                id = 1,
//                title = "Шея",
//                cards = listOf(1)
//            )
//        )
//        sectionDao.insertSection(
//            ExerciseSectionEntity(
//                id = 2,
//                title = "Плечи",
//                cards = listOf(2)
//            )
//        )
//
//        println("Данные успешно внесены")
//    }
//}