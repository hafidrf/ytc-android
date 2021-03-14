package com.yureka.technology.ytc.ui.beranda.materi.practice2

import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.ActivityPractice2Binding
import com.yureka.technology.ytc.ui.beranda.materi.practice3.fragment.BottomSheetCorrectFragment
import com.yureka.technology.ytc.ui.beranda.materi.practice3.fragment.BottomSheetWrongFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*


@AndroidEntryPoint
class Practice2Activity : AppCompatActivity() {

    lateinit var mTTS: TextToSpeech

    private val viewModel: Practice2ViewModel by viewModels()
    private lateinit var binding: ActivityPractice2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_practice2)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale.US
            }
        })

        //speak button click
        binding.btnSound.setOnClickListener {
            countdown()
            val toSpeak = viewModel.question
            mTTS.setSpeechRate(0.7f)
            mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,null)
        }

//        binding.btnCekPractice2.  backgroundtint colorPrimary
//        binding.tvCekPractice2   text color  colorSnow

        binding.btnCekPractice2.setOnClickListener {
            viewModel.submit()
        }

        setupList(binding.recyclerViewPractice2Button)
        initObservers()
    }

    fun countdown(){
        object : CountDownTimer(1200, 100) {
            override fun onTick(millisUntilFinished: Long) {
                binding.btnSound.visibility = View.GONE
                binding.lottieSoundWave.visibility = View.VISIBLE
            }

            override fun onFinish() {
                binding.btnSound.visibility = View.VISIBLE
                binding.lottieSoundWave.visibility = View.GONE
            }
        }.start()
    }

    private fun initObservers(){
        viewModel.isCorrect.observe(this, Observer {

            if (it) {
                //todo benar
                val bottomSheet = BottomSheetCorrectFragment.createInstance(viewModel.question)
                bottomSheet.show(supportFragmentManager, BottomSheetCorrectFragment.TAG)
            } else {
                // todo salah
                val bottomSheet = BottomSheetWrongFragment.createInstance(viewModel.question)
                bottomSheet.show(supportFragmentManager, BottomSheetWrongFragment.TAG)
            }

        })
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupList(ideaList: RecyclerView) {

        val ideaListAdapter = Practice2Adapter { data ->
            Timber.d("Item Clicked: $data")
            viewModel.onItemClicked(data)
        }

        ideaList.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = ideaListAdapter
        }

        viewModel.data.observe(this, Observer { result ->
            ideaListAdapter.submitList(result)
            ideaListAdapter.notifyDataSetChanged()
        })

    }
}