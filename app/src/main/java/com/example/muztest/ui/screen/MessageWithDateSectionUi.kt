package com.example.muztest.ui.screen

import com.example.muztest.domain.model.MessageRegister

sealed class MessageWithDateSectionUi {
    data class Section(
        val date: String
    ) : MessageWithDateSectionUi()

    data class Item(
        val message: MessageRegister
    ) : MessageWithDateSectionUi()
}
