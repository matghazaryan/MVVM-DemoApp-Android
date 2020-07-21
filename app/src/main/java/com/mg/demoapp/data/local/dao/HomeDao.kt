package com.mg.demoapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.mg.demoapp.common.base.BaseDao
import com.mg.demoapp.data.model.GithubUser
import java.util.*

abstract class HomeDao: BaseDao<GithubUser>(){

    @Query("SELECT * FROM GithubUser ORDER BY login ASC LIMIT 30")
    abstract fun getTopUsers(): LiveData<List<GithubUser>>

    @Query("SELECT * FROM GithubUser WHERE login = :login LIMIT 1")
    abstract fun getUser(login: String): LiveData<GithubUser>

    // ---

    /**
     * Each time we save an user, we update its 'lastRefreshed' field
     * This allows us to know when we have to refresh its data
     */

    suspend fun save(user: GithubUser) {
        insert(user.apply { lastRefreshed = Date() })
    }

    suspend fun save(users: List<GithubUser>) {
        insert(users.apply { forEach { it.lastRefreshed = Date() } })
    }
}