package com.example.fitme_up.feature_register_admin.data.repository

import com.example.fitme_up.core_data.base.BaseRepository
import com.example.fitme_up.core_data.base.BaseResultData
import com.example.fitme_up.core_data.base.RetrofitBuilderModule
import com.example.fitme_up.feature_register_admin.data.service.api.RegisterAdminApiService
import com.example.fitme_up.feature_register_admin.data.service.request.RegisterAdminRequest
import com.example.fitme_up.feature_register_admin.data.service.response.RegisterAdminResponse
import javax.inject.Inject

class RegisterAdminRepository @Inject constructor() :
    BaseRepository<RegisterAdminApiService>(
    RegisterAdminApiService::class.java,
    RetrofitBuilderModule.provideUserEndpointBuilder()
) {
    suspend fun registerAdmin(request: RegisterAdminRequest): BaseResultData<RegisterAdminResponse> {
        return executeApiCall {
            restInterface.registerAdmin(request)
        }
    }
}