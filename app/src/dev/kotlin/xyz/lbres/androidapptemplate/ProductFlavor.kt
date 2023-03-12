package xyz.lbres.androidapptemplate

import xyz.lbres.androidapptemplate.ui.BaseFragment
import xyz.lbres.androidapptemplate.ui.DeveloperToolsDialog
import xyz.lbres.androidapptemplate.ext.view.visible

/**
 * Configuration for dev build flavor
 */
object ProductFlavor : ProductFlavorConfig {
    override val devMode = true

    /**
     * Show dev tools button and setup dialog
     */
    override fun setupFlavor(activity: BaseActivity) {
        val devToolsButton = activity.binding.devToolsButton
        devToolsButton.visible()

        val dialog = DeveloperToolsDialog()
        devToolsButton.setOnClickListener {
            val fragmentManager = BaseFragment.dialogFragmentManager

            if (fragmentManager != null) {
                dialog.show(fragmentManager, DeveloperToolsDialog.TAG)
            }
        }
    }
}
