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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.RouteListAdapter;
import adapters.TripListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.RouteCallback;
import constants.Travel;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import io.realm.Realm;
import listeners.TransportCostListener;
import model.Route;
import model.TransportProvider;
import model.Trip;
import retrofit2.Call;
import retrofit2.Response;
import userDefinder.TailorMade;

public class TripRouteActivity extends AppCompatActivity implements TransportCostListener{
    private static final int MENU_ITEM_NEXT = 1;
    private static int perPersonTranspotCost =0;
    private static int totalTranspotCost =0;
    private ArrayList<TransportProvider> transportProviders=new ArrayList<>();
    private int from_district_id=0;
    private int to_district_id=0;

    private Route [] routes;
    // Data store
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Realm realm;
    // Views
    @BindView(R.id.tripList) RecyclerView tripList;
    @BindView(R.id.itineraryPlaceholderText) TextView itineraryPlaceholderText;
    @BindView(R.id.transportCostTotal) TextView transportCostTotal;
    @BindView(R.id.GrandTotal) TextView GrandTotal;
    @BindView(R.id.txtPersons) TextView txtPersons;
    @BindView(R.id.estimtedcostforlang) TextView estimtedcostforlang;



    //----------------------------------------------
    private RouteCallback routeCallback;
    private TripListAdapter tripListAdapter;
    private ArrayList<Trip> tripArrayList=new ArrayList<>();
    private int person=1;
    private TailorMade tailorMade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_trip);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (!BaseURL.LANGUAGE_ENG)
        {
            this.setTitle("পরিবহন রুট");
            estimtedcostforlang.setText("আনুমানিক পরিবহন খরচ");
        }
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_clear_24);
        // Data storage init
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_key),MODE_PRIVATE);
        editor=sharedPreferences.edit();
        Realm.init(this);
        realm=Realm.getDefaultInstance();
        // initialize callbacks
        routeCallback=new RouteCallback(this);

        int current_tailormade_id=sharedPreferences.getInt(Travel.CURRENT_TAILORMADE_ID,0); // current tailormade
        tailorMade=realm.where(TailorMade.class).equalTo("tailormade_id",current_tailormade_id).findFirst();
        List<Route> realm_route=tailorMade.routes;
        // checking if route is set previously or not
        // if data set previously set new routes
        if(realm_route!=null && realm_route.size()!=0 ){
            from_district_id=sharedPreferences.getInt(Travel.DEPART_LOCATION,0);
            to_district_id=sharedPreferences.getInt(Travel.TO_LOCATION,0);
            realm_route=realm.copyFromRealm(realm_route);
            Toast.makeText(this, "Not null", Toast.LENGTH_SHORT).show();
//            if(realm_route.get(0).getStartDistrictId()==from_district_id && realm_route.get(realm_route.size()-1).getEndDistrictId()==to_district_id){
                // Add all previous transport provider in selected list
                for (int i=0; i<realm_route.size();i++){
                    TransportProvider temp_provider=realm_route.get(i).getTransportProvider();
                    if (temp_provider!=null){
                        transportProviders.add(temp_provider);
                    }

                }
                routes=realm_route.toArray(new Route[realm_route.size()]);
                RouteListAdapter routeListAdapter=new RouteListAdapter(TripRouteActivity.this,routes);
                routeListAdapter.setTransportCostListener(TripRouteActivity.this);
                tripList.setAdapter(routeListAdapter);
                return;
//            }
        }
        searchRoute();

//        ------------------------------------------

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String menuName = "Next";
        if (!BaseURL.LANGUAGE_ENG) menuName = "পরবর্তী";
        MenuItem menuItem=menu.add(Menu.NONE,MENU_ITEM_NEXT, Menu.NONE,menuName);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case MENU_ITEM_NEXT:

                realm.beginTransaction();
                tailorMade.transportCost=totalTranspotCost;
                //BaseURL.totalCost+= totalTranspotCost;
                tailorMade.routes.clear();
                tailorMade.routes.addAll(Arrays.asList(routes));
                realm.commitTransaction();
