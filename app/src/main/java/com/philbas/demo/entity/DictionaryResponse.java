
package com.philbas.demo.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DictionaryResponse {

    @SerializedName("list")
    @Expose
    private java.util.List<DictionaryEntry> list = null;

    public java.util.List<DictionaryEntry> getList() {
        return list;
    }

    public void setList(java.util.List<DictionaryEntry> dictionaryEntry) {
        this.list = dictionaryEntry;
    }

}
