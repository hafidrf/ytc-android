package com.yureka.technology.ytc.ui.beranda.materi.practice3

import android.view.LayoutInflater
import android.view.View
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.chip.Chip
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.FragmentPractice3Binding
import com.yureka.technology.ytc.ui.beranda.materi.practice3.fragment.BottomSheetCorrectFragment
import com.yureka.technology.ytc.ui.beranda.materi.practice3.fragment.BottomSheetWrongFragment
import com.yureka.technology.ytc.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Practice3Fragment : BaseFragment<FragmentPractice3Binding>() {
    //    private lateinit var binding: FragmentPractice3Binding
    val viewModel: Practice3ViewModel by viewModels()

    private var answer = ""
    private var soal_id = 0

    override fun getLayoutResource(): Int = R.layout.fragment_practice3

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())
        binding.viewModel = viewModel

    }

    override fun initObservers() {

    }

    override fun initData() {

    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.soal.observe(this, Observer { soals ->
            initQuestion(soals[soal_id].soal)
        })
    }

    private fun initQuestion(soal: String) {
        binding.tvQuestion.text = soal
        val wordsFromSentence: MutableList<String> = soal.split(" ").toMutableList()

        shuffle(wordsFromSentence)

        for ((index, word) in wordsFromSentence.withIndex()) {
            val mChip = LayoutInflater.from(context)
                .inflate(R.layout.item_chip_word_question, null, false) as Chip
            mChip.run {
                text = word
                setPadding(10, 2, 10, 2)
                setOnCheckedChangeListener { chip, isChecked ->
                    if (isChecked) {
                        setAnswer(this, word, index)
                        this.isCheckable = false
                    }
                    setButtonState()
                }
            }
            binding.cgOptions.addView(mChip, index)
        }
        buttonHandler(soal)
    }

    private fun <T> shuffle(list: MutableList<T>) {
        list.shuffle()
    }

    private fun setAnswer(chip: Chip, word: String, index: Int) {
        val mChip = LayoutInflater.from(context)
            .inflate(R.layout.item_chip_word_answer, null, false) as Chip
        mChip.run {
            text = word
            setPadding(10, 2, 10, 2)
            setOnCheckedChangeListener { chip, isChecked ->
                binding.cgAnswer.removeView(this)
                val ann: Chip = binding.cgOptions[index] as Chip
                ann.isCheckable = true
                ann.isChecked = false
            }
        }
        binding.cgAnswer.addView(mChip)
    }

    private fun buttonHandler(soal: String) {
        binding.btnCek.setOnClickListener {
            if (checkAnswer(soal)) {
                val bottomSheet = BottomSheetCorrectFragment.createInstance(answer)
                bottomSheet.show(parentFragmentManager, BottomSheetCorrectFragment.TAG)
            } else {
                val bottomSheet = BottomSheetWrongFragment.createInstance(soal)
                bottomSheet.show(parentFragmentManager, BottomSheetWrongFragment.TAG)
            }
        }
    }

    private fun checkAnswer(soal: String): Boolean {
        answer = ""
        for (index in 0 until binding.cgAnswer.childCount) {
            val chip = binding.cgAnswer.getChildAt(index) as Chip
            answer += chip.text.toString()
            if (index < binding.cgAnswer.childCount - 1) {
                answer += " "
            }
        }
        return answer.equals(soal, true)
    }

    private fun setButtonState() {
        if (binding.cgAnswer.size > 0) {
            binding.btnCek.visibility = View.VISIBLE
            binding.btnMulai.visibility = View.INVISIBLE
        } else {
            binding.btnCek.visibility = View.INVISIBLE
            binding.btnMulai.visibility = View.VISIBLE
        }
    }

    ///////////////

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = DataBindingUtil.setContentView(this, R.layout.fragment_practice3)
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

    }*/

}