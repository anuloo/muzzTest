package com.example.muztest.domain.repository

import com.example.muztest.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getAllMessages(): Flow<List<Message>>
    suspend fun insertMessage(message: Message)
}