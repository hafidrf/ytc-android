package com.yureka.technology.ytc.ui.home.homefragments

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.ui.extensions.LiveEvent
import com.yureka.technology.ytc.ui.peringkat.model.ItemModel
import timber.log.Timber

class FragmentPeringkatViewModel @ViewModelInject constructor() : ViewModel() {
    private val _data = MutableLiveData<List<ItemModel>>()
    val data: LiveData<List<ItemModel>> = _data

    private val sampleData = mutableListOf<ItemModel>()

    init {
        generateSampleDataSet()
        _data.value = sampleData.toList()
    }

    private fun generateSampleDataSet() {
//        for (i in 1..10) {
//            sampleData.add(ItemModel(i, "This is sample item with id: $i"))
//        }
        sampleData.add(ItemModel(1,"Eringga Saputra","588","001",
        "ic_circle_ava_1","ava_1"))
        sampleData.add(ItemModel(2,"Theresa Webb","374","002",
            "ic_circle_ava_2","ava_2"))
        sampleData.add(ItemModel(3,"Devon Lane","352","003",
        "ic_circle_ava_3","ava_3"))
        sampleData.add(ItemModel(4,"Brooklyn Simmons","329","004",
        "ic_circle_ava_4","ava_4"))
        sampleData.add(ItemModel(5,"Floyd Miles","228","005",
        "ic_circle_ava_5","ava_5"))
        sampleData.add(ItemModel(6,"Eringga Saputra","588","006",
            "ic_circle_ava_1","ava_1"))
        sampleData.add(ItemModel(7,"Theresa Webb","374","007",
            "ic_circle_ava_2","ava_2"))
        sampleData.add(ItemModel(8,"Devon Lane","352","008",
            "ic_circle_ava_3","ava_3"))
    }

    fun onItemClicked(item: ItemModel) {
        sampleData.remove(item)
        _data.value = sampleData.toList()
    }
}
