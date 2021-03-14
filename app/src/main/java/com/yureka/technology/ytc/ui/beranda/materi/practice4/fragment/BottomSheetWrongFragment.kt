package com.yureka.technology.ytc.ui.beranda.materi.practice4.fragment

import android.app.Dialog
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.text.bold
import androidx.databinding.DataBindingUtil
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.BsResultWrongBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import kotlin.random.Random


class BottomSheetWrongFragment : BottomSheetDialogFragment() {

    private var result: String? = ""
    private var correctAnswer: String? = ""

    private var customAnswer: SpannableStringBuilder? = null

    lateinit var mTTS: TextToSpeech

    companion object {
        const val TAG = "BottomSheetWrongFragment"
        private const val RESULT = "RESULT"
        private const val CORRECT_ANSWER = "CORRECT_ANSWER"

        fun createInstance(result: String, correctAnswer: String): BottomSheetWrongFragment {
            val args = Bundle()
            args.putString(RESULT, result)
            args.putString(CORRECT_ANSWER, correctAnswer)
            val fragment = BottomSheetWrongFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding: BsResultWrongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.bs_result_wrong, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val word = getRandomQuote()
        arguments?.let {
            result = it.getString(RESULT)
            correctAnswer = it.getString(CORRECT_ANSWER)
        }
        customAnswer = SpannableStringBuilder()
        initTTS()
        checkAnswer()
        binding.tvSorry.text = word
        binding.tvAnswer.text = customAnswer
        binding.btnAudio.setOnClickListener {
            mTTS.speak(correctAnswer, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    fun getRandomQuote(): String {
        val quotes = arrayOf("Yah salah","Kurang tepat nih","Belum benar")
        val randomValue = Random.nextInt(quotes.size)
        return quotes[randomValue]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog =
            super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }

    private fun initTTS() {
        mTTS = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale.US
            }
        })
    }

    private fun checkAnswer() {
        val splitAnswer = result?.split(" ")
        val splitCorrectAnswer = correctAnswer?.split(" ")

        splitCorrectAnswer?.forEachIndexed { index, item ->
            if (index <= splitAnswer?.lastIndex!!) {
                if (item.equals(splitAnswer[index], true)) {
                    customAnswer?.append(item)
                } else {
                    customAnswer?.bold { append(item) }
                }
            } else {
                customAnswer?.bold { append(item) }
            }
            if (index < splitCorrectAnswer.size - 1) {
                customAnswer?.append(" ")
            }
        }
    }
}