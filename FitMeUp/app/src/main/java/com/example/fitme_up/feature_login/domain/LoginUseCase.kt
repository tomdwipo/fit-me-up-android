package com.example.fitme_up.feature_login.domain

import com.example.fitme_up.core_data.base.BaseResultData
import com.example.fitme_up.core_data.base.UserDataStoreRepository
import com.example.fitme_up.feature_login.data.repository.LoginRepository
import com.example.fitme_up.feature_login.data.service.request.LoginRequest
import com.example.fitme_up.feature_login.data.service.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginUseCase {
    suspend fun login(request: LoginRequest): BaseResultData<LoginResponse>
}

class LoginUseCaseImpl @Inject constructor(
    private val repository: LoginRepository,
    private val userDataStoreRepository: UserDataStoreRepository
) : LoginUseCase {
    override suspend fun login(request: LoginRequest): BaseResultData<LoginResponse> {
        val login = repository.login(request)
        val response = withContext(Dispatchers.IO) { login }
        if (response.isSuccess){
            userDataStoreRepository.saveUserSession(response.data?.token ?: "")
        }
        return response
    }
}
