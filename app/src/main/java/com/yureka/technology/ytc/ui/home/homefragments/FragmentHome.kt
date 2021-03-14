package com.yureka.technology.ytc.ui.home.homefragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.FragmentHomeBinding
import com.yureka.technology.ytc.ui.beranda.materi.practice4.Practice4Fragment
import com.yureka.technology.ytc.ui.beranda.materi.practice3.Practice3Fragment
import com.yureka.technology.ytc.ui.beranda.SliderAdapter
import com.yureka.technology.ytc.ui.beranda.model.SliderModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

//import kotlinx.technology.synthetic.main.fragment_home.into_tab_layout as pager_tab_layout


/**
 * Demo fragment for tab content.
 *
 * This fragment is different than [FragmentPeringkat] and [FragmentC] in following ways:
 * - This has it's own view model [FragmentHomeViewModel] which is provided via injected factory.
 * - This loads a custom layout to test other activities.
 *
 * TODO: Move the fragment to it's own feature package.
 */
@AndroidEntryPoint
class FragmentHome : Fragment() {
    companion object {
        fun createInstance(): FragmentHome {
            return FragmentHome()
        }
    }

    lateinit var binding: FragmentHomeBinding
    val viewModel: FragmentHomeViewModel by viewModels()

    private lateinit var mSliderModel: ArrayList<SliderModel>
    private lateinit var mSliderAdapter: SliderAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Got injected fragment's own viewmodel instance: %s.", viewModel)

        // Inflate the layout for this fragment using data binding and set the view model
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel

        loadCards()

        binding.viewPageHome.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            @SuppressLint("MissingSuperCall")
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                binding.tvWeek.setText(mSliderModel.get(position).title_week)
                binding.tvWeekDecs.setText(mSliderModel.get(position).decs_week)
            }

            override fun onPageSelected(position: Int) {
            }
        })


        return binding.root
    }

    private fun loadCards() {
        mSliderModel = ArrayList()

        mSliderModel.add(
            SliderModel(1,"Minggu 1",
                "Two line dolor urna tempus malesuada cras sit in", R.drawable.temp_img_1)
        )
        mSliderModel.add(
            SliderModel(2,"Minggu 2",
                "Sed ut perspiciatis unde omnis iste natus error", R.drawable.temp_img_2)
        )
        mSliderModel.add(
            SliderModel(3,"Minggu 3",
                "But I must explain to you how all this mistaken", R.drawable.temp_img_1)
        )
        mSliderModel.add(
            SliderModel(4,"Minggu 4",
                "At vero eos et accusamus et iusto odio dignissimos", R.drawable.temp_img_2)
        )
        mSliderModel.add(
            SliderModel(5,"Minggu 5",
                "On the other hand, we denounce with righteous", R.drawable.temp_img_1)
        )

        mSliderAdapter = SliderAdapter(requireContext() ,mSliderModel, findNavController())

        binding.intoTabLayout.setupWithViewPager(binding.viewPageHome, true)
        binding.tvWeek.setText(mSliderModel.get(0).title_week)
        binding.tvWeekDecs.setText(mSliderModel.get(0).decs_week)

        binding.viewPageHome.adapter = mSliderAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigationEvents(viewModel)
    }

    private fun observeNavigationEvents(viewModel: FragmentHomeViewModel) {
        viewModel.featurePractice3.observe(viewLifecycleOwner, Observer {
            Timber.i("Launching feature X activity.")
            startActivity(Intent(activity, Practice3Fragment::class.java))
        })
        viewModel.featurePractice4.observe(viewLifecycleOwner, Observer {
            Timber.i("Launching feature X activity.")
            startActivity(Intent(activity, Practice4Fragment::class.java))
        })
    }
}
