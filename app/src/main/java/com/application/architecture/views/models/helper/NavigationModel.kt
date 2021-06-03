package com.application.architecture.views.models.helper

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.application.architecture.views.utils.Enums


data class NavigationModel(
    val id: Int,
    val bundle: Bundle = bundleOf(),
    val fragNavigatorExtras : FragmentNavigator.Extras? = null,
    val navigationType: Enums.NavigationType = Enums.NavigationType.ACTION
)