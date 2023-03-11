package xyz.lbres.androidapptemplate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import xyz.lbres.androidapptemplate.BaseActivity
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.databinding.FragmentFirstBinding

class FirstFragment : NavHostFragment() {
    private lateinit var binding: FragmentFirstBinding

    /**
     * Initialize fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        binding.navigateText.setOnClickListener {
            (requireActivity() as BaseActivity).runNavAction(R.id.navigateFirstToSecond)
        }

        return binding.root
    }
}
