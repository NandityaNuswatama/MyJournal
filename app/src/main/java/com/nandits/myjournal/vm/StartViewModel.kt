package com.nandits.myjournal.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartViewModel : ViewModel() {
    private val _pin = MutableLiveData<Int>()
    val pin: LiveData<Int> = _pin
    
    fun setPin(input: Int){
        _pin.postValue(input)
    }
}