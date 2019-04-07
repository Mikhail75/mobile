package ru.iandreyshev.mymusicapplication.application

import ru.iandreyshev.model.player.IPlayerPresenter
import ru.iandreyshev.model.player.PlayingState
import ru.iandreyshev.model.player.Timeline

class PlayerEventProvider : IPlayerEventProvider, IPlayerPresenter  {

    private val mObservers = mutableListOf<IPlayerPresenter>()

    // IPlayerEventProvider ----------------------------------------

    override fun subscribe (observer: IPlayerPresenter) {
        if (!mObservers.contains(observer)) {
            mObservers.add(observer)
        }
    }

    override fun unsubscribe (observer: IPlayerPresenter) {
        if (mObservers.contains(observer)) {
            mObservers.remove(observer)
        }
    }

    // IPlayerPresenter --------------------------------------------

    override fun updateTitle(title: String?) {
        mObservers.forEach { it.updateTitle(title) }
    }

    override fun updatePoster(poster: Int?) {
        mObservers.forEach { it.updatePoster(poster) }
    }

    override fun updateTimeline(timeline: Timeline)  {
        mObservers.forEach { it.updateTimeline(timeline) }
    }

    override fun updatePlaying(state: PlayingState) {
        mObservers.forEach { it.updatePlaying(state) }
    }
}