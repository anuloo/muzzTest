package com.example.muztest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.muztest.data.db.entities.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MuzzDao {
    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Query("DELETE FROM messages")
    suspend fun deleteAllMessages()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)
}