package com.philbas.demo.sorting

import com.philbas.demo.entity.DictionaryEntry

// Using Insertion Sort because I expect results to be short (1 page of results)
class ThumbsUpSortingStrategy : SortingStrategy {
    override fun sort(list: List<DictionaryEntry>): List<DictionaryEntry> {
        val l = list.toMutableList()
        var temp: DictionaryEntry
        for (i in 1 until l.size) {
            for (j in i downTo 1) {
                if (l[j].thumbsUp ?: 0 > l[j - 1].thumbsUp ?: 0) {
                    temp = l[j]
                    l[j] = l[j - 1]
                    l[j - 1] = temp
                }
            }
        }
        return l
    }
}