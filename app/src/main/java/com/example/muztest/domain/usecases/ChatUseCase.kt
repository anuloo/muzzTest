package com.example.muztest.domain.usecases

data class ChatUseCase(
    val getAllMessagesUseCase: GetAllMessagesUseCase,
    val sendMessageUseCaseUseCase: SendMessageUseCase
)