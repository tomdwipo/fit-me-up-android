package com.example.fitme_up.feature_get_coaches.data.service.api

import com.example.fitme_up.feature_get_coaches.data.service.request.GetCoachesRequest
import com.example.fitme_up.feature_get_coaches.data.service.response.GetCoachesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface GetCoachesApiService {
    @GET("coach")
    suspend fun getCoaches(
        @Header("Authorization") auth: String,
    ): Response<GetCoachesResponse>

}