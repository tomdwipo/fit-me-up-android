package com.example.fitme_up.feature_get_coaches.data.service.request

import com.google.gson.annotations.SerializedName


data class GetCoachesRequest(
    @SerializedName("full_name") var full_name: String = "",
    @SerializedName("favSports") var favSports: String = ""

)



