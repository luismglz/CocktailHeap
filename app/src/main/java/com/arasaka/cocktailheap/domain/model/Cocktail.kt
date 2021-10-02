package com.arasaka.cocktailheap.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@Json(name = "original name of the attribute in the json") val name: String = "",

@JsonClass(generateAdapter = true)
class Cocktail(
    val idDrink: Int =0,
    @Json(name = "strDrink") val name: String = "",
    @Json(name = "strCategory") val category: String = "",
    @Json(name = "strDrinkThumb") val urlThumb: String = "",
    @Json(name = "strImageSource") val urlLargeImage: String = ""
) {
}