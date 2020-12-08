package com.suvidha.caavo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;

public class Home extends AppCompatActivity {
    Context context = this;
    final String URL = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/reciped9d7b8c.json";
    RecyclerView recyclerView_recipes;
    Button button_view_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView_recipes = findViewById(R.id.home_rv);
        button_view_cart = findViewById(R.id.home_btn_view_cart);

        button_view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,ViewCart.class));
            }
        });

        getRecipes(URL);

    }

    private void getRecipes(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        process(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ConstraintLayout constraintLayout = findViewById(R.id.home_cl);
                        Snackbar snackbar = Snackbar.make(constraintLayout, "No Response From Server", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void process(JSONArray jsonArray)
    {
        ConstraintLayout constraintLayout = findViewById(R.id.home_cl);
        GridLayoutManager manager = new GridLayoutManager(context, 1);
        recyclerView_recipes.setLayoutManager(manager);
        recyclerView_recipes.setAdapter(new RVAdapterRecipes(context,jsonArray,recyclerView_recipes));
    }
}