package com.philbas.demo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philbas.demo.entity.DictionaryEntry
import com.philbas.demo.entity.DictionaryResponse
import com.philbas.demo.network.RetrofitClient
import com.philbas.demo.repository.EntryRepository
import com.philbas.demo.sorting.EntrySorter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(): ViewModel() {
    private val repository = EntryRepository()

    fun getEntryLiveData(): LiveData<List<DictionaryEntry>> = repository.getEntryLiveData()

    fun getIsLoadingLiveData(): LiveData<Boolean> = repository.getIsLoadingLiveData()

    fun postQuery(query: String){
        repository.queryEntries(query)
    }
}