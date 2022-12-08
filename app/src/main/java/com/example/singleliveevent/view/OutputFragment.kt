package com.example.singleliveevent.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.singleliveevent.R
import com.example.singleliveevent.databinding.FragmentOutputBinding
import com.example.singleliveevent.util.launchWith
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class OutputFragment : Fragment(R.layout.fragment_output) {

    private val viewModel by hiltNavGraphViewModels<HomeViewModel>(R.id.home_graph)
    private var _binding: FragmentOutputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOutputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.dataFlow.onEach {
            binding.resultTextView.text = "${resources.getString(R.string.output)} $it"
        }.launchWith(lifecycle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/*
onViewCreated

No destination with ID 2131230938 is on the NavController's back stack. The current destination is Destination(com.example.singleliveevent:id/homeFragment)

 */