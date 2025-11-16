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
        composable("subjects") {
            ModulesScreen { subjectId ->
                navController.navigate("quiz/$subjectId")
            }
        }

        composable("quiz/{subjectId}") { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            QuizScreen(
                subjectId = subjectId,
                onBack = { navController.popBackStack() }
            )
        }
    }

    LaunchedEffect(isAuthorizedState) {
        val route = when (isAuthorizedState) {
            true -> "subjects"
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