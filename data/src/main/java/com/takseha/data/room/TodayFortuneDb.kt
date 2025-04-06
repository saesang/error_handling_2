package com.takseha.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.takseha.data.room.entity.ErrorLogEntity
import com.takseha.data.room.entity.TotalInfoEntity

@Database(
    entities = [TotalInfoEntity::class, ErrorLogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TodayFortuneDb : RoomDatabase() {
    abstract fun dao(): TodayFortuneDao
}