package com.nandits.myjournal.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandits.myjournal.R
import com.nandits.myjournal.databinding.ActivityHomeBinding
import com.nandits.myjournal.source.db.Journal
import com.nandits.myjournal.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddJournalActivity::class.java)
            startActivity(intent)
        }
    }
    
    override fun onResume() {
        super.onResume()
        viewModel.getJournal().observe(this, {
            initRecyclerview(it)
        })
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.pin_menu -> {
                val intent = Intent(this, PinActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun initRecyclerview(list: List<Journal>) {
        val journalAdapter = JournalAdapter()
        with(binding.rvJournal) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = journalAdapter
            journalAdapter.setData(list)
        }
    }
    
//    private val listJournal = listOf(
//        Journal(
//            "Hari pertama",
//            "onCreateViewHolder(): RecyclerView calls this method whenever it needs to create a new ViewHolder. The method creates and initializes the ViewHolder and its associated View, but does not fill in the view's contents—the ViewHolder has not yet been bound to specific data.",
//            "7 September 2021"
//        ),
//        Journal(
//            "Hari kedua",
//            "onCreateViewHolder(): RecyclerView calls this method whenever it needs to create a new ViewHolder. The method creates and initializes the ViewHolder and its associated View, but does not fill in the view's contents—the ViewHolder has not yet been bound to specific data.",
//            "8 September 2021"
//        ),
//        Journal(
//            "Hari ketiga",
//            "onCreateViewHolder(): RecyclerView calls this method whenever it needs to create a new ViewHolder. The method creates and initializes the ViewHolder and its associated View, but does not fill in the view's contents—the ViewHolder has not yet been bound to specific data.",
//            "9 September 2021"
//        ),
//        Journal(
//            "Hari keempat",
//            "onCreateViewHolder(): RecyclerView calls this method whenever it needs to create a new ViewHolder. The method creates and initializes the ViewHolder and its associated View, but does not fill in the view's contents—the ViewHolder has not yet been bound to specific data.",
//            "10 September 2021"
//        )
//    )
}