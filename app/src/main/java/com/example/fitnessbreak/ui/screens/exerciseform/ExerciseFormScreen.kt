package com.example.fitnessbreak.ui.screens.exerciseform

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitnessbreak.R
import com.example.fitnessbreak.ui.components.ScreenTemplate
import com.example.fitnessbreak.ui.theme.AddImageButtonStyle
import com.example.fitnessbreak.ui.theme.CreateButtonLightStyle
import com.example.fitnessbreak.ui.theme.CreateButtonPaleStyle
import com.example.fitnessbreak.ui.theme.CreateButtonStyle
import com.example.fitnessbreak.ui.theme.DarkGreen
import com.example.fitnessbreak.ui.theme.Peachy
import com.example.fitnessbreak.ui.theme.SaveNewExerciseButtonStyle
import com.example.fitnessbreak.ui.theme.Tangerine

@Composable
fun ExerciseFormScreen(
    onExerciseSaved: () -> Unit = {}
) {
    val context = LocalContext.current
    var exerciseName by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var isImageSelected by remember { mutableStateOf(false) }

    // Логика выбора изображения
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            isImageSelected = true
        }
    }

    ScreenTemplate(title = "Создать упражнение") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            // Заголовок "Название"
            Text(
                text = "Название",
                style = CreateButtonStyle,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 0.dp)
            )

            // Поле ввода с нижней рыжей полоской
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = exerciseName,
                    onValueChange = { text ->
                        if (text.length <= 30) exerciseName = text
                    },
                    placeholder = {
                        Text(
                            text = "Круговые вращения головы",
                            style = CreateButtonPaleStyle
                        )
                    },
                    textStyle = CreateButtonLightStyle,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = Tangerine
                    )
                )

                // Рыжая полоска внизу
                HorizontalDivider(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth(),
                    thickness = 2.dp,
                    color = Tangerine
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Блок с изображением
            if (isImageSelected && selectedImageUri != null) {
                // Отображаем выбранное изображение + кнопку "Удалить"
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    AsyncImage(
                        model = selectedImageUri,
                        contentDescription = "Выбранное изображение",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            isImageSelected = false
                            selectedImageUri = null
                        },
                        modifier = Modifier
                            .width(280.dp)
                            .height(40.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkGreen)
                    ) {
                        Text(
                            text = "Удалить изображение",
                            style = AddImageButtonStyle
                        )
                    }
                }
            } else {
                // Кнопка "Добавить изображение"
                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier
                        .width(280.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkGreen)
                ) {
                    Text(
                        text = "Добавить изображение",
                        style = AddImageButtonStyle
                    )
                }
            }

            Spacer(modifier = Modifier.height(45.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                // Кнопка "Сохранить"
                Button(
                    onClick = {
                        // Валидация: имя не пустое и изображение выбрано
                        if (exerciseName.isNotBlank() && isImageSelected) {
                            // TODO: сохранить в БД
                            println("Сохраняем упражнение: $exerciseName")
                            onExerciseSaved()
                        }
                    },
                    enabled = exerciseName.isNotBlank() && isImageSelected,
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Tangerine,
                        disabledContainerColor = Peachy
                    )
                ) {
                    Text(
                        text = "Сохранить",
                        style = SaveNewExerciseButtonStyle
                    )
                }
            }

        }


    }
}