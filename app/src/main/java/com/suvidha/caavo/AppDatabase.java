package com.suvidha.caavo;
//This is abstract class for Database
import androidx.room.Database;
import androidx.room.RoomDatabase;

//Annotate the class with @Database and proide with created Entity class
@Database(entities = {RecipeDetail.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDAO getRecipeDAO();
}
