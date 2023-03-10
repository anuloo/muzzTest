package com.example.muztest.data

import com.example.muztest.domain.DispatchProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatchProviderImp : DispatchProvider {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}