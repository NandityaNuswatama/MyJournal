package com.nandits.myjournal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.datepicker.MaterialDatePicker
import com.nandits.myjournal.databinding.ActivityAddJournalBinding
import com.nandits.myjournal.milisToDate

class AddJournalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddJournalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        initListener()
    }
    
    private fun initListener(){
        with(binding){
            btnCalendar.setOnClickListener {
                showDatePicker()
            }
        }
    }
    
    private fun showDatePicker(){
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
            
        datePicker.addOnPositiveButtonClickListener {
            binding.tvDate.text = milisToDate(it)
        }
        datePicker.show(supportFragmentManager, null)
    }
}