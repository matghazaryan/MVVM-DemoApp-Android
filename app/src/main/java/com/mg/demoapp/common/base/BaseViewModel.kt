package com.mg.demoapp.common.base

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.mg.demoapp.R
import com.mg.demoapp.common.utils.Event
import com.mg.demoapp.data.repository.AppDispatchers
import com.mg.demoapp.data.repository.utils.Resource
import com.mg.demoapp.navigation.NavigationCommand
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel: ViewModel() {

    // FOR ERROR HANDLER
    protected val _snackbarError = MutableLiveData<Event<Int>>()
    val snackBarError: LiveData<Event<Int>> get() = _snackbarError

    // FOR NAVIGATION
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    private var status = MediatorLiveData<Resource.Status>()
    private val statusObserver: androidx.lifecycle.Observer<Resource.Status> =
        androidx.lifecycle.Observer {}

    init {
        status.observeForever { statusObserver }//statusObserver's onChanged() method won't be  called without active observer
    }


    /**
     * Convenient method to handle navigation from a [ViewModel]
     */
    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(directions))
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
            status.addSource(Transformations.map(result) { _result -> _result.status }) { _status ->
                status.value = _status
                handleError(_status)
            }
            callback(result)

        }
    }

    private fun handleError(status: Resource.Status) {
        if (status == Resource.Status.ERROR) _snackbarError.value =
            Event(R.string.an_error_happened)
    }

    override fun onCleared() {
        super.onCleared()
        status.removeObserver(statusObserver)
    }
}

