package com.nandits.myjournal.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.datepicker.MaterialDatePicker
import com.nandits.myjournal.databinding.ActivityAddJournalBinding
import com.nandits.myjournal.milisToDate
import com.nandits.myjournal.showToast
import com.nandits.myjournal.source.db.Journal
import com.nandits.myjournal.vm.AddViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddJournalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddJournalBinding
    private val viewModel: AddViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        initListener()
    }
    
    private fun initListener() {
        with(binding) {
            btnCalendar.setOnClickListener {
                showToast("Loading...")
                showDatePicker()
            }
            
            edtTitle.doAfterTextChanged {
                viewModel.setTitle(it.toString())
            }
            
            edtContent.doAfterTextChanged {
                viewModel.setContent(it.toString())
            }
            
            btnSave.setOnClickListener {
                viewModel.saveJournal()
                showToast("Berhasil membuat jurnal!")
            }
        }
    }
    
    private fun showDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tanggal Jurnal")
                .build()
        
        datePicker.show(supportFragmentManager, "Journaling")
        
        datePicker.addOnPositiveButtonClickListener {
            binding.tvDate.text = milisToDate(it)
            viewModel.setDate(milisToDate(it))
        }
    }
    
    private fun deleteJournal(data: Journal) {
        viewModel.deleteJournal(data)
    }
}