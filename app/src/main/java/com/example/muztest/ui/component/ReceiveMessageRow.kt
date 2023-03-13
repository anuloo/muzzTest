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
import com.example.muztest.ui.theme.LightGrey
import com.example.muztest.ui.theme.spacing

@Composable
fun ReceiveMessageRow(
    text: String,
) {

    // Whole column that contains chat bubble and padding on start or end
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.extraLarge,
                top = MaterialTheme.spacing.extraSmall,
                bottom = MaterialTheme.spacing.extraSmall
            )
    ) {
        // This is chat bubble

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(
                    bottomEnd = MaterialTheme.spacing.medium,
                    topEnd = MaterialTheme.spacing.medium,
                    bottomStart = MaterialTheme.spacing.medium))
                .background(LightGrey)
                .clickable { },
            content = {
                Text(
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.extraSmall,
                        top = MaterialTheme.spacing.extraSmall,
                        end = MaterialTheme.spacing.small,
                        bottom = MaterialTheme.spacing.extraSmall
                    ),
                    text = text,
                    style = AvenirMedium,
                )

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReceiveMessageRowPreview() {
    ReceiveMessageRow(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip"
    )
}