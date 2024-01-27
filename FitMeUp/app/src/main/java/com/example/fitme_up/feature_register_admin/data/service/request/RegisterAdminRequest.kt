package com.example.fitme_up.feature_register_admin.data.service.request

import com.google.gson.annotations.SerializedName

//{
//    "fullName": "admin register",
//    "email": "admin@gmail.com",
//    "password": "adminregister",
//    "password_confirmation": "adminregister",
//    "dateBirth": "2001-11-15"
//}

data class RegisterAdminRequest(
    @SerializedName("fullName") var fullName: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("password") var password: String = "",
    @SerializedName("password_confirmation") var password_confirmation: String = "",
    @SerializedName("dateBirth") var dateBirth: String = "",
    )

