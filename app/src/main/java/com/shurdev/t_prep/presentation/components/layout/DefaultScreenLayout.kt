package com.shurdev.t_prep.presentation.components.layout

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.shurdev.t_prep.presentation.components.buttons.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultScreenLayout(
    modifier: Modifier = Modifier,
    onBackInvoked: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    title: String? = null,
    fab: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
    ) {
        Column {
            if (actions != null || onBackInvoked != null || title != null) {
                TopAppBar(
                    title = {
                        if (title != null) {
                            Text(
                                modifier = Modifier,
                                text = title,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                            )
                        }
                    },
                    navigationIcon = { if (onBackInvoked != null) BackButton(onBackInvoked) },
                    actions = actions ?: {}
                )
            }

            Box(modifier) {
                content()
            }
        }

        fab?.let { it() }
    }


    if (onBackInvoked != null) {
        BackHandler(onBack = onBackInvoked)
    }
}