package ru.iandreyshev.mymusicapplication.application

import android.app.Application
import ru.iandreyshev.model.player.Player
import ru.iandreyshev.model.playlist.Playlist
import ru.iandreyshev.model.repository.Repository
import ru.iandreyshev.mymusicapplication.presenter.PlayerPresenter
import ru.iandreyshev.mymusicapplication.presenter.PlaylistPresenter

class MusicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        private lateinit var instance: MusicApplication
    }

}
