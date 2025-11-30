package com.shurdev.t_prep.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shurdev.t_prep.presentation.screens.createModule.CreateModuleScreen
import com.shurdev.t_prep.presentation.screens.home.HomeScreen
import com.shurdev.t_prep.presentation.screens.login.LoginScreen
import com.shurdev.t_prep.presentation.screens.quiz.QuizScreen
import com.shurdev.t_prep.presentation.screens.modules.ModulesScreen
import com.shurdev.t_prep.presentation.screens.profile.ProfileRoute
import com.shurdev.t_prep.presentation.screens.settings.SettingsRoute

@Composable
fun NavGraph(
    padding: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onSuccessLogin = {},
            )
        }
        composable("modules") {
            ModulesScreen(
                onModuleClick = { moduleId ->
                    navController.navigate("quiz/$moduleId")
                },
                onCreateModuleClick = {
                    navController.navigate("create_module")
                }
            )
        }

        composable("create_module") {
            CreateModuleScreen(
                onBackInvoked = navController::navigateUp,
            )
        }

        composable("home") {
            HomeScreen()
        }

        composable("profile") {
            ProfileRoute(
                onSettingsClick = {
                    navController.navigate("settings")
                }
            )
        }

        composable("settings") {
            SettingsRoute(
                onDismiss = navController::navigateUp,
            )
        }

        composable("quiz/{moduleId}") { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: ""
            QuizScreen(
                moduleId = moduleId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}