package com.example.mhasan.bonmeal;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


/**
 * Created by mhasan on 6/7/2017.
 *
 */

public class MyRecipes extends Fragment {

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private ArrayList<Ingredients> ingredients = new ArrayList<>();
    private RecipeAdapter mRecipeAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_recipes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecipeRV = (RecyclerView) getActivity().findViewById(R.id.recipesRV);
        mRecipeAdapter = new RecipeAdapter(getActivity(), recipes);
        mRecipeRV.setAdapter(mRecipeAdapter);
        mRecipeRV.setLayoutManager(new LinearLayoutManager(getActivity()));


        DatabaseReference mDBReference = FirebaseDatabase.getInstance().getReference("Recipes").child("1");
        mDBReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loadData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    /*
     * load Recipes data from firebase DB
     *
     * @param dataSnapshot
     */
    public void loadData(DataSnapshot dataSnapshot) {
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
        mRecipeAdapter.notifyDataSetChanged();


    }




}
