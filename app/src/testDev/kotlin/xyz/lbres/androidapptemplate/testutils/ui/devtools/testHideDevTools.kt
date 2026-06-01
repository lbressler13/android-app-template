package xyz.lbres.androidapptemplate.testutils.ui.devtools

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Assert.assertFalse
import org.robolectric.shadows.ShadowDialog
import org.robolectric.shadows.ShadowLooper
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.testutils.runWithFailMessage
import xyz.lbres.androidapptemplate.ui.testutils.onViewInDialog
import java.util.concurrent.TimeUnit

private val spinner = onViewInDialog(withId(R.id.devToolsTimeSpinner))
private val hideDevToolsButton = onViewInDialog(withId(R.id.hideDevToolsButton))
private val devToolsButton = onView(withId(R.id.devToolsButton))

fun testHideDevToolsOptionsDisplayed() {
    openDevTools()

    spinner.check(matches(withSpinnerText("5000ms"))).perform(click())

    spinnerItemAt(0).check(matches(allOf(isDisplayed(), withText("5000ms"))))
    spinnerItemAt(1).check(matches(allOf(isDisplayed(), withText("10000ms"))))
    spinnerItemAt(2).check(matches(allOf(isDisplayed(), withText("30000ms"))))
    spinnerItemAt(3).check(matches(allOf(isDisplayed(), withText("60000ms"))))

    var performException = false
    try {
        spinnerItemAt(4).check(matches(isDisplayed()))
    } catch (e: PerformException) {
        performException = true
    }

    if (!performException) {
        throw PerformException.Builder()
            .withCause(IllegalStateException("Dev tools spinner has too many options"))
            .build()
    }
}

fun testInteractWithHideDevToolsSpinner() {
    openDevTools()

    spinner.perform(click())
    spinnerItemAt(1).perform(click())
    spinner.check(matches(withSpinnerText("10000ms")))

    spinner.perform(click())
    spinnerItemAt(0).perform(click())
    spinner.check(matches(withSpinnerText("5000ms")))

    spinner.perform(click())
    spinnerItemAt(2).perform(click())
    spinner.check(matches(withSpinnerText("30000ms")))

    spinner.perform(click())
    spinnerItemAt(3).perform(click())
    spinner.check(matches(withSpinnerText("60000ms")))

    // close and re-open dialog
    closeDialog()
    openDevTools()
    spinner.check(matches(withSpinnerText("60000ms")))
}

fun testHideDevTools() {
    val hideTimes = listOf(5000L, 10000L, 30000L, 60000L)
    val buffer = 500L

    hideTimes.forEachIndexed { index, time ->
        runWithFailMessage("Checking duration $time at index $index") {
            openDevTools()
            spinner.perform(click())
            spinnerItemAt(index).perform(click())
            spinner.check(matches(withSpinnerText("${time}ms")))
            hideDevToolsButton.perform(click())

            // check that dialog is not showing
            val dialog = ShadowDialog.getLatestDialog()
            assertFalse(dialog.isShowing)

            val shadowLooper = ShadowLooper.shadowMainLooper()
            devToolsButton.check(matches(not(isDisplayed())))
            shadowLooper.idleFor(time - buffer, TimeUnit.MILLISECONDS)
            devToolsButton.check(matches(not(isDisplayed())))
            shadowLooper.idleFor(buffer * 2, TimeUnit.MILLISECONDS)
            devToolsButton.check(matches(isDisplayed()))
        }
    }
}

/**
 * Get the item at a specific position in the spinner
 *
 * @param position [Int]
 * @return [DataInteraction]: the item located at [position]
 */
private fun spinnerItemAt(position: Int): DataInteraction {
    return onData(`is`(instanceOf(String::class.java)))
        .inRoot(isPlatformPopup())
        .atPosition(position)
}
