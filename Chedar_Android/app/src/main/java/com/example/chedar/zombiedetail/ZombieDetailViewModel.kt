package com.example.chedar.zombiedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chedar.database.Zombie
import com.example.chedar.database.ZombieDatabaseDao
import kotlinx.coroutines.*
import javax.sql.DataSource

class ZombieDetailViewModel(
    private val zombieKey: Long = 0L,
    val dataSource: ZombieDatabaseDao): ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val zombie = MediatorLiveData<Zombie>()

    fun getZombie() = zombie

    private val _navigateToZombieTrackerFragment = MutableLiveData<Boolean>()
    val navigateToZombieTrackerFragment: LiveData<Boolean>
        get() = _navigateToZombieTrackerFragment


    init {
        zombie.addSource(dataSource.get(zombieKey), zombie::setValue)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun delete() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.delete(zombieKey)
            }
            _navigateToZombieTrackerFragment.value = true
        }
    }

    fun doneNavigatingToZombieTrackerFragment() {
        _navigateToZombieTrackerFragment.value = false
    }

}