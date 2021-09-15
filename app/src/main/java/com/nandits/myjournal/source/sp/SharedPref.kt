package com.nandits.myjournal.source.sp

import android.content.Context
import androidx.core.content.edit
import com.nandits.myjournal.PIN
import com.nandits.myjournal.SP_NAME

class SharedPref(private val context: Context) {
    private val sharedPref = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    
    fun savePin(input: Int){
        sharedPref.edit {
            putInt(PIN, input)
        }
    }
    
    fun getPin() = sharedPref.getInt(PIN, 0)
}