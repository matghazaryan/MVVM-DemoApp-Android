package com.mg.demoapp.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mg.demoapp.common.base.BaseViewModel
import com.mg.demoapp.data.model.GithubUser
import com.mg.demoapp.data.repository.AppDispatchers
import com.mg.demoapp.data.repository.utils.Resource
import com.mg.demoapp.ui.home.domain.GetTopUsersUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(app: Application,
                    private val getUsersUseCase: GetTopUsersUseCase,
                    private val dispatchers: AppDispatchers) : BaseViewModel(app) {

    private val _users = MediatorLiveData<Resource<List<GithubUser>>>()
    val users: LiveData<Resource<List<GithubUser>>> get() = _users
    private var userSource: LiveData<Resource<List<GithubUser>>> = MutableLiveData()

    init {
        getUsers()
    }

    // PUBLIC ACTIONS ---
    fun userClicksOnItem(user: GithubUser){}



    fun userRefreshesItems() = getUsers()

    private fun getUsers() = viewModelScope.launch(dispatchers.main){
        _users.removeSource(userSource) // We make sure there is only one source of livedata (allowing us properly refresh)
        withContext(dispatchers.io){userSource = getUsersUseCase()}
        _users.addSource(userSource){
            _users.value = it
        }
    }
}