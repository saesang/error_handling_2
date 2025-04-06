package com.takseha.data.repositoryImpl

import com.takseha.data.mapper.TotalInfoMapper
import com.takseha.data.retrofit.ServerApi
import com.takseha.data.room.TodayFortuneDb
import com.example.domain.model.TotalInfoData
import com.takseha.data.exception.ExceptionToFailureMapper
import com.takseha.domain.model.Failure
import com.takseha.domain.model.ResultWrapper
import com.takseha.domain.repository.TotalInfoRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class TotalInfoRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi,
    private val todayFortuneDb: TodayFortuneDb,
) : TotalInfoRepository {
    override suspend fun getTotalInfo(username: String): ResultWrapper<TotalInfoData?> {
        try {
            val totalInfoEntity =
                todayFortuneDb.dao().getTotalInfoByUsername(username).firstOrNull()

            return ResultWrapper.Success(TotalInfoMapper.mapperToTotalInfoData(totalInfoEntity))
        } catch (e: Exception) {
            return ResultWrapper.Error(ExceptionToFailureMapper.map(e))
        }
    }

    override suspend fun fetchTotalInfo(username: String): ResultWrapper<TotalInfoData> {
        return try {
            val userInfoResponse = serverApi.fetchUserInfo(username)
            val fortuneInfoResponse = serverApi.fetchFortuneInfo(username)

            if (!userInfoResponse.isSuccessful) {
                val logSaveMsg = "서버 응답 에러(${userInfoResponse.code()})"
                return ResultWrapper.Error(Failure.ServerFailure(logSaveMsg, null))
            }

            if (!fortuneInfoResponse.isSuccessful) {
                val logSaveMsg = "서버 응답 에러(${userInfoResponse.code()})"
                return ResultWrapper.Error(Failure.ServerFailure(logSaveMsg, null))
            }

            val userInfo = userInfoResponse.body()
            val fortuneInfo = fortuneInfoResponse.body()

            val totalInfoEntity = TotalInfoMapper.mapperToTotalInfoEntity(fortuneInfo!!, userInfo!!)
            val returnData = TotalInfoMapper.mapperToTotalInfoData(totalInfoEntity)!!

            todayFortuneDb.dao().insertTotalInfo(totalInfoEntity)

            ResultWrapper.Success(returnData)
        } catch (e: Exception) {
            return ResultWrapper.Error(ExceptionToFailureMapper.map(e))
        }
    }
}