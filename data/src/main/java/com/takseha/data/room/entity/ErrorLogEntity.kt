package com.takseha.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class ErrorLogEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "dateTime") val dateTime: String,
    @ColumnInfo(name = "logSaveMsg") val logSaveMsg: String
)