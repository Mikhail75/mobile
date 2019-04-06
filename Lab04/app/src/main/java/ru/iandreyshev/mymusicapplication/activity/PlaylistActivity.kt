package ru.iandreyshev.mymusicapplication.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_playlist.*
import kotlinx.android.synthetic.main.item_track.view.*
import ru.iandreyshev.model.player.PlayingState
import ru.iandreyshev.model.playlist.ITrack
import ru.iandreyshev.mymusicapplication.R
import ru.iandreyshev.mymusicapplication.application.MusicApplication
import ru.iandreyshev.mymusicapplication.viewmodel.PlaylistViewModel
import ru.iandreyshev.utils.disable
import ru.iandreyshev.utils.enable

class PlaylistActivity : AppCompatActivity() {

    private val mInjector = MusicApplication.getViewModelInjector()
    private lateinit var mPlaylistViewModel: PlaylistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        mPlaylistViewModel = mInjector.getPlaylistViewModel(this)

        mPlaylistViewModel.trackTitle.observe(this, Observer {
            if (it != null) {
                updateTitleView(it)
            }
        })
        mPlaylistViewModel.playingState.observe(this, Observer {
            if (it != null) {
                updatePlayingButtons(it)
            }
        })
        mPlaylistViewModel.tracklist.observe(this, Observer {
            if (it != null) {
                updateTracklist(it)
            }
        })

        initIntroView()
    }

    private fun initIntroView() {
        btnPlay.setBackgroundResource(R.drawable.icon_play)
        btnPlay.setOnClickListener {
            mPlaylistViewModel.onPlay()
        }

        introClickableBackground.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
    }

    private fun updateTitleView(title: String) {
        tvIntroTitle.text = title
    }

    private fun updatePlayingButtons(state: PlayingState) {
        when (state) {
            PlayingState.Disabled -> {
                btnPlay.disable()
                btnPlay.setBackgroundResource(R.drawable.icon_play)
            }
            PlayingState.Idle -> {
                btnPlay.enable()
                btnPlay.setBackgroundResource(R.drawable.icon_play)
            }
            PlayingState.Playing -> {
                btnPlay.enable()
                btnPlay.setBackgroundResource(R.drawable.icon_pause)
            }
            PlayingState.Paused -> {
                btnPlay.enable()
                btnPlay.setBackgroundResource(R.drawable.icon_play)
            }
        }
    }

    private fun updateTracklist(playlist: List<ITrack>) {
        tracksList.removeAllViews()

        playlist.forEach { track ->
            val trackView = layoutInflater.inflate(R.layout.item_track, tracksList, false)
            tracksList.addView(trackView)
            trackView.tvTitle.text = track.title

            trackView.setOnClickListener {
                track.play()
            }
        }
    }

}
