package com.philbas.demo.network

import com.philbas.demo.entity.DictionaryResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object RetrofitClient {

    const val BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com/"
    const val PATH = "define"

    const val API_KEY = "X-RapidAPI-Key"
    const val API_KEY_VALUE = "10a5108924mshe5ac5e3a43c7beap1dfb25jsnf85444001ea3"

    private val okHttpClient by lazy {
        // Format is chosen so it's easy to add interceptors, caches, etc
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private fun createClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): UrbanDictionaryService{
        return createClient().create(UrbanDictionaryService::class.java)
    }

    interface UrbanDictionaryService {
        @Headers("$API_KEY: $API_KEY_VALUE")
        @GET(PATH)
        fun getDictionaryEntriesByQueryString(@Query("term") query: String): Call<DictionaryResponse>
    }
}
