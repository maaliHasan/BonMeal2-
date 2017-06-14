package com.example.mhasan.bonmeal;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static com.google.android.gms.internal.zzs.TAG;
import static java.lang.System.load;

/**
 * Created by mhasan on 6/8/2017.
 *
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.DataHolder> {
    private LayoutInflater inflater;
    private Context mContext;
    public ArrayList<Recipe> recipesList;
    public FirebaseStorage mStorage;
    public StorageReference mSReference;

    public RecipeAdapter(Context context,ArrayList<Recipe> recipesList) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.recipesList =recipesList;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mStorage=FirebaseStorage.getInstance();
        mSReference = mStorage.getReference();

        View view = inflater.inflate(R.layout.recipes_container, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
      final   Recipe current= recipesList.get(position);
       // String image=getImageUrl(position);
       // Log.d("onBindViewHolder: ",image);
        holder.title.setText(current.getName());
        Glide.with(mContext).load(current.getImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
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

    public String getImageUrl(int position){
         // String url;
        final Recipe current= recipesList.get(position);
        String image= current.getImage();
        mSReference.child("Recipies/"+image.concat(".png")).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("imageuri: ", uri.toString());
                current.setImage(uri.toString());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d(exception.toString() + "error", "onFailure: ");

            }
        });
       return  current.getImage();
    }
}
