package io.github.psgroup.model

import android.os.AsyncTask
import java.util.concurrent.Executors

class CookModel {

    interface IPresenter {
        fun update(cookingState: CookingState, pizza: String = "")
    }

    inner class OrderPizzaTask : AsyncTask<String, Int, CookingState>() {
        lateinit var mPizza: String

        override fun onPreExecute() {
            super.onPreExecute()
            mState = CookingState.InProgress(MAX_PROGRESS, 0)
            mPresenter?.update(mState)
        }

        override fun doInBackground(vararg params: String?): CookingState? {
            mPizza = params.getOrNull(0) ?: ""
            var progress = MIN_PROGRESS

            if (mPizza !in AVAILABLE_PIZZA) {
                return CookingState.Error(CookingError.INVALID_PIZZA_NAME)
            }

            while (progress <= MAX_PROGRESS) {
                Thread.sleep(500)
                progress += PROGRESS_STEP

                if (isCancelled) return null

                publishProgress(progress)
            }

            return CookingState.Completed
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val progress = values.getOrNull(0) ?: 0
            mState = CookingState.InProgress(MAX_PROGRESS, progress)
            mPresenter?.update(mState, mPizza)
        }

        override fun onPostExecute(result: CookingState?) {
            super.onPostExecute(result)
            mState = result ?: return
            mPresenter?.update(mState, mPizza)
            mTasks.remove(mPizza)
        }
    }

    private var mPresenter: IPresenter? = null
    private var mState: CookingState = CookingState.NotStarted
    private var mTasks: MutableMap<String, AsyncTask<*, *, *>> = mutableMapOf()
    private val mExecutor = Executors.newFixedThreadPool(3)

    fun start(pizza: String) {
        if (!mTasks.containsKey(pizza)) {
            mTasks[pizza] = OrderPizzaTask().executeOnExecutor(mExecutor, pizza)
        }
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

        private const val PROGRESS_STEP = 10
        private val AVAILABLE_PIZZA = arrayOf(
                "margarita",
                "venezia",
                "salami"
        )
    }
}