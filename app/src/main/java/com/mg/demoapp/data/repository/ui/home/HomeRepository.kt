package com.mg.demoapp.data.repository.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.mg.demoapp.data.local.dao.HomeDao
import com.mg.demoapp.data.model.Error
import com.mg.demoapp.data.model.GithubUser
import com.mg.demoapp.data.remote.service.ui.home.HomeDataSource
import com.mg.demoapp.data.repository.utils.Resource
import java.io.IOException

interface HomeRepository {
    suspend fun getUsersWithCache(): LiveData<Resource<List<GithubUser>>>
//    suspend fun getUserDetailWithCache(login: String): LiveData<Resource<GithubUser>>

}


class DefaultHomeRepository(private val dataSource: HomeDataSource, private val homeDao: HomeDao): HomeRepository {
    override suspend fun getUsersWithCache(): LiveData<Resource<List<GithubUser>>>  =
        liveData {
            val disposable = emitSource(
                homeDao.getTopUsers().map {
                Resource.loading(it)
            })
            try {
                val user = dataSource.fetchTopUsersAsync()
                // Stop the previous emission to avoid dispatching the updated user
                // as `loading`.
                disposable.dispose()
                // Update the database.
                homeDao.save(user.data)
                // Re-establish the emission with success type.
                emitSource(
                    homeDao.getTopUsers().map {
                        Resource.success(it)
                    }
                )
            }catch (exception: IOException) {
                // Any call to `emit` disposes the previous one automatically so we don't
                // need to dispose it here as we didn't get an updated value.
                emitSource(
                    homeDao.getTopUsers().map {
                        val error =  Error()
                        error.message = exception.message
                        Resource.error(error,it)
                    }
                )
            }
        }


//    override suspend fun getUserDetailWithCache(login: String): LiveData<Resource<GithubUser>> {
//
//    }

}