package com.example.fitme_up.feature_register_admin.di

import com.example.fitme_up.feature_login.data.repository.LoginRepository
import com.example.fitme_up.feature_register_admin.data.repository.RegisterAdminRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RegisterAdminModule {
    @Singleton
    @Provides
    fun provideRegisterAdminRepository(): RegisterAdminRepository = RegisterAdminRepository()
}
