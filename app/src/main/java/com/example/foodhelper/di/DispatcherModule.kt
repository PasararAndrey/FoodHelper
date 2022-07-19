package com.example.foodhelper.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import javax.inject.Qualifier

@Module
object DispatcherModule {
    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher