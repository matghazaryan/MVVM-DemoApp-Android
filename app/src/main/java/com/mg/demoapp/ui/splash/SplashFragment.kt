package com.mg.demoapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.mg.demoapp.R
import com.mg.demoapp.common.base.BaseFragment
import com.mg.demoapp.common.base.BaseViewModel
import com.mg.demoapp.common.estensions.withDelay
import com.mg.demoapp.databinding.SplashFragmentBinding
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    // FOR DATA
    private lateinit var binding: SplashFragmentBinding
    private val viewModel : SplashViewModel by viewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        withDelay(3000, viewModel::loadDataWhenActivityStarts)
    }

    override fun getViewModel(): BaseViewModel = viewModel
}