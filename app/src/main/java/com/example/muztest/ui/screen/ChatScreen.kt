package com.example.muztest.ui.screen

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.muztest.utils.sdf
import kotlinx.coroutines.delay


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    keyboardController: SoftwareKeyboardController
) {
    val getAllMessages by viewModel.getAllMessages.collectAsState(initial = emptyList())
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
    messages: List<MessageWithDateSectionUi>,
    context: Context,
    onSendMessage: (Message) -> Unit,
    scrollState: LazyListState? = null,
    currentUser: User = User.UserOne
) {
    var updateUser by remember {
        mutableStateOf(currentUser)
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        ChatAppBar(
            context = context,
            title = updateUser.name,
            onUserProfilePictureClick = {
                println("crap")
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
                            when (chat.message.isMessageFromOpponent) {
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
                    dateTime = sdf.format(System.currentTimeMillis())
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