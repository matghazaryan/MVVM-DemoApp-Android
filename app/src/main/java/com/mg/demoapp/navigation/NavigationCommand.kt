package com.mg.demoapp.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions


/**
 * A simple sealed class to handle more properly
 * navigation from a [ViewModel]
 */
sealed class NavigationCommand {
    data class To(
        val directions: NavDirections,
        val options: NavOptions? = null,
        val closeKeyboard: Boolean = true
    ) : NavigationCommand()

    object Back : NavigationCommand()
    data class PopBackStackTo(@IdRes val destinationId: Int, val inclusive: Boolean) :
        NavigationCommand()

//    data class ToPin(val id: String, val info: Bundle? = null, val args: NavArgs? = null) :
//        NavigationCommand()

}