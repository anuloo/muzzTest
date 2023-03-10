package com.example.muztest.di

import android.content.Context
import androidx.room.Room
import com.example.muztest.data.DispatchProviderImp
import com.example.muztest.data.db.MuzzDatabase
import com.example.muztest.data.db.MuzzDatabase.Companion.configure
import com.example.muztest.data.repository.ChatRepositoryImp
import com.example.muztest.domain.DispatchProvider
import com.example.muztest.domain.repository.ChatRepository
import com.example.muztest.domain.usecases.GetAllMessagesUseCase
import com.example.muztest.domain.usecases.SendMessageUseCase
import com.example.muztest.domain.usecases.ChatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDispatcherProviders(): DispatchProvider =
        DispatchProviderImp()

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): MuzzDatabase {
        return Room.databaseBuilder(
            context,
            MuzzDatabase::class.java,
            MuzzDatabase.NAME
        )
            .configure()
            .build()
    }

    @Provides
    fun provideChatRepository(
        db : MuzzDatabase
    ):ChatRepository{
        return ChatRepositoryImp(
            db = db
        )
    }

    @Provides
    fun provideUseCases(
        repository: ChatRepository,
        dispatchProvider: DispatchProvider
    ):ChatUseCase = ChatUseCase(
        getAllMessagesUseCase = GetAllMessagesUseCase(repository, dispatchProvider),
        sendMessageUseCaseUseCase = SendMessageUseCase(repository,dispatchProvider)
    )

}