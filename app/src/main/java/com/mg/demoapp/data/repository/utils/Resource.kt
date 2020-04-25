package com.mg.demoapp.data.repository.utils

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) where T : com.mg.demoapp.data.model.Error{
    companion object {
        fun <T> success(data: T?): Resource<T> where T : com.mg.demoapp.data.model.Error{
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable, data: T?): Resource<T> where T : com.mg.demoapp.data.model.Error{
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): Resource<T> where T : com.mg.demoapp.data.model.Error{
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}