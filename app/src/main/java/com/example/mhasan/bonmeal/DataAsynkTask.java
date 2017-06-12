package com.example.mhasan.bonmeal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mhasan on 6/12/2017.
 * AsynkTask for retrieved Recipes  from fireDataBase and send broadcast
 * of this recipes to registered fragments
 */

public class DataAsynkTask extends AsyncTask<String, Void, String> {
    private static final String KET_INTENT_MSG = "Recipe_Obj_is_Retrieved";
    private ProgressDialog pDialog;
    private Context context;
    private DatabaseReference mDBReference;

    DataAsynkTask(Context context) {
        this.context = context;
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

    @Override
    protected String doInBackground(String[] params) {
         String result = null;
        mDBReference = FirebaseDatabase.getInstance().getReference("Recipes");
        mDBReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("recipes", dataSnapshot.getValue().toString());
                //  result= dataSnapshot.getValue().toString();
                System.out.println(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return result != null ? result.toString() : null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
