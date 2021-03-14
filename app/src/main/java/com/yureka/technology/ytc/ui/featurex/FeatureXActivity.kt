package com.yureka.technology.ytc.ui.featurex

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.ActivityFeatureXBinding
import com.yureka.technology.ytc.ui.common.Result
import com.yureka.technology.ytc.ui.extensions.onChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_feature_x.*

/**
 * This activity shows how web service API can be used via ViewModel.
 *
 * @see FeatureXViewModel
 */
@AndroidEntryPoint
class FeatureXActivity : AppCompatActivity() {

    private val viewModel: FeatureXViewModel by viewModels()
    private lateinit var binding: ActivityFeatureXBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feature_x)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        var username: String

        viewModel.message.onChanged { result ->
            when (result) {
                is Result.Success -> {
                    username = result.data.data.get(1).username
                    binding.messageText.text = username
                }
                is Result.Error -> {
                    binding.messageText.text = result.exception.message
                }
            }
        }*/
    }
}
