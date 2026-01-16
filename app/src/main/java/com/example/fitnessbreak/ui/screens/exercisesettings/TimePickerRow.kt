package com.example.fitnessbreak.ui.screens.exercisesettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnessbreak.ui.theme.CreateButtonStyle
import com.example.fitnessbreak.ui.theme.Peachy
import com.example.fitnessbreak.ui.theme.Tangerine

@Composable
fun ReminderTimePicker(
    hours: Int,
    minutes: Int,
    onTimeChange: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
        // отступы между элементами
    ) {
        Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Часы: ", style = CreateButtonStyle)

            NumberInputField(
                value = hours,
                onValueChange = { newHours -> onTimeChange(newHours, minutes) },
                range = 0..23,
                step = 1
            )
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Минуты: ", style = CreateButtonStyle)

            NumberInputField(
                value = minutes,
                onValueChange = { newMinutes -> onTimeChange(hours, newMinutes) },
                range = 0..55,
                step = 5
            )
        }
    }
}

@Composable
private fun NumberInputField(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    step: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            if (value < range.last) onValueChange(value + step)
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Увеличить",
                tint = Tangerine
            )
        }

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(40.dp)
                .background(
                    color = Peachy,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value.toString().padStart(2, '0'),
                style = CreateButtonStyle
            )
        }

        IconButton(onClick = {
            if (value > range.first) onValueChange(value - step)
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Уменьшить",
                tint = Tangerine
            )
        }
    }
}

