package com.mg.demoapp.data.remote.service.ui.home

import com.mg.demoapp.data.model.ApiResult
import com.mg.demoapp.data.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {

    @GET("search/users")
    suspend fun fetchTopUsersAsync(@Query("q") query: String = "matevos",
                                   @Query("sort") sort: String = "followers"): ApiResult<GithubUser>

    @GET("users/{login}")
    suspend fun fetchUserDetailsAsync(@Path("login") login: String): GithubUser

}