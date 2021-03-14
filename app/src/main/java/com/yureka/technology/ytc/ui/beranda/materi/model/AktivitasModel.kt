package com.yureka.technology.ytc.ui.beranda.materi.model

data class AktivitasModel(
    val id: Int,
    val aktivitas_name: String,
    val aktivitas_desc: String,
    val aktivitas_icon: String,
    val aktivitas_progress: Int,
    var activated: Boolean
)