package com.example.muztest.domain.model

data class MessageRegister(
    val chatMessage: Message,
    val isMessageFromOpponent: Boolean
)