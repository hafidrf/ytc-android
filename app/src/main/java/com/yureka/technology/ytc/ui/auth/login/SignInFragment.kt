package com.yureka.technology.ytc.ui.auth.login

import android.content.Intent
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutSignInV2Binding
import com.yureka.technology.ytc.ui.home.MainActivity
import com.yureka.technology.ytc.util.*
import com.yureka.technology.ytc.util.base.BaseFragment
import com.yureka.technology.ytc.util.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast
import timber.log.Timber


@AndroidEntryPoint
class SignInFragment : BaseFragment<LayoutSignInV2Binding>() {

    companion object {
        private const val RC_SIGN_IN =1
        const val EXTRA_BARU = "extra_baru"
    }

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    override fun getLayoutResource(): Int = R.layout.layout_sign_in_v2

    override fun initViews() {

        binding.toolbar.setupWithNavController(findNavController())

        binding.btnNext.setOnClickListener {
            viewModel.login(
                userName = binding.edEmailEdit.text.toString(),
                password = binding.edPasswordSignIn.text.toString()
            )
        }

        binding.edEmailEdit.addOnSubmitListener(500) {
            viewModel.validateEmail(it)
        }

        binding.edPasswordSignIn.doAfterTextChanged {
            binding.btnNext.disable()
            if(it.isNullOrEmpty()) {
                setErrorField(binding.tvLabelNotValidKataSandiSignIn, binding.edPasswordSignIn)
            } else {
                setSuccessField(binding.tvLabelNotValidKataSandiSignIn, binding.edPasswordSignIn)
                if(viewModel.isEmailValid.value == true) binding.btnNext.enable()
            }
        }

        binding.buttonSignInGoogle.setOnClickListener {
            sigInWithGoogle()
        }

        binding.tvLabelLupaPasswordSignIn.setOnClickListener {
            findNavController().navigate(
                R.id.action_signInFragment_to_forgotPasswordFragment, bundleOf(
                    EXTRA_BARU to "Hay",
                    "extra_integer" to 4
                )
            )
        }

    }

    override fun initObservers() {

        viewModel.isEmailValid.observe(viewLifecycleOwner){
            if(!it) setErrorField(binding.tvLabelInvalidUsername, binding.edEmailEdit)
            else setSuccessField(binding.tvLabelInvalidUsername, binding.edEmailEdit)
            if(!binding.edPasswordSignIn.text.isNullOrEmpty() && it){
                binding.btnNext.enable()
            }else{
                binding.btnNext.disable()
            }
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    setLoading(false)
                    startActivity(
                        requireContext().intentFor<MainActivity>().clearTask().newTask()
                    )
                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                    requireContext().toast(it.message.toString())
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
            .requestIdToken(getString(R.string.my_google_api_access_key))
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso);

        callbackManager = CallbackManager.Factory.create()

        binding.buttonSignInFacebook.setPermissions("email")
        binding.buttonSignInFacebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                Timber.d("${result?.accessToken?.token}")
                result?.accessToken?.let {
                    requireContext().toast(it.token)
                }
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
                Timber.d(error)
            }

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RC_SIGN_IN -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleGoogleSignInResult(task)
            }
            else->{
            }
        }
    }

    private fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
            val token = account.idToken
            context?.toast("$token")
//            viewModel.loginWithGooggle(account.idToken!!)
        } catch (e: ApiException) {
            Timber.d("signInResult:failed code=${e.statusCode} ${e.message}")
            //todo error
        }

    }


    private fun sigInWithGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent,
            RC_SIGN_IN
        )
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.btnNext.disable()
            binding.loading.visible()
        } else {
            binding.btnNext.enable()
            binding.loading.gone()
        }
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