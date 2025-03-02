package com.psutools.reminder.di

import com.psutools.reminder.app.navigation.Router
import com.psutools.reminder.app.navigation.RouterImpl
import com.psutools.reminder.base.ResourceProvider
import com.psutools.reminder.base.ResourceProviderImpl
import com.psutools.reminder.base.coroutines.CoroutineDispatchers
import com.psutools.reminder.base.coroutines.CoroutineDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Reusable
    fun bindResourceProvider(impl: ResourceProviderImpl): ResourceProvider

    @Binds
    @Reusable
    fun bindCoroutineDispatchers(impl: CoroutineDispatchersImpl): CoroutineDispatchers

    @Binds
    @Reusable
    fun bindRouter(impl: RouterImpl): Router
}
