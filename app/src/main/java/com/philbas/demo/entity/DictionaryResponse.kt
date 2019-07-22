package com.philbas.demo.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DictionaryResponse {

    @SerializedName("list")
    @Expose
    var list: List<DictionaryEntry>? = null

}
