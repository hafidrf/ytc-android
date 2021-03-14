package com.yureka.technology.ytc.ui.beranda.materi.practice.fragment

import androidx.navigation.findNavController
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.BasePracticeFragmentBinding
import com.yureka.technology.ytc.util.base.BaseFragment

class PracticeFragment : BaseFragment<BasePracticeFragmentBinding>() {

    private var id: String? = null

    override fun getLayoutResource(): Int = R.layout.base_practice_fragment

    override fun initViews() {
        id = arguments?.getString("id")
        when (id) {
            "0" -> {
//                    Toast.makeText(this, id.toString(), Toast.LENGTH_LONG).show()
                view?.findNavController()
                    ?.navigate(R.id.action_practiceFragment_to_practice1Fragment)
            }
            "5fad450743f5774511111111" -> {
                view?.findNavController()
                    ?.navigate(R.id.action_practiceFragment_to_practice1Fragment)
            }
            "5fad450743f5774511111112" -> {
                view?.findNavController()
                    ?.navigate(R.id.action_practiceFragment_to_practice2Fragment)
            }
            "5fad450743f5774511111113" -> {
                view?.findNavController()
                    ?.navigate(R.id.action_practiceFragment_to_practice3Fragment)
            }
            "5fad450743f5774511111114" -> {
                view?.findNavController()
                    ?.navigate(R.id.action_practiceFragment_to_practice4Fragment)
            }
            else -> {
//                Toast.makeText(this, "Invalid", Toast.LENGTH_LONG).show()
                view?.findNavController()
                    ?.navigate(R.id.action_practiceFragment_to_practice1Fragment)
            }
        }

    }

    override fun initObservers() {

    }

    override fun initData() {

    }
}