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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import adapters.RouteAccommodationAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.RouteCallback;
import constants.Travel;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import helpers.CustomTripPlanDataHolder;
import helpers.TailorMadeDataHolder;
import io.realm.Realm;
import listeners.AccommodationListener;
import model.AccommodationRoom;
import model.ListWrapper;
import model.Route;
import retrofit2.Call;
import retrofit2.Response;
import userDefinder.TailorMade;

public class AccommodationActivity extends AppCompatActivity implements AccommodationListener {
    // This is the activity where user will choose own accommodation for tailor made
    public static final int ACTION_MULTIPLE_ROOM_ADDITION=0;
    public static final int ACTION_DELETE_ROOM=1;
    public static final int ACTION_INCRESE_ROOM=2;
    public static final int ACTION_DECREASE_ROOM=3;
    int test = 0;
    private RouteCallback routeCallback;
    private ArrayList<AccommodationRoom> accommodationRooms;
    private ArrayList<AccommodationRoom> my;
    private static int totalAccommodationCost=0;
    private Button btnnext;
    // TODO Data storages declear
    SharedPreferences sharedPreferences;
    Realm realm;
    // TODO init view
    @BindView(R.id.accommodations) RecyclerView accommodations;
    @BindView(R.id.selectdRooms) RecyclerView selectedAccommodation;
    @BindView(R.id.accommodationCostTotal) TextView accommodationCostTotal;
    @BindView(R.id.estimaforlang) TextView estimaforlang;
    @BindView(R.id.totalCost) TextView totalCost;
    @BindView(R.id.totalForLang) TextView totalForLang;
    private TailorMade tailorMade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation);
        btnnext =findViewById(R.id.btnnext);
        if (!BaseURL.LANGUAGE_ENG){
            btnnext.setText("পরবর্তী");
        }
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Hotel");
        if (BaseURL.checkoutdates.size()>0) {
            BaseURL.checkindates.clear();
            BaseURL.checkoutdates.clear();
            }
        if (!BaseURL.LANGUAGE_ENG)
        {
            getSupportActionBar().setTitle("হোটেল");
            this.setTitle("বাসস্থান নির্বাচন করুন");
            estimaforlang.setText("আনুমানিক বাসস্থানের খরচ");
            totalForLang.setText("মোট");
            getSupportActionBar().setTitle("হোটেল নির্বাচন করুন");
            accommodationCostTotal.setText(BanglaNumberParser.getBangla("00")+" ৳");
        }
        //TODO init callback
        routeCallback=new RouteCallback(this);
        accommodationRooms=new ArrayList<>();
        my = new ArrayList<>();
        int cost = CustomTripPlanDataHolder.transPortcost;
        if (BaseURL.isEdit){
            cost+= TailorMadeDataHolder.accommodationCost;
        }
        if (!BaseURL.LANGUAGE_ENG)
        totalCost.setText(BanglaNumberParser.getBangla(cost+"")+" ৳");
        else
            totalCost.setText(cost+""+" ৳");
//        ...............................................
//        //TODO Data storage init
//        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_key),MODE_PRIVATE);
//        Realm.init(this);
//        realm=Realm.getDefaultInstance();
//        // ------------------------------------------------
//        int tailormade_id=sharedPreferences.getInt(Travel.CURRENT_TAILORMADE_ID,0);
//        tailorMade=realm.where(TailorMade.class).equalTo("tailormade_id",tailormade_id).findFirst();
////        ArrayList<AccommodationRoom> accomodationRoomsFromRealm= (ArrayList<AccommodationRoom>) realm.copyFromRealm(tailorMade.accommodationRooms);
//        if (!BaseURL.LANGUAGE_ENG)
//        {
//            accommodationCostTotal.setText(BanglaNumberParser.getBangla(BaseURL.totalCost+"")+" ৳");
//            totalCost.setText(BanglaNumberParser.getBangla(BaseURL.totalCost+BaseURL.transPortCost+"")+" ৳");
//        }
//        else{
//            accommodationCostTotal.setText(BaseURL.totalCost+" ৳");
//            totalCost.setText(BaseURL.totalCost+BaseURL.transPortCost+" ৳");
//        }
//        //TODO Load with prevoius accommodation data if has any
//        if(accomodationRoomsFromRealm.size()>0 && accomodationRoomsFromRealm!=null){
//            RouteAccommodationAdapter routeAccommodationAdapter=new RouteAccommodationAdapter(AccommodationActivity.this,accomodationRoomsFromRealm);
//            routeAccommodationAdapter.setAccommodationListener(AccommodationActivity.this);
//            accommodations.setAdapter(routeAccommodationAdapter);
//            accommodationRooms=accomodationRoomsFromRealm;
//
//            totalAccommodationCost=getTotalAccommodationCost();
//            if (!BaseURL.LANGUAGE_ENG)
//            {
//                accommodationCostTotal.setText(BanglaNumberParser.getBangla(BaseURL.totalCost+"")+" ৳");
//                totalCost.setText(BanglaNumberParser.getBangla(BaseURL.totalCost+BaseURL.transPortCost+"")+" ৳");
//
//            }
//            else {
//                accommodationCostTotal.setText(BaseURL.totalCost+" ৳");
//                totalCost.setText(BaseURL.totalCost+BaseURL.transPortCost+" ৳");
//            }
//        }else{
////            viewAccommodationPlaces(1,26);
//            // TODO Load new accommodation according to route
           // viewAccommodationPlaces(0,0);
