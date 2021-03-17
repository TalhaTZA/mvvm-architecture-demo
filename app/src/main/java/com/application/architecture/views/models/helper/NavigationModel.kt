package com.application.architecture.views.models.helper

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras


data class NavigationModel(
    val id: Int,
    val bundle: Bundle = bundleOf(),
    val fragNavigatorExtras : FragmentNavigator.Extras? = null
)