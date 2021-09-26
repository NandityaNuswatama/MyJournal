package com.nandits.myjournal.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.nandits.myjournal.databinding.ActivityPinBinding
import com.nandits.myjournal.showToast
import com.nandits.myjournal.vm.PinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPinBinding
    private val viewModel: PinViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            edtPin.doAfterTextChanged {
                viewModel.setPin(it.toString().toInt())
            }
            btnSavePin.setOnClickListener {
                viewModel.savePin()
                showToast("Berhasil mengubah pin")
                intentToStart()
            }
        }
    }
    
    private fun intentToStart(){
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        finish()
    }
}