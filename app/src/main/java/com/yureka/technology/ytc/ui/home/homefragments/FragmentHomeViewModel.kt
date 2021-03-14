package com.yureka.technology.ytc.ui.home.homefragments

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.ui.beranda.model.SliderModel
import com.yureka.technology.ytc.ui.extensions.LiveEvent
import com.yureka.technology.ytc.ui.peringkat.model.ItemModel
import timber.log.Timber

class FragmentHomeViewModel @ViewModelInject constructor() : ViewModel() {
    private val _data = MutableLiveData<List<SliderModel>>()
    val data: LiveData<List<SliderModel>> = _data

    private val sampleData = mutableListOf<SliderModel>()

    private val _featurePractice3 = LiveEvent<Unit>()
    private val _featurePractice4 = LiveEvent<Unit>()

    val featurePractice3: LiveData<Unit> = _featurePractice3
    val featurePractice4: LiveData<Unit> = _featurePractice4

    init {
        generateSampleDataSet()
        _data.value = sampleData.toList()
    }

    private fun generateSampleDataSet() {
        sampleData.add(
            SliderModel(1,"Minggu 1",
                "Two line dolor urna tempus malesuada cras sit in", R.drawable.temp_img_1)
        )
        sampleData.add(
            SliderModel(2,"Minggu 2",
                "Two line dolor urna tempus malesuada cras sit in", R.drawable.temp_img_2)
        )
        sampleData.add(
            SliderModel(3,"Minggu 3",
                "Two line dolor urna tempus malesuada cras sit in", R.drawable.temp_img_1)
        )
        sampleData.add(
            SliderModel(4,"Minggu 4",
                "Two line dolor urna tempus malesuada cras sit in", R.drawable.temp_img_2)
        )
    }

    fun onItemClicked(item: ItemModel) {
//        sampleData.remove(item)
        _data.value = sampleData.toList()
    }

    fun openPracticeClicked(section: Int) {
        when (section) {
            3 -> _featurePractice3.value = Unit
            4 -> _featurePractice4.value = Unit
        }
    }
}
