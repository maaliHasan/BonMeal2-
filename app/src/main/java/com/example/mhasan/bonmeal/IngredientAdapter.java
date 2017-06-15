package com.example.mhasan.bonmeal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by mhasan on 6/15/2017.
 * Recycler view adapter to hold ingredient icon
 */

public class IngredientAdapter  extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder>{
    public LayoutInflater mInflater;
    public Context mContex;
    public ArrayList<Recipe> mIngredienticon;

    public IngredientAdapter(  Context mContex, ArrayList<Recipe> mIngredienticon) {
        this.mContex = mContex;
        mInflater=LayoutInflater.from(mContex);
        this.mIngredienticon = mIngredienticon;
    }

    @Override
    public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =mInflater.inflate(R.layout.ingredient_container, parent, false);
        return new IngredientHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientHolder holder, int position) {
        holder.icon1.setImageResource(R.drawable.almond);
        holder.txt1.setText("خيار");
        holder.icon2.setImageResource(R.drawable.apple);
        holder.txt2.setText("تفاح");
        holder.icon3.setImageResource(R.drawable.apple);
        holder.txt3.setText("خوخ");

    }


    @Override
    public int getItemCount() {
        return 20;
    }

    public class IngredientHolder extends RecyclerView.ViewHolder{

        CircleImageView icon1,icon2,icon3;
        TextView txt1,txt2,txt3;
        public IngredientHolder(View itemView) {
            super(itemView);
            icon1=(CircleImageView)itemView.findViewById(R.id.icon_1);
            icon2=(CircleImageView)itemView.findViewById(R.id.icon_2);
            icon3=(CircleImageView)itemView.findViewById(R.id.icon_3);
            txt1=(TextView)itemView.findViewById(R.id.icon1_text);
            txt2=(TextView)itemView.findViewById(R.id.icon2_text);
            txt3=(TextView)itemView.findViewById(R.id.icon3_text);
        }
    }
}
