package com.philbas.demo.sorting

import com.philbas.demo.entity.DictionaryEntry

// Copy/pasted and one line changed. Don't @ me.
class ThumbsDownSortingStrategy : SortingStrategy {
    override fun sort(list: List<DictionaryEntry>): List<DictionaryEntry> {
        val l = list.toMutableList()
        var temp: DictionaryEntry
        for (i in 1 until l.size) {
            for (j in i downTo 1) {
                if (l[j].thumbsDown ?: 0 > l[j - 1].thumbsDown ?: 0) {
                    temp = l[j]
                    l[j] = l[j - 1]
                    l[j - 1] = temp
                }
            }
        }
        return l
    }
}
/*
 * I know it's ugly, but I genuinely don't think it would be
 * worth the time to abstract the sorting algorithm from the field
 * we're sorting on, given the scale of the project. In a long-running
 * production, I would consider this code debt.
 */