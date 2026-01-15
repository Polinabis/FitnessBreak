package com.example.fitnessbreak.ui.components

import com.example.fitnessbreak.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnessbreak.ui.theme.Background
import com.example.fitnessbreak.ui.theme.ScreenTitleStyle

@Composable
fun ScreenTemplate(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Background)
            .padding(top = 15.dp, bottom = 15.dp, start = 15.dp, end = 15.dp)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.Start
    ) {

        Row {
            Image(
                painter = painterResource(id = R.drawable.fitness_break_logo),
                contentDescription = "App Logo",
                modifier = Modifier.sizeIn(maxWidth = 120.dp, maxHeight = 17.dp),
                contentScale = ContentScale.Fit
            )
        }
        Row {
            Spacer(modifier = Modifier.height(10.dp))
        }
        Row (modifier = modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = ScreenTitleStyle
            )
        }

        content()
    }
}