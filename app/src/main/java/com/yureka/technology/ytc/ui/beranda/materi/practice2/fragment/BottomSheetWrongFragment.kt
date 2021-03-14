package com.yureka.technology.ytc.ui.beranda.materi.practice2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.BottomSheetResultWrongBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetWrongFragment : BottomSheetDialogFragment() {

    private var result: String? = ""

    companion object {
        const val TAG = "BottomSheetWrongFragment"
        private const val RESULT = "RESULT"

        fun createInstance(result: String): BottomSheetWrongFragment {
            val args = Bundle()
            args.putString(RESULT, result)
            val fragment = BottomSheetWrongFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding: BottomSheetResultWrongBinding

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
        arguments?.let {
            result = it.getString(RESULT)
        }
        binding.tvAnswer.text = result.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

}