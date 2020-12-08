package com.suvidha.caavo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {RecipeDetail.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDAO getRecipeDAO();
}
