package com.example.fitme_up.core_data.base


import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


const val BASE_URL = "http://10.0.2.2:3333/"
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class UserEndpointSource
@InstallIn(SingletonComponent::class)
@Module
object RetrofitBuilderModule {
    private val clientConfig: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(150, TimeUnit.SECONDS)


        okHttpClient.addInterceptor(logging)
        okHttpClient.build()
    }

    @Provides
    @UserEndpointSource
    fun provideUserEndpointBuilder(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(clientConfig)
            .build()
    }

}

@Module
@InstallIn(SingletonComponent::class)
object DataStoreRepositoryModule {

    @Singleton
    @Provides
    fun provideUserDataStoreRepository(
        @ApplicationContext context: Context
    ): UserDataStoreRepository = UserDataStoreRepository(context)
}