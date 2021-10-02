package com.arasaka.cocktailheap.presentation.cocktails.detail

import com.arasaka.cocktailheap.domain.model.Cocktail
import com.arasaka.cocktailheap.presentation.BaseViewState

sealed class CocktailViewState: BaseViewState(){

    data class CocktailsRecived(val cocktails: List <Cocktail>):BaseViewState()
    object ResultReceived: CocktailViewState()
}