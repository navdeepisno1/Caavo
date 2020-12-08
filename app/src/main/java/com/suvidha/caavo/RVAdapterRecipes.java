package com.suvidha.caavo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;

public class RVAdapterRecipes extends RecyclerView.Adapter<RVAdapterRecipes.ViewH> {
    private Context context;
    private JSONArray recipes;
    private RecyclerView constraintLayout;
    private RecipeDetail recipeDetail;
    public RVAdapterRecipes(Context context, JSONArray recipes, RecyclerView constraintLayout) {
        this.context = context;
        this.recipes = recipes;
        this.constraintLayout = constraintLayout;
    }

    @NonNull
    @Override
    public ViewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_item_recipe, parent,false);
        return new ViewH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewH holder, int position) {
        try {
            String id = recipes.getJSONObject(position).getString("id");
            String img_url = recipes.getJSONObject(position).getString("image");
            String name = recipes.getJSONObject(position).getString("name");
            String price = recipes.getJSONObject(position).getString("price");
            String description = recipes.getJSONObject(position).getString("description");
            Glide
                    .with(context)
                    .load(img_url)
                    .centerCrop()
                    .placeholder(R.drawable.icon_app)
                    .into(holder.imageView_recipe);
            holder.textView_name.setText(name);
            holder.textView_category.setText("Rs " + price + "/-");
            holder.textView_description.setText(description);

            holder.button_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recipeDetail = new RecipeDetail();
                    recipeDetail.setId(id);
                    recipeDetail.setImage(img_url);
                    recipeDetail.setName(name);
                    recipeDetail.setPrice(price);
                    new InsertRecipe().execute();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return recipes.length();
    }

    public class ViewH extends RecyclerView.ViewHolder
    {
        ImageView imageView_recipe;
        TextView textView_name,textView_category,textView_description;
        Button button_add;
        public ViewH(@NonNull View itemView) {
            super(itemView);
            imageView_recipe = itemView.findViewById(R.id.rv_item_recipe_iv);
            textView_category = (TextView) itemView.findViewById(R.id.rv_item_recipe_tv_category);
            textView_name = (TextView) itemView.findViewById(R.id.rv_item_recipe_tv_name);
            textView_description = itemView.findViewById(R.id.rv_item_recipe_tv_description);
            button_add = (Button) itemView.findViewById(R.id.rv_item_recipe_btn_add);

        }
    }
    private class InsertRecipe extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase appDatabase =Room.databaseBuilder(context,AppDatabase.class,"dbR").build();
            RecipeDAO recipeDAO = appDatabase.getRecipeDAO();
            Log.e("Recipe","" + recipeDetail.getName());
            recipeDAO.insertRecipe(recipeDetail);
            appDatabase.close();
            Snackbar snackbar = Snackbar.make(constraintLayout, "Recipe Added!", Snackbar.LENGTH_SHORT);
            snackbar.show();
            return null;
        }
    }

}
