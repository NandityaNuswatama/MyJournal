package com.nandits.myjournal.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.nandits.myjournal.source.db.JournalDao
import com.nandits.myjournal.source.db.JournalDatabase
import com.nandits.myjournal.source.sp.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): JournalDatabase =
        Room.databaseBuilder(context, JournalDatabase::class.java, "journal.db")
            .fallbackToDestructiveMigration().build()
    
    @Provides
    fun provideDao(database: JournalDatabase): JournalDao = database.journalDao()
    
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPref =
        SharedPref(context)
}