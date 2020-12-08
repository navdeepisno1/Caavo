package com.suvidha.caavo;

import android.content.Context;
import android.os.AsyncTask;
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

import java.util.List;

public class RVAdapterCartItems extends RecyclerView.Adapter<RVAdapterCartItems.ViewH> {
    private Context context;
    private List<RecipeDetail> recipeDetails;
    private ConstraintLayout constraintLayout;
    private int Position;

    public RVAdapterCartItems(Context context, List<RecipeDetail> recipeDetails,ConstraintLayout constraintLayout) {
        this.context = context;
        this.recipeDetails = recipeDetails;
        this.constraintLayout = constraintLayout;
    }

    @NonNull
    @Override
    public ViewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_cart, parent,false);
        return new ViewH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewH holder, int position) {
        RecipeDetail recipeDetail = recipeDetails.get(position);
        Glide.with(context).load(recipeDetail.getImage()).centerCrop().placeholder(R.drawable.icon_app_light).into(holder.imageView_item);
        holder.textView_name.setText(recipeDetail.getName());
        holder.textView_price.setText(recipeDetail.getPrice());
        holder.button_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteRecipe().execute(recipeDetail);
                recipeDetails.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeDetails.size();
    }

    public class ViewH extends RecyclerView.ViewHolder
    {
        ImageView imageView_item;
        TextView textView_name,textView_price;
        Button button_remove;
        public ViewH(@NonNull View itemView) {
            super(itemView);

            imageView_item = (ImageView) itemView.findViewById(R.id.rv_item_cart_iv);
            textView_name = itemView.findViewById(R.id.rv_item_cart_tv_name);
            textView_price = itemView.findViewById(R.id.rv_item_cart_tv_price);
            button_remove = itemView.findViewById(R.id.rv_item_cart_btn_remove);
            Position = getAdapterPosition();
        }
    }

    public class DeleteRecipe extends AsyncTask<RecipeDetail,Void,Void>
    {
        @Override
        protected Void doInBackground(RecipeDetail... recipeDetails) {
            AppDatabase appDatabase = Room.databaseBuilder(context,AppDatabase.class,"dbR").build();
            RecipeDAO recipeDAO = appDatabase.getRecipeDAO();
            recipeDAO.delete(recipeDetails[0]);
            Snackbar snackbar = Snackbar.make(constraintLayout, "Recipe Removed!", Snackbar.LENGTH_SHORT);
            snackbar.show();
            return null;
        }
    }
}
