package com.suvidha.caavo;
//This is abstract class for Database
import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {RecipeDetail.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDAO getRecipeDAO();
}
