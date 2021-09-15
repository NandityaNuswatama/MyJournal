package com.nandits.myjournal.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nandits.myjournal.databinding.ActivityMainBinding
import com.nandits.myjournal.source.sp.SharedPref

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPref
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = SharedPref(this)
        
        if (!hasPin()) intentToHome()
        else pinLogin()
    }
    
    private fun pinLogin(){
        val pin = sharedPref.getPin()
        with(binding){
            btnSignIn.setOnClickListener {
                val input = edtPin.text.toString()
                if (input.toInt() != pin) showToast("Pin Salah!")
                else intentToHome()
            }
        }
    }
    
    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    private fun intentToHome(){
        showToast("Ayo Journaling!")
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
    
    private fun hasPin(): Boolean = sharedPref.getPin() != 0
}