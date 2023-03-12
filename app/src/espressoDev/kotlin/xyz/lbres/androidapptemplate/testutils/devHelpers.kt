package xyz.lbres.androidapptemplate.testutils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import xyz.lbres.androidapptemplate.R

/**
 * Open dialog using dev tools button
 */
fun openDevTools() {
    onView(withId(R.id.devToolsButton)).perform(click())
}

/**
 * Close dialog
 */
fun closeDialog() {
    onView(withText("Done")).perform(click())
}
