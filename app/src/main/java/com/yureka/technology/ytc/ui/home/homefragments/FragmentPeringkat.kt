package com.yureka.technology.ytc.ui.home.homefragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.FragmentPeringkatBinding
import com.yureka.technology.ytc.ui.featurex.FeatureXActivity
import com.yureka.technology.ytc.ui.featurey.FeatureYActivity
import com.yureka.technology.ytc.ui.featurey.FeatureYListAdapter
import com.yureka.technology.ytc.ui.featurez.FeatureZActivity
import com.yureka.technology.ytc.ui.peringkat.PeringkatAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class FragmentPeringkat : Fragment() {
    companion object {
        private var atitleStringId: Int = 0
        fun createInstance(titleStringId: Int): FragmentPeringkat {
            atitleStringId = titleStringId
            return FragmentPeringkat()
        }
    }

    lateinit var binding: FragmentPeringkatBinding
    val viewModel: FragmentPeringkatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Got injected fragment's own viewmodel instance: %s.", viewModel)

        // Inflate the layout for this fragment using data binding and set the view model
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_peringkat, container, false)
        binding.viewModel = viewModel

        //set custom toolbar, what you want dude -hrf-
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.apply {
            setTitle(atitleStringId)
        }
        //end of set toolbar

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupIdeaList(binding.recyclerViewPeringkat)
    }

    private fun setupIdeaList(ideaList: RecyclerView) {

        val ideaListAdapter = PeringkatAdapter { data ->
            Timber.d("Item Clicked: $data")
            viewModel.onItemClicked(data)
        }

        ideaList.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = ideaListAdapter
        }

        viewModel.data.observe(viewLifecycleOwner, Observer { result ->
            ideaListAdapter.submitList(result)
        })
    }
}
