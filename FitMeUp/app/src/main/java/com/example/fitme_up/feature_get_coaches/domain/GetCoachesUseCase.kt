package com.example.fitme_up.feature_get_coaches.domain

import com.example.fitme_up.core_data.base.BaseResultData
import com.example.fitme_up.feature_get_coaches.data.repository.GetCoachesRepository
import com.example.fitme_up.feature_get_coaches.data.service.response.GetCoachesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetCoachesUseCase {
    suspend fun getCoaches(): BaseResultData<GetCoachesResponse>
}



class GetCoachesUseCaseImpl @Inject constructor(
    private val repository: GetCoachesRepository
) : GetCoachesUseCase {
    override suspend fun getCoaches(): BaseResultData<GetCoachesResponse> {
        val coaches = repository.getCoaches()
        return withContext(Dispatchers.IO) {coaches}
    }

}
