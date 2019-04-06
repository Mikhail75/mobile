package ru.iandreyshev.mymusicapplication.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.res.Resources
import ru.iandreyshev.model.player.Player
import ru.iandreyshev.model.playlist.Playlist
import ru.iandreyshev.mymusicapplication.activity.PlayerActivity
import ru.iandreyshev.mymusicapplication.activity.PlaylistActivity
import ru.iandreyshev.mymusicapplication.application.IPlayerEventProvider

class ViewModelInjector(
    private val resources: Resources,
    private val player: Player,
    private val playerEventProvider: IPlayerEventProvider,
    private val playlist: Playlist
) {
    fun getPlayerViewModel(activity: PlayerActivity): PlayerViewModel {
        val viewModelProvider = ViewModelProvider(activity, Factory())
        return viewModelProvider[PlayerViewModel::class.java]
    }

    fun getPlaylistViewModel(activity: PlaylistActivity): PlaylistViewModel {
        val viewModelProvider = ViewModelProvider(activity, Factory())
        return viewModelProvider[PlaylistViewModel::class.java]
    }

    private inner class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass == PlayerViewModel::class.java) {
                return PlayerViewModel(resources, player, playerEventProvider) as T
            }
            else if (modelClass == PlaylistViewModel::class.java) {
                return PlaylistViewModel(resources, player, playerEventProvider, playlist) as T
            }

            throw IllegalArgumentException("ViewModel ${modelClass.canonicalName} not supported")
        }
    }
}