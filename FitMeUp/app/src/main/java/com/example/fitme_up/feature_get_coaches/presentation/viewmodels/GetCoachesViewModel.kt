package com.example.fitme_up.feature_get_coaches.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fitme_up.feature_get_coaches.data.service.request.GetCoachesRequest
import com.example.fitme_up.feature_get_coaches.data.service.response.GetCoachesResponse
import com.example.fitme_up.feature_get_coaches.domain.GetCoachesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GetCoachesViewModel @Inject constructor(
    private val usecase: GetCoachesUseCase,
) : ViewModel() {


    suspend fun getCoaches(request: GetCoachesRequest): String {
        val call = usecase.getCoaches()
        val response = withContext(Dispatchers.IO) { call }
        return if (response.isSuccess){
            Log.d("print", request.toString())
            response.data?.message ?: ""

        }else{
            ""
        }
    }
}