package com.arasaka.cocktailheap.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arasaka.cocktailheap.framework.db.CocktailDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private val MIGRATION_1_2 = object : Migration(1,2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Cocktail ADD COLUMN alcoholic TEXT")
        }
    }

    //MIGRATION 2->3 add Cocktail Instructions info column to db
   private val MIGRATION_2_3 = object : Migration(2,3){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Cocktail ADD COLUMN instructions TEXT")
        }
    }


    //MIGRATION 3->4 Add User table to "cocktails" database...
    private val MIGRATION_3_4 = object : Migration(3,4){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `User`(`idUser` INTEGER PRIMARY KEY AUTOINCREMENT, `username` TEXT, `mail` TEXT, `avatar` TEXT, `token` TEXT)")
        }
    }

    @Provides
    @Singleton
    fun provideCocktailDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CocktailDb::class.java, "cocktails").addMigrations(
            MIGRATION_3_4).build()


}