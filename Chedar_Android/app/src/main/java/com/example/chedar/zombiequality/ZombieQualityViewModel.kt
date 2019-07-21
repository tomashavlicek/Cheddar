package com.example.chedar.zombiequality

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.chedar.database.Zombie
import com.example.chedar.database.ZombieDatabaseDao
import kotlinx.coroutines.*

class ZombieQualityViewModel(
    application: Application,
    val database: ZombieDatabaseDao): AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insert(zombie: Zombie) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.insert(zombie)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}