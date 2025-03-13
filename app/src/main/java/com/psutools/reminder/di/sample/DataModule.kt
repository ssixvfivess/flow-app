package com.psutools.reminder.di.sample

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.psutools.reminder.BuildConfig
import com.psutools.reminder.data.repository.sample.SampleDataRepositoryImpl
import com.psutools.reminder.data.service.sample.SampleDataService
import com.psutools.reminder.domain.repository.sample.SampleDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Reusable
    abstract fun bindSampleDataRepository(impl: SampleDataRepositoryImpl): SampleDataRepository

    @Module
    @InstallIn(SingletonComponent::class)
    class Providers {

        @Provides
        fun provideService(): SampleDataService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(
                    Json {
                        ignoreUnknownKeys = true
                    }.asConverterFactory("application/json".toMediaType())
                )
                .build()
                .create(SampleDataService::class.java)
        }
    }
}
