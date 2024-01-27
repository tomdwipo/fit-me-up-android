package com.example.fitme_up.feature_login.data.service.request

import com.google.gson.annotations.SerializedName


//{
//    "email": "323@gmail.com",
//    "password": "secretpassword"
//}
data class LoginRequest(
    @SerializedName("email") var email: String = "",
    @SerializedName("password") var password: String = ""
)



