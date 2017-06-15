package com.example.mhasan.bonmeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by mhasan on 6/7/2017.
 *
 */

public class Fridge extends Fragment{

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private IngredientAdapter mIngAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fridge, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView ingredientRV= (RecyclerView)getActivity().findViewById(R.id.ingredientIconsRV);
        mIngAdapter= new IngredientAdapter(getActivity(),recipes);
        ingredientRV.setAdapter(mIngAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        ingredientRV.setLayoutManager( layoutManager);
    }
}
