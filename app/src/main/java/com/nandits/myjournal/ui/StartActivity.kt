package com.nandits.myjournal.ui

import android.content.Intent
import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nandits.myjournal.databinding.ActivityMainBinding
import com.nandits.myjournal.showToast
import com.nandits.myjournal.vm.PinViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import android.os.Build

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PinViewModel by viewModels()
    private var contentObserver: ContentObserver? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE)
        
        setContentView(binding.root)
    
        Timber.d("On Create")
    
        if (!hasPin()) intentToHome()
        else pinLogin()
    
        contentObserver = registerObserver()
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
    
    private fun registerObserver(): ContentObserver {
        val contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean, uri: Uri?) {
                super.onChange(selfChange, uri)
                Timber.d("Screen capture detected")
            }
        }
        return contentObserver
    }
}