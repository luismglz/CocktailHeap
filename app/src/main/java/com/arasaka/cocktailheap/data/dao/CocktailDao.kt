package com.arasaka.cocktailheap.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.arasaka.cocktailheap.domain.model.Cocktail

@Dao
interface CocktailDao {
    @Query("SELECT * FROM Cocktail WHERE name LIKE :filter")
    fun getCocktailsByName(filter:String): List<Cocktail>

    @Insert(onConflict = IGNORE)
    fun saveCocktails(cocktails: List<Cocktail>):List<Long>//return Long because is the ID of cocktail which is brought from api

    @Update
    fun updateCocktails(cocktail: Cocktail): Int

}