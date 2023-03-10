package com.example.muztest.domain

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchProvider {
    val io: CoroutineDispatcher
}
