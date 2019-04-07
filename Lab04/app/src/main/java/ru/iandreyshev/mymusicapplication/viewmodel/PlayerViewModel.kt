package ru.iandreyshev.mymusicapplication.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import ru.iandreyshev.model.player.IPlayerPresenter
import ru.iandreyshev.model.player.Player
import ru.iandreyshev.model.player.PlayingState
import ru.iandreyshev.model.player.Timeline
import ru.iandreyshev.mymusicapplication.R
import ru.iandreyshev.mymusicapplication.application.IPlayerEventProvider

open class PlayerViewModel(
    private val resources: Resources,
    private val player: Player,
    private val playerEventProvider: IPlayerEventProvider
) : ViewModel(), IPlayerPresenter {

    val trackTitle = MutableLiveData<String>().apply {
        setValue(player.title())
    }
    val trackPoster = MutableLiveData<Int>().apply {
        setValue(player.poster())
    }
    val timeline = MutableLiveData<Timeline>().apply {
        setValue(player.timeLine())
    }
    val playingState = MutableLiveData<PlayingState>().apply {
        setValue(player.playingState())
    }

    fun onPlay() = player.onPlay()
    fun onStop() = player.onStop()
    fun onRestart() = player.onRestart()
    fun onChangeTimePosition(timePercent: Float) = player.onChangeTimelinePosition(timePercent)

    init {
        playerEventProvider.subscribe(this)
    }

    override fun onCleared() {
        super.onCleared()
        playerEventProvider.unsubscribe(this)
    }

    override fun updateTitle(title: String?) {
        trackTitle.value = title ?: resources.getString(R.string.player_song_not_selected)
    }

    override fun updatePoster(poster: Int?) {
        trackPoster.value = poster
    }

    override fun updateTimeline(timeline: Timeline) {
        this.timeline.value = timeline
    }

    override fun updatePlaying(state: PlayingState) {
        playingState.value = state
    }
}