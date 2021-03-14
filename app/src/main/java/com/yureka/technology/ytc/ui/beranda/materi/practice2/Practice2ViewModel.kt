package com.yureka.technology.ytc.ui.beranda.materi.practice2

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.ui.beranda.materi.practice2.model.ItemModel
import timber.log.Timber

class Practice2ViewModel @ViewModelInject constructor() : ViewModel() {
    private val _data = MutableLiveData<List<ItemModel>>()
    val data: LiveData<List<ItemModel>> = _data

    private val sampleData = mutableListOf<ItemModel>()

    val question = "Fried Rice"

    private val _isCorrect = MutableLiveData<Boolean>()
    val isCorrect: LiveData<Boolean> = _isCorrect

    init {
        generateSampleDataSet()
        _data.value = sampleData.toList()
    }

    private fun generateSampleDataSet() {
        sampleData.add(
            ItemModel(1, "Breakfast")
        )
        sampleData.add(
            ItemModel(2, "Fried Rice")
        )
        sampleData.add(
            ItemModel(3, "Leaves")
        )
    }

    private fun isCorrect(item: ItemModel) = item.jawaban == question

    fun onItemClicked(item: ItemModel) {
//        sampleData.remove(item)
//        _data.value = sampleData.toList()
//        val selected = _data.value?.filter { it.isSelected == true }
        val list = data.value ?: emptyList()
        list.find { it.isSelected }?.let {
            it.isSelected = !it.isSelected
        }
        list.find { it.id == item.id }?.let {
            it.isSelected = !it.isSelected
        }
        Timber.e(data.value.toString())
        _data.value = list

    }

    fun submit() {

        val selected: ItemModel? = data.value?.find {
            it.isSelected

        }

        if (selected == null) {
            //error
            return
        }

        _isCorrect.value = isCorrect(selected)

    }
//    yureka async
//    fun submit()=viewModelScope.launch{
//
//        val selected: ItemModel? = data.value?.find { it.isSelected }
//
//        if(selected == null) {
//            //error
//            return@launch
//        }
//
//        _isCorrect.postValue(isCorrect(selected))
//
//    }

}