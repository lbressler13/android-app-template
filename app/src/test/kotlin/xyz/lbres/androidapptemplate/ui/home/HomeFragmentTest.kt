package xyz.lbres.androidapptemplate.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import xyz.lbres.androidapptemplate.BaseActivity
import xyz.lbres.androidapptemplate.R

@Category(Robolectric::class)
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    private var scenario: ActivityScenario<BaseActivity>? = null

    @Before
    fun setupTest() {
        scenario = ActivityScenario.launchActivityForResult(BaseActivity::class.java)
    }

    @After
    fun cleanupTest() {
        scenario = null
    }

    @Test
    fun actionBarTitle() {
        onView(withText("Android App Template")).check(matches(isDisplayed()))
    }

    @Test
    fun initialUi() {
        onView(withText("Welcome to the app template!")).check(matches(isDisplayed()))
    }

    @Test
    fun attributionsFragment() {
        onView(withId(R.id.infoButton)).perform(click())
        onView(withText("Image Attributions")).check(matches(isDisplayed()))
    }

    @Test
    fun recreate() {
        // TODO copy from dev test
    }
}
