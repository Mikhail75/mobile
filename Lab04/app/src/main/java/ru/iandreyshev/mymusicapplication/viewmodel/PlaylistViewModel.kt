package ru.iandreyshev.mymusicapplication.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import ru.iandreyshev.model.player.Player
import ru.iandreyshev.model.playlist.IPlaylistPresenter
import ru.iandreyshev.model.playlist.ITrack
import ru.iandreyshev.model.playlist.Playlist
import ru.iandreyshev.mymusicapplication.application.IPlayerEventProvider

class PlaylistViewModel(
    private val resources: Resources,
    private val player: Player,
    private val playerEventProvider: IPlayerEventProvider,
    private val playlist: Playlist
) : PlayerViewModel(resources, player, playerEventProvider), IPlaylistPresenter {

    var tracklist = MutableLiveData<List<ITrack>>()

    init {
        playlist.subscribe(this)
    }

    override fun onCleared() {
        super.onCleared()
        playlist.unsubscribe()
    }

    override fun updatePlaylist(value: List<ITrack>) {
        tracklist.value = value
    }
}