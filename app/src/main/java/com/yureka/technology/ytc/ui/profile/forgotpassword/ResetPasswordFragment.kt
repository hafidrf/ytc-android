package com.yureka.technology.ytc.ui.profile.forgotpassword

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutResetPasswordBinding
import com.yureka.technology.ytc.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : BaseFragment<LayoutResetPasswordBinding>() {

//    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.layout_reset_password

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())

        binding.btnSelanjutnyaResetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_aturUlangPasswordFragment)
        }
    }

    override fun initObservers() {
    }

    override fun initData() = Unit

}