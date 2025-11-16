package com.shurdev.t_prep.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shurdev.t_prep.presentation.components.viewModel.AuthViewModel
import com.shurdev.t_prep.presentation.screens.login.LoginScreen
import com.shurdev.t_prep.presentation.screens.quiz.QuizScreen
import com.shurdev.t_prep.presentation.screens.modules.ModulesScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    val authViewModel = hiltViewModel<AuthViewModel>()

    val isAuthorizedState by authViewModel.isAuthenticated.collectAsState()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onSuccessLogin = {},
            )
        }
        composable("modules") {
            ModulesScreen { moduleId ->
                navController.navigate("quiz/$moduleId")
            }
        }

        composable("quiz/{moduleId}") { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: ""
            QuizScreen(
                moduleId = moduleId,
                onBack = { navController.popBackStack() }
            )
        }
    }

    LaunchedEffect(isAuthorizedState) {
        val route = when (isAuthorizedState) {
            true -> "modules"
            false -> "login"
            null -> "login"
        }

        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }
}