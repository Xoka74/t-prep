package com.shurdev.t_prep.presentation.components.layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun StickyBottomColumn(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    stickyBottom: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }

        stickyBottom()
    }
}