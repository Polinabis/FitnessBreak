package com.example.fitnessbreak.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnessbreak.R
import com.example.fitnessbreak.ui.theme.DarkGreen
import com.example.fitnessbreak.ui.theme.Tangerine

@Composable
fun BottomNavigationItem(
    title: String,
    isSelected: Boolean,
    selectedIcon: Int,
    unselectedIcon: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(
                id = if (isSelected) selectedIcon else unselectedIcon
            ),
            contentDescription = title,
            tint = Color.Unspecified // чтобы использовался цвет из иконки
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            color = if (isSelected) Tangerine else DarkGreen,
            style = MaterialTheme.typography.labelSmall
        )
    }
}