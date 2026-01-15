package com.example.fitnessbreak.ui.screens.reminder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnessbreak.R

@Composable
fun StartContent(onStartButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = onStartButtonClick,
            modifier = Modifier.size(130.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Unspecified
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.start_button),
                contentDescription = "Start Button",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ContinueContent(onStopButtonClick: () -> Unit, onPauseButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            TextButton(
                onClick = onStopButtonClick,
                modifier = Modifier.size(115.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Unspecified
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.stop_button),
                    contentDescription = "Stop Button",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            TextButton(
                onClick = onPauseButtonClick,
                modifier = Modifier.size(115.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Unspecified
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pause_button),
                    contentDescription = "Pause Button",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun PauseContent(onStopButtonClick: () -> Unit, onContinueButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            TextButton(
                onClick = onStopButtonClick,
                modifier = Modifier.size(115.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Unspecified
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.stop_button),
                    contentDescription = "Stop Button",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            TextButton(
                onClick = onContinueButtonClick,
                modifier = Modifier.size(115.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Unspecified
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.start_button),
                    contentDescription = "Continue Button",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}
