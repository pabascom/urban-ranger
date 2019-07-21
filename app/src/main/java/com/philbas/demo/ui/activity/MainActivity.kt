package com.philbas.demo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.philbas.demo.R
import com.philbas.demo.ui.adapter.EntriesAdapter
import com.philbas.demo.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        configureRecyclerView()
        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        searchButton.setOnClickListener { onSearchButtonClicked() }
    }

    private fun configureRecyclerView(){
        val adapter = EntriesAdapter()
        entriesRecyclerView.adapter = adapter
        entriesRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getEntryLiveData().observe(this, Observer { entries ->
            adapter.updateEntries(entries)
        })
    }

    private fun onSearchButtonClicked(){
        val query = searchEditText.text.toString()
        viewModel.getEntries(query)
    }
}
