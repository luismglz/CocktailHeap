package com.arasaka.cocktailheap.presentation.cocktails.detail


import com.arasaka.cocktailheap.data.dto.CocktailsResponse
import com.arasaka.cocktailheap.domain.usecase.GetCocktailsByName
import com.arasaka.cocktailheap.presentation.BaseViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

class CocktailViewModel @Inject constructor(private val getCocktailsByName: GetCocktailsByName) : BaseViewModel() {

    //:: -> convert function to a lambda and it passes the params directly
    @DelicateCoroutinesApi
    fun doGetCocktailsByName(name:String){
        getCocktailsByName(name){
            it.fold(::handleFailure){
                state.value = CocktailViewState.CocktailsRecived(it.listDrinks ?: listOf())
                true
            }
        }
    }



}