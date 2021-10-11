package com.arasaka.cocktailheap.domain.usecase

import com.arasaka.cocktailheap.core.exception.Failure
import com.arasaka.cocktailheap.core.functional.Either
import com.arasaka.cocktailheap.core.interactor.UseCase
import com.arasaka.cocktailheap.domain.model.Cocktail
import com.arasaka.cocktailheap.domain.repository.CocktailRepository
import javax.inject.Inject

class SaveCocktails @Inject constructor(private val cocktailRepository: CocktailRepository):
    UseCase<Boolean, List<Cocktail>>(){
    override suspend fun run(params: List<Cocktail>) = cocktailRepository.saveCocktails(params)

}