package com.example.hw6.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw6.model.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    private val _liveData = MutableLiveData<Int>()

    val liveData: LiveData<Int>
        get() = _liveData

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            _liveData.postValue(10)
            delay(1000)
        }
    }
}