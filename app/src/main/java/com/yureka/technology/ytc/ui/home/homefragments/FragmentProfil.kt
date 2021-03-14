package com.yureka.technology.ytc.ui.home.homefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.FragmentProfileUnregisteredBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FragmentProfil : Fragment() {
    companion object {
        private var atitleStringId: Int = 0
        fun createInstance(titleStringId: Int): FragmentProfil {
            atitleStringId = titleStringId
            return FragmentProfil()
        }
    }

    lateinit var binding: FragmentProfileUnregisteredBinding
    val viewModel: FragmentProfilViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Got injected fragment's own viewmodel instance: %s.", viewModel)

        // Inflate the layout for this fragment using data binding and set the view model
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_profile_unregistered,
                container,
                false
            )
        binding.viewModel = viewModel

        //set custom toolbar, what you want dude -hrf-
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.apply {
            setTitle(atitleStringId)
        }
        //end of set toolbar


        observeNavigationEvents(viewModel)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun observeNavigationEvents(viewModel: FragmentProfilViewModel) {
        viewModel.featureRegistEvent.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_navigation_profil_to_signUpFragment)
        })
        viewModel.featureSignEvent.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_navigation_profil_to_signInFragment)
        })
    }

}