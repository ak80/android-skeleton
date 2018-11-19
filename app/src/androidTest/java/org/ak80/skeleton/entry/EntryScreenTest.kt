package org.ak80.skeleton.entry

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.ak80.skeleton.AcceptanceTest
import org.ak80.skeleton.R
import org.junit.Test


class EntryScreenTest : AcceptanceTest<EntryActivity>(EntryActivity::class.java) {

    @Test
    fun start_showsEntryView() {
        // Then
        onView(withId(R.id.entry_view)).check(matches(isDisplayed()))
    }

}