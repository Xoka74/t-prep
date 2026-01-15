package com.shurdev.t_prep.presentation.components.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.presentation.utils.useDebounce

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    hint: String = "",
    searchText: String = "",
    debounceTimeMillis: Long,
    onSearchTextChange: (String) -> Unit,
) {
    var currentSearchText by remember { mutableStateOf(searchText) }

    currentSearchText.useDebounce(
        delayMillis = debounceTimeMillis,
        onChange = onSearchTextChange
    )

    AppTextField(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(16.dp)),
        text = currentSearchText,
        onTextChange = {
            currentSearchText = it
        },
        singleLine = true,
        hint = hint,
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search Icon")
        },
    )
}

@Preview
@Composable
fun SearchFieldPreview() {
    SearchField(
        hint = "Поиск",
        searchText = "",
        debounceTimeMillis = 1000L,
        onSearchTextChange = {},
    )
}