package com.nandits.myjournal.source.db

import androidx.room.*

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal")
    fun getJournal(): List<Journal>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Journal)
    
    @Delete
    suspend fun delete(data: Journal)
}