package com.example.fitme_up

import com.example.fitme_up.blueprint.DataResponse
import com.example.fitme_up.blueprint.MainData
import com.example.fitme_up.blueprint.RegisterCoachInput
import com.example.fitme_up.blueprint.RegisterUserInput
import com.example.fitme_up.blueprint.RegisterVenueOwnerInput
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("domicile")
    suspend fun getDomicilePost(): MainData.MainDomicileData

    @GET("fav-sport")
    suspend fun getFavSport(): MainData.MainFavSportData

    @POST("auth/register/user")
    fun postRegisterUser(@Body data: RegisterUserInput?): Call<DataResponse>

    @POST("auth/register/venue")
    fun postRegisterVenueOwner(@Body data: RegisterVenueOwnerInput?): Call<DataResponse>

    @POST("auth/register/coach")
    fun postRegisterCoach(@Body data: RegisterCoachInput?): Call<DataResponse>
}