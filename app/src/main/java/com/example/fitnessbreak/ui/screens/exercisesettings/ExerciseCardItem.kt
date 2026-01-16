package com.example.fitnessbreak.ui.screens.exercisesettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import android.net.Uri
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import com.example.fitnessbreak.domain.model.ExerciseCard
import com.example.fitnessbreak.domain.model.ImageSource
import com.example.fitnessbreak.ui.theme.LightGreen
import com.example.fitnessbreak.ui.theme.Peachy
import com.example.fitnessbreak.R
import com.example.fitnessbreak.ui.theme.Background
import com.example.fitnessbreak.ui.theme.CardTitleStyle
import com.example.fitnessbreak.ui.theme.DarkGreen
import com.example.fitnessbreak.ui.theme.Tangerine

@Composable
fun ExerciseCardItem(
    card: ExerciseCard,
    onButtonClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {

    val backgroundColor = if(isSelected) LightGreen else Peachy

    Column(
        modifier = modifier
            .width(220.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Картинка
        Box(
            modifier = Modifier
                .size(height = 150.dp, width = 200.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Transparent)
        ) {
            val imageModel = when (val source = card.imageSource) {
                is ImageSource.Resource -> source.resId
                is ImageSource.FileUri -> Uri.parse(source.uri)
            }

            AsyncImage(
                model = imageModel,
                contentDescription = card.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth,
                placeholder = androidx.compose.ui.res.painterResource(
                    id = R.drawable.default_image
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = card.title,
            style = CardTitleStyle,
            textAlign = TextAlign.Center,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка
        Button(
            onClick = onButtonClick,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = (if (isSelected) DarkGreen else Tangerine)
            )
        ) {
            Text((if (isSelected) "Убрать" else "Выбрать"), style = CardTitleStyle, color = Background)
        }
    }

}