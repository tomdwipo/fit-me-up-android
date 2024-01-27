package com.example.fitme_up.blueprint

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.collections.ArrayList

@Parcelize
data class RegisterUserInput(
    val fullName: String,
    val email: String,
    val password: String,
    val password_confirmation: String,
    val dateBirth: String,
    val favSportIds: ArrayList<Int?> = arrayListOf(),
    var domicileId: Int?
) : Parcelable {
    fun setFavSport(value: Int?) {
        this.favSportIds.add(value)
    }

    fun deleteFavSport(value: Int) {
        this.favSportIds.remove(value)
    }

    fun setDomicile(value: Int) {
        this.domicileId = value
    }
}
