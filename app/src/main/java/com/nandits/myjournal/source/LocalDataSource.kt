package com.nandits.myjournal.source

import com.nandits.myjournal.source.db.Journal
import com.nandits.myjournal.source.db.JournalDao
import com.nandits.myjournal.source.sp.SharedPref
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val dao: JournalDao, private val sharedPref: SharedPref) {
    fun getJournal() = dao.getJournal()
    
    suspend fun insert(journal: Journal) = dao.insert(journal)
    
    suspend fun delete(journal: Journal) = dao.delete(journal)
    
    fun getPin() = sharedPref.getPin()
    
    fun savePin(input: Int) = sharedPref.savePin(input)
}