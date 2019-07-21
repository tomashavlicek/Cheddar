package com.example.chedar.zombietracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chedar.database.Zombie
import com.example.chedar.database.ZombieDatabaseDao
import kotlinx.coroutines.*

class ZombieTrackerViewModel(
    val database: ZombieDatabaseDao,
    application: Application): AndroidViewModel(application) {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val zombies = database.getAllZombies()

    fun delete(zombieKey: Long) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.delete(zombieKey)
            }
        }
    }
}