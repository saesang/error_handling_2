package com.takseha.errorhandling2

import android.content.Context
import androidx.room.Room
import com.takseha.data.room.TodayFortuneDao
import com.takseha.data.room.TodayFortuneDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Singleton
    @Provides
    fun todayFortuneDb(
        @ApplicationContext context: Context
    ): TodayFortuneDb = Room
        .databaseBuilder(context, TodayFortuneDb::class.java, "todayFortuneDb.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun todayFortuneDao(todayFortuneDb: TodayFortuneDb): TodayFortuneDao = todayFortuneDb.dao()
}