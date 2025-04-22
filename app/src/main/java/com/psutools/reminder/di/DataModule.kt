package com.psutools.reminder.di
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.psutools.reminder.BuildConfig
import com.psutools.reminder.data.repository.details.TripDetailsRepositoryImpl
import com.psutools.reminder.data.repository.trip.TripDataRepositoryImpl
import com.psutools.reminder.data.service.trip.TripDataService
import com.psutools.reminder.domain.repository.details.TripDetailsRepository
import com.psutools.reminder.domain.repository.trip.TripDataRepository
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
    abstract fun bindTripDataRepository(impl: TripDataRepositoryImpl): TripDataRepository

    @Binds
    @Reusable
    abstract fun bindTripDetailsRepository(impl: TripDetailsRepositoryImpl): TripDetailsRepository

    @Module
    @InstallIn(SingletonComponent::class)
    class Providers {

        @Provides
        fun provideService(): TripDataService {
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
                        explicitNulls = false // Позволяет null-полям быть null
                        coerceInputValues = true // Автоматически преобразует невалидные значения
                        isLenient = true // Более мягкий парсинг
                    }.asConverterFactory("application/json".toMediaType())
                )
                .build()
                .create(TripDataService::class.java)
        }
    }
}