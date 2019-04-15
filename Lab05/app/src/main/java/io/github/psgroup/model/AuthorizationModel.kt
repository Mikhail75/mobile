package io.github.psgroup.model

class AuthorizationModel {

    interface IPresenter {
        fun waitSignIn(isWait: Boolean)
        fun signInCompleted()
    }

    private var mPresenter: IPresenter? = null
    private var mIsWait = false

    fun signIn(login: String, password: String) {
    }

    fun subscribe(presenter: IPresenter) {
        mPresenter = presenter
        mPresenter?.waitSignIn(mIsWait)
    }

    fun unsubscribe() {
        mPresenter = null
    }

}
