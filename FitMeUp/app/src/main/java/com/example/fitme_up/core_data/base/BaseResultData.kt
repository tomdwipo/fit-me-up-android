package com.example.fitme_up.core_data.base

import com.google.gson.JsonObject
import java.lang.Exception

data class BaseResultData<T> (
    val data: T?,
    val isSuccess: Boolean,
    val error: BaseErrorMessage?,
    val responseCode: Int,
    val exception: Exception? = null
)

data class BaseErrorMessage (
    val code: Int,
    val message: String? = null,
    val messageKey: String,
    val values: JsonObject? = null
)