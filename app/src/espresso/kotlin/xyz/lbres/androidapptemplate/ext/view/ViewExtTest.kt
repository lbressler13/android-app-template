package xyz.lbres.androidapptemplate.ext.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import xyz.lbres.androidapptemplate.BaseActivity
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.testutils.viewassertions.isNotPresented

@RunWith(AndroidJUnit4::class)
class ViewExtTest {
    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(BaseActivity::class.java)

    @Test
    fun visible() {
        onView(withId(R.id.infoButton))
            .perform(callInvisible())
            .check(isNotPresented())
            .perform(callVisible())
            .check(matches(isDisplayed()))
    }

    @Test
    fun invisible() {
        onView(withId(R.id.infoButton))
            .check(matches(isDisplayed()))
            .perform(callInvisible())
            .check(isNotPresented())
    }

    @Test
    fun gone() {
        onView(withId(R.id.infoButton))
            .check(matches(isDisplayed()))
            .perform(callGone())
            .check(isNotPresented())
    }

    @Test
    fun enable() {
        onView(withId(R.id.infoButton))
            .perform(callDisable())
            .check(matches(isNotEnabled()))
            .perform(callEnable())
            .check(matches(isEnabled()))
    }

    @Test
    fun disable() {
        onView(withId(R.id.infoButton))
            .check(matches(isEnabled()))
            .perform(callDisable())
            .check(matches(isNotEnabled()))
    }
}
