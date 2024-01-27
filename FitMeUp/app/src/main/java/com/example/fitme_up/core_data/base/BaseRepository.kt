package com.example.fitme_up.core_data.base

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit


open class BaseRepository<T>(apiServiceClass: Class<T>, apiRetrofitBuilder: Retrofit) {
    val restInterface: T = apiRetrofitBuilder.create(apiServiceClass)

    suspend fun <R> executeApiCall(userDataStoreRepository: UserDataStoreRepository? = null, call: suspend (accessToken: String?) -> Response<R>): BaseResultData<R> {
        return try {
            val accessToken = userDataStoreRepository?.getAccessToken()?.firstOrNull().orEmpty()
            val response = withContext(Dispatchers.IO) { call(accessToken) }
            val responseCode = response.code()

            if (response.isSuccessful) {
                BaseResultData(
                    data = response.body(),
                    isSuccess = true,
                    error = null,
                    responseCode = responseCode
                )
            } else {
                BaseResultData(
                    data = null,
                    isSuccess = false,
                    error = parseErrorMessage(response.errorBody()?.string()),
                    responseCode = responseCode
                )
            }
        } catch (e: Exception) {
            BaseResultData(
                data = null,
                isSuccess = false,
                error = parseErrorMessage(null),
                responseCode = -1,
                exception = e
            )
        }
    }
}


fun parseErrorMessage(errorJson: String?): BaseErrorMessage {
    val gson = Gson()
    val default = BaseErrorMessage(
        code = 500,
        message = "Unknown error",
        messageKey = "unknown.error"
    )
    return if(errorJson == null) default else gson.fromJson(errorJson, BaseErrorMessage::class.java)
}