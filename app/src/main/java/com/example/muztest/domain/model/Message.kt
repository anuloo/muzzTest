package com.example.muztest.domain.model

data class Message(
    val id: Long? = null,
    val message: String,
    val userName: String,
    val timeStamp: Long,
    val dateTime: String
)
