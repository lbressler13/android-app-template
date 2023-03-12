package xyz.lbres.androidapptemplate.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.lbres.androidapptemplate.R
import xyz.lbres.androidapptemplate.databinding.FragmentFirstBinding
import xyz.lbres.androidapptemplate.ui.BaseFragment

/**
 * Initial fragment in the app
 */
class FirstFragment : BaseFragment() {
    private lateinit var binding: FragmentFirstBinding

    /**
     * Initialize fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        binding.navigateText.setOnClickListener {
            requireBaseActivity().runNavAction(R.id.navigateFirstToSecond)
        }

        return binding.root
    }
}
