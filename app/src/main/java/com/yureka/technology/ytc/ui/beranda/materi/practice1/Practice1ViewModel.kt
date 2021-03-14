package com.yureka.technology.ytc.ui.beranda.materi.practice1

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.ui.beranda.materi.practice1.model.SoalPractice1Model
import timber.log.Timber

class Practice1ViewModel @ViewModelInject constructor(sharedPreferences: SharedPreferences) :
    ViewModel() {

    private val _soal = MutableLiveData<List<SoalPractice1Model>>()
    private val _selectedSoal = MutableLiveData<SoalPractice1Model>()
    val soal: LiveData<List<SoalPractice1Model>> = _soal
    val selectedSoal: LiveData<SoalPractice1Model> = _selectedSoal

    private val sampleSoals = mutableListOf<SoalPractice1Model>()

    init {
        Timber.i("Injected shared prefs")
        generateSampleSoal()
        _soal.value = sampleSoals.toList()
    }

    private fun generateSampleSoal() {
        sampleSoals.add(
            SoalPractice1Model(
                0,
                "chair",
                0,
                arrayListOf("chair", "robot"),
                arrayListOf("Kursi", "Robot"),
                arrayListOf(R.drawable.img_chair, R.drawable.img_robot)
            )
        )
    }

}