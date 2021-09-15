package com.nandits.myjournal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nandits.myjournal.databinding.ActivityPinBinding
import com.nandits.myjournal.source.sp.SharedPref

class PinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref = SharedPref(this)
        with(binding){
            val newPin = edtPin.text
            btnSavePin.setOnClickListener {
                sharedPref.savePin(newPin.toString().toInt())
                Toast.makeText(this@PinActivity, "Berhasil mengubah pin", Toast.LENGTH_SHORT).show()
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