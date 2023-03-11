package xyz.lbres.androidapptemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import xyz.lbres.androidapptemplate.databinding.ActivityBaseBinding

/**
 * Activity that contains all functionality of app
 */
class BaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityBaseBinding

    /**
     * Initialize activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Pop most recent fragment from backstack.
     */
    fun popBackStack() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.popBackStack()
    }

    /**
     * Run navigation action.
     *
     * @param actionResId [Int]: resource ID of action to run
     * @param args [Bundle?]: arguments to pass with action
     */
    fun runNavAction(actionResId: Int, args: Bundle? = null) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (args == null) {
            navController.navigate(actionResId)
        } else {
            navController.navigate(actionResId, args)
        }
    }
}
