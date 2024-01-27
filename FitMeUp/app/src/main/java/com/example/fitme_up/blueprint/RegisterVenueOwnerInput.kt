package com.example.fitme_up.blueprint

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RegisterVenueOwnerInput(
    val fullName: String,
    val email: String,
    val password: String,
    val password_confirmation: String,
    val dateBirth: String,
    var domicileId: Int?,
    var venueName: Int?,
    var venueAddress: Int?,
    var venueSports: @RawValue ArrayList<venueSport>,
    var venueSchedules: Int?,
    var venueTimeOpen: Int?,
    var venueTimeClose: Int?,
    var venuePhotos: Int?,
    var venueDescription: Int?,

): Parcelable {

    fun setDomicile(value: Int) {
        this.domicileId = value
    }
}

data class venueSport(
    var favSport: ArrayList<Int?> = arrayListOf(),
    var venueMax: Int?,
    var venuePrice: Int?
) {
    fun setFavSport(value: Int?){
        this.favSport.add(value)
    }

    fun deleteFavSport(value: Int){
        this.favSport.remove(value)
    }
}