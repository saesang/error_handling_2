package com.takseha.data.mapper

import com.takseha.data.room.entity.ErrorLogEntity
import com.takseha.domain.model.ErrorLogData
import com.takseha.domain.model.Failure
import java.time.LocalDateTime

// failure <-> entity
object ErrorLogMapper {
    fun mapperToErrorLogEntity(dateTime: String, failure: Failure): ErrorLogEntity {
        return ErrorLogEntity(
            id = 0,
            dateTime = dateTime,
            logSaveMsg = failure.msg
        )
    }

    fun mapperToErrorLogData(errorLogEntity: ErrorLogEntity): ErrorLogData {
        return ErrorLogData(
            dateTime = errorLogEntity.dateTime,
            logeSaveMsg = errorLogEntity.logSaveMsg
        )
    }
}