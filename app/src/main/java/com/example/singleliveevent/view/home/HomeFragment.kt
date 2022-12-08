package com.example.singleliveevent.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.singleliveevent.R
import com.example.singleliveevent.databinding.FragmentFirstBinding
import com.example.singleliveevent.databinding.FragmentHomeBinding
import com.example.singleliveevent.util.launchWith
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {}