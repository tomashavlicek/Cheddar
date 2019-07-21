package com.example.chedar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ZombieDatabaseDao {

    @Insert(onConflict = REPLACE)
    fun insert(zombie: Zombie)

    @Query("SELECT * FROM zombies_account_table WHERE zombieId = :key")
    fun get(key: Long): LiveData<Zombie>

    @Query("DELETE FROM zombies_account_table WHERE zombieId = :key")
    fun delete(key: Long)

    /**
     * Selects and returns all rows in the table,
     * sorted by zombieId in descending order.
     */
    @Query("SELECT * FROM zombies_account_table ORDER BY zombieId DESC")
    fun getAllZombies(): LiveData<List<Zombie>>
}
