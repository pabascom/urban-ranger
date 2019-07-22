package com.philbas.demo.sorting

import com.philbas.demo.entity.DictionaryEntry

/*
 * As far as I can tell, the provided API doesn't offer result sorting,
 * so I'm implementing it manually. This whole section is ugly and unoptimized.
 * It needs dependency injection, the architecture could be cleaner, and all
 * of this should really be handled server side anyway since we might want to
 * implement paging. But sorting's in the spec so sorting's in the deliverable.
 */
object EntrySorter {
    var sortMode: SortMode = SortMode.RELEVANCE
    private val thumbsUpStrategy: SortingStrategy by lazy {
        ThumbsUpSortingStrategy()
    }
    private val thumbsDownStrategy: SortingStrategy by lazy {
        ThumbsDownSortingStrategy()
    }

    fun sort(list: List<DictionaryEntry>): List<DictionaryEntry>{
        return when(sortMode){
            EntrySorter.SortMode.THUMBS_UP -> thumbsUpStrategy.sort(list)
            EntrySorter.SortMode.THUMBS_DOWN -> thumbsDownStrategy.sort(list)
            else -> list
        }
    }

    enum class SortMode {
        THUMBS_UP,
        THUMBS_DOWN,
        RELEVANCE
    }
}