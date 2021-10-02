package com.arasaka.cocktailheap.data.dto

import com.arasaka.cocktailheap.domain.model.Cocktail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CocktailsResponse(val drinks: List<Cocktail> ?= listOf())//If the list response is null, this 'return' an empty list...

