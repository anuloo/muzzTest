package com.example.muztest.domain.usecases

import com.example.muztest.domain.DispatchProvider
import com.example.muztest.domain.model.Message
import com.example.muztest.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository,
    private val dispatchProvider: DispatchProvider
){
    suspend operator fun invoke(message: Message) {
        return withContext(dispatchProvider.io) {
            repository.insertMessage(message)
        }
    }
}