package com.example.hw3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val firstNumber: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val secondNumber: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val action: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}