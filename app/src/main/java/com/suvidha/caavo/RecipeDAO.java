package com.suvidha.caavo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDAO{
    @Query("SELECT * FROM Recipes")
    public List<RecipeDetail> getAll();

    @Insert
    void insertRecipe(RecipeDetail recipeDetail);

    @Delete
    void delete(RecipeDetail recipeDetail);
}

