package com.arasaka.cocktailheap.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arasaka.cocktailheap.domain.model.Cocktail

@Database(entities = [Cocktail::class], version = 1)
abstract class UserDb:RoomDatabase() {
    //abstract fun userDao(): userDao
}