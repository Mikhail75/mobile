package ru.iandreyshev.mymusicapplication.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_player.*
import ru.iandreyshev.model.player.PlayingState
import ru.iandreyshev.mymusicapplication.R
import ru.iandreyshev.mymusicapplication.application.MusicApplication
import ru.iandreyshev.mymusicapplication.viewmodel.PlayerViewModel
import ru.iandreyshev.utils.disable
import ru.iandreyshev.utils.enable
import java.text.SimpleDateFormat
import java.util.Date

class PlayerActivity : AppCompatActivity() {

    private val mInjector = MusicApplication.getViewModelInjector()
    private lateinit var mPlayerViewModel: PlayerViewModel
    private var mFormatter = SimpleDateFormat("mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        mPlayerViewModel = mInjector.getPlayerViewModel(this)

        mPlayerViewModel.trackTitle.observe(this, Observer {
            if (it != null) {
                updateTitleView(it)
            }
        })
        mPlayerViewModel.playingState.observe(this, Observer {
            if (it != null) {
                updatePlayingButtons(it)
            }
        })
        mPlayerViewModel.timeline.observe(this, Observer {
            if (it != null) {
                updateTimelineView(it.percent, it.timeInMillis.toString())
            }
        })

        initButtons()
        initTimeline()
    }

    private fun initButtons() {
        btnStop.setBackgroundResource(R.drawable.icon_stop)
        btnStop.setOnClickListener {
            mPlayerViewModel.onStop()
        }

        btnPlay.setBackgroundResource(R.drawable.icon_play)
        btnPlay.setOnClickListener {
            mPlayerViewModel.onPlay()
        }

        btnRestart.setBackgroundResource(R.drawable.icon_restart)
        btnRestart.setOnClickListener {
            mPlayerViewModel.onRestart()
        }
    }

    private fun initTimeline() {
        sbTimeLine.progress = TIMELINE_MIN
        sbTimeLine.max = TIMELINE_MAX
        sbTimeLine.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    mPlayerViewModel.onChangeTimePosition(seekBar.progress.toFloat() / 100)
                }
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) = Unit
            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
        })
    }

    private fun updateTitleView(title: String) {
        tvTitle.text = title
    }

    private fun updateTimelineView(progress: Float, currentTime: String) {
        tvTime.text = mFormatter.format(Date(currentTime.toLong()))
        sbTimeLine.progress = (TIMELINE_MAX * progress).toInt()
    }

    private fun updatePlayingButtons(state: PlayingState?) {
        when (state) {
            PlayingState.Disabled -> {
                btnStop.disable()
                btnPlay.disable()
                btnRestart.disable()
                sbTimeLine.disable()

                btnPlay.setBackgroundResource(R.drawable.icon_play)
            }
            PlayingState.Idle -> {
                btnStop.disable()
                btnPlay.enable()
                btnRestart.disable()
                sbTimeLine.disable()

                btnPlay.setBackgroundResource(R.drawable.icon_play)
            }
            PlayingState.Playing -> {
                btnStop.enable()
                btnPlay.enable()
                btnRestart.enable()
                sbTimeLine.enable()

                btnPlay.setBackgroundResource(R.drawable.icon_pause)
            }
            PlayingState.Paused -> {
                btnStop.enable()
                btnPlay.enable()
                btnRestart.enable()
                sbTimeLine.disable()

                btnPlay.setBackgroundResource(R.drawable.icon_play)
            }
        }
    }

    companion object {
        private const val TIMELINE_MIN = 0
        private const val TIMELINE_MAX = 100
    }
}
