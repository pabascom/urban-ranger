package com.philbas.demo

import android.content.Intent
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.intent.Intents
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.philbas.demo.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java,
        false,
        true
    )

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(activityRule.activity.countingIdlingResource)
        activityRule.launchActivity(Intent())
        Intents.init()
    }

    @Test
    fun recyclerView_isNotEmpty() {
        val rvCount = activityRule.activity.entriesRecyclerView.adapter?.itemCount ?: 0
        assert(rvCount > 0)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(activityRule.activity.countingIdlingResource)
        Intents.release()
    }
}
