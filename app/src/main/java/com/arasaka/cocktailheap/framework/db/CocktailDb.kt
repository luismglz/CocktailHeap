package com.arasaka.cocktailheap.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arasaka.cocktailheap.data.dao.CocktailDao
import com.arasaka.cocktailheap.domain.model.Cocktail

@Database(entities = [Cocktail::class], version = 2)
abstract class CocktailDb:RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao
}