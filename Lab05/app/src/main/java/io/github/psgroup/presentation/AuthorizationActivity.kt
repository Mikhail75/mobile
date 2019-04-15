package io.github.psgroup.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.psgroup.R
import io.github.psgroup.application.PizzaMakerApplication
import io.github.psgroup.model.AuthorizationModel
import kotlinx.android.synthetic.main.activity_authorization.*

class AuthorizationActivity : AppCompatActivity(), AuthorizationModel.IPresenter {

    private val mModel by lazy { PizzaMakerApplication.authorizationModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        button.setOnClickListener {
            // TODO: Добавить вызов логинации
        }
    }

    override fun waitSignIn(isWait: Boolean) {
        // TODO: Добавить блокировку кнопки авторизации
    }

    override fun signInCompleted() {
        // TODO: Добавить переход на экран приготовления
    }

    override fun onResume() {
        super.onResume()
        mModel.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        mModel.unsubscribe()
    }

}
