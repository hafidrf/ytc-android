package com.yureka.technology.ytc.ui.beranda.materi.practice2

import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.ActivityPractice2Binding
import com.yureka.technology.ytc.ui.beranda.materi.practice1.Practice1CongratActivity
import com.yureka.technology.ytc.ui.beranda.materi.practice3.fragment.BottomSheetCorrectFragment
import com.yureka.technology.ytc.ui.beranda.materi.practice3.fragment.BottomSheetWrongFragment
import com.yureka.technology.ytc.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.intentFor
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class Practice2Fragment : BaseFragment<ActivityPractice2Binding>(){

    private val viewModel: Practice2ViewModel by viewModels()
    lateinit var mTTS: TextToSpeech

    override fun getLayoutResource(): Int = R.layout.activity_practice2

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())

        binding.viewModel = viewModel

        textToSpeech()
        setupList(binding.recyclerViewPractice2Button)
    }

    override fun initObservers() {
        viewModel.isCorrect.observe(this, Observer {
            if (it) {
                //todo benar
                val bottomSheet = BottomSheetCorrectFragment.createInstance(viewModel.question) {
                    startActivity(context?.intentFor<Practice1CongratActivity>())
                }
                bottomSheet.show(parentFragmentManager, BottomSheetCorrectFragment.TAG)
            } else {
                // todo salah
                val bottomSheet = BottomSheetWrongFragment.createInstance(viewModel.question)
                bottomSheet.show(parentFragmentManager, BottomSheetWrongFragment.TAG)
            }

        })
    }

    override fun initData() {
    }

    fun textToSpeech(){
        mTTS = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
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
        binding.btnCekPractice2.setOnClickListener {
            viewModel.submit()
        }
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