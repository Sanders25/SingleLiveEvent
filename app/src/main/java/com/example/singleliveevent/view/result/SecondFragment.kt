package com.example.singleliveevent.view.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.singleliveevent.R
import com.example.singleliveevent.databinding.FragmentSecondBinding
import com.example.singleliveevent.util.launchWith
import com.example.singleliveevent.util.navigate
import com.example.singleliveevent.view.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class SecondFragment : Fragment(R.layout.fragment_second) {

    private val viewModel by hiltNavGraphViewModels<HomeViewModel>(R.id.home_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentSecondBinding.bind(view)

        viewModel.dataFlow.onEach { result ->
            delay(3.seconds)
            binding.textviewResult.text = "Result: $result"
            binding.nextButton.apply {
                isEnabled = true
                setOnClickListener {
                    navigate(SecondFragmentDirections.actionSecondFragmentToResultFragmentDialog(
                        result
                    ))
                }
            }
//            viewModel.actionDone()
        }.launchWith(lifecycle)
    }
}