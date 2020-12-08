package com.suvidha.caavo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ViewCart extends AppCompatActivity {

    Context context = this;
    RecyclerView recyclerView_cart_items;
    ImageView imageView_back;
    List<RecipeDetail> recipeDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        recyclerView_cart_items = findViewById(R.id.view_cart_rv);
        imageView_back = findViewById(R.id.view_cart_iv_back);

        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recipeDetails = new ArrayList<RecipeDetail>();

        new FetchCart().execute();


    }

    private class FetchCart extends AsyncTask<Void,Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase appDatabase = Room.databaseBuilder(context,AppDatabase.class,"dbR").build();
            RecipeDAO recipeDAO = appDatabase.getRecipeDAO();
            recipeDetails = recipeDAO.getAll();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
            recyclerView_cart_items.setLayoutManager(gridLayoutManager);
            for(RecipeDetail recipeDetail:recipeDetails)
            {
                Log.e("Name",recipeDetail.getName());
            }
            ConstraintLayout constraintLayout = findViewById(R.id.view_cart_cl);
            if(recipeDetails.size()>0) {
                recyclerView_cart_items.setAdapter(new RVAdapterCartItems(context, recipeDetails,constraintLayout));
            }
            else
            {
                Snackbar snackbar = Snackbar.make(constraintLayout, "Add Recipes", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
            return null;
        }
    }
}