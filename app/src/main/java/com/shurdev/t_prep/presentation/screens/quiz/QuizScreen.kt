package com.shurdev.t_prep.presentation.screens.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    moduleId: String,
    viewModel: QuizViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    // TODO
    val state = viewModel.uiState.value

    LaunchedEffect(moduleId) {
        viewModel.startQuiz(moduleId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Тестирование") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        when {
            state.isLoading -> LoadingView()
            state.showResults -> ResultsView(state, onBack)
            else -> QuizContentView(state, viewModel, padding)
        }
    }
}

@Composable
private fun QuizContentView(
    state: QuizState,
    viewModel: QuizViewModel,
    padding: PaddingValues
) {
    val currentCard = state.currentCard

    Column(modifier = Modifier
        .padding(padding)
        .padding(16.dp)
    ) {
        // Progress
        LinearProgressIndicator(
            progress = { state.progress },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Question
        Text(
            text = currentCard?.question ?: "",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Options
        currentCard?.options?.forEachIndexed { index, option ->
            AnswerOption(
                text = option,
                isSelected = state.selectedAnswer == index,
                isCorrect = state.isAnswerCorrect == true && index == currentCard.correctAnswer,
                isWrong = state.isAnswerCorrect == false && state.selectedAnswer == index,
                onClick = { viewModel.selectAnswer(index) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.nextCard() },
            enabled = state.selectedAnswer != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Далее")
        }
    }
}