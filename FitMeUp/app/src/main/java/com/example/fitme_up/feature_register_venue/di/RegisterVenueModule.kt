package com.example.fitme_up.feature_register_venue.di

import com.example.fitme_up.core_data.base.UserDataStoreRepository
import com.example.fitme_up.feature_login.data.repository.LoginRepository
import com.example.fitme_up.feature_login.domain.LoginUseCase
import com.example.fitme_up.feature_login.domain.LoginUseCaseImpl
import com.example.fitme_up.feature_register_coach.data.repository.RegisterCoachRepository
import com.example.fitme_up.feature_register_venue.data.repository.RegisterVenueRepository
import com.example.fitme_up.feature_register_venue.domain.RegisterVenueUsecase
import com.example.fitme_up.feature_register_venue.domain.RegisterVenueUsecaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RegisterVenueModule {
    @Singleton
    @Provides
    fun provideRegisterVenueRepository(): RegisterVenueRepository = RegisterVenueRepository()
}

@Module
@InstallIn(ViewModelComponent::class)
object LoginUseCaseModule {
    @Provides
    fun provideRegisterVenueUseCase(
        repository: RegisterVenueRepository,
    ): RegisterVenueUsecase {
        return RegisterVenueUsecaseImpl(repository)
    }
}