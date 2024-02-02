package com.example.fitme_up.feature_login.presentation.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewModelScope
import com.example.fitme_up.CoachActivity
import com.example.fitme_up.R
import com.example.fitme_up.UserActivity
import com.example.fitme_up.VenueOwnerActivity
import com.example.fitme_up.feature_login.data.service.request.LoginRequest
import com.example.fitme_up.feature_login.presentation.states.LoginStates
import com.example.fitme_up.feature_login.presentation.viewmodels.LoginViewModel
import com.example.fitme_up.feature_register_coach.data.service.request.RegisterCoachRequest
import com.example.fitme_up.feature_register_coach.data.service.request.RegisterCoachSchedules
import com.example.fitme_up.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    lateinit var registerBtnText: TextView
    lateinit var loginBtn: Button
    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getWindow().setBackgroundDrawableResource(R.drawable.assetbackground)

        registerBtnText = findViewById(R.id.registerButtonText)
        loginBtn = findViewById(R.id.signInButton)
        emailInput = findViewById(R.id.input_register_email)
        passwordInput =  findViewById(R.id.input_register_password)

        loginBtn.setOnClickListener {

            loginViewModel.viewModelScope.launch {
                val result = loginViewModel.login(
                    request = LoginRequest(
                        email = emailInput.text.toString(),
                        password = passwordInput.text.toString()
                    )
                )
                when(result){
                    LoginStates.ADMIN -> {
                        println("admin")
                    }
                    LoginStates.COACH -> {
                        val intent = Intent(applicationContext, CoachActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    LoginStates.UNKNOWN -> {
                        println("unknow")
                    }
                    LoginStates.USER -> {
                        val intent = Intent(applicationContext, UserActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    LoginStates.VENUE -> {
                        val intent = Intent(applicationContext, VenueOwnerActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }
            }
        }

        registerBtnText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}