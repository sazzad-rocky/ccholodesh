/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.AccommodationListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import constants.Travel;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import listeners.AccommodationListener;
import model.AccommodationProvider;
import model.AccommodationRoom;
import model.RouteNew;
import retrofit2.Call;
import retrofit2.Response;

public class AccommodationProviderActivity extends AppCompatActivity implements AccommodationListener ,AdapterView.OnItemSelectedListener{
    private ProvideCallback provideCallback;
    int minPrice = 0;
    int maxPrice = 0;
    int minput;
    int maxput;
    private List<AccommodationProvider> accommodationProviders=new ArrayList<>();
    @BindView(R.id.acccommodationProvierList) RecyclerView acccommodationProvierList;
    @BindView(R.id.categoryStar)
    Spinner categoryStar;
    @BindView(R.id.categoryRating)
    Spinner categoryRating;
    int district_id;
    @BindView(R.id.min)
    EditText min;
    @BindView(R.id.max)
    EditText max;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.refresh)
    Button refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation_provider);
        ButterKnife.bind(this);
        categoryStar.setOnItemSelectedListener(this);
        categoryRating.setOnItemSelectedListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //setActionBar();
        if (!BaseURL.LANGUAGE_ENG)
        {
            this.setTitle("হোটেল তালিকা");
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.starBn, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoryStar.setAdapter(adapter);
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.ratingBn, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoryRating.setAdapter(adapter2);
            min.setHint("সর্বনিম্ন");
            max.setHint("সর্বোচ্চ");
            search.setText("অনুসন্ধান");
        }
        else this.setTitle("Hotel List");
        //TODO init callback
        provideCallback=new ProvideCallback(this);
        //--------------------------------------------------
        district_id=getIntent().getIntExtra("district_id",0);
        loadAccommodation(district_id);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AccommodationRoomlistActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                if (min.getText().length() == 0 || max.getText().length() == 0){
                    String error = "Please enter the range of search";
                    if (!BaseURL.LANGUAGE_ENG){
                        error = "অনুসন্ধানের পরিসীমা লিখুন দয়া করে।";
                    }
                    Toast.makeText(AccommodationProviderActivity.this,error,Toast.LENGTH_SHORT).show();
                    return;
                }
                filterSearch();
                View view = AccommodationProviderActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAccommodation(district_id);
            }
        });
        acccommodationProvierList.requestFocus();
    }

    private void filterSearch(){
        List<AccommodationProvider> temp = new ArrayList<>();
        int frstRange = Integer.parseInt(min.getText().toString());
        int lastRange = Integer.parseInt(max.getText().toString());

        minput=Integer.parseInt(min.getText().toString());
        maxput=Integer.parseInt(max.getText().toString());
        for (int i = 0 ;i< accommodationProviders.size();i++){
            AccommodationProvider accommodation = new AccommodationProvider();
            accommodation = accommodationProviders.get(i);
            int price = 0;

            try{
                minPrice = Integer.parseInt(accommodation.minPrice);
                maxPrice  = Integer.parseInt(accommodation.maxPrice);
            }catch (Exception ex)
            {
            }

            if  ( (minPrice>= frstRange && minPrice <= lastRange) || (maxPrice >= frstRange && maxPrice <= lastRange)){
                temp.add(accommodation);
            }
        }
        if (temp.size() == 0) {

           // temp = accommodationProviders;
            String meesage = "Not Found";
            if (!BaseURL.LANGUAGE_ENG){

                meesage =" কিছুই পাওয়া যায়নি";

            }
            Toast.makeText(AccommodationProviderActivity.this,meesage,Toast.LENGTH_LONG).show();
        }
        AccommodationListAdapter accommodationListAdapter=new AccommodationListAdapter(AccommodationProviderActivity.this,temp);
        accommodationListAdapter.setAccommodationListener(AccommodationProviderActivity.this);
        acccommodationProvierList.setAdapter(accommodationListAdapter);
    }
    private void loadAccommodation(int id){
        provideCallback.getDestinationWiseAccommodationList(id).enqueue(new CustomCallBack<AccommodationProvider[]>(this) {
            @Override
            public void onResponse(Call<AccommodationProvider[]> call, Response<AccommodationProvider[]> response) {
                super.onResponse(call,response);
                Log.e("Package Url",call.request().url().toString());
                if (response.body() != null && response.body().length > 0)
                {
                    accommodationProviders= Arrays.asList(response.body());
                   // Toast.makeText(AccommodationProviderActivity.this,accommodationProviders.size()+"",Toast.LENGTH_SHORT).show();
                    for (AccommodationProvider a:accommodationProviders){
                        try {
                            String rating = a.getAccommodationTypeName();
                            rating = rating.substring(0,1);
                            a.ctStart = Integer.parseInt(rating);
                            //Toast.makeText(AccommodationProviderActivity.this,a.reviewRatingAverage+"",Toast.LENGTH_SHORT).show();
                        }catch (Exception ex)
                        {
                            a.ctStart = 0;
                        }
                        if (a.reviewRatingAverage != null){
                            a.review = Float.parseFloat(a.reviewRatingAverage);
                        }
                        //Log.e(a.getProviderName(), a.review+"/n");
                    }
                    AccommodationListAdapter accommodationListAdapter=new AccommodationListAdapter(AccommodationProviderActivity.this,accommodationProviders);
                    accommodationListAdapter.setAccommodationListener(AccommodationProviderActivity.this);
                    acccommodationProvierList.setAdapter(accommodationListAdapter);
                }
                else
                {
                    String meesage ="No Accomodations";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage =" কিছুই পাওয়া যায়নি";
                    }
                    Toast.makeText(AccommodationProviderActivity.this,meesage,Toast.LENGTH_LONG).show();
                    AccommodationProviderActivity.this.onBackPressed();
                }
            }
            @Override
            public void onFailure(Call<AccommodationProvider[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";
                }
                Toast.makeText(AccommodationProviderActivity.this, meesage, Toast.LENGTH_SHORT).show();
            }
        });
        View view = AccommodationProviderActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void calculateAccommodationCost(AccommodationRoom accommodationProvider, int action) {

    }

    @Override
    public void accommodationActivityResult(int requestCode) {
        Intent roomIntent=new Intent(AccommodationProviderActivity.this, AccommodationRoomlistActivity.class);
        roomIntent.putExtra(Travel.PROVIDER_KEY,requestCode);
        roomIntent.putExtra("minpricee",minput);
        roomIntent.putExtra("maxpricee",maxput);
        startActivityForResult(roomIntent,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Pass the selected rooms to Accommodation activity
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=200){
            return;
        }
        String json=data.getExtras().getString(Travel.KEY_ROOM_LIST,null);
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString(Travel.KEY_ROOM_LIST,json);
       // Toast.makeText(this, ""+json, Toast.LENGTH_SHORT).show();
        intent.putExtras(bundle);
        setResult(200,intent);
        finish();
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        switch (spinner.getId()) {


            case R.id.categoryStar:
                //Toast.makeText(AccommodationProviderActivity.this,categoryStar.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                String searchCriteria = categoryStar.getSelectedItem().toString();
                String searchCategory = "Select Category";

                if (!BaseURL.LANGUAGE_ENG){

                    searchCategory = "বিভাগ নির্বাচন করুন";
                }
                if (!searchCriteria.equals( searchCategory)){

                    filterSearch(searchCriteria);

                }
                else
                {
                    loadAccommodation(district_id);
                }

                break;

            case R.id.categoryRating:
                searchCriteria = categoryRating.getSelectedItem().toString();
                String searchRating = "Select Rating";
                if (!BaseURL.LANGUAGE_ENG){
                    searchRating = "রেটিং নির্বাচন করুন ";
                }
                if (!searchCriteria.equals(searchRating)){
                    try {
                        searchCriteria = BanglaNumberParser.getEnglish(searchCriteria);
                        int strt = Integer.parseInt(searchCriteria.charAt(0)+"");
                        int dst = Integer.parseInt(searchCriteria.charAt(4)+"");
                        filterSearch(strt,dst);

                    }catch (Exception ex){
                        loadAccommodation(district_id);
                    }


                    //Toast.makeText(this,strt+" "+dst,Toast.LENGTH_SHORT).show();


                }
                else
                {
                    loadAccommodation(district_id);
                }
                break;

        }
    }

