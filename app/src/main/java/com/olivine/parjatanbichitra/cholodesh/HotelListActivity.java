/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import adapters.AccommodationRoomListAdapter;
import adapters.FoodListAdapter;
import adapters.HotelListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import listeners.AccommodationListener;
import model.AccommodationProvider;
import model.AccommodationRoom;
import retrofit2.Call;
import retrofit2.Response;

public class HotelListActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    @BindView(R.id.hotelListView) RecyclerView hotelListView;
    @BindView(R.id.categoryStar)
    Spinner categoryStar;
    @BindView(R.id.categoryRating)
    Spinner categoryRating;
    @BindView(R.id.min)
    EditText min;
    @BindView(R.id.max)
    EditText max;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.refresh)
    Button refresh;
    private HotelListAdapter hotelListAdapter;
    private List<AccommodationProvider> accommodationProviders=new ArrayList<>();
    // DataStrorage
    SharedPreferences sharedPreferences;
    // Callback
    ProvideCallback provideCallback;
    // Adapter
    FoodListAdapter foodListAdapter;
    int district_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        ButterKnife.bind(this);
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

        initialization();
        categoryStar.setOnItemSelectedListener(this);
        categoryRating.setOnItemSelectedListener(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AccommodationRoomlistActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                if (min.getText().length() == 0 || max.getText().length() == 0){
                    String error = "Please enter the range of search";
                    if (!BaseURL.LANGUAGE_ENG){
                        error = "অনুসন্ধানের পরিসীমা লিখুন দয়া করে।";
                    }
                    Toast.makeText(HotelListActivity.this,error,Toast.LENGTH_SHORT).show();
                    return;
                }
                filterSearch();
                View view = HotelListActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                //hotelListView.requestFocus();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAccommodation(district_id);
            }
        });

        hotelListView.requestFocus();

    }

    private void filterSearch(){

        List<AccommodationProvider> temp = new ArrayList<>();
        int frstRange = Integer.parseInt(min.getText().toString());
        int lastRange = Integer.parseInt(max.getText().toString());

        for (int i = 0 ;i< accommodationProviders.size();i++){

            AccommodationProvider accommodation = new AccommodationProvider();
            accommodation = accommodationProviders.get(i);
            int price = 0;

            int minPrice = 0;
            int maxPrice = 0;
            try{
                minPrice = Integer.parseInt(accommodation.minPrice);
                maxPrice  = Integer.parseInt(accommodation.maxPrice);

            }catch (Exception ex)
            {


            }

            if ( (minPrice>= frstRange && minPrice <= lastRange) || (maxPrice >= frstRange && maxPrice <= lastRange)){

                temp.add(accommodation);

            }
        }
        if (temp.size() == 0) {

           // temp = accommodationProviders;
            String meesage = "Not Found";
            if (!BaseURL.LANGUAGE_ENG){

                meesage =" কিছুই পাওয়া যায়নি";

            }
            Toast.makeText(HotelListActivity.this,meesage,Toast.LENGTH_LONG).show();

        }
        //Toast.makeText(HotelListActivity.this,temp.size()+"",Toast.LENGTH_LONG).show();
        AccommodationProvider[] arr = new AccommodationProvider[temp.size()];
        arr = temp.toArray(arr);
        // accommodationProviders = Arrays.asList(response.body());
        hotelListAdapter=new HotelListAdapter(HotelListActivity.this,arr);
        hotelListView.setAdapter(hotelListAdapter);

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

    private void loadAccommodation(int id){

        provideCallback = new ProvideCallback(this);
        provideCallback.getDestinationWiseAccommodationList(id).enqueue(new CustomCallBack<AccommodationProvider[]>(this) {
        @Override
        public void onResponse(Call<AccommodationProvider[]> call, Response<AccommodationProvider[]> response) {
            super.onResponse(call,response);
            Log.e("Package Url",call.request().url().toString());
            if (response.body() != null && response.body().length > 0)
            {
                accommodationProviders = Arrays.asList(response.body());
                hotelListAdapter=new HotelListAdapter(HotelListActivity.this,response.body());
                hotelListView.setAdapter(hotelListAdapter);
            }
            else
            {

                String meesage ="Nothing Found";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" কিছুই পাওয়া যায়নি";

                }
                Toast.makeText(HotelListActivity.this,meesage,Toast.LENGTH_LONG).show();

                HotelListActivity.this.onBackPressed();
            }

        }

        @Override
        public void onFailure(Call<AccommodationProvider[]> call, Throwable t) {
            String meesage ="Network Error";
            if (!BaseURL.LANGUAGE_ENG)
            {
                meesage =" নেটওয়ার্ক ত্রুটি";

            }
            Toast.makeText(HotelListActivity.this,meesage,Toast.LENGTH_LONG).show();
        }
    }
        );

        View view = HotelListActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    private void initialization() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
//        String districtId = sharedPreferences.getInt(Travel.TO_LOCATION, 26) + "";
        district_id = getIntent().getIntExtra("DESTINATION_ID",0);

        loadAccommodation(district_id);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // BaseURL.hotelServiceIds.clear();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // Toast.makeText(getApplicationContext(),"Called",Toast.LENGTH_SHORT).show();
        Spinner spinner = (Spinner) parent;
        switch (spinner.getId()) {


            case R.id.categoryStar:
                //Toast.makeText(getApplicationContext(),categoryStar.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                String searchCriteria = categoryStar.getSelectedItem().toString();
                String category = "Select Category";
                if (!BaseURL.LANGUAGE_ENG){
                    category = "বিভাগ নির্বাচন করুন";
                }

                if (!searchCriteria.equals(category)){

                    filterSearch(searchCriteria);

                }
                else
                {
                    loadAccommodation(district_id);
                }

                break;

            case R.id.categoryRating:
                searchCriteria = categoryRating.getSelectedItem().toString();
                category = "Select Rating";
                if (!BaseURL.LANGUAGE_ENG){

                    category = "রেটিং নির্বাচন করুন ";
                }
                if (!searchCriteria.equals(category)){
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

    void filterSearch(String star){
        //Toast.makeText(getApplicationContext(),star+"o",Toast.LENGTH_SHORT).show();
        String toBeMatched = star.split(" ")[0];

        Log.e("To be matched ",toBeMatched);

        star = BanglaNumberParser.getEnglish(star);
        List<AccommodationProvider> temp = new ArrayList<>();
        String var = "";
        for (int i = 0; i< accommodationProviders.size();i++){
            AccommodationProvider accommodationProvider = accommodationProviders.get(i);
            var = accommodationProvider.getAccommodationTypeName();
            //Toast.makeText(getApplicationContext(),var,Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this,"Not Found",Toast.LENGTH_SHORT).show();
            //return;
        }

        AccommodationProvider[] arr = new AccommodationProvider[temp.size()];
        arr = temp.toArray(arr);
       // accommodationProviders = Arrays.asList(response.body());
        hotelListAdapter=new HotelListAdapter(HotelListActivity.this,arr);
        hotelListView.setAdapter(hotelListAdapter);

      //  AccommodationListAdapter accommodationListAdapter=new AccommodationListAdapter(HotelListActivity.this,temp);
        //accommodationListAdapter.setAccommodationListener(AccommodationProviderActivity.this);
        //hotelListView.setAdapter(accommodationListAdapter);


    }

    void filterSearch(int strt, int end){
        strt = Integer.parseInt(BanglaNumberParser.getEnglish(strt+""));
        end = Integer.parseInt(BanglaNumberParser.getEnglish(end+""));
        //Toast.makeText(getApplicationContext(),strt+"", Toast.LENGTH_SHORT).show();
        //Log.e("start ", strt+"");
        //Log.e("End ", end+"");
        List<AccommodationProvider> temp = new ArrayList<>();
        String var = "";
        for (int i = 0; i< accommodationProviders.size();i++){
            AccommodationProvider accommodationProvider = accommodationProviders.get(i);
            try {
                accommodationProvider.review = Float.parseFloat(accommodationProvider.reviewRatingAverage);
            }catch (Exception ex){

            }
            //Toast.makeText(getApplicationContext(),accommodationProvider.review+"", Toast.LENGTH_SHORT).show();

            if (accommodationProvider.review >=strt && accommodationProvider.review <= end){

                temp.add(accommodationProvider);
            }


        }
        if (temp.size() == 0)
        {
            String notFound ="Not Found";
            if (!BaseURL.LANGUAGE_ENG){
                notFound = "পাওয়া যায় নি ";

            }

            Toast.makeText(this,notFound,Toast.LENGTH_SHORT).show();
            //return;
        }

        AccommodationProvider[] arr = new AccommodationProvider[temp.size()];
        arr = temp.toArray(arr);
        // accommodationProviders = Arrays.asList(response.body());
        hotelListAdapter=new HotelListAdapter(HotelListActivity.this,arr);
        hotelListView.setAdapter(hotelListAdapter);




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
