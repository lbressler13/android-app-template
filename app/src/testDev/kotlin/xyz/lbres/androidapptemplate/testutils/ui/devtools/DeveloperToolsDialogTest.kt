package xyz.lbres.androidapptemplate.testutils.ui.devtools

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import xyz.lbres.androidapptemplate.BaseActivity
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.ui.testutils.onViewInDialog

@RunWith(AndroidJUnit4::class)
class DeveloperToolsDialogTest {
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
    fun loadInitialUi() {
        openDevTools()

        onViewInDialog(withText("Developer Tools")).check(matches(isDisplayed()))

        onViewInDialog(withId(R.id.refreshUIButton))
            .check(matches(allOf(isDisplayed(), withText("Refresh UI"))))
        onViewInDialog(withId(R.id.hideDevToolsButton))
            .check(matches(allOf(isDisplayed(), withText("Hide dev tools"))))

        onViewInDialog(withId(R.id.devToolsTimeSpinner))
            .check(matches(allOf(isDisplayed(), withSpinnerText("5000ms"))))
    }

    @Test
    fun refreshUI() {
        openDevTools()
        onViewInDialog(withId(R.id.refreshUIButton)).perform(click())
        onViewInDialog(withText("Developer Tools")).check(matches(isDisplayed()))
    }

    @Test fun hideDevToolsOptionsDisplayed() = testHideDevToolsOptionsDisplayed()

    @Test fun interactWithHideDevToolsSpinner() = testInteractWithHideDevToolsSpinner()

    @Test fun hideDevTools() = testHideDevTools()

    @Test
    fun attributionsFragment() {
        onView(withId(R.id.infoButton)).perform(click())

        openDevTools()
        onViewInDialog(withText("Developer Tools")).check(matches(isDisplayed()))
    }
}
