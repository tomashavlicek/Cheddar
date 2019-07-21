package com.example.chedar.zombiequality

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chedar.database.ZombieDatabaseDao
import java.lang.IllegalArgumentException
import javax.sql.DataSource

class ZombieQualityViewModelFactory(
    private val application: Application,
    private val dataSource: ZombieDatabaseDao
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZombieQualityViewModel::class.java)) {
            return ZombieQualityViewModel(application, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}