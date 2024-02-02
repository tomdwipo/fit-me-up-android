package com.example.fitme_up.feature_register_venue.data.service.request

import com.google.gson.annotations.SerializedName

data class RegisterVenueRequest(
    @SerializedName("fullName") var fullName: String = "oke",
    @SerializedName("email") var email: String = "email@gmail.com",
    @SerializedName("password") var password: String = "secret_password" ,
    @SerializedName("password_confirmation") var passwordConfirmation: String = "secret_password",
    @SerializedName("dateBirth") var dateBirth: String = "2024-12-12",
    @SerializedName("domicileId") var domicileId: String = "1",
    @SerializedName("venueName") var venueName: String = "oke",
    @SerializedName("venueAddress") var venueAddress: String = "okeoke",
    @SerializedName("venueSports[0].favSportId") var favSportId: String  = "1",
    @SerializedName("venueSports[0].venueMaxCapacity") var venueMaxCapacity: String = "4",
    @SerializedName("venueSports[0].venuePricing") var venuePricing: String = "50000",
    @SerializedName("venueSchedules[0]") var venueSchedule0: String = "1",
    @SerializedName("venueSchedules[1]") var venueSchedule1: String = "3",
    @SerializedName("venueSchedules[2]") var venueSchedule2: String = "5",
    @SerializedName("venueTimeOpen") var venueTimeOpen: String = "09:00",
    @SerializedName("venueTimeClose") var venueTimeClose: String = "17:00",
    @SerializedName("venueDescription") var venueDescription: String = "This is the venue description",

    )