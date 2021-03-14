package com.yureka.technology.ytc.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yureka.technology.ytc.ui.home.homefragments.FragmentAvatar
import com.yureka.technology.ytc.ui.home.homefragments.FragmentHome
import com.yureka.technology.ytc.ui.home.homefragments.FragmentPeringkat
import com.yureka.technology.ytc.ui.home.homefragments.FragmentProfil
import com.yureka.technology.ytc.ui.home.profileregistered.RegisteredProfileFragment

class HomePagerAdapter(
    fragmentActivity: FragmentActivity, private val isLoggedIn: Boolean = false
) : FragmentStateAdapter(fragmentActivity) {
    companion object {
        private val TABS = arrayListOf(
            Screen.BERANDA,
            Screen.PERINGKAT,
            Screen.AVATAR,
            Screen.PROFIL
        )
    }

    override fun getItemCount(): Int {
        return TABS.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (TABS[position]) {
            Screen.BERANDA -> {
                FragmentHome.createInstance()
            }
            Screen.PERINGKAT -> {
                FragmentPeringkat.createInstance(Screen.PERINGKAT.titleStringId)
            }
            Screen.AVATAR -> {
                FragmentAvatar.createInstance()
            }
            Screen.PROFIL -> {
                if (isLoggedIn) RegisteredProfileFragment.newInstance()
                else FragmentProfil.createInstance(Screen.PROFIL.titleStringId)
            }
        }
    }
}
