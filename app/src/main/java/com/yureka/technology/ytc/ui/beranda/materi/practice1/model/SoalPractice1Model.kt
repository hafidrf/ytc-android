package com.yureka.technology.ytc.ui.beranda.materi.practice1.model

data class SoalPractice1Model(
    val id: Int,
    val soal: String,
    val answer: Int,
    val options: List<String>,
    val translate: List<String>,
    val icon: ArrayList<Int>
)