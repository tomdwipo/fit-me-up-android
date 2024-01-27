package com.example.fitme_up.blueprint

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RegisterCoachInput(
    val fullName: String,
    val email: String,
    val password: String,
    val password_confirmation: String,
    val dateBirth: String,
    val favSportIds: ArrayList<Int?> = arrayListOf(),
    var schedules: ArrayList<ScheduleData>? = arrayListOf(),
) : Parcelable{
    fun setFavSport(value: Int?){
        this.favSportIds.add(value)
    }

    fun deleteFavSport(value: Int){
        this.favSportIds.remove(value)
    }
}

@Parcelize
data class ScheduleData(
    var scheduleDay: Int?,
    var scheduleStart: String?,
    var scheduleEnd: String?
) : Parcelable{

    fun setDay(value2: Int?){
        this.scheduleDay = value2
    }

    fun setStart(value2: String?){
        this.scheduleStart = value2
    }

    fun setEnd(value: String?){
        this.scheduleEnd = value
    }


}
