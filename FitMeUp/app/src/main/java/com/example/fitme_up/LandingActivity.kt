package com.example.fitme_up

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitme_up.register.RegisterActivity

class LandingActivity : AppCompatActivity() {

    lateinit var loginBtn: Button
    lateinit var registerBtnText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)

        getWindow().setBackgroundDrawableResource(R.drawable.assetbackground)

        loginBtn = findViewById(R.id.buttonLogin)
        registerBtnText = findViewById(R.id.registerButtonText)


        loginBtn.setOnClickListener {
            val intent = Intent (this, LoginActivity::class.java);
            startActivity(intent)
        }

        registerBtnText.setOnClickListener {
            val intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}