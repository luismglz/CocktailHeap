package com.arasaka.cocktailheap.data.source

import com.arasaka.cocktailheap.core.platform.NetworkHandler
import com.arasaka.cocktailheap.data.api.CocktailApi
import com.arasaka.cocktailheap.data.dto.CocktailsResponse
import com.arasaka.cocktailheap.domain.repository.CocktailRepository
import com.arasaka.cocktailheap.framework.ApiRequest
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val cocktailApi: CocktailApi,
    private val networkHandler: NetworkHandler
) :
    CocktailRepository, ApiRequest {

    override fun getCocktailsByName(name: String) = makeRequest(
        networkHandler, cocktailApi.getCocktailsByName("ron"), { it }, CocktailsResponse(
            emptyList()
        )
    )
}
