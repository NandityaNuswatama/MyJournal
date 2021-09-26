package com.nandits.myjournal.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nandits.myjournal.source.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(private val local: LocalDataSource) : ViewModel() {
    private val _pin = MutableLiveData<Int>()
    val pin: LiveData<Int> = _pin
    
    fun setPin(input: Int){
        _pin.postValue(input)
    }
    
    fun savePin() = pin.value?.let { local.savePin(it) }
    
    fun getPin() = local.getPin()
}