package com.example.fitnessbreak.di

import android.app.Application
import android.content.Context
import com.example.fitnessbreak.data.local.dao.ExerciseCardDao
import com.example.fitnessbreak.data.local.dao.SectionDao
import com.example.fitnessbreak.data.local.database.AppDatabase
// import com.example.fitnessbreak.data.local.database.DatabaseCallback
import com.example.fitnessbreak.data.repository.ExerciseCardRepository
import com.example.fitnessbreak.data.repository.SectionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppBindsModule {
    @Provides
    fun provideContext(application: Application): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        AppDatabase.getDatabase(context)

    @Provides
    fun provideExerciseCardDao(database: AppDatabase) =
        database.exerciseCardDao()

    @Provides
    fun provideSectionDao(database: AppDatabase): SectionDao =
        database.sectionDao()

    @Provides
    fun provideExerciseCardRepository(
        dao: ExerciseCardDao,
    ) = ExerciseCardRepository(
        dao
    )

    @Provides
    fun provideSectionRepository(
        sectionDao: SectionDao,
        exerciseCardDao: ExerciseCardDao
    ) = SectionRepository(
        sectionDao = sectionDao,
        exerciseCardDao = exerciseCardDao,
    )

//    @Provides
//    fun provideDatabaseCallback(
//        sectionDao: SectionDao,
//        exerciseCardDao: ExerciseCardDao
//    ) =
//        DatabaseCallback(
//            sectionDao = sectionDao,
//            exerciseCardDao = exerciseCardDao,
//        )
}