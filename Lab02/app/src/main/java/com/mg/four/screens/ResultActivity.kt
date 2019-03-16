package com.mg.four.screens

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent

class ResultActivity : AppCompatActivity() {
    companion object {
        const val RESULT = "com.mg.four.screens.RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val returnResultButton = findViewById<Button>(R.id.returnResultButton)
        returnResultButton.setOnClickListener {
            onReturnResultButtonClick()
        }
    }

    private fun onReturnResultButtonClick() {
        val resultEditText = findViewById<EditText>(R.id.resultEditText)
        val resultIntent = Intent()

        resultIntent.putExtra(RESULT, resultEditText.text.toString())
        setResult(RESULT_OK, resultIntent)

        finish()
    }
}