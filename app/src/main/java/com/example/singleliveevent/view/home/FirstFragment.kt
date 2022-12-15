package com.example.singleliveevent.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.singleliveevent.R
import com.example.singleliveevent.databinding.FragmentFirstBinding
import com.example.singleliveevent.util.findDestinationNavController
import com.example.singleliveevent.util.launchWith
import com.example.singleliveevent.view.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    private val viewModel by hiltNavGraphViewModels<HomeViewModel>(R.id.firstFragment)
    private var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { bundle = it }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentFirstBinding.bind(view)

        viewModel.dataFlow.combine(
            viewModel.performActionFlow
        ) { _, _ ->
            if (viewModel.performActionFlow.value == null &&
                !bundle.getBoolean(Flag.ACTION_DONE.name)
            ) {
                bundle.putBoolean(Flag.ACTION_DONE.name, true)
                findDestinationNavController()?.navigate(
                    FirstFragmentDirections.toSecondFragment()
                )
            }
        }.launchWith(lifecycle)

        binding.buttonNavigate.setOnClickListener {
            bundle.putBoolean(Flag.ACTION_DONE.name, false)
            viewModel.startAction()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(
            Flag.ACTION_DONE.name,
            bundle.getBoolean(Flag.ACTION_DONE.name)
        )
    }
}

enum class Flag {
    ACTION_DONE
}