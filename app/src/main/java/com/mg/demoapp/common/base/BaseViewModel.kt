package com.mg.demoapp.common.base

import android.app.Application
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.IdRes
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.mg.demoapp.R
import com.mg.demoapp.common.utils.Event
import com.mg.demoapp.data.model.Error
import com.mg.demoapp.data.model.ErrorObj
import com.mg.demoapp.data.repository.AppDispatchers
import com.mg.demoapp.data.repository.utils.Resource
import com.mg.demoapp.navigation.NavigationCommand
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    // FOR ERROR HANDLER
    protected val _snackbarError = MutableLiveData<Event<String>>()
    val snackBarError: LiveData<Event<String>> get() = _snackbarError

    protected val _errors = MutableLiveData<Event<ArrayList<ErrorObj>>>()
    val errors: LiveData<Event<ArrayList<ErrorObj>>> get() = _errors

    // FOR NAVIGATION
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    private var status = MediatorLiveData<Resource.Status>()
    private var resource = MediatorLiveData<Resource<Any?>>()
    private val statusObserver: androidx.lifecycle.Observer<Resource.Status> =
        androidx.lifecycle.Observer {}
    private val resourceObserver: Observer<Resource<Any?>> = Observer { }


    init {
        status.observeForever { statusObserver }//statusObserver's onChanged() method won't be  called without active observer
        resource.observeForever(resourceObserver)
    }


    /**
     * Convenient method to handle navigation from a [ViewModel]
     */
    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(directions))
    }

    fun navigate(directions: NavDirections, options: NavOptions? = null) {
        _navigation.value = Event(NavigationCommand.To(directions, options))
    }

    fun popBackStackTo(@IdRes destinationId: Int, inclusive: Boolean) {
        _navigation.value = Event(NavigationCommand.PopBackStackTo(destinationId, inclusive))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }

    fun <T> handleRequest(
        dispatchers: AppDispatchers,
        request: suspend () -> LiveData<Resource<T>>,
        callback: (LiveData<Resource<T>>) -> Unit
    ) {
        viewModelScope.launch(dispatchers.main) {
            val result: LiveData<Resource<T>> = withContext(dispatchers.io) {
                request()
            }
            resource.addSource(Transformations.map(result) { _result -> _result }) { _result ->
                when (_result.status) {
                    Resource.Status.SUCCESS -> {
                        callback(result)
                    }
                    Resource.Status.ERROR -> {
                        handleError(_result)
                    }
                    Resource.Status.LOADING -> {}
                }
                resource.value = _result
            }

            status.addSource(Transformations.map(result) { _result -> _result.status }) { _status ->
                status.value = _status

                //   handleError(_status)
            }

        }
    }

    /** This function returns true if error has occurred **/

    private fun <T> handleError(result: Resource<T>) {
        if (result.status == Resource.Status.ERROR) {
            result.error?.let { error ->
                error.message?.let { message ->
                    _snackbarError.value =
                        Event(message)
                }
                _errors.value = Event(error.errors)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        status.removeObserver(statusObserver)
        resource.removeObserver(resourceObserver)
        coroutineContext.cancel()
    }
}


