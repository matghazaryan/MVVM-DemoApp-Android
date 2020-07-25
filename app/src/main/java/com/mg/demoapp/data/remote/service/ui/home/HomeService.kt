package com.mg.demoapp.data.remote.service.ui.home

import com.mg.demoapp.data.model.ApiResult
import com.mg.demoapp.data.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {

    @GET("52be9777-4374-405c-ad0e-f5e65d242d76")
    suspend fun fetchTopUsersAsync(@Query("q") query: String = "matevos",
                                   @Query("sort") sort: String = "followers"): ApiResult<GithubUser>

    @GET("users/{login}")
    suspend fun fetchUserDetailsAsync(@Path("login") login: String): GithubUser

}