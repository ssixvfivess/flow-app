package com.psutools.reminder.di

import com.psutools.reminder.domain.usecase.GetSampleDataListUseCase
import com.psutools.reminder.domain.usecase.GetSampleDataListUseCaseImpl
import com.psutools.reminder.domain.usecase.GetSampleDataUseCase
import com.psutools.reminder.domain.usecase.GetSampleDataUseCaseImpl
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
    fun bindGetSampleDataUseCase(impl: GetSampleDataUseCaseImpl): GetSampleDataUseCase

    @Binds
    @Reusable
    fun bindGetSampleDataListUseCase(impl: GetSampleDataListUseCaseImpl): GetSampleDataListUseCase
}
