package com.example.muztest.domain.usecases

class ChatUseCase(
    var getAllMessagesUseCase: GetAllMessagesUseCase,
    var sendMessageUseCaseUseCase: SendMessageUseCase
)