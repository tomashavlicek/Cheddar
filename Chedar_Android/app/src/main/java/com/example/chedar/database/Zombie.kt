package com.example.chedar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zombies_account_table")
data class Zombie(
    @PrimaryKey(autoGenerate = true)
    var zombieId: Long = 0L,

    @ColumnInfo(name = "timestamp_milli")
    val timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "account_name")
    val name: String = "",

    @ColumnInfo(name = "contains_birthday")
    val birthday: Boolean = false,

    @ColumnInfo(name = "contains_address")
    val address: Boolean = false,

    @ColumnInfo(name = "contains_card")
    val card: Boolean = false
)
