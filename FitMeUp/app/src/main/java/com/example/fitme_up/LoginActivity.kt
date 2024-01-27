package com.example.fitme_up

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitme_up.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    lateinit var registerBtnText: TextView
    lateinit var loginBtn: Button
    lateinit var emailInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getWindow().setBackgroundDrawableResource(R.drawable.assetbackground)

        registerBtnText = findViewById(R.id.registerButtonText)
        loginBtn = findViewById(R.id.signInButton)
        emailInput = findViewById(R.id.input_register_email)

        loginBtn.setOnClickListener() {

            val a = emailInput.text.toString()
            if (a == "coach") {
                val intent = Intent(this, CoachActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            else if(a == "venue"){
                val intent = Intent(this, VenueOwnerActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            else {
                val intent = Intent(this, UserActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }

        registerBtnText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}