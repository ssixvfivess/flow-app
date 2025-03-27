package com.psutools.reminder.di

import com.psutools.reminder.domain.usecase.trip.GetTripDataListUseCase
import com.psutools.reminder.domain.usecase.trip.GetTripDataListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    @Reusable
    fun bindGetTripDataListUseCase(impl: GetTripDataListUseCaseImpl): GetTripDataListUseCase
}