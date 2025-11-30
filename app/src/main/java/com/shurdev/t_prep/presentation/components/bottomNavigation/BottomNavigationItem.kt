package com.shurdev.t_prep.presentation.components.bottomNavigation

import com.shurdev.t_prep.R

sealed class BottomNavigationItem(
    val nameResId: Int,
    val selectedIconResId: Int,
    val unSelectedIconResId: Int,
    val route: String,
) {
    data object Home : BottomNavigationItem(
        nameResId = R.string.home,
        selectedIconResId = R.drawable.icon_home_filled,
        unSelectedIconResId = R.drawable.icon_home_unfilled,
        route = "home"
    )

    data object Modules : BottomNavigationItem(
        nameResId = R.string.library,
        selectedIconResId = R.drawable.icon_modules,
        unSelectedIconResId = R.drawable.icon_modules,
        route = "modules"
    )

    data object Profile : BottomNavigationItem(
        nameResId = R.string.profile,
        selectedIconResId = R.drawable.icon_person_filled,
        unSelectedIconResId = R.drawable.icon_person_unfilled,
        route = "profile"
    )
}