package com.example.fitme_up.feature_register_venue.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fitme_up.feature_login.data.service.request.LoginRequest
import com.example.fitme_up.feature_login.domain.LoginUseCase
import com.example.fitme_up.feature_login.presentation.states.LoginStates
import com.example.fitme_up.feature_register_venue.data.service.request.RegisterVenueRequest
import com.example.fitme_up.feature_register_venue.domain.RegisterVenueUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class RegisterVenueViewModel @Inject constructor(
    private val usecase: RegisterVenueUsecase,
) : ViewModel() {


    suspend fun addVenue(request: RegisterVenueRequest, files: List<File>): String {
        val call = usecase.registerVenue(request, files)
        val response = withContext(Dispatchers.IO) { call }
        if (response.isSuccess){
            return response.data?.message ?: ""
        }else{
            return ""
        }
    }
}