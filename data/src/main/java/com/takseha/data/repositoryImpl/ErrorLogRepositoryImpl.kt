package com.takseha.data.repositoryImpl

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import com.takseha.data.exception.ExceptionToFailureMapper
import com.takseha.data.mapper.ErrorLogMapper
import com.takseha.data.room.TodayFortuneDb
import com.takseha.domain.model.Failure
import com.takseha.domain.repository.ErrorLogRepository
import java.time.LocalDateTime
import javax.inject.Inject

class ErrorLogRepositoryImpl @Inject constructor(
    private val todayFortuneDb: TodayFortuneDb
) : ErrorLogRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun saveErrorLog(failure: Failure) {
        try {
            val dateTime = LocalDateTime.now().toString()
            val errorLogEntity = ErrorLogMapper.mapperToErrorLogEntity(dateTime, failure)
            todayFortuneDb.dao().insertErrorLog(errorLogEntity)
        } catch (e: Exception) {
            ExceptionToFailureMapper.map(e)
        }
    }
}