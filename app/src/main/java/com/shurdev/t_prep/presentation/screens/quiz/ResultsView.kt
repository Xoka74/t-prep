package com.shurdev.t_prep.presentation.screens.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ResultsView(
    state: QuizState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val score = state.score
    val totalQuestions = state.questions.size
    val percentage =
        if (totalQuestions > 0) (score.toDouble() / totalQuestions * 100).toInt() else 0

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ResultIcon(percentage = percentage)

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = getResultTitle(percentage),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$percentage%",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
//                        color = getResultColor(percentage)
                        color = Color(0xFF4CAF50)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "правильных ответов",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    ResultStatItem(
                        label = "Правильные ответы",
                        value = "$score/$totalQuestions",
                        valueColor = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ResultStatItem(
                        label = "Неправильные ответы",
                        value = "${totalQuestions - score}/$totalQuestions",
                        valueColor = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Вернуться к предметам",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun ResultIcon(percentage: Int) {
    val (icon, tint) = Pair(
        Icons.Default.Done,
        MaterialTheme.colorScheme.primary,
    )

    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Результат",
            modifier = Modifier.size(64.dp),
            tint = tint
        )
    }
}

@Composable
private fun ResultStatItem(
    label: String,
    value: String,
    valueColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = valueColor
        )
    }
}

private fun getResultTitle(percentage: Int): String {
    return when {
        percentage >= 90 -> "Отличный результат! 🎉"
        percentage >= 80 -> "Очень хорошо! 👍"
        percentage >= 70 -> "Хорошая работа! 👏"
        percentage >= 60 -> "Неплохо! 😊"
        percentage >= 50 -> "Можно лучше 🤔"
        else -> "Попробуйте еще раз 📚"
    }
}
