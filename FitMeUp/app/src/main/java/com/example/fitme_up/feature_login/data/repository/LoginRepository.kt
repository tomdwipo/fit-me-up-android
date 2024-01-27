package com.example.fitme_up.feature_login.data.repository

import com.example.fitme_up.core_data.base.BaseRepository
import com.example.fitme_up.core_data.base.BaseResultData
import com.example.fitme_up.core_data.base.RetrofitBuilderModule
import com.example.fitme_up.feature_login.data.service.api.LoginApiService
import com.example.fitme_up.feature_login.data.service.request.LoginRequest
import com.example.fitme_up.feature_login.data.service.response.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor() : BaseRepository<LoginApiService>(
    LoginApiService::class.java,
    RetrofitBuilderModule.provideUserEndpointBuilder()
) {
    suspend fun login(request: LoginRequest): BaseResultData<LoginResponse> {
        return executeApiCall {
            restInterface.login(request)
        }
    }
}