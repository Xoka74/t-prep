package com.shurdev.t_prep.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shurdev.t_prep.presentation.screens.quiz.QuizScreen
import com.shurdev.t_prep.presentation.screens.subjects.SubjectsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "subjects") {
        composable("subjects") {
            SubjectsScreen { subjectId ->
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
}