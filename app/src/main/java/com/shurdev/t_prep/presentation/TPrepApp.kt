package com.shurdev.t_prep.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shurdev.t_prep.presentation.components.bottomNavigation.AppBottomNavigation
import com.shurdev.t_prep.presentation.components.bottomNavigation.BottomNavigationItem
import com.shurdev.t_prep.presentation.components.viewModel.AuthViewModel
import com.shurdev.t_prep.presentation.navigation.NavGraph

@SuppressLint("RestrictedApi")
@Composable
fun TPrepApp(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem.Home,
            BottomNavigationItem.Modules,
            BottomNavigationItem.Profile,
        )
    }

    val selectedDestination = bottomNavigationItems.firstOrNull { item ->
        currentDestination?.hasRoute(item.route, null) == true
    }

    val authViewModel = hiltViewModel<AuthViewModel>()

    val isAuthorizedState by authViewModel.isAuthenticated.collectAsState()

    Scaffold(
        bottomBar = {
            if (selectedDestination != null) {
                BottomAppBar {
                    AppBottomNavigation(
                        items = bottomNavigationItems,
                        selectedItem = selectedDestination,
                        onItemClick = { item ->
                            navController.navigate(item.route) {
                                popUpTo(selectedDestination.route) {
                                    saveState = true
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavGraph(
            padding = padding,
            navController = navController
        )

        LaunchedEffect(isAuthorizedState) {
            val route = when (isAuthorizedState) {
                true -> "modules"
                false -> "login"
                null -> "splash"
            }

            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
    }
}