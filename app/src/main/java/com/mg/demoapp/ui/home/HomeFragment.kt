package com.mg.demoapp.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mg.demoapp.R
import com.mg.demoapp.common.base.BaseFragment
import com.mg.demoapp.common.base.BaseViewModel
import com.mg.demoapp.data.preference.di.Preferences
import com.mg.demoapp.databinding.HomeFragmentBinding
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {


    private val viewModel: HomeViewModel by viewModel()
    private val preferences: Preferences by inject()

    private lateinit var binding: HomeFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()
        println(preferences.getUserName())
    }

    private fun configureRecyclerView() {
        binding.fragmentHomeRv.adapter = HomeAdapter(viewModel)
    }



    override fun getViewModel(): BaseViewModel {
        return  viewModel
    }

}