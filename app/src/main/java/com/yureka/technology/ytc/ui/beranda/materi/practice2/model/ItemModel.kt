package com.yureka.technology.ytc.ui.beranda.materi.practice2.model

/**
 * Sample model class used in list to showcase recycler view.
 */
data class ItemModel(
    val id: Int,
    val jawaban: String,
    var isSelected: Boolean = false
)
