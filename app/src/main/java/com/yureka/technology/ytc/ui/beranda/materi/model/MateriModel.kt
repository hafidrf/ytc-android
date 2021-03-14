package com.yureka.technology.ytc.ui.beranda.materi.model

data class MateriModel(
    val id: Int,
    val materi_week: Int,
    val materi_name: String,
    val materi_desc: String,
    val materi_video: String,
    val materi_aktivitas: List<AktivitasModel>
)