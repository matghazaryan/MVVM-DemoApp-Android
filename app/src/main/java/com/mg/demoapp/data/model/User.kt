package com.mg.demoapp.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name") val firstName: String = "",
    @SerializedName("last_name") val lastName: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("github_url") val githubUrl: String = "",
    @SerializedName("is_logged_in") val isLoggedIn: Boolean = false
):Error()