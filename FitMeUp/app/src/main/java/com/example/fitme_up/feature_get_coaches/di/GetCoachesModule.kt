package com.example.fitme_up.feature_get_coaches.di

import com.example.fitme_up.core_data.base.UserDataStoreRepository
import com.example.fitme_up.feature_get_coaches.data.repository.GetCoachesRepository
import com.example.fitme_up.feature_get_coaches.domain.GetCoachesUseCase
import com.example.fitme_up.feature_get_coaches.domain.GetCoachesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GetCoachesDataModule {
    @Singleton
    @Provides
    fun provideGetCoachesRepository(userDataStoreRepository: UserDataStoreRepository): GetCoachesRepository = GetCoachesRepository(userDataStoreRepository)
}


@Module
@InstallIn(ViewModelComponent::class)
object GetCoachesUseCaseModule {
    @Provides
    fun provideGetCoachesUseCase(
        repository: GetCoachesRepository
    ): GetCoachesUseCase {
        return GetCoachesUseCaseImpl(repository)
    }
}