package com.arasaka.cocktailheap.data.source

import com.arasaka.cocktailheap.core.exception.Failure
import com.arasaka.cocktailheap.core.functional.Either
import com.arasaka.cocktailheap.core.platform.NetworkHandler
import com.arasaka.cocktailheap.data.api.CocktailApi
import com.arasaka.cocktailheap.data.dao.CocktailDao
import com.arasaka.cocktailheap.data.dto.CocktailsResponse
import com.arasaka.cocktailheap.domain.model.Cocktail
import com.arasaka.cocktailheap.domain.repository.CocktailRepository
import com.arasaka.cocktailheap.framework.api.ApiRequest
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val cocktailApi: CocktailApi,
    private val networkHandler: NetworkHandler,
    private val cocktailDao: CocktailDao
) :
    CocktailRepository, ApiRequest {

    override fun getCocktailsByName(name: String) : Either<Failure, CocktailsResponse> {
        val result = makeRequest(networkHandler, cocktailApi.getCocktailsByName(name), { it }, CocktailsResponse(emptyList())) //Api result

        //If there is an error do this
        return if(result.isLeft){
            val localResult = cocktailDao.getCocktailsByName("%$name%")

            if(localResult.isEmpty()) result //return result of API request
            else Either.Right(CocktailsResponse(localResult)) //Return the local result
        }else result
    }



    override fun saveCocktails(cocktails: List<Cocktail>): Either<Failure, Boolean> {
        val result = cocktailDao.saveCocktails(cocktails)
        return if (result.size == cocktails.size) Either.Right(true)
        else Either.Left(Failure.DatabaseError)
    }

    override fun updateCocktail(cocktail: Cocktail): Either<Failure, Boolean> {
        TODO("Not yet implemented")
    }
}
