package com.example.fitnessbreak.ui.screens.alarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitnessbreak.R
import com.example.fitnessbreak.ui.theme.Background
import com.example.fitnessbreak.ui.theme.ScreenTitleStyle

// ui/screens/alarm/AlarmCompletedContent.kt
@Composable
fun AlarmCompletedContent() {


    Box(
        modifier = Modifier.fillMaxSize().systemBarsPadding(),
    ) {
        // Фоновая картинка
        Image(
            painter = painterResource(id = R.drawable.happy_orange_3),
            contentDescription = "Поздравление",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 15.dp, bottom = 15.dp, start = 15.dp, end = 15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fitness_break_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.sizeIn(maxWidth = 120.dp, maxHeight = 17.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Великолепно!\nТак держать!",
                    style = ScreenTitleStyle,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}