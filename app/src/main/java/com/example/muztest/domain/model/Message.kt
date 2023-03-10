package com.example.muztest.domain.model

import com.example.muztest.data.db.entities.MessageEntity

data class Message(
    val id: Long?=null,
    val message: String,
    val userName: String,
    val timeStamp: Long
)

fun Message.toEntity():MessageEntity = MessageEntity(
    id = id,
    message= message,
    userName = userName,
    timeStamp = timeStamp
)
