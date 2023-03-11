package xyz.lbres.androidapptemplate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.databinding.FragmentSecondBinding

/**
 * Second fragment to navigate to
 */
class SecondFragment : BaseFragment() {
    private lateinit var binding: FragmentSecondBinding
    override var actionBarTitleResId: Int = R.string.title_second_fragment

    /**
     * Initialize fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        binding.popBackstackText.setOnClickListener { closeFragment() }

        return binding.root
    }
}