//            RouteAccommodationAdapter routeAccommodationAdapter = new RouteAccommodationAdapter(this,TailorMadeDataHolder.accommodationRooms);
//        routeAccommodationAdapter.setAccommodationListener(AccommodationActivity.this);
//        selectedAccommodation.setAdapter(routeAccommodationAdapter);


//        for (int i = 0; i< 3;i++){
//            AccommodationRoom accommodationRoom = new AccommodationRoom();
//            accommodationRoom.setDistrictId(24);
//            accommodationRoom.setSelected(true);
//            accommodationRoom.setDistrictName("CHITTAGONG");
//            accommodationRoom.setAccommodationRoomId(1);
//            accommodationRoom.setAccommodationRoomPrice("200");
//            accommodationRooms.add(accommodationRoom);
//
//        }
//
//        for (int i = 0; i< 3;i++){
//            AccommodationRoom accommodationRoom = new AccommodationRoom();
//            accommodationRoom.setDistrictId(26);
//            accommodationRoom.setSelected(true);
//            accommodationRoom.setDistrictName("COXBAZAR");
//            accommodationRoom.setAccommodationRoomId(2);
//            accommodationRoom.setAccommodationRoomPrice("200");
//            accommodationRooms.add(accommodationRoom);
//
//        }
//
//        for (int i = 0; i< 1;i++){
//            AccommodationRoom accommodationRoom = new AccommodationRoom();
//            accommodationRoom.setDistrictId(1);
//            accommodationRoom.setSelected(true);
//            accommodationRoom.setDistrictName("DHAKA");
//            accommodationRoom.setAccommodationRoomId(2);
//            accommodationRoom.setAccommodationRoomPrice("200");
//            accommodationRooms.add(accommodationRoom);
//
//        }


        boolean flag = getIntent().getBooleanExtra("FROM_EDIT",false);
        if (flag)
        {
            //Toast.makeText(getApplicationContext(),TailorMadeDataHolder.routeAccommodations.get(0).getDistrictName(),Toast.LENGTH_SHORT).show();
            accommodationRooms = TailorMadeDataHolder.routeAccommodations;
            RouteAccommodationAdapter routeAccommodationAdapter=new RouteAccommodationAdapter(AccommodationActivity.this,accommodationRooms);
            routeAccommodationAdapter.setAccommodationListener(AccommodationActivity.this);
            BaseURL.totalCost = TailorMadeDataHolder.accommodationCost;
            accommodationCostTotal.setText(TailorMadeDataHolder.accommodationCost+" ৳");
            accommodations.setAdapter(routeAccommodationAdapter);
            BaseURL.accommodationRooms.addAll(accommodationRooms);
        }
        else {
            viewAccommodationPlaces(0,0);
        }
