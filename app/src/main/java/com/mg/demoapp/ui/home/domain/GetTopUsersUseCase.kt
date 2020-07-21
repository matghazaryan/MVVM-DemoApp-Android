package com.mg.demoapp.ui.home.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mg.demoapp.data.model.GithubUser
import com.mg.demoapp.data.repository.ui.home.HomeRepository
import com.mg.demoapp.data.repository.utils.Resource

/**
 * Use case that gets a [Resource][List][GithubUser] from [HomeRepository]
 * and makes some specific logic actions on it.
 *
 * In this Use Case, I'm just doing nothing... ¯\_(ツ)_/¯
 */
class GetTopUsersUseCase(private val repository: HomeRepository) {

    suspend operator fun invoke(): LiveData<Resource<List<GithubUser>>>{
        return Transformations.map(repository.getUsersWithCache()){
            it
        }
    }
}