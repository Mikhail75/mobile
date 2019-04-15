package io.github.psgroup.model

class CookModel {

    interface IPresenter {
        fun update(cookingState: CookingState)
    }

    private var mPresenter: IPresenter? = null
    private var mIsCancelled = false
    private var mState: CookingState = CookingState.NotStarted

    fun start(pizza: String) {
    }

    fun stop() {
    }

    fun delete() {
    }

    fun subscribe(presenter: IPresenter) {
        mPresenter = presenter
        mPresenter?.update(mState)
    }

    fun unsubscribe() {
        mPresenter = null
    }

    companion object {
        const val MIN_PROGRESS = 0
        const val MAX_PROGRESS = 100

        private const val PROGRESS_STEP = 20
        private val AVAILABLE_PIZZA = arrayOf(
                "margarita",
                "venezia",
                "salami"
        )
    }

}
