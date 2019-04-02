package ru.iandreyshev.mymusicapplication.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.res.Resources
import ru.iandreyshev.model.player.Player
import ru.iandreyshev.mymusicapplication.activity.PlayerActivity

class PlayerViewModelInjector(
    private val resources: Resources,
    private val player: Player
) {

    fun getPlayerViewModel(activity: PlayerActivity): PlayerViewModel {
        val provider = ViewModelProvider(activity, Factory())
        return provider[PlayerViewModel::class.java]
    }

    private inner class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass == PlayerViewModel::class.java) {
                return PlayerViewModel(resources, player) as T
            }
            throw IllegalArgumentException("ViewModel ${modelClass.canonicalName} not supported")
        }
    }
}