package com.yureka.technology.ytc.ui.profile.forgotpassword

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutSuccessVerifiedBinding
import com.yureka.technology.ytc.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationFragment : BaseFragment<LayoutSuccessVerifiedBinding>() {

//    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.layout_success_verified

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())

        binding.btnSelanjutnyaSuccessVerified.setOnClickListener {
            findNavController().navigate(R.id.action_verificationFragment_to_resetPasswordFragment)
        }
    }

    override fun initObservers() {
    }

    override fun initData() = Unit

}