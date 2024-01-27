package com.example.fitme_up.blueprint

import java.util.*

data class Profiles(
    val id: Int?,
    val full_name: String,
    val date_birth: String,
    val active_balance: Int?,
    val user_id: Int,
    val domicile_id: Int
)
