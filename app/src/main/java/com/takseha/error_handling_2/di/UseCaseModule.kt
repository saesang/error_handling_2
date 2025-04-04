package com.example.todayfortunebytdd.di

import com.example.domain.repository.ErrorLogRepository
import com.example.domain.repository.TotalInfoRepository
import com.example.domain.usecase.FetchTotalInfoUseCase
import com.example.domain.usecase.GetTotalInfoUseCase
import com.example.domain.usecase.SaveErrorLogUseCase
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