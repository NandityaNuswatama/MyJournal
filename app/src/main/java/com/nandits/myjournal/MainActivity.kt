package com.nandits.myjournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.nandits.myjournal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, TextUtils.concat(edtPin.text))
            }
            val shareIntent = Intent.createChooser(intent, resources.getString(R.string.app_name))
            
            btnSignIn.setOnClickListener {
                Toast.makeText(this@MainActivity, edtPin.text, Toast.LENGTH_SHORT).show()
                startActivity(shareIntent)
            }
        }
    }
}