package com.shurdev.t_prep.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.shurdev.t_prep.presentation.screens.cards.CardsScreen
import com.shurdev.t_prep.presentation.screens.createModule.CreateModuleScreen
import com.shurdev.t_prep.presentation.screens.dialogs.deleteCardDialog.DeleteCardDialog
import com.shurdev.t_prep.presentation.screens.dialogs.deleteModuleDialog.DeleteModuleDialog
import com.shurdev.t_prep.presentation.screens.moduleSettings.ModuleSettingsScreen
import com.shurdev.t_prep.presentation.screens.home.HomeScreen
import com.shurdev.t_prep.presentation.screens.login.LoginScreen
import com.shurdev.t_prep.presentation.screens.quiz.QuizScreen
import com.shurdev.t_prep.presentation.screens.modules.ModulesScreen
import com.shurdev.t_prep.presentation.screens.profile.ProfileRoute
import com.shurdev.t_prep.presentation.screens.settings.SettingsRoute
import com.shurdev.t_prep.presentation.screens.splash.SplashScreen
import com.shurdev.t_prep.presentation.screens.test.TestScreen

@Composable
fun NavGraph(
    padding: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen()
        }

        composable("login") {
            LoginScreen(
                onSuccessLogin = {},
            )
        }
        composable("modules") {
            ModulesScreen(
                onModuleClick = { moduleId ->
                    navController.navigate("cards/$moduleId")
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
            HomeScreen(
                onModuleClick = { moduleId ->
                    navController.navigate("cards/$moduleId")
                },
            )
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

        composable("cards/{moduleId}") { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: ""
            CardsScreen(
                moduleId = moduleId,
                onBack = navController::navigateUp,
                onQuizClick = { moduleId ->
                    navController.navigate("quiz/$moduleId")
                },
                onTestClick = { moduleId ->
                    navController.navigate("test/$moduleId")
                },
                onEditModuleClick = {
                    navController.navigate("edit_module/$moduleId")
                },
                onDeleteModuleClick = {
                    navController.navigate("delete_module/$moduleId")
                },
                onDeleteCardClick = { moduleId, cardId ->
                    navController.navigate("delete_card/$moduleId/$cardId")
                },
                onEditCardClick = { moduleId, cardId ->
                    navController.navigate("edit_card/$moduleId/$cardId")
                },
            )
        }


        dialog("delete_module/{moduleId}") {
            DeleteModuleDialog(
                onBack = navController::navigateUp
            )
        }

        dialog("delete_card/{moduleId}/{cardId}") {
            DeleteCardDialog(
                onBack = navController::navigateUp
            )
        }

        dialog("edit_card/{moduleId}/{cardId}") {

        }

        composable("edit_module/{moduleId}") {
            ModuleSettingsScreen(
                onBack = navController::navigateUp
            )
        }

        composable("test/{moduleId}") {
            TestScreen(
                onBack = navController::navigateUp
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