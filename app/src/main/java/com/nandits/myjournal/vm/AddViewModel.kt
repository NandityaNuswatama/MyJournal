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
class AddViewModel @Inject constructor(private val local: LocalDataSource): ViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title
    
    private val _content = MutableLiveData<String>()
    val content: LiveData<String> = _content
    
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date
    
    fun setTitle(title: String){
        _title.postValue(title)
    }
    
    fun setContent(content: String){
        _content.postValue(content)
    }
    
    fun setDate(date: String){
        _date.postValue(date)
    }
    
    fun saveJournal(){
        CoroutineScope(Dispatchers.IO).launch {
            val journal = Journal(0, title.value.toString(), content.value.toString(), date.value.toString())
            local.insert(journal)
        }
    }
    
    fun deleteJournal(data: Journal){
        CoroutineScope(Dispatchers.IO).launch {
            local.delete(data)
        }
    }
}