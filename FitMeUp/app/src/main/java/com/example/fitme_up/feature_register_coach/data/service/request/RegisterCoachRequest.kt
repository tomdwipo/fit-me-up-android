package com.example.fitme_up.feature_register_coach.data.service.request

import com.google.gson.annotations.SerializedName

//{
//    "fullName": "Test Coach Name",
//    "email": "coach3@gmail.com",
//    "password": "coachpassword3",
//    "password_confirmation": "coachpassword3",
//    "dateBirth": "2001-11-15",
//    "favSportIds": [1],
//    "schedules": [
//    {
//        "scheduleDay": 1,
//        "scheduleTimeStart": "09:00",
//        "scheduleTimeEnd": "17:30"
//    }
//    ]
//}

data class RegisterCoachRequest(
    @SerializedName("fullName") var fullName: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("password") var password: String = "",
    @SerializedName("password_confirmation") var password_confirmation: String = "",
    @SerializedName("dateBirth") var dateBirth: String = "",
    @SerializedName("favSportIds") var favSportIds: List<Int> = listOf(),
    @SerializedName("schedules") var schedules: List<RegisterCoachSchedules> = listOf()
)

data class RegisterCoachSchedules(
    @SerializedName("scheduleDay") var scheduleDay: Int = 0,
    @SerializedName("scheduleTimeStart") var scheduleTimeStart: String = "",
    @SerializedName("scheduleTimeEnd") var scheduleTimeEnd: String = "",

    )
