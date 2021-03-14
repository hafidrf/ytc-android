package com.yureka.technology.ytc.ui.beranda.materi.practice

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.ActivityPracticeBinding
import com.yureka.technology.ytc.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PracticeActivity: BaseActivity<ActivityPracticeBinding>(), NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController

    override fun getLayoutResource(): Int = R.layout.activity_practice

    override fun initViews() {
//        TODO("Not yet implemented")
    }

    override fun initObservers() {
//        TODO("Not yet implemented")
    }

    override fun initData() {
//        TODO("Not yet implemented")
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
//        TODO("Not yet implemented")
    }
}