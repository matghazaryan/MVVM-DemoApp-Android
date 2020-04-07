package com.mg.demoapp.data.repository.ui.splash

import androidx.lifecycle.LiveData
import com.mg.demoapp.data.local.dao.SplashDao
import com.mg.demoapp.data.model.Splash
import com.mg.demoapp.data.remote.service.ui.splash.SplashDataSource
import com.mg.demoapp.data.repository.utils.NetworkBoundResource
import com.mg.demoapp.data.repository.utils.Resource
import kotlinx.coroutines.Deferred

interface SplashRepository {
    suspend fun getSplashlWithCache(): LiveData<Resource<Splash>>
}

class SplashRepositoryImpl(
    private val dataSource: SplashDataSource,
    private val dao: SplashDao
) : SplashRepository {

    override suspend fun getSplashlWithCache(): LiveData<Resource<Splash>> {

        return object : NetworkBoundResource<Splash, Splash>() {

            override fun processResponse(response: Splash): Splash {

                return response
            }

            override suspend fun saveCallResults(items: Splash) = dao.save(items)

            override fun shouldFetch(data: Splash?): Boolean = true

            override suspend fun loadFromDb(): Splash = dao.getSplash()

            override fun createCallAsync(): Deferred<Splash> =
                dataSource.fetchSplashAsync()

        }.build().asLiveData()

    }

}