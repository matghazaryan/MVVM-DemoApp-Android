package com.mg.demoapp.data.repository.ui.login

import androidx.lifecycle.LiveData
import com.mg.demoapp.data.model.User
import com.mg.demoapp.data.remote.service.ui.login.LoginDataSource
import com.mg.demoapp.data.repository.utils.NetworkBoundResource
import com.mg.demoapp.data.repository.utils.Resource
import kotlinx.coroutines.Deferred

interface LoginRepository {
    suspend fun loginAsyncSuccess(): LiveData<Resource<User>>
    suspend fun loginAsyncErrorMessage(): LiveData<Resource<User>>
}

class DefaultLoginRepository(private val loginDataSource: LoginDataSource) : LoginRepository {

    override suspend fun loginAsyncSuccess(): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>() {
            override fun processResponse(response: User) = response
            override suspend fun saveCallResults(items: User) {}
            override fun shouldFetch(data: User?) = true
            override suspend fun loadFromDb() = User()
            override fun createCallAsync(): Deferred<User> = loginDataSource.loginAsyncSuccessAsync()
        }.build().asLiveData()
    }

    override suspend fun loginAsyncErrorMessage(): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>() {
            override fun processResponse(response: User) = response
            override suspend fun saveCallResults(items: User) {}
            override fun shouldFetch(data: User?) = true
            override suspend fun loadFromDb() = User()
            override fun createCallAsync(): Deferred<User> = loginDataSource.loginAsyncErrorMessageAsync()
        }.build().asLiveData()
    }

}