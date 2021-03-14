package com.yureka.technology.ytc.ui.beranda.materi

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.data.model.activity.Activity
import com.yureka.technology.ytc.data.model.week.Week
import com.yureka.technology.ytc.data.repository.CourseRepository
import com.yureka.technology.ytc.data.repository.QuestionRepository
import com.yureka.technology.ytc.ui.beranda.materi.model.AktivitasModel
import com.yureka.technology.ytc.ui.beranda.materi.model.MateriModel
import com.yureka.technology.ytc.ui.beranda.materi.model.UserProgress

class MateriViewModel @ViewModelInject constructor(
    private val courseRepository: CourseRepository,
    private val questionRepository: QuestionRepository
) :
    ViewModel() {

    private val _materi = MutableLiveData<MateriModel>()
    val materi: LiveData<MateriModel> = _materi
//    private val _materi = MutableLiveData<List<Week>>()
//    val materi: LiveData<List<Week>> = _materi

    private val sampleProgress = mutableListOf<UserProgress>()
    private val sampleAktivitas = mutableListOf<AktivitasModel>()
    private val sampleMateri = mutableListOf<MateriModel>()
    private val tempMateri = mutableListOf<Week>()
    private val tempActivities = mutableListOf<Activity>()

    private val _weekId = MutableLiveData<String>()

    val weekResponse = courseRepository.getWeeks()
    val activityResponse = Transformations.switchMap(_weekId) {
        courseRepository.getActivitiesByWeek(it)
    }
    val questionResponse = questionRepository.getQuestionDetails()

        init {
            // Loading
        }

    fun fetchActivity(weekId: String) {
        _weekId.value = weekId
    }

    private fun generateSampleDataSet() {
        sampleAktivitas.clear()
        sampleMateri.clear()
        sampleAktivitas.add(
            AktivitasModel(
                1,
                "1. Kosakata",
                "Melatih kosakata barumu dengan gambar.",
                "ic_aktivitas_books",
                20,
                false
            )
        )
        sampleAktivitas.add(
            AktivitasModel(
                2,
                "2. Mendengarkan",
                "Memilih kata yang kamu dengar.",
                "ic_aktivitas_bicycle",
                10,
                false
            )
        )
        sampleAktivitas.add(
            AktivitasModel(
                3,
                "3. Menyusun Kata",
                "Menyusun kata dengan tepat.",
                "ic_aktivitas_lamp",
                50,
                false
            )
        )
        sampleAktivitas.add(
            AktivitasModel(
                4,
                "4. Merekam Suara",
                "Menjawab soal dengan suara.",
                "ic_aktivitas_microphone",
                0,
                false
            )
        )

        sampleMateri.add(
            MateriModel(
                0,
                1,
                "Greeting and Parting",
                "Greeting and Parting (Salam dan Perpisahan) adalah ungkapan untuk memberi salam atau mengucapkan selamat tinggal kepada teman.",
                "https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_640_3MG.mp4",
                sampleAktivitas
            )
        )

        sampleProgress.add(
            UserProgress(
                1,
                0,
                0
            )
        )
    }

    fun unlockMateri(id: Int) {
        sampleAktivitas.find { it.id == id }?.activated = true
        _materi.value = sampleMateri[0]
    }

    fun getMateriByWeek(week: Int) {
        when (week) {
            1 -> generateSampleDataSet()
            else -> generateSampleDataSet()
        }
        _materi.value = sampleMateri[0]
    }

    fun onItemClick(aktivitas: AktivitasModel) {

    }
}