package com.shurdev.t_prep.presentation.screens.modules.components

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.ModifierLocalMap
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.R
import com.shurdev.t_prep.domain.models.Module
import com.shurdev.t_prep.presentation.utils.toResString

@Composable
fun ModuleCard(
    module: Module,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showViewAccess: Boolean = true,
) {
    val progress = if (module.totalCards > 0) {
        module.completedCards.toFloat() / module.totalCards.toFloat()
    } else {
        0f
    }

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = module.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = module.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ProgressSection(
                progress = progress,
                completed = module.completedCards,
                total = module.totalCards
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (showViewAccess) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_eye),
                        contentDescription = null,
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = module.viewAccess.toResString(),
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = module.editAccess.toResString(),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Composable
private fun ProgressSection(
    progress: Float,
    completed: Int,
    total: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Прогресс",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "$completed/$total",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = when {
                progress >= 0.8 -> Color(0xFF4CAF50)  // Зеленый
                progress >= 0.5 -> Color(0xFFFF9800)  // Оранжевый
                else -> MaterialTheme.colorScheme.primary  // Основной цвет
            },
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${(progress * 100).toInt()}% выполнено",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StartButton(
    progress: Float,
    onClick: () -> Unit
) {
    val buttonText = when {
        progress >= 1f -> "Повторить"
        progress > 0f -> "Продолжить"
        else -> "Начать тест"
    }

    val icon = when {
        progress >= 1f -> Icons.Default.CheckCircle
        else -> Icons.Default.PlayArrow
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = buttonText,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = buttonText,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}