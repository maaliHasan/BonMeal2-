package com.example.mhasan.bonmeal;

import android.content.BroadcastReceiver;
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

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;
import static com.google.android.gms.internal.zzs.TAG;

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
        DataBroadCastReceiver mDBR = new DataBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("Recipe_Obj_is_Retrieved");
        getActivity().registerReceiver(mDBR, filter);
    }
    public  class DataBroadCastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if("Recipe_Obj_is_Retrieved".equals(intent.getAction())){
               String recipes= intent.getStringExtra("recipes");
                Log.d(recipes, "onRec: ");
            }
            else {
                Log.d("error: ","error on recive ");
            }


        }
    }

}
