package com.philbas.demo.sorting

import com.philbas.demo.entity.DictionaryEntry

interface SortingStrategy {

    fun sort(list: List<DictionaryEntry>): List<DictionaryEntry>

}