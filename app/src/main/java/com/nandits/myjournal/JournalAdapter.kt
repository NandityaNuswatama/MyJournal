package com.nandits.myjournal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nandits.myjournal.databinding.ItemJournalBinding

class JournalAdapter: RecyclerView.Adapter<JournalAdapter.ViewHolder>() {
    private val list = ArrayList<Journal>()
    
    fun setData(input: List<Journal>){
        list.clear()
        list.addAll(input)
        notifyDataSetChanged()
    }
    
    inner class ViewHolder(private val binding: ItemJournalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Journal){
            with(binding){
                tvTitle.text = item.title
                tvDate.text = item.date
                tvContent.text = item.content
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemJournalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
    
    override fun getItemCount(): Int {
        return list.size
    }
}