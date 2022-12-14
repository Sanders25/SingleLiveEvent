package com.example.singleliveevent.view.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.singleliveevent.R
import com.example.singleliveevent.databinding.FragmentSecondBinding
import com.example.singleliveevent.util.findDestinationNavController
import com.example.singleliveevent.util.launchWith
import com.example.singleliveevent.view.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class SecondFragment : Fragment(R.layout.fragment_second) {

    private val viewModel by hiltNavGraphViewModels<HomeViewModel>(R.id.firstFragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentSecondBinding.bind(view)
        var result: Int? = null
        val findDestinationNavController = {
            if (viewModel.performActionFlow.value == null)
                findDestinationNavController()
            else null
        }

        binding.nextButton.setOnClickListener {
            result?.let {
                findDestinationNavController()?.navigate(
                    SecondFragmentDirections.toResultFragmentDialog(it)
                )
            }
        }

        viewModel.dataFlow.onEach { _result ->
            delay(3.seconds)
            binding.textviewResult.text = resources.getString(R.string.result) + _result
            binding.nextButton.apply {
                result = _result
                isEnabled = true
            }
        }.launchWith(lifecycle)
    }
}