package com.mg.equation.solver

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView3)
        textView.text = Html.fromHtml("A*x<sup><small>2</small></sup> + B * x + C = 0")

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val app = CustomApp.getApplication()
            textView.text = app.test()
        }
    }


}
