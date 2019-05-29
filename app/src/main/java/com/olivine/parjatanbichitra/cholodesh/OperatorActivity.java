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
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import adapters.OperatorListAdapter;
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
import userDefinder.TailorMade;

public class OperatorActivity extends AppCompatActivity {

    Context context;
    RecyclerView operatorList;
    SharedPreferences sharedPreferences;
    EditText operatorName;
    Button search;
    ArrayList<TourOperatorModel> tourOperatorModels;
    ArrayList<TourOperatorModel> tourOperatorModelsSerialed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Select Operator");

        context = this;
        operatorList = (RecyclerView) findViewById(R.id.operatorList);
        operatorName = (EditText) findViewById(R.id.operatorName);
        search = (Button) findViewById(R.id.search);

        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_key),MODE_PRIVATE);

        ProvideCallback provideCallback = new ProvideCallback((Activity) context);

        provideCallback.getAllTourOperators().enqueue(new Callback<ArrayList<TourOperatorModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TourOperatorModel>> call, Response<ArrayList<TourOperatorModel>> response) {
                tourOperatorModelsSerialed=response.body();

                ArrayList<TourOperatorModel> employees = response.body();
                Collections.sort(employees);

              //  Arrays.sort(tourOperatorModelsSerialed.toArray());
               // Log.e("Operator Url",call.request().url().toString());
                //Toast.makeText(context,response.body().length+"",Toast.LENGTH_SHORT).show();
                tourOperatorModels = response.body();
                OperatorListAdapter operatorListAdapter = new OperatorListAdapter(context,employees);
                operatorList.setAdapter(operatorListAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<TourOperatorModel>> call, Throwable t) {

            }
        });

        operatorName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(context, operatorName.getText().toString(),Toast.LENGTH_SHORT).show();
                search();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

    }

    private void search(){

        ArrayList<TourOperatorModel> filter = new ArrayList<>();
        String txt = operatorName.getText().toString().toLowerCase();
        for(TourOperatorModel model : tourOperatorModels){

            if (model.getProviderName().toLowerCase().contains(txt)){

                filter.add(model);
            }
        }

        OperatorListAdapter operatorListAdapter = new OperatorListAdapter(context,filter);
        operatorList.setAdapter(operatorListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String menuName = "Confirm";
        if (!BaseURL.LANGUAGE_ENG)
        {
            menuName = "কনফার্ম ";
        }
        MenuItem menuItem=menu.add(Menu.NONE,1,Menu.NONE,menuName);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case 1:
                String title = "Confirmation";
                String message = "Do You Want to Confirm your Operator?";
                String yes = "Yes";
                String no = "No";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    title = "অনুমোদন";
                    message = "আপনি কি ওপেরাটরটি কনফার্ম করতে চান ?";
                    yes = "হ্যা";
                    no = "না";
                }
                AlertDialog.Builder dialog=new AlertDialog.Builder(this);
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


                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void postOperators() {
        String user=sharedPreferences.getString(Travel.USER_EMAIL,null);
        TourOperatorPostModel model = new TourOperatorPostModel();
        model.setTailormadeId(TailorMadeDataHolder.tailorMadeId+"");
        model.setTailormadeCustomerId(user);
        model.setTourOperatorStatus("Specific");
        model.setTailormadeStatus("confirm");
        model.setTourOperator(TailorMadeDataHolder.tourOperator);

        //Gson gson = new Gson();
        //String json = gson.toJson(model);
       // Log.e("Url","Null");
        //Log.e("JSON",json );

        AuthCallback auth = new AuthCallback((Activity) context);
        auth.postOperator(model).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
               // Toast.makeText(context,response.body(),Toast.LENGTH_SHORT).show();

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
