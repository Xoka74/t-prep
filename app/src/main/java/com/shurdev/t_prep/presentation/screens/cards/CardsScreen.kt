package com.shurdev.t_prep.presentation.screens.cards

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.presentation.components.cards.FlipCard
import com.shurdev.t_prep.presentation.screens.cards.viewModel.CardsViewModel
import com.shurdev.t_prep.presentation.screens.quiz.LoadingView

@Composable
fun CardsScreen(
    moduleId: String,
    viewModel: CardsViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.value
    val cardColor = Color(236, 236, 236, 255)

    LaunchedEffect(moduleId) {
        viewModel.loadCards(moduleId)
    }

    when {
        state.isLoading -> LoadingView()
        state.error != null -> Text(state.error ?: "")
        else -> {
            Column {

                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp),
                    text = "Мои карточки",
                    fontSize = 32.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 32.dp)
                ) {

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(144, 202, 249, 255)
                        ),
                        onClick = viewModel::onQuizClicked
                    ) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Повторение"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(165, 214, 167, 255)
                        ),
                        onClick = viewModel::onTestClicked
                    ) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Тест"
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "${state.currentIndex + 1} / ${state.cards.size}",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

                AnimatedContent(
                    targetState = state.currentIndex,
                    transitionSpec = {
                        val direction = if (state.slideDirection == SlideDirection.Forward) {
                            AnimatedContentTransitionScope.SlideDirection.Left
                        } else {
                            AnimatedContentTransitionScope.SlideDirection.Right
                        }

                        slideIntoContainer(
                            towards = direction,
                            animationSpec = tween(300)
                        ) togetherWith slideOutOfContainer(
                            towards = direction,
                            animationSpec = tween(300)
                        )
                    },
                    label = "Card slide animation"
                ) { it ->

                    it.dp
                    Box(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        FlipCard(
                            cardFace = state.cardFace,
                            onClick = {
                                viewModel.flipCard()
                            },
                            modifier = Modifier.aspectRatio(1f),
                            front = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(cardColor),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(state.currentCard?.question.toString() ?: "")
                                }
                            },
                            back = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(cardColor),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val correctAnswerIndex = state.currentCard?.correctAnswer ?: 0
                                    Text(
                                        state.currentCard?.options?.getOrNull(correctAnswerIndex)
                                            .toString() ?: ""
                                    )
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = cardColor
                        ),
                        onClick = viewModel::prevCard
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = cardColor
                        ),
                        onClick = viewModel::nextCard
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, null)
                        }
                    }
                }
            }
        }
    }
}
