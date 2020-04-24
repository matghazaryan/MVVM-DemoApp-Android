package com.mg.demoapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mg.demoapp.common.base.BaseFragment
import com.mg.demoapp.common.base.BaseViewModel
import com.mg.demoapp.common.constants.MGConstants
import com.mg.demoapp.common.estensions.withDelay
import com.mg.demoapp.databinding.SplashFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    private lateinit var binding: SplashFragmentBinding
    private val viewModel: SplashViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideActionBar()
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        withDelay(MGConstants.splashDelay, viewModel::loadSplashConfigs)
    }

    override fun getViewModel(): BaseViewModel = viewModel
}