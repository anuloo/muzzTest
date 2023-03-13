package com.example.muztest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.muztest.ui.theme.AvenirMedium
import com.example.muztest.ui.theme.PrimaryColor
import com.example.muztest.ui.theme.SecondaryColor
import com.example.muztest.ui.theme.spacing

@Composable
fun SentMessageRow(
    text: String,
) {

    // Whole column that contains chat bubble and padding on start or end
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = MaterialTheme.spacing.extraLarge,
                end = MaterialTheme.spacing.small,
                top = MaterialTheme.spacing.extraSmall,
                bottom = MaterialTheme.spacing.extraSmall
            )
    ) {
        // This is chat bubble

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(
                    topStart = MaterialTheme.spacing.medium,
                    topEnd = MaterialTheme.spacing.medium,
                    bottomStart = MaterialTheme.spacing.medium))
                .background(PrimaryColor)
                .clickable { },
            content = {
                Text(
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.small,
                        top = MaterialTheme.spacing.extraSmall,
                        end = MaterialTheme.spacing.extraSmall,
                        bottom = MaterialTheme.spacing.extraSmall
                    ),
                    text = text,
                    style = AvenirMedium.copy(
                        color = SecondaryColor
                    ),
                )

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SentMessageRowPreview() {
    SentMessageRow(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip"
    )
}