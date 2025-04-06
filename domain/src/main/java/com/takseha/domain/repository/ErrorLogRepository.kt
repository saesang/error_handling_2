package com.takseha.domain.repository

import com.takseha.domain.model.Failure


interface ErrorLogRepository {
    // DB에 ErrorLog 저장
    suspend fun saveErrorLog(failure: Failure)
}