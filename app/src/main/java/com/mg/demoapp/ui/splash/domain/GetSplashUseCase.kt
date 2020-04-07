package com.mg.demoapp.ui.splash.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mg.demoapp.data.model.Splash
import com.mg.demoapp.data.repository.ui.splash.SplashRepository
import com.mg.demoapp.data.repository.utils.Resource

/**
 * Use case that gets a [Resource] [Splash] from [SplashRepository]
 * and makes some specific logic actions on it.
 *
 * In this Use Case, I'm just doing nothing... ¯\_(ツ)_/¯
 */
class GetSplashUseCase(private val repository: SplashRepository) {

    suspend operator fun invoke(): LiveData<Resource<Splash>> {
        return Transformations.map(repository.getSplashlWithCache()) {
            it // Place here your specific logic actions (if any)
        }
    }
}