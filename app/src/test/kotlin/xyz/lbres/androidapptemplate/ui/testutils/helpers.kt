package xyz.lbres.androidapptemplate.ui.testutils

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.RootMatchers.isDialog
import org.hamcrest.Matcher

/**
 * Match a view within a dialog
 */
fun onViewInDialog(matcher: Matcher<View>) = onView(matcher).inRoot(isDialog())
