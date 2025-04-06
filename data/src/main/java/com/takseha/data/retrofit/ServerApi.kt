package com.takseha.data.retrofit

import com.takseha.data.dto.FortuneInfoDto
import com.takseha.data.dto.UserInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {
    @GET("/api/fortune")
    suspend fun fetchFortuneInfo(@Query("username") username: String): Response<FortuneInfoDto>

    @GET("/api/userInfo")
    suspend fun fetchUserInfo(@Query("username") username: String): Response<UserInfoDto>
}