package com.example.mhasan.bonmeal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by mhasan on 6/8/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.DataHolder> {
    private LayoutInflater inflater;
    private Context mContext;
    public ArrayList<String> recipesList;

    public RecipeAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        //this.recipesList =recipesList;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recipes_container, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        //String current= recipesList.get(position);
        holder.title.setText("عصير الخوخ");
        holder.imageView.setImageResource(R.drawable.ic_recipesone);
//        String img ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQi7sMhM6eAFVFPPJrYrM1S2qM3eq3I4z1Pz9RtYS8VX_pvuhP2";
//        Glide.with(mContext).load(img)
//                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return 1;
    }

    class DataHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public DataHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.recipe_title);
            imageView = (ImageView) itemView.findViewById(R.id.recipe_img);
        }
    }
}
