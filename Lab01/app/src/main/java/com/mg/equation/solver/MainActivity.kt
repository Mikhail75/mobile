package com.mg.equation.solver

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView3.text = Html.fromHtml("A*x<sup><small>2</small></sup> + B * x + C = 0")
    }
}
