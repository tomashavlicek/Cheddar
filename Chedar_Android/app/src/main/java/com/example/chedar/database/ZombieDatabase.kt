package com.example.chedar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Zombie::class], version = 1, exportSchema = false)
abstract class ZombieDatabase : RoomDatabase() {

    abstract val zombieDatabaseDao: ZombieDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ZombieDatabase? = null

        fun getInstance(context: Context): ZombieDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ZombieDatabase::class.java,
                        "zombies_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
