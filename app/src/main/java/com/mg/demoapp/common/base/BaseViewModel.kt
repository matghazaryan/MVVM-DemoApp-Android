package com.mg.demoapp.common.base

import android.app.Application
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.mg.demoapp.R
import com.mg.demoapp.common.utils.Event
import com.mg.demoapp.data.model.Error
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
    protected val _snackbarError = MutableLiveData<Event<Int>>()
    val snackBarError: LiveData<Event<Int>> get() = _snackbarError

    // FOR NAVIGATION
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    private var status = MediatorLiveData<Resource.Status>()
    private var resource = MediatorLiveData<Resource<Error>>()
    private val statusObserver: androidx.lifecycle.Observer<Resource.Status> =
        androidx.lifecycle.Observer {}
    private val resourceObserver: Observer<Resource<Error>> = Observer { }

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

    fun <T> handleRequest(
        dispatchers: AppDispatchers,
        request: suspend () -> LiveData<Resource<T>>,
        callback: (LiveData<Resource<T>>) -> Unit
    ) where T : com.mg.demoapp.data.model.Error {
        viewModelScope.launch(dispatchers.main) {
            val result: LiveData<Resource<T>> = withContext(dispatchers.io) {
                request()
            }
            resource.addSource(Transformations.map(result) { _result -> _result }) { _result ->
                resource.value = _result
                if(_result.status!= Resource.Status.LOADING) {
                    if (!handleError(_result)) {
                        callback(result)
                    }
                }
            }

            status.addSource(Transformations.map(result) { _result -> _result.status }) { _status ->
                status.value = _status

                //   handleError(_status)
            }

        }
    }

    /** This function returns true if error has occurred **/

    private fun <T> handleError(result: Resource<T>): Boolean where T : com.mg.demoapp.data.model.Error {
        when {
            (result.status == Resource.Status.ERROR) -> {
                _snackbarError.value =
                    Event(R.string.an_error_happened)
                return true
            }
            (result.status == Resource.Status.SUCCESS) -> {
                result.data?.let { data ->
                    if (!data.success!!) {
                        Log.d("----", "error message:${data.status_message}")
                        _snackbarError.value =
                            Event(R.string.an_error_happened)
                        return true
                    }
                }
            }
        }
        return false
    }


//    private fun handleError(status: Resource.Status) {
//        if (status == Resource.Status.ERROR) _snackbarError.value =
//            Event(R.string.an_error_happened)
//    }

    override fun onCleared() {
        super.onCleared()
        status.removeObserver(statusObserver)
        resource.removeObserver(resourceObserver)
        coroutineContext.cancel()
    }
}


