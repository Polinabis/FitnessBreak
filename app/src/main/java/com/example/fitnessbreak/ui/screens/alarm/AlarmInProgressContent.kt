package com.example.fitnessbreak.ui.screens.alarm

import android.net.Uri
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.fitnessbreak.R
import com.example.fitnessbreak.domain.model.ExerciseCard
import com.example.fitnessbreak.domain.model.ImageSource
import com.example.fitnessbreak.ui.theme.Background
import com.example.fitnessbreak.ui.theme.LightGreen
import com.example.fitnessbreak.ui.theme.LogoGreen
import com.example.fitnessbreak.ui.theme.SaveNewExerciseButtonStyle
import com.example.fitnessbreak.ui.theme.ScreenTitleStyle
import com.example.fitnessbreak.ui.theme.Tangerine

@Composable
fun AlarmInProgressContent(
    modifier: Modifier = Modifier,
    progress: Float,
    card: ExerciseCard,
    onDoneClick: () -> Unit
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
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Разминка",
                style = ScreenTitleStyle
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        // Прогресс-бар
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Tangerine,
            trackColor = LogoGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Карточка упражнения
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightGreen
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f) // ← даёт изображению всё свободное место
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    val imageModel = when (val source = card.imageSource) {
                        is ImageSource.Resource -> source.resId
                        is ImageSource.FileUri -> Uri.parse(source.uri)
                    }
                    AsyncImage(
                        model = imageModel,
                        contentDescription = card.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = card.title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            // Кнопка "Сделано"
            Button(
                onClick = onDoneClick,
                modifier = Modifier
                    .width(200.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Tangerine
                )
            ){
                Text("Сделано", style = SaveNewExerciseButtonStyle)
            }
        }

    }

}