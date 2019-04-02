package ru.iandreyshev.mymusicapplication.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import ru.iandreyshev.model.player.IPlayerPresenter
import ru.iandreyshev.model.player.Player
import ru.iandreyshev.model.player.PlayingState
import ru.iandreyshev.model.player.Timeline
import ru.iandreyshev.mymusicapplication.R

class PlayerViewModel(
    private val resources: Resources,
    private val player: Player
) : ViewModel(), IPlayerPresenter {

    val mTrackTitle = MutableLiveData<String>()
    val mTimeline = MutableLiveData<Timeline>().apply {
        setValue(Timeline(0, 0f))
    }
    val mPlayingState = MutableLiveData<PlayingState>().apply {
        setValue(PlayingState.Disabled)
    }

    fun onPlay() = player.onPlay()
    fun onStop() = player.onStop()
    fun onRestart() = player.onRestart()
    fun onChangeTimePosition(timePercent: Float) = player.onChangeTimelinePosition(timePercent)

    override fun updateTitle(title: String?) {
        mTrackTitle.value = title ?: resources.getString(R.string.player_song_not_selected)
    }

    override fun updateTimeline(timeline: Timeline) {
        mTimeline.value = timeline
    }

    override fun updatePlaying(state: PlayingState) {
        mPlayingState.value = state
    }
}