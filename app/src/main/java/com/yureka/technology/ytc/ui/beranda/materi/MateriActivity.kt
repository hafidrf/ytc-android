package com.yureka.technology.ytc.ui.beranda.materi

import android.annotation.SuppressLint
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.MediaController
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.util.Util
import com.google.gson.Gson
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.data.model.activity.Activity
import com.yureka.technology.ytc.data.model.week.Week
import com.yureka.technology.ytc.databinding.ActivityMateriWeekBinding
import com.yureka.technology.ytc.util.base.BaseFragment
import com.yureka.technology.ytc.util.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.exo_playback_control_view.*
import timber.log.Timber

const val STATE_RESUME_WINDOW = "resumeWindow"
const val STATE_RESUME_POSITION = "resumePosition"
const val STATE_PLAYER_FULLSCREEN = "playerFullscreen"
const val STATE_PLAYER_PLAYING = "playerOnPlay"

@AndroidEntryPoint
class MateriActivity : BaseFragment<ActivityMateriWeekBinding>(), Player.EventListener {

    //    lateinit var binding: ActivityMateriWeekBinding
    val viewModel: MateriViewModel by viewModels()

    private lateinit var player: SimpleExoPlayer

    lateinit var mediaController: MediaController
    lateinit var materiListAdapter: MateriAdapter

    private val weekId = 1

    //Exo Player
    private var fullscreenDialog: Dialog? = null
    private var currentWindow = 0
    private var playbackPosition: Long = 0
    private var isFullscreen = false
    private var isPlayerPlaying = false

