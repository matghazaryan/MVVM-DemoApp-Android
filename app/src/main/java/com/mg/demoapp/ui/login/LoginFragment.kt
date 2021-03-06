package com.mg.demoapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mg.demoapp.R
import com.mg.demoapp.common.base.BaseFragment
import com.mg.demoapp.common.base.BaseViewModel
import com.mg.demoapp.common.estensions.click
import com.mg.demoapp.common.estensions.value
import com.mg.demoapp.data.preference.di.Preferences
import com.mg.demoapp.databinding.LoginFragmentBinding
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setActionBarTitle(R.string.login)
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.etLogin.tag = "username"
        binding.etPassword.tag = "password"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fetchSplashDataFromDatabase()
        handleLoginButtonClick()
        observeFieldErrors()
    }

    private fun fetchSplashDataFromDatabase() {
        viewModel.splash.observe(viewLifecycleOwner, Observer {
            textView.text = it.currency
        })
    }

    private fun handleLoginButtonClick() {
        btnLogin.click {
            viewModel.chooseLoginType(etLogin.value, etPassword.value)
        }
    }

    private fun observeFieldErrors() {
        viewModel.errors.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { errors ->
                errors.find { error -> error.field_name == binding.etLogin.tag }
                    ?.let { error -> binding.etLogin.error = error.error_message }
                errors.find { error -> error.field_name == binding.etPassword.tag }
                    ?.let { error -> binding.etPassword.error = error.error_message }
            }
        })
    }
}