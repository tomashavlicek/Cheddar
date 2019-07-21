package com.example.chedar.zombietracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chedar.database.ZombieDatabaseDao
import java.lang.IllegalArgumentException

class ZombieTrackerViewModelFactory(
    private val dataSource: ZombieDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZombieTrackerViewModel::class.java)) {
            return ZombieTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}