//        }
//


    }
    public void viewAccommodationPlaces(int location,int destination){
        //Toast.makeText(AccommodationActivity.this,location+" "+ destination,Toast.LENGTH_LONG).show();
        // Get Routes
       // routeCallback.getRoutes(location,destination).enqueue(new CustomCallBack<Route[]>(this) {
       //     @Override
          //  public void onResponse(Call<Route[]> call, Response<Route[]> response) {
           //     super.onResponse(call,response);
            //    Log.e("Package Url",call.request().url().toString());

             //   if (response.body() != null && response.body().length > 0)
             //   {

                    for(Route route: CustomTripPlanDataHolder.routes){
                        AccommodationRoom accommodationRoom=new AccommodationRoom();
                        accommodationRoom.setDistrictId(route.getEndDistrictId());
                        accommodationRoom.setDistrictName(route.getEndDistrictName());

                        accommodationRooms.add(accommodationRoom);

                ///    }
                    if (accommodationRooms.get(0).getDistrictId() == null)
                    {
                        String meesage ="Nothing Found";
                        if (!BaseURL.LANGUAGE_ENG)
                        {
                            meesage =" কিছুই পাওয়া যায়নি";

                        }
                        Toast.makeText(AccommodationActivity.this,meesage,Toast.LENGTH_LONG).show();
                        return;
                    }

                    RouteAccommodationAdapter routeAccommodationAdapter=new RouteAccommodationAdapter(AccommodationActivity.this,accommodationRooms);
                    routeAccommodationAdapter.setAccommodationListener(AccommodationActivity.this);
                    accommodations.setAdapter(routeAccommodationAdapter);


//                        int counter;
//                        for(counter=0;counter<accommodationRooms.size();counter++)
//                        {
//                            AccommodationRoom tmp_provider_room=accommodationRooms.get(counter);
//                            // Get the selected hotel and place in the correct position of list
//
//                            Toast.makeText(this,TailorMadeDataHolder.accommodationRooms.size()+"",Toast.LENGTH_SHORT).show();
//                            if(tmp_provider_room.getDistrictId()==accommodationRooms.get(counter).getDistrictId()){
//                                accommodationRooms.addAll(counter+1,getAccommodationRoomsForSelectedDistrcit(tmp_provider_room.getDistrictId()));
//
//                                //accommodationRooms.addAll(counter+1,selecedProvidersRoom);
//                                //Toast.makeText(this,accommodationRooms.size()+ " ",Toast.LENGTH_LONG).show();
//                                break;
//
//                            }
//                        }
//
//                         routeAccommodationAdapter=new RouteAccommodationAdapter(AccommodationActivity.this,accommodationRooms);
//                        routeAccommodationAdapter.setAccommodationListener(AccommodationActivity.this);
//                        accommodations.setAdapter(routeAccommodationAdapter);
                }
            //    else
             //   {
            //        String meesage ="No Accomodations";
            //        if (!BaseURL.LANGUAGE_ENG)
             //       {
             //           meesage =" কিছুই পাওয়া যায়নি";

             //       }
             //       Toast.makeText(AccommodationActivity.this,meesage,Toast.LENGTH_LONG).show();

            //    }

       //     }

        //    @Override
        //    public void onFailure(Call<Route[]> call, Throwable t) {
        //        super.onFailure(call,t);
         //       String meesage ="Network Error";
           //     if (!BaseURL.LANGUAGE_ENG)
        //        {
            //        meesage =" নেটওয়ার্ক ত্রুটি";
//
           //     }
           //     Toast.makeText(AccommodationActivity.this,meesage,Toast.LENGTH_LONG).show();

       //     }
      //  });
    }

    private List<AccommodationRoom> getAccommodationRoomsForSelectedDistrcit (int id){
        List<AccommodationRoom> temp = new ArrayList<>();
        for (int i =0; i<TailorMadeDataHolder.accommodationRooms.size();i++){
            AccommodationRoom var = TailorMadeDataHolder.accommodationRooms.get(i);
            if (var.getDistrictId() == id){
                temp.add(var);
            }
        }
        return temp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String menuname = "Next";
        if (!BaseURL.LANGUAGE_ENG)
        {
            menuname = "পরবর্তী";
        }
        MenuItem menuItem=menu.add(Menu.NONE,1,Menu.NONE,menuname);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Save accommodation data when go to next activity
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case 1:
//                realm.beginTransaction();
//                tailorMade.accommodationRooms.clear();
//                tailorMade.accommodationCost=totalAccommodationCost;
//                for(AccommodationRoom selected_rooms:accommodationRooms){
//                    tailorMade.accommodationRooms.add(selected_rooms);
//                }
//                realm.commitTransaction();

                Intent intent=new Intent(AccommodationActivity.this,ItineraryPlanner.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode==200)
        {
               String json=data.getExtras().getString(Travel.KEY_ROOM_LIST,"");
               //Toast.makeText(this,json+ " json",Toast.LENGTH_LONG).show();
               //Intent popup=new Intent(this,ShowPopUp.class);
               //popup.putExtra("DETAILS_ATTRACTION",json);
               //popup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               //startActivity(popup);
            ListWrapper listWrapper=new Gson().fromJson(json,ListWrapper.class);
            //test += listWrapper.getAccommodationRooms().size();
            for (int i = 0; i<listWrapper.getAccommodationRooms().size();i++)
            {
                totalAccommodationCost+= Integer.parseInt(listWrapper.getAccommodationRooms().get(i).getAccommodationRoomPrice());
            }
            if (!BaseURL.LANGUAGE_ENG)
            {
                accommodationCostTotal.setText(BanglaNumberParser.getBangla(BaseURL.totalCost+"")+" ৳");
                totalCost.setText(BanglaNumberParser.getBangla(BaseURL.totalCost+CustomTripPlanDataHolder.transPortcost+"")+" ৳");
            }
            else {
                accommodationCostTotal.setText(BaseURL.totalCost + " ৳");
                totalCost.setText(BaseURL.totalCost+CustomTripPlanDataHolder.transPortcost+" ৳");
            }

            //Toast.makeText(this,listWrapper.getAccommodationRooms().size()+" "+test,Toast.LENGTH_LONG).show();
            //Toast.makeText(this,requestCode+" ",Toast.LENGTH_LONG).show();
            if(listWrapper.getAccommodationRooms().size()!=0)
            {
                ArrayList<AccommodationRoom> selecedProvidersRoom=listWrapper.getAccommodationRooms();

                int idTobeMatched=requestCode;
                //Toast.makeText(this,requestCode+"",Toast.LENGTH_SHORT).show();
                boolean isdesticationMatched=false;
                int counter;
                for(counter=0;counter<accommodationRooms.size();counter++)
                {
                       AccommodationRoom tmp_provider_room=accommodationRooms.get(counter);
                        // Get the selected hotel and place in the correct position of list
                       if(tmp_provider_room.getDistrictId()==idTobeMatched){
                            accommodationRooms.addAll(counter+1,selecedProvidersRoom);
                           //accommodationRooms.addAll(counter+1,selecedProvidersRoom);
                           //Toast.makeText(this,accommodationRooms.size()+ " ",Toast.LENGTH_LONG).show();
                           break;
                       }
                }
              //  Toast.makeText(this, "4"+accommodationRooms.size(), Toast.LENGTH_SHORT).show();
                RouteAccommodationAdapter routeAccommodationAdapter=new RouteAccommodationAdapter(AccommodationActivity.this,accommodationRooms);
                routeAccommodationAdapter.setAccommodationListener(AccommodationActivity.this);
                accommodations.setAdapter(routeAccommodationAdapter);
               //Toast.makeText(AccommodationActivity.this,"Here",Toast.LENGTH_SHORT).show();
            }


        }
    }

