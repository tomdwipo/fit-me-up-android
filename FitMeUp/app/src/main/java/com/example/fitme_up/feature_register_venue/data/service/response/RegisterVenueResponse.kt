package com.example.fitme_up.feature_register_venue.data.service.response

import com.google.gson.annotations.SerializedName

data class RegisterVenueResponse(
    @SerializedName("message") var message: String = "",
    )