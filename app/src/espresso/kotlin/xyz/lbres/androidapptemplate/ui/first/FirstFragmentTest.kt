package xyz.lbres.androidapptemplate.ui.first

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import xyz.lbres.androidapptemplate.BaseActivity
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.testutils.rules.RetryRule

@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {

    @Rule
    @JvmField
    val rule = ActivityScenarioRule(BaseActivity::class.java)

    @Rule
    @JvmField
    val retryRule = RetryRule()

    @Test
    fun actionBarTitle() {
        onView(withText("Android App Template")).check(matches(isDisplayed()))
    }

    @Test
    fun initialUi() {
        onView(withText("Click to navigate to next fragment")).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToSecond() {
        onView(withId(R.id.navigateText)).perform(click())
        onView(withText("Second Fragment")).check(matches(isDisplayed()))
    }
}
