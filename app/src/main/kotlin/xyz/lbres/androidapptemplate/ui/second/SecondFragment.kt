package xyz.lbres.androidapptemplate.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.databinding.FragmentSecondBinding
import xyz.lbres.androidapptemplate.ui.BaseFragment

/**
 * Second fragment to navigate to
 */
class SecondFragment : BaseFragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: SecondViewModel

    override var actionBarTitleResId: Int = R.string.title_second_fragment

    /**
     * Initialize fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SecondViewModel::class.java]

        viewModel.visitedCount++

        binding.countMessage.text = getString(R.string.visited_message, viewModel.visitedCount)
        binding.popBackstackText.setOnClickListener { closeFragment() }

        return binding.root
    }
}
