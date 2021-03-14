package com.yureka.technology.ytc.ui.home.profileregistered

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.FragmentProfileRegisteredBinding
import com.yureka.technology.ytc.ui.home.MainActivity
import com.yureka.technology.ytc.ui.profile.editprofile.EditProfileActivity
import com.yureka.technology.ytc.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.alert
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

/**
 * Created on 11/27/20.
 */

@AndroidEntryPoint
class RegisteredProfileFragment : BaseFragment<FragmentProfileRegisteredBinding>() {

    companion object {
        fun newInstance() =
            RegisteredProfileFragment()
    }

    private val viewModel: RegisteredProfileViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_profile_registered

    override fun initViews() {
        binding.toolbar.title = "Profil"
        (activity as AppCompatActivity?)?.setSupportActionBar(binding.toolbar)

        binding.btnLogout.setOnClickListener {
            activity?.alert {
                message = "Yakin akan keluar?"
                positiveButton("ya") {
                    viewModel.logout()
                }
                negativeButton("Tidak") {
                    it.dismiss()
                }
            }?.show()
        }

        binding.btnEditProfile.setOnClickListener {
            startActivity(
                requireContext().intentFor<EditProfileActivity>().clearTask().newTask()
            )
        }
    }

    override fun initObservers() {
        viewModel.loggedOut.observe(viewLifecycleOwner) {
            startActivity(
                requireContext().intentFor<MainActivity>().clearTask().newTask()
            )
        }
        viewModel.userModel.observe(viewLifecycleOwner) {
            binding.textName.text = "${it.name}"
        }
    }

    override fun initData() = Unit

}