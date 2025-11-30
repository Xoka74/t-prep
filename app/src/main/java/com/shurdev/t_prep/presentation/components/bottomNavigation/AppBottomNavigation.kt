package com.shurdev.t_prep.presentation.components.bottomNavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: BottomNavigationItem,
    onItemClick: (BottomNavigationItem) -> Unit,
) {
    NavigationBar {
        items.forEach { item ->
            val isSelected = item == selectedItem
            val name = stringResource(item.nameResId)

            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(
                            id = if (isSelected) item.selectedIconResId else item.unSelectedIconResId
                        ),
                        contentDescription = name
                    )
                },
                label = { Text(name) },
                selected = isSelected,
                onClick = { onItemClick(item) }
            )
        }
    }
}