//    void filterSearch(String star){
//        star = BanglaNumberParser.getEnglish(star);
//        List<AccommodationProvider> temp = new ArrayList<>();
//        String var = "";
//        for (int i = 0; i< accommodationProviders.size();i++){
//            AccommodationProvider accommodationProvider = accommodationProviders.get(i);
//            var = accommodationProvider.getAccommodationTypeName();
//            if (star.equals(accommodationProvider.ctStart+"")){
//
//                temp.add(accommodationProvider);
//            }
//
//
//        }
//        if (temp.size() == 0)
//        {
//            Toast.makeText(this,"Not Found",Toast.LENGTH_SHORT).show();
//            //return;
//        }
//
//        AccommodationListAdapter accommodationListAdapter=new AccommodationListAdapter(AccommodationProviderActivity.this,temp);
//        accommodationListAdapter.setAccommodationListener(AccommodationProviderActivity.this);
//        acccommodationProvierList.setAdapter(accommodationListAdapter);
//
//
//    }

    void filterSearch(String star){

        String toBeMatched = star.split(" ")[0];

        Log.e("To be matched ",toBeMatched);

        star = BanglaNumberParser.getEnglish(star);
        List<AccommodationProvider> temp = new ArrayList<>();
        String var = "";
        for (int i = 0; i< accommodationProviders.size();i++){
            AccommodationProvider accommodationProvider = accommodationProviders.get(i);
            var = accommodationProvider.getAccommodationTypeName();
            if (var != null) {
                var = var.split(" ")[0];
                Log.e("To be matched var",var);
            }
            if (toBeMatched.equalsIgnoreCase(var)){

                temp.add(accommodationProvider);
            }


        }
        if (temp.size() == 0)
        {
            String meesage ="No Accomodations";
            if (!BaseURL.LANGUAGE_ENG)
            {
                meesage =" কিছুই পাওয়া যায়নি";

            }
            Toast.makeText(AccommodationProviderActivity.this,meesage,Toast.LENGTH_LONG).show();
            //return;
        }

        AccommodationListAdapter accommodationListAdapter=new AccommodationListAdapter(AccommodationProviderActivity.this,temp);
        accommodationListAdapter.setAccommodationListener(AccommodationProviderActivity.this);
        acccommodationProvierList.setAdapter(accommodationListAdapter);


    }

    void filterSearch(int strt, int end){
        strt = Integer.parseInt(BanglaNumberParser.getEnglish(strt+""));
        end = Integer.parseInt(BanglaNumberParser.getEnglish(end+""));
        List<AccommodationProvider> temp = new ArrayList<>();
        String var = "";
        for (int i = 0; i< accommodationProviders.size();i++){
            AccommodationProvider accommodationProvider = accommodationProviders.get(i);

            if (accommodationProvider.review >=strt && accommodationProvider.review <= end){

                temp.add(accommodationProvider);
            }


        }
        if (temp.size() == 0)
        {
            String meesage ="No Accomodations";
            if (!BaseURL.LANGUAGE_ENG)
            {
                meesage =" কিছুই পাওয়া যায়নি";

            }
            Toast.makeText(AccommodationProviderActivity.this,meesage,Toast.LENGTH_LONG).show();
            //return;
        }

        AccommodationListAdapter accommodationListAdapter=new AccommodationListAdapter(AccommodationProviderActivity.this,temp);
        accommodationListAdapter.setAccommodationListener(AccommodationProviderActivity.this);
        acccommodationProvierList.setAdapter(accommodationListAdapter);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
