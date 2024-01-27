package com.example.fitme_up.blueprint

import com.google.gson.annotations.SerializedName
import retrofit2.Call

class MainData() {
    data class MainDomicileData(
        @SerializedName("data")
        val data: List<Domicile>
    )

    data class MainFavSportData(
        @SerializedName("data")
        val data: List<FavSportData>
    )

    data class MainRegisterUserData(
        val data: Call<RegisterUserInput>
    )
}

