package com.example.fitnessbreak.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fitnessbreak.data.local.entity.ExerciseCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseCardDao {
    @Query("SELECT * FROM cards")
    fun getAllCards(): Flow<List<ExerciseCardEntity>>

    @Query("SELECT * FROM cards WHERE id IN (:cardIds)")
    suspend fun getCardsByIds(cardIds: List<Int>): List<ExerciseCardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(card: ExerciseCardEntity)

    @Update
    suspend fun updateCard(card: ExerciseCardEntity)

    @Delete
    suspend fun deleteCard(card: ExerciseCardEntity)
}