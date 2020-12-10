package com.suvidha.caavo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
//This is used to create all the queries

//Annotate with @Dao
@Dao
public interface RecipeDAO{
    //To create Query ,Annotate with @Query
    @Query("SELECT * FROM Recipes")
    List<RecipeDetail> getAll();

    //To create Insert Query ,Annotate with @Insert
    @Insert
    void insertRecipe(RecipeDetail recipeDetail);

    //To create Delete Query ,Annotate with @Delete
    @Delete
    void delete(RecipeDetail recipeDetail);
}

