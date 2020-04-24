package com.mg.demoapp.ui.login.domain

import androidx.lifecycle.LiveData
import com.mg.demoapp.common.constants.MGConstants
import com.mg.demoapp.data.model.LoginObj
import com.mg.demoapp.data.model.User
import com.mg.demoapp.data.repository.ui.login.LoginRepository
import com.mg.demoapp.data.repository.utils.Resource

class GetLoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(login: LoginObj): LiveData<Resource<User>> {
        return when (login.urlValue) {
            MGConstants.successLogin -> repository.loginAsyncSuccess()
            else -> repository.loginAsyncErrorMessage()
        }
    }
}

