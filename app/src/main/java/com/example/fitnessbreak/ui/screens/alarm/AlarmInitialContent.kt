package com.example.fitnessbreak.ui.screens.alarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnessbreak.R
import com.example.fitnessbreak.ui.theme.AlarmButtonStyle
import com.example.fitnessbreak.ui.theme.Background
import com.example.fitnessbreak.ui.theme.DarkGreen
import com.example.fitnessbreak.ui.theme.LogoGreen
import com.example.fitnessbreak.ui.theme.SaveNewExerciseButtonStyle
import com.example.fitnessbreak.ui.theme.ScreenTitleStyle
import com.example.fitnessbreak.ui.theme.Tangerine

@Composable
fun AlarmInitialContent(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
    onPostponeClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Background)
            .padding(top = 15.dp, bottom = 15.dp, start = 15.dp, end = 15.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        )
        {
            Image(
                painter = painterResource(id = R.drawable.fitness_break_logo),
                contentDescription = "App Logo",
                modifier = Modifier.sizeIn(maxWidth = 120.dp, maxHeight = 17.dp),
                contentScale = ContentScale.Fit
            )
        }
        Row {
            Spacer(modifier = Modifier.height(20.dp))
        }
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Время для разминки!",
                style = ScreenTitleStyle
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                onClick = onStartClick,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Tangerine
                )
            ) {
                Text("Начать", style = AlarmButtonStyle)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                onClick = onPostponeClick,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LogoGreen
                )
            ) {
                Text("Отложить на 15 минут", style = AlarmButtonStyle)
            }
        }
    }
}