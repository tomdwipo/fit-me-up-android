package com.example.fitme_up.feature_login.di

import com.example.fitme_up.core_data.base.UserDataStoreRepository
import com.example.fitme_up.feature_login.data.repository.LoginRepository
import com.example.fitme_up.feature_login.domain.LoginUseCase
import com.example.fitme_up.feature_login.domain.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LoginDataModule {
    @Singleton
    @Provides
    fun provideLoginRepository(): LoginRepository = LoginRepository()
}


@Module
@InstallIn(ViewModelComponent::class)
object LoginUseCaseModule {
    @Provides
    fun provideLoginUseCase(
        repository: LoginRepository,
        userDataStoreRepository: UserDataStoreRepository
    ): LoginUseCase {
        return LoginUseCaseImpl(repository,userDataStoreRepository)
    }
}