package com.example.mhasan.bonmeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mhasan on 6/7/2017.
 *
 */

public class MyRecipes extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_recipes, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecipeRV= (RecyclerView)getActivity().findViewById(R.id.recipesRV);
        RecipeAdapter mRecipeAdapter= new RecipeAdapter(getActivity());
        mRecipeRV.setAdapter(mRecipeAdapter);
        mRecipeRV.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}
