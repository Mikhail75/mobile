package com.mg.lifecycle.counter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var res = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        res += 1

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = res.toString()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            textView.text = res.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        res += 1
    }

    override fun onResume() {
        super.onResume()
        res += 1
    }

    override fun onPause() {
        super.onPause()
        res -= 1

    }

    override fun onStop() {
        super.onStop()
        res -= 1
    }

    override fun onDestroy() {
        super.onDestroy()
        res -= 1
    }

    override fun onRestart() {
        super.onRestart()
        res += 2
    }
}
