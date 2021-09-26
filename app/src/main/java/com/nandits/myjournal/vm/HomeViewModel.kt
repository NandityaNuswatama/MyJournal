package com.nandits.myjournal.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nandits.myjournal.source.LocalDataSource
import com.nandits.myjournal.source.db.Journal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val local: LocalDataSource): ViewModel() {
    fun getJournal(): LiveData<List<Journal>>{
        val liveData = MutableLiveData<List<Journal>>()
        val list = mutableListOf<Journal>()
        CoroutineScope(Dispatchers.IO).launch {
            list.addAll(local.getJournal())
            liveData.postValue(list)
        }
        return liveData
    }
}