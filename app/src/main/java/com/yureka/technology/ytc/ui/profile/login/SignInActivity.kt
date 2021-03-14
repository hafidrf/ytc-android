package com.yureka.technology.ytc.ui.profile.login

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.observe
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutSignInV2Binding
import com.yureka.technology.ytc.ui.home.MainActivity
import com.yureka.technology.ytc.ui.profile.forgotpassword.ForgotPasswordFragment
import com.yureka.technology.ytc.util.*
import com.yureka.technology.ytc.util.base.BaseActivity
import com.yureka.technology.ytc.util.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast
import timber.log.Timber


@AndroidEntryPoint
class SignInActivity : BaseActivity<LayoutSignInV2Binding>() {

    companion object {
        private const val RC_SIGN_IN = 1
    }

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun getLayoutResource(): Int = R.layout.layout_sign_in_v2

    override fun initViews() {
        /* binding.toolbar.title = "Masuk"
         setSupportActionBar(binding.toolbar)
         supportActionBar?.apply {
             setDisplayHomeAsUpEnabled(true)
         }*/

        binding.btnNext.setOnClickListener {
            viewModel.login(
                userName = binding.edEmailEdit.text.toString(),
                password = binding.edPasswordSignIn.text.toString()
            )
        }

        binding.edEmailEdit.doAfterTextChanged {
            Timber.d("ping et!")
            if (!binding.edPasswordSignIn.text.isNullOrBlank() && !it.isNullOrBlank())
                toggleButton(true)
            else toggleButton(false)
        }

        binding.edPasswordSignIn.doAfterTextChanged {
            if (!binding.edEmailEdit.text.isNullOrBlank() && !it.isNullOrBlank()) toggleButton(true)
            else toggleButton(false)
        }

//        binding.btnGoogleSignIn.setOnClickListener {
//            sigInWithGoogle()
//        }

        binding.tvLabelLupaPasswordSignIn.setOnClickListener {
//            startActivity(
//                intentFor<ForgotPasswordActivity>().clearTask().newTask()
//            )
            startActivity(Intent(this, ForgotPasswordFragment::class.java))
        }

    }

    override fun initObservers() {

        viewModel.loginResponse.observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    setLoading(false)
                    startActivity(
                        intentFor<MainActivity>().clearTask().newTask()
                    )
                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                    toast(it.message.toString())
                }

                Resource.Status.LOADING -> {
                    setLoading(true)
                }

                else -> {
                }
            }
        }

    }

    override fun initData() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("token") //todo replace with server token
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RC_SIGN_IN -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleGoogleSignInResult(task)
            }
        }
    }

    private fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
            viewModel.loginWithGooggle(account.idToken!!)
        } catch (e: ApiException) {
            Timber.d("signInResult:failed code=${e.statusCode}")
            //todo error
        }

    }


    private fun sigInWithGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,
            RC_SIGN_IN
        )
    }


    private fun toggleButton(enabled: Boolean) {
//        val context: Context = binding.btnNext.context
//        val id: Int = context.resources.getIdentifier("button_state_selector", "drawable", context.packageName)
//
//        if (enabled) {
//            binding.btnNext.apply {
////                setCardBackgroundColor(getCompatColor(R.color.colorPrimary))
//                setBackgroundResource(id)
//                enable()
//            }
//            binding.tvSelanjutnyaSignIn.setTextColor(getCompatColor(R.color.colorWhite))
//        } else {
//            binding.btnNext.apply {
//                setCardBackgroundColor(getCompatColor(R.color.colorButtonDisabled))
//                disable()
//            }
//            binding.tvSelanjutnyaSignIn.setTextColor(getCompatColor(R.color.exo_gray))
//
//        }
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            toggleButton(false)
            binding.loading.visible()
        } else {
            toggleButton(true)
            binding.loading.gone()
        }
    }

}