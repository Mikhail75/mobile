package io.github.psgroup.model

import android.os.Handler
import android.os.Looper
import kotlin.concurrent.thread

class CookModel {

    interface IPresenter {
        fun update(cookingState: CookingState)
    }

    private var mPresenter: IPresenter? = null
    private var mIsCancelled = false
    private var mState: CookingState = CookingState.NotStarted

    fun start(pizza: String) {
        // поменять состояние на начальное
        mIsCancelled = false
        mState = CookingState.InProgress(MAX_PROGRESS, 0)
        mPresenter?.update(mState)

        // создать и запустить поток
        thread {
            val handler = Handler(Looper.getMainLooper())
            var progress = MIN_PROGRESS
            Thread.sleep(1000)

            // Проверяем валидность имени пиццы
            if (pizza !in AVAILABLE_PIZZA) {
                handler.post {
                    if (!mIsCancelled) {
                        mState = CookingState.Error(CookingError.INVALID_PIZZA_NAME)
                        mPresenter?.update(mState)
                    }
                }
                return@thread
            }

            while (progress <= MAX_PROGRESS) {
                // Каждый шаг приготовления занимает одну секунду
                Thread.sleep(1000)
                progress += PROGRESS_STEP

                // Если приготовление было отменено, то выходим из цикла
                if (mIsCancelled) {
                    return@thread
                }

                handler.post {
                    if (!mIsCancelled) {
                        mState = CookingState.InProgress(MAX_PROGRESS, progress)
                        mPresenter?.update(mState)
                    }
                }
            }

            handler.post {
                if (!mIsCancelled) {
                    mState = CookingState.Completed
                    mPresenter?.update(mState)
                }
            }
        }
    }

    fun stop() {
        mIsCancelled = true
        mState = CookingState.NotStarted
        mPresenter?.update(mState)
    }

    fun delete() {
        mState = CookingState.NotStarted
        mPresenter?.update(mState)
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
