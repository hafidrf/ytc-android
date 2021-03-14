package com.yureka.technology.ytc.ui.profile.forgotpassword

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutAturUlangKataSandiBinding
import com.yureka.technology.ytc.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AturUlangPasswordFragment : BaseFragment<LayoutAturUlangKataSandiBinding>() {

//    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.layout_atur_ulang_kata_sandi

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())

        binding.btnSelanjutnyaAturUlang.setOnClickListener {
            findNavController().navigate(R.id.action_aturUlangPasswordFragment_to_passwordTerbaruiFragment)
        }
    }

    override fun initObservers() {
    }

    override fun initData() = Unit

}