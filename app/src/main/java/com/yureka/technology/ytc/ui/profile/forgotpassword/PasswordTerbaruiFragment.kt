package com.yureka.technology.ytc.ui.profile.forgotpassword

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutKataSandiTerbaruiBinding
import com.yureka.technology.ytc.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordTerbaruiFragment : BaseFragment<LayoutKataSandiTerbaruiBinding>() {

//    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.layout_kata_sandi_terbarui

    override fun initViews() {
        binding.toolbar.setupWithNavController(findNavController())

        binding.btnSelanjutnyaSuccessVerifiedKataSandi.setOnClickListener {
            findNavController().navigate(R.id.action_passwordTerbaruiFragment_to_signInFragment)
        }
    }

    override fun initObservers() {
    }

    override fun initData() = Unit

}