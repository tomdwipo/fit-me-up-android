package com.example.fitme_up.feature_register_coach.di

import com.example.fitme_up.feature_register_coach.data.repository.RegisterCoachRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RegisterCoachModule {
    @Singleton
    @Provides
    fun provideRegisterCoachRepository(): RegisterCoachRepository = RegisterCoachRepository()
}
