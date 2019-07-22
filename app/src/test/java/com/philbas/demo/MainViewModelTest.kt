package com.philbas.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.philbas.demo.entity.DictionaryEntry
import com.philbas.demo.repository.EntryRepository
import com.philbas.demo.sorting.EntrySorter
import com.philbas.demo.ui.viewmodel.MainViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(JUnit4ClassRunner::class)
class MainViewModelTest {

    lateinit var sampleData: List<DictionaryEntry>
    lateinit var entry1: DictionaryEntry
    lateinit var entry2: DictionaryEntry
    lateinit var entry3: DictionaryEntry

    @Before
    fun setUp() {
        entry1 = DictionaryEntry()
        entry1.thumbsUp = 3
        entry1.thumbsDown = 3

        entry2 = DictionaryEntry()
        entry2.thumbsUp = 5
        entry2.thumbsDown = 1

        entry3 = DictionaryEntry()
        entry3.thumbsUp = 1
        entry3.thumbsDown = 5

        sampleData = listOf(
            entry1, entry2, entry3
        )
    }

    @Test
    fun sortByThumbsUp_works() {
        EntrySorter.sortMode = EntrySorter.SortMode.THUMBS_UP
        val list = EntrySorter.sort(sampleData)
        assertEquals(entry2,list.first())
        assertEquals(entry1, list[1])
        assertEquals(entry3, list.last())
    }

    @Test
    fun sortByThumbsDown_works() {
        EntrySorter.sortMode = EntrySorter.SortMode.THUMBS_DOWN
        val list = EntrySorter.sort(sampleData)
        assertEquals(entry3,list.first())
        assertEquals(entry1, list[1])
        assertEquals(entry2, list.last())
    }

}
