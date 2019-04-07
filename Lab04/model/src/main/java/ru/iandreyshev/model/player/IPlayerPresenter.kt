package ru.iandreyshev.model.player

interface IPlayerPresenter {
    fun updateTitle(title: String?)
    fun updatePoster(poster: Int?)
    fun updateTimeline(timeline: Timeline)
    fun updatePlaying(state: PlayingState)
}
