package com.philbas.demo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.philbas.demo.R
import com.philbas.demo.databinding.ListItemEntryBinding
import com.philbas.demo.entity.DictionaryEntry

class EntriesAdapter(): RecyclerView.Adapter<EntriesAdapter.EntryViewHolder>() {
    private var entries: List<DictionaryEntry>? = null
    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding = DataBindingUtil.inflate<ListItemEntryBinding>(
            layoutInflater!!,
            R.layout.list_item_entry,
            parent,
            false)
        return EntryViewHolder(binding)
    }

    override fun getItemCount(): Int = entries?.size ?: 0


    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        if(entries != null) {
            holder.itemBinding.entry = entries!![position]
        }
    }

    fun updateEntries(entries: List<DictionaryEntry>?){
        this.entries = entries
        notifyDataSetChanged()
    }

    class EntryViewHolder(val itemBinding: ListItemEntryBinding): RecyclerView.ViewHolder(itemBinding.root)
}