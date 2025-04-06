package com.takseha.domain.usecase

import com.takseha.domain.model.ResultWrapper
import com.example.domain.model.TotalInfoData
import com.takseha.domain.repository.TotalInfoRepository
import com.takseha.domain.usecase.validator.UsernameValidator

class FetchTotalInfoUseCase(
    private val totalInfoRepository: TotalInfoRepository
) {
    suspend operator fun invoke(username: String): ResultWrapper<TotalInfoData> {
        // 서버 요청
        // validation 체크
        val validationResult = UsernameValidator.validate<TotalInfoData>(username)
        if (validationResult is ResultWrapper.Error) return validationResult

        return totalInfoRepository.fetchTotalInfo(username)
    }
}