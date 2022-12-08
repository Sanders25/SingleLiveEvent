package com.example.singleliveevent.view.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.example.singleliveevent.R
import com.example.singleliveevent.databinding.FragmentFirstBinding
import com.example.singleliveevent.util.launchWith
import com.example.singleliveevent.util.navigate
import com.example.singleliveevent.view.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    private val viewModel by hiltNavGraphViewModels<HomeViewModel>(R.id.home_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentFirstBinding.bind(view)

        viewModel.dataFlow.onEach {
            if (viewModel.performActionFlow.value != null) {
                navigate(
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment()
                )
//                viewModel.actionDone()
            }
        }.launchWith(lifecycle)

        binding.buttonNavigate.setOnClickListener {
            viewModel.startAction()
        }
    }
}

enum class Flag {
    ACTION_DONE
}