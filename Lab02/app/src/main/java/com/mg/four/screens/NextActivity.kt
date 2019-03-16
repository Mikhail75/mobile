package com.mg.four.screens

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        val textFromMainTextView = findViewById<TextView>(R.id.textFromMainTextView)
        textFromMainTextView.text = intent.getStringExtra(MainActivity.TEXT_FROM_MAIN_SCREEN)

        val returnButton = findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener {
            finish()
        }
    }
}