package com.shurdev.t_prep.presentation.screens.subjects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.presentation.components.ErrorView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectsScreen(
    viewModel: SubjectsViewModel = hiltViewModel(),
    onSubjectClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Подготовка к экзаменам") })
        }
    ) { padding ->
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                ErrorView(error = state.error, onRetry = { viewModel.loadSubjects() })
            }

            else -> {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    items(state.subjects) { subject ->
                        SubjectCard(
                            subject = subject,
                            onClick = { onSubjectClick(subject.id) }
                        )
                    }
                }
            }
        }
    }
}