//
                private int getTotalAccommodationCost(){
                    //AccommodationRoom ar = accommodationRooms.get(0);
                for (int i = 0; i<accommodationRooms.size()-1;i++){
                    AccommodationRoom ar = accommodationRooms.get(i);
                    if(ar.getProviderId()==null){
                        continue;
                    }
                    //Toast.makeText(this, ar.getQuantity()+" ",Toast.LENGTH_LONG).show ();
                    totalAccommodationCost+=Integer.parseInt(ar.getAccommodationRoomPrice())*ar.getQuantity();

                }
                return totalAccommodationCost;

    }

    @Override
    public void calculateAccommodationCost(AccommodationRoom accommodationRoom, int action) {
        switch (action){
            case ACTION_INCRESE_ROOM:
                totalAccommodationCost+=Integer.parseInt(accommodationRoom.getAccommodationRoomPrice());
                BaseURL.totalCost+= Integer.parseInt(accommodationRoom.getAccommodationRoomPrice());
                break;
            case ACTION_DECREASE_ROOM:
                totalAccommodationCost-=Integer.parseInt(accommodationRoom.getAccommodationRoomPrice());
                BaseURL.totalCost-= Integer.parseInt(accommodationRoom.getAccommodationRoomPrice());
                break;
            case ACTION_DELETE_ROOM:
                int accommodationCost=Integer.parseInt(accommodationRoom.getAccommodationRoomPrice());
                int roomQuantity=accommodationRoom.getQuantity();

                totalAccommodationCost-=accommodationCost*roomQuantity;
                BaseURL.totalCost-= accommodationCost*roomQuantity;
                break;
        }
        if (!BaseURL.LANGUAGE_ENG)
        {
            accommodationCostTotal.setText(BanglaNumberParser.getBangla(BaseURL.totalCost+"")+" ৳");
            totalCost.setText(BanglaNumberParser.getBangla(BaseURL.totalCost+CustomTripPlanDataHolder.transPortcost+"")+" ৳");
        }
        else {
            accommodationCostTotal.setText(BaseURL.totalCost +" ৳");
            totalCost.setText(BaseURL.totalCost+CustomTripPlanDataHolder.transPortcost+" ৳");
        }
        //Toast.makeText(this,BaseURL.totalCost+"",Toast.LENGTH_SHORT).show();

    }
    @Override
    public void accommodationActivityResult(int resultCode) {
//        TODO
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BaseURL.accommodationRooms.clear();
        BaseURL.totalCost=0;
        finish();
    }

    public void next_accommodation(View view) {
        Intent intent=new Intent(AccommodationActivity.this,ItineraryPlanner.class);
        startActivity(intent);
    }
}
