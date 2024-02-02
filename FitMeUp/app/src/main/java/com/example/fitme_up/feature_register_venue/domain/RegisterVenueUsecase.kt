package com.example.fitme_up.feature_register_venue.domain

import com.example.fitme_up.core_data.base.BaseResultData
import com.example.fitme_up.feature_register_venue.data.repository.RegisterVenueRepository
import com.example.fitme_up.feature_register_venue.data.service.request.RegisterVenueRequest
import com.example.fitme_up.feature_register_venue.data.service.response.RegisterVenueResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

interface RegisterVenueUsecase {
    suspend fun registerVenue(request: RegisterVenueRequest, fileRequest:List<File>): BaseResultData<RegisterVenueResponse>
}



class RegisterVenueUsecaseImpl @Inject constructor(
    private val repository: RegisterVenueRepository
) : RegisterVenueUsecase {
    override suspend fun registerVenue(
        request: RegisterVenueRequest,
        fileRequest: List<File>
    ): BaseResultData<RegisterVenueResponse> {
        val call = repository.registerVenue(request, fileRequest)
        return withContext(Dispatchers.IO) { call }
    }
}
