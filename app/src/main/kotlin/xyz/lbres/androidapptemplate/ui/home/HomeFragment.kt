package xyz.lbres.androidapptemplate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import xyz.lbres.androidapptemplate.databinding.FragmentHomeBinding

/**
 * Initial fragment in the app
 */
class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
            .apply {
                composeView.setContent { HomeScreen() }
            }
        return binding.root
    }
}
