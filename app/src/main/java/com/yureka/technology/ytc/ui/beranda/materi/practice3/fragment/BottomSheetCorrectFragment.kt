package com.yureka.technology.ytc.ui.beranda.materi.practice3.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.BottomSheetResultCorrectBinding
import com.yureka.technology.ytc.ui.beranda.materi.practice4.Practice4ViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast
import kotlin.random.Random

@AndroidEntryPoint
class BottomSheetCorrectFragment : BottomSheetDialogFragment() {

    private var result: String? = ""
    private var action: () -> Unit = {}

    companion object {
        const val TAG = "BottomSheetCorrectFragment"
        private const val RESULT = "RESULT"

        fun createInstance(result: String, onNext: () -> Unit = {}): BottomSheetCorrectFragment {
            val args: Bundle = Bundle()
            args.putString(RESULT, result)
            val fragment = BottomSheetCorrectFragment()
            fragment.arguments = args
            fragment.action = onNext
            return fragment
        }
    }

    lateinit var binding: BottomSheetResultCorrectBinding
    val viewModel: Practice4ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_result_correct,
            container,
            false
        )
        binding.viewModel = viewModel

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
        binding.btnNext.setOnClickListener {
            context?.toast("onclick")
            action.invoke()
        }
    }

    fun getRandomQuote(): String {
        val quotes = arrayOf("Keren !", "Sempurna !", "Hebat !")
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
}