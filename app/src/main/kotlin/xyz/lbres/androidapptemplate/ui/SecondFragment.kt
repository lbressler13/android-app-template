package xyz.lbres.androidapptemplate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import xyz.lbres.androidapptemplate.BaseActivity
import xyz.lbres.androidapptemplate.databinding.FragmentSecondBinding

/**
 * Second fragment to navigate to
 */
class SecondFragment : NavHostFragment() {
    private lateinit var binding: FragmentSecondBinding

    /**
     * Initialize fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        binding.popBackstackText.setOnClickListener {
            (requireActivity() as BaseActivity).popBackStack()
        }

        return binding.root
    }
}
