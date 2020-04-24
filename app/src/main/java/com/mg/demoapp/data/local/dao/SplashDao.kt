package com.mg.demoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.mg.demoapp.common.base.BaseDao
import com.mg.demoapp.data.model.Splash

@Dao
abstract class SplashDao: BaseDao<Splash>() {

//    @Query("SELECT * FROM User ORDER BY login ASC LIMIT 30")
//    abstract suspend fun getTopUsers(): List<User>
//
//    @Query("SELECT * FROM User WHERE login = :login LIMIT 1")
//    abstract suspend fun getUser(login: String): User

    @Query("SELECT * FROM Splash")
    abstract suspend fun getSplash(): Splash

    /**
     * Each time we save an user, we update its 'lastRefreshed' field
     * This allows us to know when we have to refresh its data
     */

    suspend fun save(splash: Splash) {
        insert(splash)
    }

//    suspend fun save(users: List<Splash>) {
//        insert(users.apply { forEach { it.lastRefreshed = Date() } })
//    }
}