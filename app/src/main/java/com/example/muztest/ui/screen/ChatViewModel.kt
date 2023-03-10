package com.example.muztest.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muztest.domain.model.Message
import com.example.muztest.domain.usecases.ChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCases: ChatUseCase
):ViewModel() {
    private val _getAllMessages: MutableStateFlow<List<Message>> =  MutableStateFlow(emptyList())
    val getAllMessages : Flow<List<Message>> = _getAllMessages

    private val _sendMessage: MutableStateFlow<Message?> =  MutableStateFlow(null)

    fun getAllMessages(){
        viewModelScope.launch {
            try {
                useCases.getAllMessagesUseCase().collect{
                    _getAllMessages.value =  it
                }
            }catch (e:Throwable){
                println("error$e")
            }
        }
    }

    fun sendMessage(message: Message){
        viewModelScope.launch{
            try{
                useCases.sendMessageUseCaseUseCase(message)
            }catch (e:Throwable){
                println("error$e")
            }
        }
    }
}