package com.takseha.errorhandling2

import com.takseha.data.repositoryImpl.ErrorLogRepositoryImpl
import com.takseha.data.repositoryImpl.TotalInfoRepositoryImpl
import com.takseha.domain.repository.ErrorLogRepository
import com.takseha.domain.repository.TotalInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun totalInfoRepository(repositoryImpl: TotalInfoRepositoryImpl): TotalInfoRepository

    @Binds
    abstract fun errorLogRepository(repositoryImpl: ErrorLogRepositoryImpl): ErrorLogRepository
}