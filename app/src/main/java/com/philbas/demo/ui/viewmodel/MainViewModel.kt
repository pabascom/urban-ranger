package com.philbas.demo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philbas.demo.entity.DictionaryEntry
import com.philbas.demo.entity.DictionaryResponse
import com.philbas.demo.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(): ViewModel() {
    private val urbanDictionaryService = RetrofitClient.getService()
    private val entries: MutableLiveData<List<DictionaryEntry>> = MutableLiveData()

    fun getEntryLiveData(): LiveData<List<DictionaryEntry>> = entries

    fun getEntries(query: String) {
        val call = urbanDictionaryService.getDictionaryEntriesByQueryString(query)
        call.enqueue(object: Callback<DictionaryResponse>{
            override fun onResponse(call: Call<DictionaryResponse>, response: Response<DictionaryResponse>) {
                entries.value = response.body()?.list
            }

            override fun onFailure(call: Call<DictionaryResponse>, t: Throwable) {
                throw t
            }

        })
    }
}