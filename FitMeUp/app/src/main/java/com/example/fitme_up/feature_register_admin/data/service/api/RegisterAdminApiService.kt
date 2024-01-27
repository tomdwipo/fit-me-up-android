package com.example.fitme_up.feature_register_admin.data.service.api

import com.example.fitme_up.feature_register_admin.data.service.request.RegisterAdminRequest
import com.example.fitme_up.feature_register_admin.data.service.response.RegisterAdminResponse
import com.example.fitme_up.feature_register_coach.data.service.request.RegisterCoachRequest
import com.example.fitme_up.feature_register_coach.data.service.response.RegisterCoachResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterAdminApiService {
    @POST("auth/register/admin")
    suspend fun registerAdmin(
        @Body request: RegisterAdminRequest
    ): Response<RegisterAdminResponse>

}