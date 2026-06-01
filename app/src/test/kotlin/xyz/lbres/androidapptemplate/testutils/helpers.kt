package xyz.lbres.androidapptemplate.testutils

import androidx.test.espresso.PerformException

/**
 * Run a block of code and print a specific message if an [AssertionError] is thrown
 *
 * @param failureMessage [String]: message to print in case of failure
 * @param block: code to run
 */
fun runWithFailMessage(failureMessage: String, block: () -> Unit) {
    try {
        block()
    } catch (e: Throwable) {
        if (e is AssertionError || e is PerformException) {
            printErr(failureMessage)
        }
        throw e
    }
}
