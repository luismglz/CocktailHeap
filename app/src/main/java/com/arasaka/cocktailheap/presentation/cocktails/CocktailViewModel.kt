package com.arasaka.cocktailheap.presentation.cocktails


import android.widget.SearchView
import com.arasaka.cocktailheap.R
import com.arasaka.cocktailheap.domain.usecase.GetCocktailsByName
import com.arasaka.cocktailheap.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject


@DelicateCoroutinesApi
@HiltViewModel
class CocktailViewModel @Inject constructor(private val getCocktailsByName: GetCocktailsByName) : BaseViewModel() {

    fun doGetCocktailsByName(name:String){
        getCocktailsByName(name){
            it.fold(::handleFailure){
                state.value = CocktailViewState.CocktailsReceived(it.drinks ?: listOf())
                true
            }
        }
    }








    //:: -> convert function to a lambda and it passes the params directly
}