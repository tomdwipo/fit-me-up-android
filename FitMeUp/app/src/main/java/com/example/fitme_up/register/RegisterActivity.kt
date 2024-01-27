package com.example.fitme_up.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitme_up.LoginActivity
import com.example.fitme_up.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        getWindow().setBackgroundDrawableResource(R.drawable.assetbackgroundlight)

        supportFragmentManager.beginTransaction().replace(R.id.frame_register_cont, RegisterFragment()).commit()

    }

    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_register_cont)

        if (currentFragment is RegisterCoachFragment5 && supportFragmentManager.backStackEntryCount > 0) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        if (currentFragment is RegisterUserFragment3 && supportFragmentManager.backStackEntryCount > 0) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        if (currentFragment is RegisterVenueOwnerFragment4 && supportFragmentManager.backStackEntryCount > 0) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        else{
            super.onBackPressed()
        }

    }
}