//                .........................................................
                Intent intent=new Intent(TripRouteActivity.this,AccommodationActivity.class);
                startActivity(intent);
                break;

            case android.R.id.home:
                onBackPressed();
                break;
                // migration done
        }
        return super.onOptionsItemSelected(item);
    }


    public void searchRoute() {

// ;
        // get data from user input from trip planner fragment
        from_district_id=sharedPreferences.getInt(Travel.DEPART_LOCATION,0);
        to_district_id=sharedPreferences.getInt(Travel.TO_LOCATION,0);
        int person=sharedPreferences.getInt(Travel.NUMBER_OF_TOUIST,0);
        if (!BaseURL.LANGUAGE_ENG)
        {
            txtPersons.setText("আনুমানিক খরচ ("+ BanglaNumberParser.getBangla(person+"")+" জন)");
        }
        else txtPersons.setText("Estimated cost ("+person+" Person)");
        // get routes according to user input
        createRouteList(from_district_id,to_district_id);

    }


    private void createRouteList(int location_id,int destination_id){
        //Toast.makeText(this,location_id + " "+destination_id,Toast. )
        routeCallback.getRoutes(location_id,destination_id).enqueue(new CustomCallBack<Route[]>(this) {
            @Override
            public void onResponse(Call<Route[]> call, Response<Route[]> response) {
                super.onResponse(call,response);
                Log.e("Package Url",call.request().url().toString());
                routes=response.body();

                if(response.body()==null||response.body().length==0)
                {
                    itineraryPlaceholderText.setVisibility(View.VISIBLE);
                }
                else
                {
                    itineraryPlaceholderText.setVisibility(View.GONE);
                    if (routes[0].getStartDistrictName() == null || routes[0].getStartDistrictName() == "")
                    {

                        String meesage ="Nothing Found";
                        if (!BaseURL.LANGUAGE_ENG)
                        {
                            meesage =" কিছুই পাওয়া যায়নি";

                        }
                        Toast.makeText(TripRouteActivity.this,meesage,Toast.LENGTH_LONG).show();

    //                    TripRouteActivity.this.onBackPressed();
                        return;
                    }
                    RouteListAdapter routeListAdapter=new RouteListAdapter(TripRouteActivity.this,routes);
                    routeListAdapter.setTransportCostListener(TripRouteActivity.this);
                    tripList.setAdapter(routeListAdapter);
                    perPersonTranspotCost =0;
                    transportProviders.clear();
                }
            }

            @Override
            public void onFailure(Call<Route[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="No Accomodations";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" কিছুই পাওয়া যায়নি";

                }
                Toast.makeText(TripRouteActivity.this,meesage,Toast.LENGTH_LONG).show();

                TripRouteActivity.this.onBackPressed();
            }
        });


    }

    @Override
    public void addTransportCost(TransportProvider selectedTransportProvider){
        // add cost if any transportis selected
        for(TransportProvider tmpProvider:transportProviders){
            if(tmpProvider.getRouteId()==selectedTransportProvider.getRouteId()){
                if(tmpProvider.getTransportInfoId()==selectedTransportProvider.getTransportInfoId()){
                    String meesage ="Already Selected";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage =" ইতিমধ্যে নির্বাচিত";

                    }
                    Toast.makeText(this,meesage,Toast.LENGTH_LONG).show();

                    return;
                }
                transportProviders.remove(tmpProvider);
                perPersonTranspotCost -=Integer.parseInt(tmpProvider.getTransportInfoPrice());
                totalTranspotCost -=Integer.parseInt(tmpProvider.getTransportInfoPrice())*person;
                break;
            }

        }
         person = sharedPreferences.getInt(Travel.NUMBER_OF_TOUIST, 1);
        transportProviders.add(selectedTransportProvider);
        String cost = "0";
        //Toast.makeText(this,selectedTransportProvider.getTransportInfoPrice(),Toast.LENGTH_LONG).show();
        if (selectedTransportProvider.getTransportInfoPrice() != null)
        {
            cost = selectedTransportProvider.getTransportInfoPrice();
        }
        perPersonTranspotCost +=Integer.parseInt(cost);
        totalTranspotCost +=Integer.parseInt(cost)*person;
        BaseURL.transPortCost = totalTranspotCost;
        if (!BaseURL.LANGUAGE_ENG)
        {
            transportCostTotal.setText(BanglaNumberParser.getBangla(perPersonTranspotCost+"") +" ৳");
            GrandTotal.setText(BanglaNumberParser.getBangla(totalTranspotCost+"")+" ৳");
        }
        else
        {
            transportCostTotal.setText(perPersonTranspotCost +" ৳");
            GrandTotal.setText(totalTranspotCost+" ৳");
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


