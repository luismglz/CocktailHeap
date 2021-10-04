package com.arasaka.cocktailheap.presentation.cocktails

import com.arasaka.cocktailheap.domain.model.Cocktail
import com.arasaka.cocktailheap.presentation.BaseViewState

sealed class CocktailViewState: BaseViewState(){

    data class CocktailsReceived(val cocktails: List <Cocktail>):BaseViewState()
}