    override fun getLayoutResource(): Int = R.layout.activity_materi_week

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())

        binding.viewModel = viewModel

        setupMediaController()

        initFullScreenDialog()
        initFullScreenButton()

        initButtonHandler()
    }

    override fun initObservers() {

    }

    override fun initData() {

    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initPlayer()
            binding.videoViewMateri.onResume()
        }
    }

    override fun onResume() {
        super.onResume()
        observeMateri()
        if (Util.SDK_INT <= 23) {
            initPlayer()
            binding.videoViewMateri.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            binding.videoViewMateri.onPause()
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        isPlayerPlaying = player.playWhenReady
        playbackPosition = player.currentPosition
        currentWindow = player.currentWindowIndex
        player.release()
    }

    private fun observeMateri() {
        viewModel.materi.observe(this, Observer { result ->
            /*setupVideoView(result.materi_video)
            setupMateri(result)
            setupList(result.materi_aktivitas)*/
        })

        viewModel.weekResponse.observe(this) { it ->
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Timber.tag("WEEK RESPONSE").d("DATA RECEIVED: ${it.data?.data?.weeks}")
                    it.data?.data.let { data ->
                        viewModel.fetchActivity(data!!.weeks[0].id)
                        setupVideoView(data.weeks[0].video)
                        setupMateri(data.weeks[0])
                    }
                }
                Resource.Status.ERROR -> {
                    Timber.e("ERROR ON RESPONSE ${it.message}")
                }
                else -> {
                }
            }
        }

        viewModel.activityResponse.observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Timber.tag("ACTIVITY RESPONSE").d("DATA RECEIVED: ${it.data?.data?.activities}")
                    it.data?.data.let { data ->
                        setupList(data!!.activities)
                    }
                }
                Resource.Status.ERROR -> {
                    Timber.e("ERROR ON RESPONSE ${it.message}")
                }
                else -> {
                }
            }
        }

        viewModel.questionResponse.observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Timber.d("DATA RECEIVED: ${it.data?.data?.question}")
                    Timber.tag("RESPONSE").d(Gson().toJson(it))
                }
                Resource.Status.ERROR -> {
                    Timber.e("ERROR ON RESPONSE ${it.message}")
                }
                else -> {
                }
            }
        }
    }

    private fun initButtonHandler() {
        binding.btnUnlock.setOnClickListener {
            viewModel.unlockMateri(1)
            viewModel.unlockMateri(2)
            viewModel.unlockMateri(3)
            viewModel.unlockMateri(4)
        }
    }

    private fun clickListener(id: String) {
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.action_materiActivity_to_practiceFragment2, bundle)
        when (id) {
            "0" -> {
//                    Toast.makeText(this, id.toString(), Toast.LENGTH_LONG).show()
            }
            "5fad450743f5774511111111" -> {
//                startActivity(Intent(this, Practice1Activity::class.java))
//                startActivity(Intent(this, PracticeActivity::class.java))
            }
            "5fad450743f5774511111112" -> {
//                startActivity(Intent(this, PracticeActivity::class.java))
//                startActivity(Intent(this, Practice2Activity::class.java))
            }
            "5fad450743f5774511111113" -> {
//                startActivity(Intent(this, PracticeActivity::class.java))
//                startActivity(Intent(this, Practice3Activity::class.java))
            }
            "5fad450743f5774511111114" -> {
//                startActivity(Intent(this, PracticeActivity::class.java))
//                startActivity(Intent(this, Practice4Activity::class.java))
            }
            else -> {
//                Toast.makeText(this, "Invalid", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initPlayer() {
        binding.videoViewMateri.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
        player = SimpleExoPlayer.Builder(requireContext()).build().apply {
            playWhenReady = isPlayerPlaying
            seekTo(currentWindow, playbackPosition)
            prepare()
        }
        binding.videoViewMateri.player = player

        if (isFullscreen) {
            openFullscreenDialog()
        }
    }

    private fun setupMediaController() {
        player = SimpleExoPlayer.Builder(requireContext()).build()
        mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoViewMateri)
    }

    private fun setupMateri(result: Week) {
        binding.tvMateriTitle.text = result.topic
        binding.tvMateriDesc.text = result.desc
    }

    private fun setupVideoView(materiVideo: String) {
        initPlayer()
        val mediaItem = MediaItem.fromUri(Uri.parse(materiVideo))
        player.setMediaItem(mediaItem, false)
        player.addListener(this)
    }

    private fun setupList(aktivitas: List<Activity>) {
        materiListAdapter = MateriAdapter { data ->
            clickListener(data.id)
        }

        binding.recyclerViewMateri.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = materiListAdapter
        }
        materiListAdapter.submitList(aktivitas)
    }

    override fun onPlaybackStateChanged(@Player.State state: Int) {
        super.onPlaybackStateChanged(state)
        when (state) {
            Player.STATE_ENDED -> {
                Timber.d("VIDEO ENDED")
                viewModel.unlockMateri(1)
                viewModel.unlockMateri(2)
                viewModel.unlockMateri(3)
                viewModel.unlockMateri(4)
            }
            else -> {
            }
        }
    }

    // Fullscreen exo player

    private fun initFullScreenDialog() {
        fullscreenDialog =
            object : Dialog(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
                override fun onBackPressed() {
                    if (isFullscreen) closeFullscreenDialog()
                    super.onBackPressed()
                }
            }
    }

    private fun initFullScreenButton() {
        exo_fullscreen.setOnClickListener {
            if (!isFullscreen) {
                openFullscreenDialog()
            } else {
                closeFullscreenDialog()
            }
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun openFullscreenDialog() {
        exo_fullscreen_icon.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_fullscreen_shrink
            )
        )
//        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        (binding.videoViewMateri.parent as ViewGroup).removeView(binding.videoViewMateri)
        isFullscreen = true
        fullscreenDialog?.addContentView(
            binding.videoViewMateri,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        fullscreenDialog?.show()
    }

    private fun closeFullscreenDialog() {
//        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        (binding.videoViewMateri.parent as ViewGroup).removeView(binding.videoViewMateri)
        (binding.mainMediaFrame as FrameLayout).addView(binding.videoViewMateri)
        exo_fullscreen_icon.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_fullscreen_expand
            )
        )
        isFullscreen = false
        fullscreenDialog?.dismiss()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_RESUME_WINDOW, player.currentWindowIndex)
        outState.putLong(STATE_RESUME_POSITION, player.currentPosition)
        outState.putBoolean(STATE_PLAYER_FULLSCREEN, isFullscreen)
        outState.putBoolean(STATE_PLAYER_PLAYING, isPlayerPlaying)
        super.onSaveInstanceState(outState)
    }

    ///////////

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_materi_week)

//        viewModel.getMateriByWeek(weekId)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Minggu $weekId"
        }

        //TODO Fix Fullscreen

        savedInstanceState?.let {
            currentWindow = savedInstanceState.getInt(STATE_RESUME_WINDOW)
            playbackPosition = savedInstanceState.getLong(STATE_RESUME_POSITION)
            isFullscreen = savedInstanceState.getBoolean(STATE_PLAYER_FULLSCREEN)
            isPlayerPlaying = savedInstanceState.getBoolean(STATE_PLAYER_PLAYING)
        }
    }*/

}