package com.philbas.demo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philbas.demo.entity.DictionaryEntry
import com.philbas.demo.entity.DictionaryResponse
import com.philbas.demo.network.RetrofitClient
import com.philbas.demo.sorting.EntrySorter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(): ViewModel() {
    private val urbanDictionaryService = RetrofitClient.getService()

    private val entries: MutableLiveData<List<DictionaryEntry>> = MutableLiveData()
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getEntryLiveData(): LiveData<List<DictionaryEntry>> = entries

    fun getIsLoadingLiveData(): LiveData<Boolean> = isLoading

    fun getEntries(query: String) {
        val call = urbanDictionaryService.getDictionaryEntriesByQueryString(query)
        isLoading.value = true
        call.enqueue(object: Callback<DictionaryResponse>{
            override fun onResponse(call: Call<DictionaryResponse>, response: Response<DictionaryResponse>) {
                val body = response.body()
                if (body == null) { entries.value = null }
                else { entries.value = EntrySorter.sort(body.list!!) }
                isLoading.value = false
            }

            override fun onFailure(call: Call<DictionaryResponse>, t: Throwable) {
                throw t
            }

        })
    }
}