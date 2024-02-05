package com.example.fitme_up.feature_get_coaches.data.service.response

import com.google.gson.annotations.SerializedName

//{
//    "message": "Data fetched!",
//    "data": [
//    {
//        "full_name": "Test Coach Name",
//        "favSports": [
//        {
//            "sport_name": "Badminton"
//        }
//        ]
//    }
//    ]
//}


data class GetCoachesResponse(
    @SerializedName("message") var message: String = "",
    @SerializedName("data") var data: List<CoachesData>
)

data class CoachesData(
    @SerializedName("full_name") var full_name: String = "",
    @SerializedName("favSports") var favSports: List<CoachesSport>
)

data class CoachesSport(
    @SerializedName("sport_name") var sport_name: String = ""
)