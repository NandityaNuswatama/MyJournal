package com.nandits.myjournal.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nandits.myjournal.R
import com.nandits.myjournal.databinding.ActivityHomeBinding
import com.nandits.myjournal.showToast
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
        showData()
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
            if (list.isEmpty()) showToast("Ayo Buat Jurnal Pertamamu!")
            
            journalAdapter.onItemClick = {
                showDeleteDialog(it)
            }
        }
    }
    
    private fun showData(){
        viewModel.getJournal().observe(this, {
            initRecyclerview(it)
        })
    }
    
    private fun showDeleteDialog(journal: Journal){
        MaterialAlertDialogBuilder(this)
            .setTitle("Peringatan")
            .setMessage("Jurnal yang ada hapus tidak dapat dikembalikan")
            .setPositiveButton("Hapus"){ _,_, ->
                deleteJournal(journal)
            }
            .setNegativeButton("Batal"){ _,_ ->
            
            }
            .show()
    }
    
    private fun deleteJournal(journal: Journal){
        viewModel.deleteJournal(journal)
        showToast("Journal berhasil dihapus")
        showData()
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