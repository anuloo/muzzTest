package com.example.muztest.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muztest.domain.model.Message
import com.example.muztest.domain.model.MessageRegister
import com.example.muztest.domain.model.User
import com.example.muztest.domain.usecases.ChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCases: ChatUseCase
) : ViewModel() {
    private val _allMessages: MutableStateFlow<List<MessageWithDateSectionUi>> =
        MutableStateFlow(emptyList())
    val allMessages: Flow<List<MessageWithDateSectionUi>> = _allMessages

    private val _lastMessage: MutableStateFlow<Message?> =
        MutableStateFlow(null)
    val lastMessage: Flow<Message?> = _lastMessage

    init {
        getAllMessages()
    }

    fun getAllMessages() {
        viewModelScope.launch {
            try {
                useCases.getAllMessagesUseCase().collect { list ->
                    _lastMessage.value = list.last()
                    var messages :  List<MessageRegister> = listOf()
                    list.map {
                        messages = if (it.userName == User.UserTwo.name) {
                            messages + MessageRegister(it, true) // opposite user
                        } else {
                            messages + MessageRegister(it, false) //  user
                        }
                    }

                    val groupedList = messages.sortedBy { it.chatMessage.timeStamp }.groupBy {
                        it.chatMessage.dateTime
                    }

                    val uiList = mutableListOf<MessageWithDateSectionUi>()

                    groupedList.forEach { message ->
                        uiList.add(MessageWithDateSectionUi.Section(message.key))
                        uiList.addAll(message.value.map {
                            MessageWithDateSectionUi.Item(it)
                        })
                    }
                    _allMessages.value = uiList
                }

            } catch (e: Throwable) {
                println("error$e")
            }
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            try {
                useCases.sendMessageUseCaseUseCase(message)
            } catch (e: Throwable) {
                println("error$e")
            }
        }
    }
}