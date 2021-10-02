package com.arasaka.cocktailheap.domain.usecase

import com.arasaka.cocktailheap.core.exception.Failure
import com.arasaka.cocktailheap.core.functional.Either
import com.arasaka.cocktailheap.core.interactor.UseCase
import com.arasaka.cocktailheap.data.dto.CocktailsResponse
import com.arasaka.cocktailheap.domain.repository.CocktailRepository
import javax.inject.Inject

class GetCocktailsByName @Inject constructor(private val cocktailRepository: CocktailRepository) :
    UseCase<CocktailsResponse, String>() {

    override suspend fun run(params: String) = cocktailRepository.getCocktailsByName(params);
}