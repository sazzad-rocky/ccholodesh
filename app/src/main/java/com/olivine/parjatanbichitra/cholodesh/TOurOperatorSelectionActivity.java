/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import callbacks.AuthCallback;
import callbacks.ProvideCallback;
import constants.Travel;
import helpers.BaseURL;
import helpers.TailorMadeDataHolder;
import model.TourOperator;
import model.TourOperatorModel;
import model.TourOperatorPostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TOurOperatorSelectionActivity extends AppCompatActivity {

    Context context;
    Button  anyOperaotr,speOperaotr;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_touroperator_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Select Operator");
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_key),MODE_PRIVATE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width*.8), (int) (height*.4));
        context = this;



        anyOperaotr = (Button) findViewById(R.id.anyOperaotr);
        speOperaotr = (Button) findViewById(R.id.speOperaotr);

        if (!BaseURL.LANGUAGE_ENG){

            this.setTitle("অপারেটর নির্বাচন করুন ");
            anyOperaotr.setText("যে কোনো অপারেটর ");
            speOperaotr.setText("নির্দিষ্ট অপারেটর ");
        }


        anyOperaotr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String title = "Confirmation";
                String message = "Do You Want to Confirm your Trip Plan To this Operator?";
                String yes = "Yes";
                String no = "No";

                if (!BaseURL.LANGUAGE_ENG)
                {
                    title = "অনুমোদন";
                    message = "আপনি কি আপনার ট্রিপ প্ল্যান টি এই অপারেটরের কাছে পাঠাতে চান ?";
                    yes = "হ্যা";
                    no = "না";
                }
                AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                dialog.setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        postOperators();
                        //finish();
                    }
                });
                dialog.setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Intent intent=new Intent(ItineraryPlanner.this,MainActivity.class);
                        //startActivity(intent);
                    }
                });
                dialog.setMessage(message);
                dialog.setTitle(title);
                dialog.show();

            }
        });


        speOperaotr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,OperatorActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private void postOperators() {

        String user=sharedPreferences.getString(Travel.USER_EMAIL,null);
        TourOperatorPostModel model = new TourOperatorPostModel();
        model.setTailormadeId(TailorMadeDataHolder.tailorMadeId+"");
        model.setTailormadeCustomerId(user);
        model.setTourOperatorStatus("All");
        model.setTailormadeStatus("confirm");
        model.setTourOperator(new ArrayList<TourOperator>());


      //  Gson gson = new Gson();
       // String json = gson.toJson(model);
       // Log.e("Url","Null");
       // Log.e("JSON",json );

        AuthCallback auth = new AuthCallback((Activity) context);
        auth.postOperator(model).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(context,response.body(),Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                String meesage ="Your TailorMade has been Saved. Please Check \"My Trips\" for Details.";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" আপনার টেইলর মেডটি সংরক্ষিত হয়েছে।  বিস্তারিত জানতে \"আমার পরিকল্পনা\" অপশন দেখুন ";

                }
                intent.putExtra("Message",meesage);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

}
