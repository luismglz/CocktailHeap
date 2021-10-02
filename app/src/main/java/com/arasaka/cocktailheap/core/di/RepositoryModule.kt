package com.arasaka.cocktailheap.core.di

import com.arasaka.cocktailheap.core.platform.NetworkHandler
import com.arasaka.cocktailheap.data.api.CocktailApi
import com.arasaka.cocktailheap.data.source.CocktailRepositoryImpl
import com.arasaka.cocktailheap.domain.repository.CocktailRepository
import com.arasaka.cocktailheap.framework.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCocktailRepository(
        apiProvider: ApiProvider,
        networkHandler: NetworkHandler
    ): CocktailRepository = CocktailRepositoryImpl(apiProvider.getEndpoint(CocktailApi::class.java), networkHandler)
}