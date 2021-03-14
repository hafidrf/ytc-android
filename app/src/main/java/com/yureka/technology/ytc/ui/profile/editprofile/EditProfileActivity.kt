package com.yureka.technology.ytc.ui.profile.editprofile

import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.observe
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutSignUpV2Binding
import com.yureka.technology.ytc.ui.home.MainActivity
import com.yureka.technology.ytc.ui.profile.registration.SignUpViewModel
import com.yureka.technology.ytc.util.*
import com.yureka.technology.ytc.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

@AndroidEntryPoint
class EditProfileActivity : BaseActivity<LayoutSignUpV2Binding>() {

    private val viewModel: SignUpViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.layout_sign_up_v2

    override fun initViews() {
/*
        binding.toolbar.title = "Edit Profil"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }*/

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

    }

    override fun initObservers() {
        viewModel.isEmailValid.observe(this) {
            if (!it) setErrorField(binding.tvLabelNotValidSignUpEmail, binding.edEmailSignUp)
            else setSuccessField(binding.tvLabelNotValidSignUpEmail, binding.edEmailSignUp)
        }

        viewModel.isPasswordValid.observe(this) {
            if (!it) setErrorField(binding.tvLabelNotValidSignUpPassword, binding.edPasswordSignUp)
            else setSuccessField(binding.tvLabelNotValidSignUpPassword, binding.edPasswordSignUp)
        }

        viewModel.allValid.observe(this) {
            binding.btnNext.apply {
                if (it) {
                    enable()
                } else {
                    disable()
                }
            }
            binding.btnNext.setOnClickListener {
                startActivity(
                    intentFor<MainActivity>().clearTask().newTask()
                )
            }
        }
    }

    override fun initData() {

    }


    private fun validate() {
        viewModel.validate(
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