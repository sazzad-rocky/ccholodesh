/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import adapters.AccommodationRoomListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import constants.Travel;
import helpers.BaseURL;
import helpers.CustomCallBack;
import helpers.CustomTripPlanDataHolder;
import model.AccommodationRoom;
import model.HotelDate;
import model.ListWrapper;
import retrofit2.Call;
import retrofit2.Response;

public class AccommodationRoomlistActivity extends AppCompatActivity {
    //start sazzad
    //AppControler appControler;
    ProgressDialog progressDialog;
    TextView returnDateTextView, departDateTextView, editText_no_of_days, hotel_nametv;
    int year;
    int month;
    int day;
    int dayout;
    Calendar calendar;
    String checkInStr;
    String checkOutStr;
    int checkOut_month = 0;
    int checkIn_month = 0;
    int dayin=0;
    int dayOut=0;
    private String hotel_name;
    //end sazzad

    private static final int DONE_ITEM = 1;
    @BindView(R.id.roomList) RecyclerView roomList;
    @BindView(R.id.min)
    EditText min;
    @BindView(R.id.max)
    EditText max;
    @BindView(R.id.search)
    Button search;
    // Callbacks
    ProvideCallback provideCallback;
    //List
    List<AccommodationRoom> accommodationRooms=new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation_roomlist);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        calendar = Calendar.getInstance(Locale.getDefault());
        // k=1;
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dayout = calendar.get(Calendar.DAY_OF_MONTH)+1;

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);


        //end
//        Intent intent = getIntent();
//        hotel_name = intent.getStringExtra("NAME");

        //setActionBar();
        if (!BaseURL.LANGUAGE_ENG)
        {
            this.setTitle("রুম তালিকা");
            min.setHint("সর্বনিম্ন");
            max.setHint("সর্বোচ্চ");
            search.setText("অনুসন্ধান");
        }
        else this.setTitle(BaseURL.HOTEL_NAME);

        //Callback init
        provideCallback=new ProvideCallback(this);
        //----------------------------
        Intent intent=getIntent();
        int providerId=intent.getIntExtra(Travel.PROVIDER_KEY,0);
        int minprice =intent.getIntExtra("minpricee",0);
        int maxprice =intent.getIntExtra("maxpricee",0);
        if (maxprice<50){
            min.setText("");
            max.setText("");
        }else {
            min.setText(minprice+"");
            max.setText(maxprice+"");
        }


        // show rooms according to hotel
        showRooms(providerId,provideCallback);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AccommodationRoomlistActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                if (min.getText().length() == 0 || max.getText().length() == 0){
                    String error = "Please enter the range of search";
                    if (!BaseURL.LANGUAGE_ENG){
                        error = "অনুসন্ধানের পরিসীমা লিখুন দয়া করে।";
                    }
                    Toast.makeText(AccommodationRoomlistActivity.this,error,Toast.LENGTH_SHORT).show();
                    return;
                }
                filterSearch();
                View view = AccommodationRoomlistActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        roomList.requestFocus();
    }

    private void filterSearch(){
        List<AccommodationRoom> temp = new ArrayList<>();
        int frstRange = Integer.parseInt(min.getText().toString());
        int lastRange = Integer.parseInt(max.getText().toString());
        for (int i = 0 ;i< accommodationRooms.size();i++){
            AccommodationRoom accommodationRoom = new AccommodationRoom();
            accommodationRoom = accommodationRooms.get(i);
            int price = Integer.parseInt(accommodationRoom.getAccommodationRoomPrice());
            if (price >= frstRange && price < lastRange){

                temp.add(accommodationRoom);
            }
        }

        AccommodationRoomListAdapter accommodationRoomListAdapter=new AccommodationRoomListAdapter(AccommodationRoomlistActivity.this,temp);
        roomList.setAdapter(accommodationRoomListAdapter);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String menuName = "Done";


        if (!BaseURL.LANGUAGE_ENG) menuName = "সম্পন্ন করুন ";
        MenuItem menuItem=menu.add(Menu.NONE,DONE_ITEM,Menu.NONE,menuName);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    DatePickerDialog dpd;
    public void checkinDate() {
        dpd = new DatePickerDialog(this, dateListenerclubentry, year, month, day);
        dpd.show();
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    private DatePickerDialog.OnDateSetListener dateListenerclubentry = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            checkIn_month = i1 + 1;
            dayin=i2;
            checkInStr = i2 + "-" + checkIn_month + "-" + i;
            departDateTextView.setText(checkInStr);
            checkIn_month = 0;
        }
    };

    public void checkOutDate() {
        DatePickerDialog dpd = new DatePickerDialog(this, dateOut, year, month, dayout);
        dpd.show();
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }
    private DatePickerDialog.OnDateSetListener dateOut = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            //  String s = i+"/"+i1+"/"+i2;
            checkOut_month = i1 + 1;
            i2=dayin+1;
            checkOutStr = i2 + "-" + checkOut_month + "-" + i;
            returnDateTextView.setText(checkOutStr);
            checkOut_month = 0;

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case DONE_ITEM:
                BaseURL.checkindates.add(CustomTripPlanDataHolder.checkindatee);
                BaseURL.checkoutdates.add(CustomTripPlanDataHolder.checkoutdatee);
                ArrayList<AccommodationRoom> rooms=new ArrayList<>();
                for (AccommodationRoom ar:accommodationRooms){
                    if(ar.isSelected()){
                        rooms.add(ar);
                        BaseURL.accommodationRooms.add(ar);
                            BaseURL.accommodationname = ar.getProviderName();
                            BaseURL.providerId = ar.getProviderId();
                            BaseURL.districtname = ar.getDistrictName();
                            BaseURL.flag=true;
                    }
                }
    HotelDate hotelDate = new HotelDate();
    hotelDate.setCheckindate(CustomTripPlanDataHolder.checkindatee);
    hotelDate.setCheckoutDate(CustomTripPlanDataHolder.checkoutdatee);
    hotelDate.setProviderdate(BaseURL.providerId);
    hotelDate.setHotelName(BaseURL.accommodationname+"("+BaseURL.districtname+")");
    BaseURL.dates.add(hotelDate);
//}
                //Toast.makeText(this,rooms.size()+" activity",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                ListWrapper listWrapper =new ListWrapper();
                listWrapper.setAccommodationRooms(rooms);
                bundle.putString(Travel.KEY_ROOM_LIST,new Gson().toJson(listWrapper));
                intent.putExtras(bundle);
                setResult(200,intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showRooms(int providerId,ProvideCallback callback){
        callback.getAccommodationRoom(providerId).enqueue(new CustomCallBack<AccommodationRoom[]>(this) {
            @Override
            public void onResponse(Call<AccommodationRoom[]> call, Response<AccommodationRoom[]> response) {
                super.onResponse(call,response);
                Log.e("Package Url",call.request().url().toString());
                if (response.body() != null && response.body().length > 0)
                {
                    accommodationRooms= Arrays.asList(response.body());
                    AccommodationRoomListAdapter accommodationRoomListAdapter=new AccommodationRoomListAdapter(AccommodationRoomlistActivity.this,accommodationRooms);
                    roomList.setAdapter(accommodationRoomListAdapter);
                }
                else
                {
                    String meesage ="No Room Available";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage =" কিছুই পাওয়া যায়নি";
                    }
                    Toast.makeText(AccommodationRoomlistActivity.this, meesage, Toast.LENGTH_SHORT).show();
                    AccommodationRoomlistActivity.this.onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<AccommodationRoom[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(AccommodationRoomlistActivity.this,meesage,Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
