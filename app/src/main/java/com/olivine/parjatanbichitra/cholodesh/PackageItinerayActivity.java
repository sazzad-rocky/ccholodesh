/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import adapters.PackageItineraryAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import helpers.BaseURL;
import model.PackageItinerary;
import retrofit2.Call;
import retrofit2.Response;
import helpers.CustomCallBack;

import static constants.Travel.INTENT_PACKAGE_ID;

public class PackageItinerayActivity extends AppCompatActivity {
    private ProvideCallback provideCallback;
    @BindView(R.id.packageItinerary) RecyclerView packageItinerary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_itineray);
        ButterKnife.bind(this);
        if (!BaseURL.LANGUAGE_ENG)
        {
            this.setTitle("অন্তর্ভুক্তি");
        }
        else this.setTitle("Inclusions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int packageId=getIntent().getIntExtra(INTENT_PACKAGE_ID,0);
        provideCallback=new ProvideCallback(this);
        // Load related package itinerary
        provideCallback.getPackageItineraries(packageId).enqueue(new CustomCallBack<PackageItinerary[]>(this) {
            @Override
            public void onResponse(Call<PackageItinerary[]> call, Response<PackageItinerary[]> response) {
                super.onResponse(call, response);
                Log.e("Package Url",call.request().url().toString());
                if (response.body() != null && response.body().length > 0)
                {
                    PackageItineraryAdapter packageItineraryAdapter=new PackageItineraryAdapter(PackageItinerayActivity.this,response.body());
                    packageItinerary.setAdapter(packageItineraryAdapter);
                }
                else {

                    String meesage = "Nothing found";
                    if (!BaseURL.LANGUAGE_ENG){
                        meesage = "কিছুই পাওয়া যায়নি";
                    }
                    Toast.makeText(PackageItinerayActivity.this,meesage,Toast.LENGTH_LONG).show();
                    onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<PackageItinerary[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(PackageItinerayActivity.this,meesage,Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
