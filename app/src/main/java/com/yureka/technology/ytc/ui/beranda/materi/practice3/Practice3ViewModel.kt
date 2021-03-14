package com.yureka.technology.ytc.ui.beranda.materi.practice3

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.ui.beranda.materi.practice4.fragment.SoalPractice3Model

class Practice3ViewModel @ViewModelInject constructor(sharedPreferences: SharedPreferences) :
    ViewModel() {

    private val _soal = MutableLiveData<List<SoalPractice3Model>>()

    val soal: LiveData<List<SoalPractice3Model>> = _soal

    private val sampleSoals = mutableListOf<SoalPractice3Model>()

    init {
        generateSampleSoal()
        _soal.value = sampleSoals.toList()
    }

    private fun generateSampleSoal(){
        sampleSoals.add(
            SoalPractice3Model(
                0,
                "What do you mean"
            )
        )
        sampleSoals.add(
            SoalPractice3Model(
                1,
                "I eat apple in the morning"
            )
        )
    }
}