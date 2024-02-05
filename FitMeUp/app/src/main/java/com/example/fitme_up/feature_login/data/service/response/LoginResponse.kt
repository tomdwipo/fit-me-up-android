package com.example.fitme_up.feature_login.data.service.response

import com.google.gson.annotations.SerializedName

//{
//    "message": "Login success!",
//    "token": "Nw._kvE663vuQ4c_LkfGo0_m6eAp73zSDRgMyU09EHvaTRoY4Oz3H73WyGlcZ57",
//    "role": 1
//}
data class LoginResponse(
    @SerializedName("message") var message: String = "",
    @SerializedName("token") var token: String = "",
    @SerializedName("roleId") var roleId: Int = 0
)
