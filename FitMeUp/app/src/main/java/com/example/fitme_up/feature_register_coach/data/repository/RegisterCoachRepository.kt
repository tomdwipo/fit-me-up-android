package com.example.fitme_up.feature_register_coach.data.repository

import com.example.fitme_up.core_data.base.BaseRepository
import com.example.fitme_up.core_data.base.BaseResultData
import com.example.fitme_up.core_data.base.RetrofitBuilderModule
import com.example.fitme_up.feature_register_coach.data.service.api.RegisterCoachApiService
import com.example.fitme_up.feature_register_coach.data.service.request.RegisterCoachRequest
import com.example.fitme_up.feature_register_coach.data.service.response.RegisterCoachResponse
import javax.inject.Inject


class RegisterCoachRepository @Inject constructor() : BaseRepository<RegisterCoachApiService>(
    RegisterCoachApiService::class.java,
    RetrofitBuilderModule.provideUserEndpointBuilder()
) {
    suspend fun registerCoach(request: RegisterCoachRequest): BaseResultData<RegisterCoachResponse> {
        return executeApiCall {
            restInterface.registerCoach(request)
        }
    }
}