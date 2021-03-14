package com.yureka.technology.ytc.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.ActivityMainV2Binding
import com.yureka.technology.ytc.util.base.BaseActivity
import com.yureka.technology.ytc.util.gone
import com.yureka.technology.ytc.util.visible
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainV2Binding>(),
    NavController.OnDestinationChangedListener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var navController: NavController

    override fun getLayoutResource(): Int = R.layout.activity_main_v2

    override fun initViews() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        if(viewModel.isLoggedIn) navController.graph = navController.navInflater.inflate(R.navigation.nav_home_registered)
        else navController.graph = navController.navInflater.inflate(R.navigation.nav_home_unregistered)
        binding.navView.setupWithNavController(navController)

        toast(viewModel.isLoggedIn.toString())
    }

    override fun initObservers() {
        viewModel.weekPassed.observe(this) {

            when {

                it < 1 -> {
                    //todo free user
                }

                it in 2..24 -> {
                    //todo trial must login user
                }

                else -> {
                    // todo must pay user
                }

            }

        }
    }

    override fun initData() = Unit

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

        when (destination.id) {

            R.id.navigation_home, R.id.navigation_dashboard,
            R.id.navigation_notifications, R.id.navigation_profil -> {
                binding.bottomAppBar.visible()
                binding.fab.visible()
            }

            else -> {
                binding.bottomAppBar.gone()
                binding.fab.gone()
            }

        }

    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(this)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(this)
    }
}
