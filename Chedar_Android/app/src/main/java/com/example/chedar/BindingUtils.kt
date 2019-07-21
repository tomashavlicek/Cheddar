package com.example.chedar

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.chedar.database.Zombie

@BindingAdapter("createdDateFormatted")
fun TextView.setCreatedDateFormatted(item: Zombie?) {
    item?.let {
        text = convertLongToDateString(item.timestamp)
    }
}

@BindingAdapter("accountWithThreadLevel")
fun TextView.setAccountWithThreadLevel(item: Zombie?) {
    item?.let {
        text = "${item.name} thread: " + getZombieLevel(item)
    }
}