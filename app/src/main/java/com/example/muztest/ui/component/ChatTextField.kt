package com.example.muztest.ui.component

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.muztest.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
internal fun ChatTextField(
    modifier: Modifier = Modifier,
    onMessageSend: (String) -> Unit,
    onFocusEvent: ((Boolean) -> Unit)?
) {

    var input by remember { mutableStateOf(TextFieldValue("")) }
    var hasFocus by remember { mutableStateOf(false) }
    val textEmpty: Boolean by derivedStateOf { input.text.isEmpty() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            modifier = modifier
                .weight(1f)
                .border(
                    width = 2.dp,
                    color = if (textEmpty) PrimaryVariantColor else PrimaryColor,
                    shape = MaterialTheme.shapes.extraLarge
                ),
                //.onFocusChanged { focusState -> hasFocus = focusState.hasFocus },
            value = input,
            textStyle = AvenirMedium,
            onValueChange = { input = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Unspecified,
                unfocusedBorderColor = Color.Unspecified
            )
        )

        Spacer(modifier = modifier.width(20.dp))

        IconButton(
            modifier = modifier
                .background(if (textEmpty) PrimaryVariantColor else PrimaryColor, CircleShape)
                .align(CenterVertically),
            onClick = {
               if(!textEmpty){
                   onMessageSend(input.text)
                   input = TextFieldValue("")
               }
            }) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatTextFieldPreview() {
    ChatTextField(
        onMessageSend = {
        },
        onFocusEvent = null
    )
}
