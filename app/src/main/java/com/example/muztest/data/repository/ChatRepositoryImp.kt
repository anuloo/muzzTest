package com.example.muztest.data.repository

import com.example.muztest.data.db.MuzzDatabase
import com.example.muztest.data.db.entities.toDomain
import com.example.muztest.domain.model.Message
import com.example.muztest.domain.model.toEntity
import com.example.muztest.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val db: MuzzDatabase
) : ChatRepository {
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    override suspend fun getAllMessages(): Flow<List<Message>> {
        return db.muzzDao.getAllMessages().mapLatest { messages ->
            messages.map {
                it.toDomain()
            }
        }
    }

    override suspend fun insertMessage(message: Message) {
        db.muzzDao.insertMessage(message.toEntity())
    }
}