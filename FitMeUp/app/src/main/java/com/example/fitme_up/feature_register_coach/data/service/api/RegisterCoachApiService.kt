package com.example.fitme_up.feature_register_coach.data.service.api

import com.example.fitme_up.feature_login.data.service.request.LoginRequest
import com.example.fitme_up.feature_login.data.service.response.LoginResponse
import com.example.fitme_up.feature_register_coach.data.service.request.RegisterCoachRequest
import com.example.fitme_up.feature_register_coach.data.service.response.RegisterCoachResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterCoachApiService {
    @POST("auth/register/coach")
    suspend fun registerCoach(
        @Body request: RegisterCoachRequest
    ): Response<RegisterCoachResponse>

}