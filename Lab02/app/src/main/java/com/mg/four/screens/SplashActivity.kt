package com.mg.four.screens

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000L)
    }
}