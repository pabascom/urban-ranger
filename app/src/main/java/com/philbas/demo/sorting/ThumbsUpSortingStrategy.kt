package com.philbas.demo.sorting

import com.philbas.demo.entity.DictionaryEntry

/*
 * I'm using Insertion Sort because I expect results to be short. This algorithm
 * is the fastest for small data sets, but has horrible (O(n^2)) time complexity.
 * It also uses very little memory (though I lose some of that benefit since
 * the whole thing starts by duplicating the List). If we expect to handle larger
 * result sets, we should consider a merge sort or quick sort. Even if we don't,
 * it would be a good idea to offload sorting to a background thread--if I were
 * writing tasks for our next sprint, that would be at the top of the list.
 */
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