package com.example.singleliveevent.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

fun Fragment.findDestinationNavController(): NavController? {
    return findNavController().takeIf {
        javaClass.name == when (val destination = it.currentDestination) {
            is FragmentNavigator.Destination -> destination.className
            is DialogFragmentNavigator.Destination -> destination.className
            else -> null
        }
    }
}