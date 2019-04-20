package io.github.psgroup.presentation

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import io.github.psgroup.R
import io.github.psgroup.application.PizzaMakerApplication
import io.github.psgroup.model.CookModel
import io.github.psgroup.model.CookingState
import kotlinx.android.synthetic.main.activity_triple_execute.*

class TripleExecuteActivity : AppCompatActivity(), CookModel.IPresenter {

    private val mModel by lazy { PizzaMakerApplication.cookModel }
    private val buttonToPizzaMap: MutableMap<Button, String> = mutableMapOf()
    private val buttonToProgressBarMap: MutableMap<Button, ProgressBar> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_triple_execute)

        initButtonToPizzaMap()
        initButtonToProgressBarMap()
        initButtonOnClickListeners()
    }

    override fun update(cookingState: CookingState, pizza: String) {
        var button: Button? = null
        for ((key, value) in buttonToPizzaMap)
        {
            if (value == pizza) {
                button = key
                break
            }
        }

        val progressBar: ProgressBar? = buttonToProgressBarMap[button]

        if (button == null || progressBar == null) {
            return
        }

        when (cookingState) {
            CookingState.NotStarted -> {
                progressBar.max = 0
                progressBar.progress = 0
                button.isEnabled = true
             }
            is CookingState.InProgress -> {
                progressBar.max = cookingState.max
                progressBar.progress = cookingState.current
                button.isEnabled = false
                button.setBackgroundColor(Color.GRAY)
            }
            is CookingState.Completed -> {
                progressBar.max = CookModel.MAX_PROGRESS
                progressBar.progress = CookModel.MAX_PROGRESS
                button.isEnabled = true
                button.setBackgroundColor(Color.rgb(63, 81, 181))
            }
            is CookingState.Error -> {
                progressBar.max = 0
                progressBar.progress = 0
                button.isEnabled = false
                button.setBackgroundColor(Color.GRAY)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mModel.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        mModel.unsubscribe()
    }

    private fun initButtonToPizzaMap() {
        buttonToPizzaMap[buttonFirst] = "margarita"
        buttonToPizzaMap[buttonSecond] = "venezia"
        buttonToPizzaMap[buttonThird] = "salami"
    }

    private fun initButtonToProgressBarMap() {
        buttonToProgressBarMap[buttonFirst] = progressBarFirst
        buttonToProgressBarMap[buttonSecond] = progressBarSecond
        buttonToProgressBarMap[buttonThird] = progressBarThird
    }

    private fun initButtonOnClickListeners() {
        setTaskButtonOnClickListener(buttonFirst)
        setTaskButtonOnClickListener(buttonSecond)
        setTaskButtonOnClickListener(buttonThird)

        buttonReset.setOnClickListener {
            progressBarFirst.progress = 0
            progressBarSecond.progress = 0
            progressBarThird.progress = 0
        }
    }

    private fun setTaskButtonOnClickListener(button: Button) {
        button.setOnClickListener {
            mModel.start(buttonToPizzaMap[button]!!)
        }
    }
}