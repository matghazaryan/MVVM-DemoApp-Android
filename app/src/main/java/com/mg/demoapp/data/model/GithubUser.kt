package com.mg.demoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.TimeUnit

@Entity
data class GithubUser (
    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("company")
    val company: String?,

    @SerializedName("blog")
    val blog: String?,

    var lastRefreshed: Date
    ){
    fun haveToRefreshFromNetwork() : Boolean
            = TimeUnit.MILLISECONDS.toMinutes(Date().time - lastRefreshed.time) >= 10
}