package com.arasaka.cocktailheap.data.api



import com.arasaka.cocktailheap.data.dto.CocktailsResponse
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.QueryName

interface CocktailApi {

    @GET("json/v1/1/search.php")
    fun getCocktailsByName(@Query("s") name: String): Call<CocktailsResponse>

}