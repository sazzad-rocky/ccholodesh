/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import callbacks.PlaceCallback;
import constants.Travel;
import helpers.CustomCallBack;
import helpers.ViewAssist;
import io.realm.Realm;
import io.realm.RealmResults;
import model.Place;
import retrofit2.Call;
import retrofit2.Response;
import userDefinder.TailorMade;

public class TripPlannerActivity extends AppCompatActivity {
    // This activity is not used anymore.. keep it for future use or delete it
    @BindView(R.id.editText_no_of_tourist) EditText editText_no_of_tourist;
    @BindView(R.id.editText_no_of_days) EditText editText_no_of_days;
    @BindView(R.id.departDateTextView) TextView departDateTextView;
    @BindView(R.id.returnDateTextView) TextView returnDateTextView;
    @BindView(R.id.locationAutoComplete) AutoCompleteTextView locationAutoComplete;
    @BindView(R.id.destinationAutoComplete) AutoCompleteTextView destinationAutoComplete;
    // callback
    PlaceCallback placeCallback;
    // TODO local storage
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private List<Place> places;
    private Realm realm;

    @OnClick(R.id.departDateLayout)
    public void setTripDate(View view){
        int days=0;
        String selected_days=editText_no_of_days.getText().toString();
        if(selected_days.length()!=0){
            days=Integer.parseInt(selected_days);
        }
        ViewAssist.setDate(TripPlannerActivity.this,departDateTextView,returnDateTextView,days,"Select Trip date");
    }
    @OnClick(R.id.returnDateLayout)
    public void setReturnDate(View view){
        ViewAssist.setDate(TripPlannerActivity.this,returnDateTextView,"Select Return date");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_planner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        // Data storage init
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_key),MODE_PRIVATE);
        editor=sharedPreferences.edit();
        Realm.init(this);
        realm=Realm.getDefaultInstance();
        // TODO callback init
        placeCallback=new PlaceCallback(this);
        loadLocations();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void searchRoute(View view){

        Intent intent=new Intent(TripPlannerActivity.this,TripRouteActivity.class);

        String destinationlocation=destinationAutoComplete.getText().toString();
        String departlocation=locationAutoComplete.getText().toString();

        String tmp_tourist=editText_no_of_tourist.getText().toString();
        String tmp_days=editText_no_of_days.getText().toString();
        String fromDate=departDateTextView.getText().toString();
        String toDate=returnDateTextView.getText().toString();
        if(!validate()){
            return;
        }
        int numberOfTourist=Integer.parseInt(tmp_tourist);
        int numberOfDays=Integer.parseInt(tmp_days);
        int destinationDistrictId=0;
        String destinationDistrictName="";
        int departDistrictId=0;
        String DepartDistrictName="";

        for (Place place:places){
            if (place.getDistrictName().equalsIgnoreCase(destinationlocation)){
                destinationDistrictId=place.getDistrictId();
                destinationDistrictName=place.getDistrictName();
                break;
            }
        }
        for (Place place:places){
            if (place.getDistrictName().equalsIgnoreCase(departlocation)){
                departDistrictId=place.getDistrictId();
                DepartDistrictName=place.getDistrictName();
                break;
            }
        }

        editor.putInt(Travel.DEPART_LOCATION,departDistrictId);
        editor.putInt(Travel.TO_LOCATION,destinationDistrictId);
        editor.putInt(Travel.NUMBER_OF_TOUIST,numberOfTourist);
        editor.putInt(Travel.NUMBER_OF_DAYS,numberOfDays);
        editor.putString(Travel.DEPART_DATE,fromDate);
        editor.putString(Travel.RETURN_DATE,toDate);
        editor.commit();
        // TODO save tailormade data
        int current_tailormade_id =sharedPreferences.getInt(Travel.CURRENT_TAILORMADE_ID,0);
        RealmResults<TailorMade> tailormades=realm.where(TailorMade.class).equalTo("tailormade_id",current_tailormade_id).findAll();
        TailorMade tailorMade=new TailorMade();
        tailorMade.tailormade_id=current_tailormade_id;
        tailorMade.deapartDistrictId=departDistrictId;
        tailorMade.destinationDistrictId=destinationDistrictId;
        tailorMade.departDistrictName=DepartDistrictName;
        tailorMade.destinationDistrictName=destinationDistrictName;
        tailorMade.departDate=fromDate;
        tailorMade.returnDate=toDate;
        tailorMade.numberOFDays=numberOfDays;
        tailorMade.numberOFTourists=numberOfTourist;
        if(tailormades.size()==0){

        }
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(tailorMade);
        realm.commitTransaction();

        startActivity(intent);
    }
    public boolean validate(){
        String tmp_tourist=editText_no_of_tourist.getText().toString();
        String tmp_days=editText_no_of_days.getText().toString();
        String fromDate=departDateTextView.getText().toString();
        String toDate=returnDateTextView.getText().toString();
        String destinationlocation=destinationAutoComplete.getText().toString();
        String departlocation=locationAutoComplete.getText().toString();
        if(tmp_tourist.length()==0|| tmp_tourist==""){
            Toast.makeText(this, "Enter Number of tourist", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tmp_days.length()==0|| tmp_days==""){
            Toast.makeText(this, "Enter Number of days", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(fromDate==null|| fromDate==""){
            Toast.makeText(this, "Enter depart date", Toast.LENGTH_SHORT).show();
            return false;
        }
         if(toDate==null|| toDate==""){
            Toast.makeText(this, "Enter return date", Toast.LENGTH_SHORT).show();
             return false;
        }
        if(destinationlocation==null|| destinationlocation==""){
            Toast.makeText(this, "Enter Destination ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(departlocation==null|| departlocation==""){
            Toast.makeText(this, "Enter Depart Location", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void loadLocations(){
        placeCallback.getLocations().enqueue(new CustomCallBack<Place[]>(this) {
            @Override
            public void onResponse(Call<Place[]> call, Response<Place[]> response) {
                super.onResponse(call,response);
                places= Arrays.asList(response.body());
                ArrayAdapter<Place> placeAdapter=new ArrayAdapter<Place>(getApplicationContext(),R.layout.layout_spinner,R.id.spinnerText,response.body());
                locationAutoComplete.setAdapter(placeAdapter);
                destinationAutoComplete.setAdapter(placeAdapter);
            }

            @Override
            public void onFailure(Call<Place[]> call, Throwable t) {
                super.onFailure(call,t);
                Toast.makeText(TripPlannerActivity.this, "Locations Could not be loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
