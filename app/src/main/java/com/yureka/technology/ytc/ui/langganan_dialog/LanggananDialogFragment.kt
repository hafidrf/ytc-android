package com.yureka.technology.ytc.ui.langganan_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.DialogLanggananBinding
import com.yureka.technology.ytc.ui.beranda.materi.MateriViewModel

class LanggananDialogFragment: DialogFragment() {

    lateinit var binding: DialogLanggananBinding
    val viewModel: MateriViewModel by viewModels()

    companion object {
        const val TAG = "Langganan Dialog"

        fun createInstance(): LanggananDialogFragment {
            return LanggananDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_langganan, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonHandler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomDialogFragmentStyle)
    }

    private fun buttonHandler() {
        binding.btnBatal.setOnClickListener {
            dismiss()
        }
    }
}