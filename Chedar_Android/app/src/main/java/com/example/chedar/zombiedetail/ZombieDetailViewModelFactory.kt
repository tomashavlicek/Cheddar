package com.example.chedar.zombiedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chedar.database.ZombieDatabaseDao
import java.lang.IllegalArgumentException
import javax.sql.DataSource

class ZombieDetailViewModelFactory(
    private val zombieKey: Long = 0L,
    private val dataSource: ZombieDatabaseDao): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZombieDetailViewModel::class.java)) {
            return ZombieDetailViewModel(zombieKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}