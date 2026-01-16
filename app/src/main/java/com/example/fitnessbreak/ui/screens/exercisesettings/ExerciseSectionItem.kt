package com.example.fitnessbreak.ui.screens.exercisesettings

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnessbreak.R
import com.example.fitnessbreak.domain.model.ExerciseSection
import com.example.fitnessbreak.ui.theme.CreateButtonStyle
import com.example.fitnessbreak.ui.theme.ScreenTitleStyle

@Composable
fun ExerciseSectionItem(
    section: ExerciseSection,
    selectedCardIds: Set<Int>,
    onSwitchButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToForm: () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = section.title,
                style = ScreenTitleStyle,
            )

            TextButton(
                onClick = onNavigateToForm,
                interactionSource = remember { MutableInteractionSource() },
                colors = androidx.compose.material3.ButtonDefaults.textButtonColors(
                    contentColor = androidx.compose.ui.graphics.Color.Unspecified
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.create_button),
                        contentDescription = "Create Button",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Упражнение",
                        style = CreateButtonStyle
                    )
                }
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {

            items(section.cards) { card ->
                val isSelected = card.id in selectedCardIds
                ExerciseCardItem(
                    card = card,
                    onButtonClick = { onSwitchButtonClick(card.id) },
                    isSelected = isSelected
                )
            }
        }
    }
}