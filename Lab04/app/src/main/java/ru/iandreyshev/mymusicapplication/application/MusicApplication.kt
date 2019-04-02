package ru.iandreyshev.mymusicapplication.application

import android.app.Application
import ru.iandreyshev.model.player.Player
import ru.iandreyshev.model.playlist.Playlist
import ru.iandreyshev.model.repository.Repository
import ru.iandreyshev.mymusicapplication.presenter.PlaylistPresenter
import ru.iandreyshev.mymusicapplication.viewmodel.PlayerViewModelInjector


class MusicApplication : Application() {

    private val mRepository = Repository()
    private val mPlaylist = Playlist(mRepository.getAllSongs(), ::onSelectSong)
    private val mPlayer = Player(this)
    private lateinit var mPlayerViewModelInjector: PlayerViewModelInjector

    override fun onCreate() {
        super.onCreate()

        instance = this
        mPlayerViewModelInjector = PlayerViewModelInjector(instance.resources, instance.mPlayer)
    }

    private fun onSelectSong(songId: Long) {
        val songToPlay = mRepository.getSongById(songId) ?: return
        mPlayer.setSong(songToPlay)
        mPlayer.onPlay()
    }

    companion object {
        private lateinit var instance: MusicApplication

        fun getPlaylistPresenter(): PlaylistPresenter {
            val presenter = PlaylistPresenter()
            instance.mPlaylist.subscribe(presenter)
            return presenter
        }

        fun getPlayerViewModelInjector(): PlayerViewModelInjector {
            return instance.mPlayerViewModelInjector
        }
    }
}
