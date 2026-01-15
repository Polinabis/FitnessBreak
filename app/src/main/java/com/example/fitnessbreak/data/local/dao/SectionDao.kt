package com.example.fitnessbreak.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fitnessbreak.data.local.entity.ExerciseCardEntity
import com.example.fitnessbreak.data.local.entity.ExerciseSectionEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SectionDao {
    @Query("SELECT * FROM sections WHERE id = :id")
    suspend fun getSectionEntity(id: Int): ExerciseSectionEntity?

    @Query("SELECT * FROM sections")
    fun getAllSections(): Flow<List<ExerciseSectionEntity>>

    @Query("SELECT * FROM cards WHERE id IN (:cardIds)")
    suspend fun getCardsByIds(cardIds: List<Int>): List<ExerciseCardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSection(section: ExerciseSectionEntity)

    @Update
    suspend fun updateSection(section: ExerciseSectionEntity)

    @Delete
    suspend fun deleteSection(section: ExerciseSectionEntity)

}
