package com.mg.demoapp.data.remote.service.ui.home

class HomeDataSource(private val homeService: HomeService) {
    suspend fun fetchTopUsersAsync() = homeService.fetchTopUsersAsync()
    suspend fun fetchUserDetailsAsync(login: String) = homeService.fetchUserDetailsAsync(login)
}