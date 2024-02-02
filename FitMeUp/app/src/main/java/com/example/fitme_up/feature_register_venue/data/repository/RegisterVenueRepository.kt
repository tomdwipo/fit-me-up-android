package com.example.fitme_up.feature_register_venue.data.repository

import com.example.fitme_up.core_data.base.BaseRepository
import com.example.fitme_up.core_data.base.BaseResultData
import com.example.fitme_up.core_data.base.RetrofitBuilderModule
import com.example.fitme_up.feature_register_venue.data.service.api.RegisterVenueApiService
import com.example.fitme_up.feature_register_venue.data.service.request.RegisterVenueRequest
import com.example.fitme_up.feature_register_venue.data.service.response.RegisterVenueResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class RegisterVenueRepository @Inject constructor() : BaseRepository<RegisterVenueApiService>(
    RegisterVenueApiService::class.java,
    RetrofitBuilderModule.provideUserEndpointBuilder()
) {
    suspend fun registerVenue(
        request: RegisterVenueRequest,
        files:List<File>
    ): BaseResultData<RegisterVenueResponse> {
        val fullname = request.fullName.toRequestBody("text/plain".toMediaTypeOrNull())
        val email = request.email.toRequestBody("text/plain".toMediaTypeOrNull())
        val password = request.password.toRequestBody("text/plain".toMediaTypeOrNull())
        val password_confirmation = request.passwordConfirmation.toRequestBody("text/plain".toMediaTypeOrNull())
        val dateBirth = request.dateBirth.toRequestBody("text/plain".toMediaTypeOrNull())
        val domicileId = request.domicileId.toRequestBody("text/plain".toMediaTypeOrNull())
        val venueName = request.venueName.toRequestBody("text/plain".toMediaTypeOrNull())
        val venueAddress = request.venueAddress.toRequestBody("text/plain".toMediaTypeOrNull())
        val favSportId = request.favSportId.toRequestBody("text/plain".toMediaTypeOrNull())
        val venueMaxCapacity = request.venueMaxCapacity.toRequestBody("text/plain".toMediaTypeOrNull())
        val venuePricing = request.venuePricing.toRequestBody("text/plain".toMediaTypeOrNull())
        val venueSchedule0 = request.venueSchedule0.toRequestBody("text/plain".toMediaTypeOrNull())
        val venueSchedule1 = request.venueSchedule1.toRequestBody("text/plain".toMediaTypeOrNull())
        val venueSchedule2 = request.venueSchedule2.toRequestBody("text/plain".toMediaTypeOrNull())
        val venueTimeOpen = request.venueTimeOpen.toRequestBody("text/plain".toMediaTypeOrNull())
        val venueTimeClose = request.venueTimeClose.toRequestBody("text/plain".toMediaTypeOrNull())
        val venueDescription = request.venueDescription.toRequestBody("text/plain".toMediaTypeOrNull())

        val body = hashMapOf(
            "fullName" to fullname,
            "email" to email,
            "password" to password,
            "password_confirmation" to password_confirmation,
            "dateBirth" to dateBirth,
            "domicileId" to domicileId,
            "venueName" to venueName,
            "venueAddress" to venueAddress,
            "venueSports[0].favSportId" to favSportId,
            "venueSports[0].venueMaxCapacity" to venueMaxCapacity,
            "venueSports[0].venuePricing" to venuePricing,
            "venueSchedules[0]" to venueSchedule0,
            "venueSchedules[1]" to venueSchedule1,
            "venueSchedules[2]" to venueSchedule2,
            "venueTimeOpen" to venueTimeOpen,
            "venueTimeClose" to venueTimeClose,
            "venueDescription" to venueDescription,
            )

        var fileParts = ArrayList<MultipartBody.Part>()

        for ((index, file) in files.withIndex()){
            val fileReqBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val part = MultipartBody.Part.createFormData("venuePhotos[$index]", file.name, fileReqBody)
            fileParts.add(part)
        }


        return executeApiCall {
            restInterface.updateVenue(body, fileParts).execute()
        }
    }
}