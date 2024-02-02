package com.example.fitme_up.feature_login.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fitme_up.feature_login.data.service.request.LoginRequest
import com.example.fitme_up.feature_login.domain.LoginUseCase
import com.example.fitme_up.feature_login.presentation.states.LoginStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usecase: LoginUseCase,
) : ViewModel() {


    suspend fun login(request: LoginRequest): LoginStates {
        val call = usecase.login(request)
        val response = withContext(Dispatchers.IO) { call }
        return when(response.data?.role){
            1 -> LoginStates.ADMIN
            2 -> LoginStates.USER
            3 -> LoginStates.COACH
            4 -> LoginStates.VENUE
            else -> LoginStates.UNKNOWN
        }
    }

    suspend fun coaches() {
        val call = usecase.coaches()
        val response = withContext(Dispatchers.IO) { call }
        if (response.isSuccess){
            //TODO: do something
        }

    }
}