package com.nandits.myjournal.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nandits.myjournal.databinding.ActivityMainBinding
import com.nandits.myjournal.showToast
import com.nandits.myjournal.vm.PinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PinViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        if (!hasPin()) intentToHome()
        else pinLogin()
    }
    
    private fun pinLogin(){
        val pin = viewModel.getPin()
        with(binding){
            btnSignIn.setOnClickListener {
                val input = edtPin.text.toString()
                if (input.toInt() != pin) {
                    showToast("Pin Salah!")
                    edtPin.setText("")
                }
                else intentToHome()
            }
        }
    }
    
    private fun intentToHome(){
        showToast("Ayo Menulis Jurnal!")
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
    
    private fun hasPin(): Boolean = viewModel.getPin() != 0
}