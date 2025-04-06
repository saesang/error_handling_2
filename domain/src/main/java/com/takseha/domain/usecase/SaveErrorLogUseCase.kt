package com.takseha.domain.usecase

import com.takseha.domain.model.Failure
import com.takseha.domain.repository.ErrorLogRepository

class SaveErrorLogUseCase(
    private val errorLogRepository: ErrorLogRepository
) {
    suspend operator fun invoke(failure: Failure) {
        return errorLogRepository.saveErrorLog(failure)
    }
}