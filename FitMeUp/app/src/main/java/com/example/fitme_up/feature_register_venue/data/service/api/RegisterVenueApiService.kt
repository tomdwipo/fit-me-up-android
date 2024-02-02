package com.example.fitme_up.feature_register_venue.data.service.api

import com.example.fitme_up.feature_register_coach.data.service.response.RegisterCoachResponse
import com.example.fitme_up.feature_register_venue.data.service.response.RegisterVenueResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap


interface RegisterVenueApiService {
    @Multipart
    @POST("auth/register/venue")
    fun updateVenue(
        @PartMap partMap: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part files: List<MultipartBody.Part>
    ): Call<RegisterVenueResponse>
}
