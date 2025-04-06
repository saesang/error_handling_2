package com.takseha.errorhandling2

import com.takseha.domain.repository.ErrorLogRepository
import com.takseha.domain.repository.TotalInfoRepository
import com.takseha.domain.usecase.FetchTotalInfoUseCase
import com.takseha.domain.usecase.GetTotalInfoUseCase
import com.takseha.domain.usecase.SaveErrorLogUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun getTotalInfoUseCase(
        totalInfoRepository: TotalInfoRepository
    ): GetTotalInfoUseCase {
        return GetTotalInfoUseCase(totalInfoRepository)
    }

    @Provides
    fun fetchTotalInfoUseCase(
        totalInfoRepository: TotalInfoRepository
    ): FetchTotalInfoUseCase {
        return FetchTotalInfoUseCase(totalInfoRepository)
    }

    @Provides
    fun saveErrorLog(
        saveErrorLogRepository: ErrorLogRepository
    ): SaveErrorLogUseCase {
        return SaveErrorLogUseCase(saveErrorLogRepository)
    }
}