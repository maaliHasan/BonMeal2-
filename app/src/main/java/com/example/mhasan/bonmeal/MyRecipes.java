package com.example.mhasan.bonmeal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.BroadcastReceiver;
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
    private DatabaseReference  mDBReference;
    private ArrayList<Recipe> recipes;
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
        DataBroadCastReceiver mDBR = new DataBroadCastReceiver();
        IntentFilter filter= new IntentFilter();
        filter.addAction("Recipe_Obj_is_Retrieved");
        getActivity().registerReceiver(mDBR,filter);




    }

    @Override
    public void onStart() {
        super.onStart();
    }

   public  class DataBroadCastReceiver extends BroadcastReceiver{

       @Override
       public void onReceive(Context context, Intent intent) {
           if("Recipe_Obj_is_Retrieved".equals(intent.getAction())){
               // updateView();
           }

       }
   }
}
