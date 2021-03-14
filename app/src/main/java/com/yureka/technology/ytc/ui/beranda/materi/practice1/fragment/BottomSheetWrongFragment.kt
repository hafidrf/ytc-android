package com.yureka.technology.ytc.ui.beranda.materi.practice1.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.BottomSheetResultWrongBinding
import com.yureka.technology.ytc.ui.beranda.materi.practice4.Practice4ViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yureka.technology.ytc.ui.beranda.materi.practice1.Practice1CongratActivity
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import kotlin.random.Random

@AndroidEntryPoint
class BottomSheetWrongFragment : BottomSheetDialogFragment() {

    private var result: String? = ""
    private var answer_image: Int? = null

    companion object {
        const val TAG = "BottomSheetWrongFragment"
        private const val RESULT = "RESULT"
        private const val IMAGE = "IMAGE"

        fun createInstance(result: String, image: Int): BottomSheetWrongFragment {
            val args = Bundle()
            args.putString(RESULT, result)
            args.putInt(IMAGE, image)
            val fragment = BottomSheetWrongFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding: BottomSheetResultWrongBinding
    val viewModel: Practice4ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_result_wrong, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val word = getRandomQuote()
        arguments?.let {
            result = it.getString(RESULT)
            answer_image = it.getInt(IMAGE)
        }
        binding.tvSorry.text = word
        binding.tvAnswer.text = "Ini bukan gambar ${result.toString()}"
        answer_image?.let {
            binding.imgAnswer.visibility = View.VISIBLE
            binding.imgAnswer.setImageResource(answer_image!!)
        }
        binding.btnNext?.setOnClickListener {
//            startActivity(Intent(this, Practice1CongratActivity::class.java))
            startActivity(
                requireContext().intentFor<Practice1CongratActivity>().clearTask().newTask()
            )
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
}