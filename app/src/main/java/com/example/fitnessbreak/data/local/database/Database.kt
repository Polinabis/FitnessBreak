package com.example.fitnessbreak.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessbreak.data.local.dao.ExerciseCardDao
import com.example.fitnessbreak.data.local.entity.ExerciseCardEntity

@Database(
    entities = [ExerciseCardEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseCardDao(): ExerciseCardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_break_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}