package xyz.lbres.androidapptemplate.ui.second

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import xyz.lbres.androidapptemplate.BaseActivity
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.testutils.rules.RetryRule

@RunWith(AndroidJUnit4::class)
class SecondFragmentTest {

    @Rule
    @JvmField
    val rule = ActivityScenarioRule(BaseActivity::class.java)

    @Rule
    @JvmField
    val retryRule = RetryRule()

    @Before
    fun setupTest() {
        onView(withId(R.id.navigateText)).perform(click())
    }

    @Test
    fun actionBarTitle() {
        onView(withText("Second Fragment")).check(matches(isDisplayed()))
    }

    @Test
    fun initialUi() {
        onView(withText("Visited 1 times.")).check(matches(isDisplayed()))
        onView(withText("Click to pop backstack.")).check(matches(isDisplayed()))
    }

    @Test
    fun popBackstack() {
        onView(withId(R.id.popBackstackText)).perform(click())
        onView(withText("Android App Template")).check(matches(isDisplayed()))
    }

    @Test
    fun countIncremented() {
        onView(withText("Visited 1 times.")).check(matches(isDisplayed()))

        onView(withId(R.id.popBackstackText)).perform(click())
        onView(withId(R.id.navigateText)).perform(click())

        onView(withText("Visited 2 times.")).check(matches(isDisplayed()))

        onView(withId(R.id.popBackstackText)).perform(click())
        onView(withId(R.id.navigateText)).perform(click())

        onView(withText("Visited 3 times.")).check(matches(isDisplayed()))
    }
}
