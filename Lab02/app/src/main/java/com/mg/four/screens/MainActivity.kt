package com.mg.four.screens

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.content.Intent

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_RESULT = 1
        const val TEXT_FROM_MAIN_SCREEN = "com.mg.four.screens.TEXT_FROM_MAIN_SCREEN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestResultButton = findViewById<Button>(R.id.requestResultButton)
        requestResultButton.setOnClickListener {
            onRequestResultButtonClick()
        }

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            onNextButtonClick()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_RESULT && resultCode == RESULT_OK && data != null) {
            val resultTextView = findViewById<TextView>(R.id.resultTextView)
            val text = data.getStringExtra(ResultActivity.RESULT)

            resultTextView.text = text
        }
    }

    private fun onRequestResultButtonClick() {
        startActivityForResult(Intent(this, ResultActivity::class.java), REQUEST_RESULT)
    }

    private fun onNextButtonClick() {
        val editText = findViewById<EditText>(R.id.editText)
        val intent = Intent(this, NextActivity::class.java)

        intent.putExtra(TEXT_FROM_MAIN_SCREEN, editText.text.toString())
        startActivity(intent)
    }
}