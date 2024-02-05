package com.example.fitme_up.feature_get_coaches.data.repository

import android.util.Log
import com.example.fitme_up.core_data.base.BaseRepository
import com.example.fitme_up.core_data.base.BaseResultData
import com.example.fitme_up.core_data.base.RetrofitBuilderModule
import com.example.fitme_up.core_data.base.UserDataStoreRepository
import com.example.fitme_up.feature_get_coaches.data.service.api.GetCoachesApiService
import com.example.fitme_up.feature_get_coaches.data.service.request.GetCoachesRequest
import com.example.fitme_up.feature_get_coaches.data.service.response.GetCoachesResponse
import javax.inject.Inject

class GetCoachesRepository @Inject constructor(private val userDataStoreRepository: UserDataStoreRepository) :
    BaseRepository<GetCoachesApiService>(
        GetCoachesApiService::class.java,
        RetrofitBuilderModule.provideUserEndpointBuilder()
    ) {
    suspend fun getCoaches(): BaseResultData<GetCoachesResponse> {
        return executeApiCall(userDataStoreRepository) { auth ->
            restInterface.getCoaches("Bearer ${auth ?: ""}", GetCoachesRequest())
        }
    }
}