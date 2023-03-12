package com.example.muztest.ui.screen

import android.content.Context
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.muztest.domain.model.Message
import com.example.muztest.ui.component.ChatAppBar
import com.example.muztest.ui.component.ChatTextField
import com.example.muztest.ui.theme.spacing
import kotlinx.coroutines.delay


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    keyboardController: SoftwareKeyboardController
) {
    val getAllMessages by viewModel.getAllMessages.collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = getAllMessages.size)

    LaunchedEffect(getAllMessages) {
        viewModel.getAllMessages()
        delay(200)
        if (getAllMessages.isNotEmpty()) {
            scrollState.scrollToItem(
                index = getAllMessages.size - 1
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .focusable()
            .wrapContentHeight()
            .imePadding()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { keyboardController.hide() })
            }
    ) {

        ChatScreenView(
            messages = getAllMessages,
            context = context,
            onSendMessage = {
                viewModel.sendMessage(it)
            },
            scrollState = scrollState
        )
    }

}

@Composable
fun ChatScreenView(
    modifier: Modifier = Modifier,
    messages: List<Message>,
    context: Context,
    onSendMessage: (Message) -> Unit,
    scrollState: LazyListState? = null
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.medium)) {
        ChatAppBar(
            context = context
        )
        Divider()
        if (scrollState != null) {
            LazyColumn(
                modifier =  modifier.weight(1f),
                state = scrollState
            ) {
                items(messages) { chat ->
                    Text(text = chat.message)
                }
            }
        }
        ChatTextField(
            onMessageSend = {
                val message = Message(
                    message = it,
                    userName = "Sarah",
                    timeStamp = System.currentTimeMillis()
                )
                onSendMessage(message)
            },
            onFocusEvent = null
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ChatScreenViewPreview() {
    ChatScreenView(messages = emptyList(), context = LocalContext.current, onSendMessage = {})
}