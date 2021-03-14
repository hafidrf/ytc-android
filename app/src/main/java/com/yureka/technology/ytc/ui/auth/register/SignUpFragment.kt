package com.yureka.technology.ytc.ui.auth.register

import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutSignUpV2Binding
import com.yureka.technology.ytc.ui.home.MainActivity
import com.yureka.technology.ytc.util.*
import com.yureka.technology.ytc.util.base.BaseFragment
import com.yureka.technology.ytc.util.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast


@AndroidEntryPoint
class SignUpFragment : BaseFragment<LayoutSignUpV2Binding>() {

    private val viewModel: SignUpViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.layout_sign_up_v2

    override fun initViews() {

        binding.toolbar.setupWithNavController(findNavController())

        binding.edPasswordSignUp.addOnSubmitListener(500) {
            viewModel.validatePassword(it)
        }
        binding.edEmailSignUp.addOnSubmitListener(500) {
            viewModel.validateEmail(it)
        }

        binding.edNameSignUp.addOnSubmitListener(500) {
            validate()
        }

        binding.edPhoneSignUp.addOnSubmitListener(500) {
            validate()
        }

        binding.edUsiaSignUp.addOnSubmitListener(500) {
            validate()
        }

        binding.btnNext.setOnClickListener { submit() }

    }

    override fun initObservers() {
        viewModel.isEmailValid.observe(viewLifecycleOwner) {
            if (!it) setErrorField(binding.tvLabelNotValidSignUpEmail, binding.edEmailSignUp)
            else setSuccessField(binding.tvLabelNotValidSignUpEmail, binding.edEmailSignUp)
        }

        viewModel.isPasswordValid.observe(viewLifecycleOwner) {
            if (!it) setErrorField(binding.tvLabelNotValidSignUpPassword, binding.edPasswordSignUp)
            else setSuccessField(binding.tvLabelNotValidSignUpPassword, binding.edPasswordSignUp)
        }

        viewModel.allValid.observe(viewLifecycleOwner) {
            binding.btnNext.apply {
                if (it) {
                    enable()
                } else {
                    disable()
                }
            }
        }

        viewModel.registerResponse.observe(viewLifecycleOwner){
            binding.btnNext.enable()
            binding.loading.visible()
            when(it.status){
                Resource.Status.LOADING->{
                    binding.loading.gone()
                    binding.btnNext.disable()
                }

                Resource.Status.SUCCESS ->{
                    startActivity(requireContext().intentFor<MainActivity>().clearTask().newTask())
                }

                Resource.Status.ERROR->{
                    requireContext().toast(it.message.toString())
                }

                else->{}

            }

        }
    }

    override fun initData() = Unit


    private fun validate() {
        viewModel.validate(
            name = binding.edNameSignUp.text?.toString() ?: "",
            email = binding.edEmailSignUp.text?.toString() ?: "",
            phone = binding.edPhoneSignUp.text?.toString() ?: "",
            age = binding.edUsiaSignUp.text?.toString() ?: "",
            password = binding.edPasswordSignUp.text?.toString() ?: ""
        )
    }
    private fun submit() {
        viewModel.submmit(
            name = binding.edNameSignUp.text?.toString() ?: "",
            email = binding.edEmailSignUp.text?.toString() ?: "",
            phone = binding.edPhoneSignUp.text?.toString() ?: "",
            age = binding.edUsiaSignUp.text?.toString() ?: "",
            password = binding.edPasswordSignUp.text?.toString() ?: ""
        )
    }


    private fun setErrorField(errorLabel: AppCompatTextView, editText: AppCompatEditText) {
        errorLabel.visible()
        editText.apply {
            setBackgroundResource(R.drawable.rounded_sign_in_error)
            setDrawableIcon(
                rightDrawable = R.drawable.ic_wrong_input
            )
        }
    }

    private fun setSuccessField(errorLabel: AppCompatTextView, editText: AppCompatEditText) {
        errorLabel.gone()
        editText.apply {
            setBackgroundResource(R.drawable.rounded_sign_in_success)
            setDrawableIcon(
                rightDrawable = R.drawable.ic_right_input
            )
        }
    }


}