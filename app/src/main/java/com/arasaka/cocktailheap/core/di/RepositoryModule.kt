package com.arasaka.cocktailheap.core.di

import com.arasaka.cocktailheap.core.platform.NetworkHandler
import com.arasaka.cocktailheap.data.api.CocktailApi
import com.arasaka.cocktailheap.data.dao.CocktailDao
import com.arasaka.cocktailheap.data.source.CocktailRepositoryImpl
import com.arasaka.cocktailheap.domain.repository.CocktailRepository
import com.arasaka.cocktailheap.framework.api.ApiProvider
import com.arasaka.cocktailheap.framework.db.CocktailDb
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
        cocktailDb: CocktailDb,
        networkHandler: NetworkHandler
    ): CocktailRepository = CocktailRepositoryImpl(apiProvider.getEndpoint(CocktailApi::class.java),networkHandler = networkHandler, cocktailDao = cocktailDb.cocktailDao())
}