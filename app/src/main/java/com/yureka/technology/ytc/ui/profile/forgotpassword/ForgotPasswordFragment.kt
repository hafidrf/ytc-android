package com.yureka.technology.ytc.ui.profile.forgotpassword

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutForgotPasswordBinding
import com.yureka.technology.ytc.ui.auth.login.SignInFragment
import com.yureka.technology.ytc.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast


@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<LayoutForgotPasswordBinding>() {

//    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.layout_forgot_password

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())


        binding.btnSelanjutnyaForgot.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_verificationFragment)
        }
    }

    override fun initObservers() {

    }

    override fun initData() {

        arguments?.getString(SignInFragment.EXTRA_BARU)?.let {
            context?.toast(it)
        }

    }

}