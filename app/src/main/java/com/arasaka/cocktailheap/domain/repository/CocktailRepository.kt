package com.arasaka.cocktailheap.domain.repository

import com.arasaka.cocktailheap.core.exception.Failure
import com.arasaka.cocktailheap.core.functional.Either
import com.arasaka.cocktailheap.data.dto.CocktailsResponse
import com.arasaka.cocktailheap.domain.model.Cocktail

interface CocktailRepository {
    fun getCocktailsByName(name:String):Either<Failure, CocktailsResponse>

    fun saveCocktails(cocktails:List<Cocktail>): Either<Failure, Boolean>

    fun updateCocktail(cocktail:Cocktail):Either<Failure, Boolean>
}