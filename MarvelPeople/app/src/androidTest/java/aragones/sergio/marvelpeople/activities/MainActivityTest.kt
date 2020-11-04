/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 4/11/2020
 */

package aragones.sergio.marvelpeople.activities

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import aragones.sergio.marvelpeople.R
import aragones.sergio.marvelpeople.utils.Constants
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    //MARK: - Private properties

    private val TIME_TO_WAIT: Long = 1500
    private val CHARACTER_COUNT = Constants.LIMIT
    private val CHARACTER_SEARCH = "spider"

    //MARK: - Public properties

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    //MARK: - Public methods

    @Before
    fun setUp() {
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT))
    }

    @Test
    fun testInitialGames() {
        onView(withId(R.id.recycler_view_characters)).check(matches(hasNumberOfChildren(CHARACTER_COUNT)))
    }

    @Test
    fun testSearchCharacters() {

        onView(withId(R.id.action_search)).perform(click())
        onView(withClassName(`is`("android.widget.SearchView\$SearchAutoComplete"))).perform(replaceText(CHARACTER_SEARCH))
        onView(allOf(withClassName(`is`("android.widget.SearchView\$SearchAutoComplete")), withText(CHARACTER_SEARCH))).perform(pressImeActionButton())
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT))
    }

    @Test
    fun testGoToDetail() {
        onView(withId(R.id.recycler_view_characters)).perform(scrollToPosition<RecyclerView.ViewHolder>(0), actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    //MARK: - Private methods

    private fun hasNumberOfChildren(count: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description) {}

            override fun matchesSafely(view: View): Boolean {

                if (view is RecyclerView) {

                    val adapter = view.adapter
                    return adapter != null && adapter.itemCount == count
                }
                return (view as ViewGroup).childCount == count
            }
        }
    }

    private fun waitFor(millis: Long): ViewAction? {

        return object : ViewAction {

            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return ""
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}