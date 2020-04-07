package com.mg.demoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mg.demoapp.data.local.dao.SplashDao
import com.mg.demoapp.data.model.Splash

@Database(entities = [Splash::class], version = 1, exportSchema = false)
abstract class MGDatabase: RoomDatabase() {

    // DAO
    abstract fun splashDao(): SplashDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, MGDatabase::class.java, "MGDatasbase617.db")
                .build()
    }
}