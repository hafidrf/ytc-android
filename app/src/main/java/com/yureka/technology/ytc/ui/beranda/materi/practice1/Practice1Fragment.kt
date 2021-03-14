package com.yureka.technology.ytc.ui.beranda.materi.practice1

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.FragmentPractice1Binding
import com.yureka.technology.ytc.ui.beranda.materi.practice1.fragment.BottomSheetCorrectFragment
import com.yureka.technology.ytc.ui.beranda.materi.practice1.fragment.BottomSheetWrongFragment
import com.yureka.technology.ytc.ui.beranda.materi.practice1.model.SoalPractice1Model
import com.yureka.technology.ytc.util.base.BaseFragment

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Locale


@AndroidEntryPoint
class Practice1Fragment : BaseFragment<FragmentPractice1Binding>() {

//    private lateinit var binding: FragmentPractice1Binding
    val viewModel: Practice1ViewModel by viewModels()

    lateinit var mTTS: TextToSpeech

    private var soal_id = 0
    var selected_answer = 0
    override fun getLayoutResource(): Int = R.layout.fragment_practice1

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())
        
        binding.viewModel = viewModel
        buttonHandler()
        initTTS()
    }

    override fun initObservers() {
        observeSoal()
    }

    override fun initData() {

    }

    private fun observeSoal() {
        viewModel.soal.observe(this, Observer { soals ->
            initSoal(soals)
            buttonJawaban(soals[soal_id])
        })
    }

    private fun buttonJawaban(soal: SoalPractice1Model) {
        binding.btnCek.setOnClickListener {
            if (checkAnswer(soal.answer)) {
                val bottomSheet =
                    BottomSheetCorrectFragment.createInstance(soal.soal, soal.icon[selected_answer])
                bottomSheet.show(parentFragmentManager, BottomSheetCorrectFragment.TAG)
            } else {
                val bottomSheet =
                    BottomSheetWrongFragment.createInstance(soal.soal, soal.icon[selected_answer])
                bottomSheet.show(parentFragmentManager, BottomSheetWrongFragment.TAG)
            }
        }
    }

    private fun initSoal(soals: List<SoalPractice1Model>) {
        binding.tvQuestion.text = "Manakah yang merupakan ${soals[soal_id].soal}?"
        binding.imgAnswer1.setImageResource(soals[soal_id].icon[0])
        binding.imgAnswer2.setImageResource(soals[soal_id].icon[1])
        binding.tvLabelAnswer1.text = soals[soal_id].translate[0]
        binding.tvLabelAnswer2.text = soals[soal_id].translate[1]
        buttonAudio(soals[soal_id].options)
    }

    private fun initTTS() {
        mTTS = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale.US
            }
        })
    }

    private fun buttonAudio(answer: List<String>) {
        binding.audioAnswer1.setOnClickListener {
            Timber.d(answer[0])
            val toSpeak = answer[0]
            mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
        }
        binding.audioAnswer2.setOnClickListener {
            Timber.d(answer[1])
            val toSpeak = answer[1]
            mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    private fun buttonHandler() {
        binding.rgAnswer.setOnCheckedChangeListener { radioGroup, i ->
            binding.btnCek.visibility = View.VISIBLE
        }
    }

    private fun checkAnswer(answer: Int): Boolean {
        val rbAnswer1 = binding.rbAnswer1
        val rbAnswer2 = binding.rbAnswer2
        selected_answer = if (rbAnswer1.isChecked) {
            binding.rgAnswer.indexOfChild(rbAnswer1)
        } else {
            binding.rgAnswer.indexOfChild(rbAnswer2)
        }
        Timber.d("Jawabannya : $selected_answer")
        return selected_answer == answer
    }

    ////

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = DataBindingUtil.setContentView(this, R.layout.fragment_practice1)
        binding.viewModel = viewModel

        *//*(this as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (this as AppCompatActivity?)!!.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }*//*

        buttonHandler()
        initTTS()
    }

    override fun onResume() {
        super.onResume()
        observeSoal()
    }*/


}