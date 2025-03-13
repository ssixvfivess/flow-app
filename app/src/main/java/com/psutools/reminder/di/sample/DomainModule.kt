package com.psutools.reminder.di.sample

import com.psutools.reminder.domain.usecase.sample.GetSampleDataListUseCase
import com.psutools.reminder.domain.usecase.sample.GetSampleDataListUseCaseImpl
import com.psutools.reminder.domain.usecase.sample.GetSampleDataUseCase
import com.psutools.reminder.domain.usecase.sample.GetSampleDataUseCaseImpl
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
