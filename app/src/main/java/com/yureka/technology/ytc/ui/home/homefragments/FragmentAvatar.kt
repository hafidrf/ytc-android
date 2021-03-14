package com.yureka.technology.ytc.ui.home.homefragments

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.FragmentAvatarBinding
import com.yureka.technology.ytc.ui.featurex.FeatureXActivity
import com.yureka.technology.ytc.ui.featurey.FeatureYActivity
import com.yureka.technology.ytc.ui.featurez.FeatureZActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FragmentAvatar : Fragment() {
    companion object {
        fun createInstance(): FragmentAvatar {
            return FragmentAvatar()
        }
    }

    lateinit var binding: FragmentAvatarBinding
    val viewModel: FragmentAvatarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Got injected fragment's own viewmodel instance: %s.", viewModel)

        // Inflate the layout for this fragment using data binding and set the view model
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_avatar, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigationEvents(viewModel)
    }

    /**
     * TODO: This is an yureka of how LiveData can be used to navigate. Update accordingly.
     */
    private fun observeNavigationEvents(viewModel: FragmentAvatarViewModel) {
        /*viewModel.featureXEvent.observe(viewLifecycleOwner, Observer {
            Timber.i("Launching feature X activity.")
            startActivity(Intent(activity, FeatureXActivity::class.java))
        })

        viewModel.featureYEvent.observe(viewLifecycleOwner, Observer {
            Timber.i("Launching feature Y activity.")
            startActivity(Intent(activity, FeatureYActivity::class.java))
        })

        viewModel.featureZEvent.observe(viewLifecycleOwner, Observer {
            Timber.i("Launching feature Z activity.")
            startActivity(Intent(activity, FeatureZActivity::class.java))
        })*/
    }

}