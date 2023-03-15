package com.example.muztest.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.muztest.domain.model.Message

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "timeStamp") val timeStamp: Long,
    @ColumnInfo(name = "dateTime") val dateTime: String
)

fun MessageEntity.toDomain(): Message = Message(
    id = id,
    message = message,
    userName = userName,
    timeStamp = timeStamp,
    dateTime = dateTime
)

fun Message.toEntity():MessageEntity = MessageEntity(
    id = id,
    message= message,
    userName = userName,
    timeStamp = timeStamp,
    dateTime = dateTime
)
