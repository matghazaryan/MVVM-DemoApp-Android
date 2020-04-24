package com.mg.demoapp.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mg.demoapp.R
import com.mg.demoapp.common.estensions.setupSnackbar
import com.mg.demoapp.navigation.NavigationCommand
import kotlinx.android.synthetic.main.login_fragment.view.*

abstract class BaseFragment: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigation(getViewModel())
        setupSnackbar(this, getViewModel().snackBarError, Snackbar.LENGTH_LONG)
    }

    abstract fun getViewModel(): BaseViewModel

    // UTILS METHODS ---

    /**
     * Observe a [NavigationCommand] [Event] [LiveData].
     * When this [LiveData] is updated, [Fragment] will navigate to its destination
     */
    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> findNavController().navigate(command.directions, getExtras())
                    is NavigationCommand.Back -> findNavController().navigateUp()
                }
            }
        })
    }

    /**
     * [FragmentNavigatorExtras] mainly used to enable Shared Element transition
     */
    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

    open fun setActionBarTitle(title: String = getString(R.string.app_name)){
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

    open fun setActionBarTitle(resID: Int){
        showActionBar()
        setActionBarTitle(getString(resID))
    }

    open fun hideActionBar(){
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    open fun showActionBar(){
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }
}