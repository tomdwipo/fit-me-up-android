package com.example.fitme_up.feature_login.presentation.states

sealed class LoginStates {
   data object ADMIN: LoginStates()
   data object USER: LoginStates()
   data object COACH: LoginStates()
   data object VENUE: LoginStates()
   data object UNKNOWN: LoginStates()

}