package com.example.fitnessbreak.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fitnessbreak.data.local.converters.ListConverter
import com.example.fitnessbreak.data.local.dao.ExerciseCardDao
import com.example.fitnessbreak.data.local.dao.SectionDao
import com.example.fitnessbreak.data.local.entity.ExerciseCardEntity
import com.example.fitnessbreak.data.local.entity.ExerciseSectionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.fitnessbreak.R

@Database(
    entities = [ExerciseCardEntity::class, ExerciseSectionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseCardDao(): ExerciseCardDao
    abstract fun sectionDao(): SectionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_break_db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                INSTANCE?.let { database ->
                                    // Вставляем данные
                                    val cardDao = database.exerciseCardDao()
                                    val sectionDao = database.sectionDao()

                                    cardDao.insertCard(
                                        ExerciseCardEntity(
                                            id = 1,
                                            title = "Наклоны головы вперёд-назад",
                                            isSelected = false,
                                            imageResId = R.drawable.neck_1,
                                            imageFilePath = null
                                        )
                                    )
                                    cardDao.insertCard(
                                        ExerciseCardEntity(
                                            id = 2,
                                            title = "Наклоны головы вправо-влево",
                                            isSelected = false,
                                            imageResId = R.drawable.neck_2,
                                            imageFilePath = null
                                        )
                                    )
                                    cardDao.insertCard(
                                        ExerciseCardEntity(
                                            id = 3,
                                            title = "Наклоны головы с растяжением",
                                            isSelected = false,
                                            imageResId = R.drawable.neck_3,
                                            imageFilePath = null
                                        )
                                    )
                                    cardDao.insertCard(
                                        ExerciseCardEntity(
                                            id = 4,
                                            title = "Потягивание на стуле",
                                            isSelected = false,
                                            imageResId = R.drawable.back_1,
                                            imageFilePath = null
                                        )
                                    )
                                    cardDao.insertCard(
                                        ExerciseCardEntity(
                                            id = 5,
                                            title = "Скручивание на стуле",
                                            isSelected = false,
                                            imageResId = R.drawable.back_2,
                                            imageFilePath = null
                                        )
                                    )
                                    cardDao.insertCard(
                                        ExerciseCardEntity(
                                            id = 6,
                                            title = "Крылья бабочки",
                                            isSelected = false,
                                            imageResId = R.drawable.back_3,
                                            imageFilePath = null
                                        )
                                    )
                                    cardDao.insertCard(
                                        ExerciseCardEntity(
                                            id = 7,
                                            title = "Круговые вражения голеностоп",
                                            isSelected = false,
                                            imageResId = R.drawable.foot_1,
                                            imageFilePath = null
                                        )
                                    )
                                    cardDao.insertCard(
                                        ExerciseCardEntity(
                                            id = 8,
                                            title = "Подъём носков и пяток",
                                            isSelected = false,
                                            imageResId = R.drawable.foot_2,
                                            imageFilePath = null
                                        )
                                    )

                                    sectionDao.insertSection(
                                        ExerciseSectionEntity(
                                            id = 1,
                                            title = "Шея",
                                            cards = listOf(1, 2, 3)
                                        )
                                    )
                                    sectionDao.insertSection(
                                        ExerciseSectionEntity(
                                            id = 2,
                                            title = "Спина",
                                            cards = listOf(4, 5, 6)
                                        )
                                    )
                                    sectionDao.insertSection(
                                        ExerciseSectionEntity(
                                            id = 3,
                                            title = "Ноги",
                                            cards = listOf(7, 8)
                                        )
                                    )
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private suspend fun insertDefaultData(database: AppDatabase) {
            val cardDao = database.exerciseCardDao()
            val sectionDao = database.sectionDao()

            cardDao.insertCard(
                ExerciseCardEntity(
                    id = 1,
                    title = "Мягкие повороты шеи сидя",
                    isSelected = false,
                    imageResId = R.drawable.card_image2,
                    imageFilePath = null
                )
            )
            cardDao.insertCard(
                ExerciseCardEntity(
                    id = 2,
                    title = "Потягивание за руками вверх",
                    isSelected = false,
                    imageResId = R.drawable.card_image1,
                    imageFilePath = null
                )
            )

            sectionDao.insertSection(
                ExerciseSectionEntity(
                    id = 1,
                    title = "Шея",
                    cards = listOf(1)
                )
            )
            sectionDao.insertSection(
                ExerciseSectionEntity(
                    id = 2,
                    title = "Плечи",
                    cards = listOf(2)
                )
            )
        }
    }
}