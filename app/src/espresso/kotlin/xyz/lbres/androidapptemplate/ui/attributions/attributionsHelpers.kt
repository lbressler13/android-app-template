package xyz.lbres.androidapptemplate.ui.attributions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.testutils.matchers.isShown
import xyz.lbres.androidapptemplate.testutils.matchers.matchesAtPosition
import xyz.lbres.androidapptemplate.testutils.viewactions.actionOnChildWithId
import xyz.lbres.androidapptemplate.testutils.viewassertions.isNotPresented
import xyz.lbres.androidapptemplate.ui.attributions.authorattribution.AuthorAttributionViewHolder
import xyz.lbres.androidapptemplate.ui.attributions.constants.authorAttributions
import xyz.lbres.androidapptemplate.ui.attributions.imageattribution.ImageAttributionViewHolder
import xyz.lbres.kotlinutils.list.IntList

private val imageUrls = authorAttributions.map { it.images.map { it.url } }
private val attributionsRecycler = onView(withId(R.id.attributionsRecycler))
private val nestedRecyclerId = R.id.imagesRecycler

/**
 * Wrapper function to scroll a RecyclerView to [position], with ViewHolder type [AuthorAttributionViewHolder]
 *
 * @param position [Int]: position to scroll to
 * @return [ViewAction]: action to scroll to the given position
 */
fun scrollToAuthorPosition(position: Int): ViewAction =
    RecyclerViewActions.scrollToPosition<AuthorAttributionViewHolder>(position)

/**
 * Wrapper function to scroll a RecyclerView to [position], with ViewHolder type [ImageAttributionViewHolder]
 *
 * @param position [Int]: position to scroll to
 * @return [ViewAction]: action to scroll to the given position
 */
fun scrollToImagePosition(position: Int): ViewAction =
    RecyclerViewActions.scrollToPosition<ImageAttributionViewHolder>(position)

/**
 * Wrapper function to perform an action at a given index in a RecyclerView, with ViewHolder type [AuthorAttributionViewHolder]
 *
 * @param position [Int]: position to perform action
 * @param action [ViewAction]: action to perform
 * @return [ViewAction]: action to perform given [action] on the ViewHolder at position [position]
 */
fun actionOnAuthorItemAtPosition(position: Int, action: ViewAction): ViewAction =
    RecyclerViewActions.actionOnItemAtPosition<AuthorAttributionViewHolder>(position, action)

/**
 * Click the expand/collapse button for an element in the main RecyclerView
 *
 * @param position [Int]: position of item to click
 */
fun expandCollapseAttribution(position: Int) {
    val clickExpandCollapse = actionOnChildWithId(R.id.expandCollapseButton, click())

    attributionsRecycler.perform(actionOnAuthorItemAtPosition(position, clickExpandCollapse))
    attributionsRecycler.check(matches(matchesAtPosition(position, hasDescendant(withId(nestedRecyclerId)))))
}

/**
 * Check that image urls for specified dropdowns are not visible
 *
 * @param positions [IntList]: list of positions where image urls should not be visible
 */
fun checkImagesNotPresented(positions: IntList) {
    for (position in positions) {
        attributionsRecycler.perform(scrollToAuthorPosition(0))
        for (url in imageUrls[position]) {
            onView(withText(url)).check(isNotPresented())
        }
    }
}

/**
 * Check that image urls for specified dropdowns are visible
 *
 * @param positions [IntList]: list of positions where image urls should be visible
 */
fun checkImagesDisplayed(positions: IntList) {
    for (position in positions) {
        attributionsRecycler.perform(scrollToAuthorPosition(position))

        for (pair in imageUrls[position].withIndex()) {
            val nestedPosition = pair.index
            val url = pair.value

            val scrollImagesRecycler = actionOnChildWithId(R.id.imagesRecycler, scrollToImagePosition(nestedPosition))
            attributionsRecycler.perform(actionOnAuthorItemAtPosition(position, scrollImagesRecycler))

            val urlMatcher = allOf(isShown(), hasDescendant(withText(url)))
            val nestedMatcher =
                allOf(withId(nestedRecyclerId), isDisplayed(), matchesAtPosition(nestedPosition, urlMatcher))

            attributionsRecycler.check(matches(matchesAtPosition(position, hasDescendant(nestedMatcher))))
        }
    }
}
