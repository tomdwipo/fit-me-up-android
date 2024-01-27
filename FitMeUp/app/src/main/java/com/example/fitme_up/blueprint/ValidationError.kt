package com.example.fitme_up.blueprint

data class ValidationError(
    val error: ErrorItem
)

data class ErrorItem(
    val message: List<ErrorMessage>,
    val status: Int
)

data class ErrorMessage(
    val field: String,
    val message: String,
    val rule: String
)
