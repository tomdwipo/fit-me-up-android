package com.example.fitme_up.feature_login.data.service.api

import com.example.fitme_up.feature_login.data.service.request.LoginRequest
import com.example.fitme_up.feature_login.data.service.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApiService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>


    @GET("coach")
    suspend fun getCoaches(
        @Header("Authorization") auth: String,
    ): Response<LoginResponse>
}