package com.example.muztest.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.muztest.domain.model.Message


@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    val getAllMessages by viewModel.getAllMessages.collectAsState(initial = emptyList())

    LaunchedEffect(getAllMessages){
        viewModel.getAllMessages()
    }

    ChatScreenView(
        messages = getAllMessages,
        onSendMessage = {
            viewModel.sendMessage(it)
        }
    )

}

@Composable
fun ChatScreenView(
    modifier: Modifier = Modifier,
    messages: List<Message>,
    onSendMessage: (Message) -> Unit
) {
    Column(modifier.fillMaxSize()) {
        Row() {
            Text(text = "top bar")
        }
        LazyColumn(modifier.weight(1f)) {
            items(messages) { chat ->
                Text(text = chat.message)
            }
        }
        Button(
            onClick = {
                val message = Message(
                    message = "hello testing",
                    userName = "bob",
                    timeStamp = System.currentTimeMillis()
                )
                onSendMessage(message)
            }
        ) {
            Text(text = "click me ")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ChatScreenViewPreview() {
    ChatScreenView(messages = emptyList(), onSendMessage = {})
}