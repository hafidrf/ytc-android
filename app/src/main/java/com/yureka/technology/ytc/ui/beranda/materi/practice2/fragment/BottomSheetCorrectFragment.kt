package com.yureka.technology.ytc.ui.beranda.materi.practice4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.BottomSheetResultCorrectBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.random.Random


class BottomSheetCorrectFragment : BottomSheetDialogFragment() {

    private var result: String? = ""

    companion object {
        const val TAG = "BottomSheetCorrectFragment"
        private const val RESULT = "RESULT"

        fun createInstance(result: String): BottomSheetCorrectFragment {
            val args = Bundle()
            args.putString(RESULT, result)
            val fragment = BottomSheetCorrectFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding: BottomSheetResultCorrectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_result_correct,
            container,
            false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val word = getRandomQuote()
        arguments?.let {
            result = it.getString(RESULT)
        }
        binding.tvKeren.text = word
        binding.tvAnswer.text = result.toString()
    }

    fun getRandomQuote(): String {
        val quotes = arrayOf("Keren !","Sempurna !","Hebat !")
        val randomValue = Random.nextInt(quotes.size)
        return quotes[randomValue]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

}