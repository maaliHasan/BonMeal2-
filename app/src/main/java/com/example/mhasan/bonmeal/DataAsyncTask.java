package com.example.mhasan.bonmeal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import static com.google.android.gms.internal.zzs.TAG;


/**
 * Created by mhasan on 6/12/2017.
 * AsynkTask for retrieved Recipes  from fireDataBase and send broadcast
 * of this recipes to registered fragments
 */

public class DataAsyncTask extends AsyncTask<String, Void, Void>   {
    private static final String KET_INTENT_MSG = "Recipe_Obj_is_Retrieved";
    private ProgressDialog pDialog;
    private Context context;
    public ArrayList<Recipe> recipes = new ArrayList<>();
    private ArrayList<Ingredients> ingredients = new ArrayList<>();
    private DatabaseReference mDBReference;

    DataAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(String... params) {
        Log.d(  "doInBackground","inside doInBackGround");
        mDBReference=FirebaseDatabase.getInstance().getReference("Recipes").child("1");
       mDBReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               getData(dataSnapshot);
           }
           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //showing progress dialog
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void getData(DataSnapshot dataSnapshot) {
        recipes.clear();
        for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
            Recipe recipe = new Recipe();
            recipe.setHowToPrepare((String) recipeSnapshot.child("how-to-prepare").getValue());
            recipe.setAdImage((String) recipeSnapshot.child("Ad-image").getValue());
            recipe.setImage((String) recipeSnapshot.child("image").getValue());
            recipe.setAdUrl((String) recipeSnapshot.child("Ad-url").getValue());
            recipe.setIngredientCount((String) recipeSnapshot.child("ingredient-count").getValue());
            recipe.setName((String) recipeSnapshot.child("name").getValue());
            recipe.setRecipeIngredients((String) recipeSnapshot.child("recipe-ingredients").getValue());

            DataSnapshot recipeIngredient = recipeSnapshot.child("ingredients");
            for (DataSnapshot recipeShot : recipeIngredient.getChildren()) {
                Ingredients ingredient = new Ingredients();
                ingredient.setIngIcon((String) recipeShot.child("name").getValue());
                ingredient.setType((String) recipeShot.child("type").getValue());
                ingredients.add(ingredient);

            }
            recipe.setIngredients(ingredients);
            recipes.add(recipe);
        }
        Log.d("getData",String.valueOf(recipes.size()));
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pDialog.dismiss();
        Intent intent= new Intent(KET_INTENT_MSG);
       // intent.putExtra("recipes",recipes);
        intent.putExtra("recipes",recipes.toString());
        Log.d(String.valueOf(recipes.size()), "onPostExecute: ");
        context.sendBroadcast(intent);
    }
}
