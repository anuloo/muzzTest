package com.example.muztest.ui.screen

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.muztest.domain.model.Message
import com.example.muztest.domain.model.User
import com.example.muztest.ui.component.ChatAppBar
import com.example.muztest.ui.component.ChatTextField
import com.example.muztest.ui.component.ReceiveMessageRow
import com.example.muztest.ui.component.SentMessageRow
import com.example.muztest.ui.theme.AvenirMedium
import com.example.muztest.ui.theme.spacing
import com.example.muztest.utils.millis
import com.example.muztest.utils.sdf
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    keyboardController: SoftwareKeyboardController
) {
    val getAllMessages by viewModel.allMessages.collectAsState(initial = emptyList())
    val lastMessage by viewModel.lastMessage.collectAsState(null)
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = getAllMessages.size)

    val scope = rememberCoroutineScope()

    LaunchedEffect(getAllMessages) {
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
            onSendMessage = {
                scope.launch {
                    viewModel.sendMessage(it)
                    delay(200)
                    viewModel.getAllMessages()
                }

            },
            scrollState = scrollState,
            lastMessage = lastMessage
        )
    }
}

@Composable
fun ChatScreenView(
    modifier: Modifier = Modifier,
    messages: List<MessageWithDateSectionUi>,
    lastMessage: Message?,
    onSendMessage: (Message) -> Unit,
    scrollState: LazyListState? = null,
    currentUser: User = User.UserOne
) {
    var updateUser by remember {
        mutableStateOf(currentUser)
    }

    var lastMessageTimeStamp by remember {
        val lastMessageDate = lastMessage?.timeStamp ?: System.currentTimeMillis()
        mutableStateOf(lastMessageDate)
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        ChatAppBar(
            title = updateUser.name,
            onUserProfilePictureClick = {
                updateUser = if (updateUser == User.UserOne) {
                    User.UserTwo
                } else {
                    User.UserOne
                }
            }
        )
        Divider()
        if (scrollState != null) {
            LazyColumn(
                modifier = modifier.weight(1f),
                state = scrollState
            ) {
                items(messages) { chat ->
                    when (chat) {
                        is MessageWithDateSectionUi.Section -> {
                            Text(
                                modifier = modifier.fillMaxWidth(),
                                text = chat.date,
                                style = AvenirMedium.copy(
                                    color = Color.LightGray
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                        is MessageWithDateSectionUi.Item -> {
                            when (chat.message.isMessageFromOtherUser) {
                                true -> {
                                    ReceiveMessageRow(text = chat.message.chatMessage.message)
                                }
                                false -> {
                                    SentMessageRow(text = chat.message.chatMessage.message)
                                }
                            }
                        }
                    }
                }
            }
        }
        ChatTextField(
            onMessageSend = {
                val message = Message(
                    message = it,
                    userName = updateUser.name,
                    timeStamp = System.currentTimeMillis(),
                    dateTime = sdf.format(
                        checkDate(lastMessageTimeStamp)
                    )
                )


                onSendMessage(message)
            },
            onFocusEvent = null
        )
    }

}

private fun checkDate(lastDate: Long = System.currentTimeMillis()): Long {
    val currentDate: Long = System.currentTimeMillis()
    return if (currentDate >= lastDate + millis) {
        currentDate
    } else {
        lastDate
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenViewPreview() {
    ChatScreenView(
        messages = emptyList(),
        onSendMessage = {},
        lastMessage = Message(
            id = 72344343,
            message = "balala",
            userName = "bob",
            timeStamp = 342352353625,
            dateTime = ""
        )
    )
}