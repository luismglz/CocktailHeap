package com.arasaka.cocktailheap.core.di

import android.content.Context
import com.arasaka.cocktailheap.core.platform.NetworkHandler
import com.arasaka.cocktailheap.framework.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiProvider() = ApiProvider();

    @Provides
    @Singleton
    fun provideNetworkHandler(@ApplicationContext context: Context) = NetworkHandler(context)
}