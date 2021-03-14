package com.yureka.technology.ytc.ui.featurez

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.DialogFeatureZBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureZDialogFragment : DialogFragment() {
    lateinit var binding: DialogFeatureZBinding
    val viewModel: FeatureZViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_feature_z, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }
}
