package com.arasaka.cocktailheap.domain.repository

import com.arasaka.cocktailheap.core.exception.Failure
import com.arasaka.cocktailheap.core.functional.Either
import com.arasaka.cocktailheap.data.dto.CocktailsResponse

interface CocktailRepository {
    fun getCocktailsByName(name:String):Either<Failure, CocktailsResponse>
}