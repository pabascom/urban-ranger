package com.philbas.demo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.philbas.demo.R
import com.philbas.demo.sorting.EntrySorter
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
        configureLoadingState()
        setOnClickListeners()
    }

    private fun configureRecyclerView() {
        val adapter = EntriesAdapter()
        entriesRecyclerView.adapter = adapter
        entriesRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getEntryLiveData().observe(this, Observer { entries ->
            adapter.updateEntries(entries)
        })
    }

    private fun configureLoadingState() {
        viewModel.getIsLoadingLiveData().observe(this, Observer { isLoading ->
            if(isLoading){
                entriesRecyclerView.visibility = View.GONE
                loadingScreen.visibility = View.VISIBLE
            } else {
                entriesRecyclerView.visibility = View.VISIBLE
                loadingScreen.visibility = View.GONE
            }
        })
    }

    private fun setOnClickListeners() {
        searchButton.setOnClickListener { submitQuery() }
        sortByRadioGroup.setOnCheckedChangeListener { group, id ->
            EntrySorter.sortMode = when (id) {
                R.id.thumbsUpButton -> EntrySorter.SortMode.THUMBS_UP
                R.id.thumbsDownButton -> EntrySorter.SortMode.THUMBS_DOWN
                else -> EntrySorter.SortMode.RELEVANCE
            }
            submitQuery()
        }
    }

    private fun submitQuery() {
        val query = searchEditText.text.toString()
        viewModel.postQuery(query)
    }
}
