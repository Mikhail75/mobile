package com.mg.equation.solver

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private data class Factors(
        var a: Double,
        var b: Double,
        var c: Double
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView3)
        textView.text = Html.fromHtml("A*x<sup><small>2</small></sup> + B * x + C = 0")

        val button = findViewById<Button>(R.id.button)
        button.text = this.getString(R.string.solve_btn)
        button.setOnClickListener {
            onSolveButtonClick()
        }
    }

    private fun onSolveButtonClick() {
        val factors = getFactors() ?: return
        val result = solve(factors.a, factors.b, factors.c)

        var resultText = "D: " + result.d.toString()
        if (result.d >= 0.0) {
            resultText += "\nX1: " + result.x1.toString()
            if (result.d > 0.0) {
                resultText += "\nX2: " + result.x2.toString()
            }
        }

        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        resultTextView.text = resultText
    }

    private fun getFactors(): Factors? {
        val textA = findViewById<EditText>(R.id.inputA).text
        val textB = findViewById<EditText>(R.id.inputB).text
        val textC = findViewById<EditText>(R.id.inputC).text

        val a = textA.toString().toDoubleOrNull()
        val b = textB.toString().toDoubleOrNull()
        val c = textC.toString().toDoubleOrNull()

        if (a == null || b == null || c == null) {
            val errorMessage = this.getString(R.string.invalid_factors) +
                    (if (a == null) "A " else "") +
                    (if (b == null) "B " else "") +
                    (if (c == null) "C " else "")
            Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
            return null
        }

        if (a == 0.0) {
            Toast.makeText(this@MainActivity, this.getString(R.string.factor_a_cannot_equal_to_0), Toast.LENGTH_SHORT)
                .show()
            return null
        }

        return Factors(a, b, c)
    }
}