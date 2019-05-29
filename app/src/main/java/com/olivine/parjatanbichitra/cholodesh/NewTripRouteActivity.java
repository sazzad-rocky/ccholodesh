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
import android.support.v7.widget.LinearLayoutManager;
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
import java.util.Arrays;
import java.util.List;

import adapters.CustomTripPlanTransportAdapter;
import adapters.NewRouteListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.RouteCallback;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import helpers.CustomTripPlanDataHolder;
import helpers.RouteCallBack;
import helpers.RouteTwoCallBack;
import helpers.TailorMadeDataHolder;
import listeners.TransportCostListener;
import model.CustomTripPlanNewRouteGetModel;
import model.CustomTripPlanRoutesListModel;
import model.Route;
import model.TransportProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import userDefinder.TailorMade;
public class NewTripRouteActivity extends AppCompatActivity implements TransportCostListener, RouteCallBack , RouteTwoCallBack{
    RecyclerView selectedoperators;
    RouteCallback routeCallback;
    private Button btnaccommodation;
    private TextView transportdates;
    CustomTripPlanRoutesListModel list;
    private List<CustomTripPlanNewRouteGetModel> routesList;
    private LinearLayoutManager layoutManager;
    Context context;
    RecyclerView TripList;
    //    @BindView(R.id.tripList)
//    RecyclerView tripList;
    @BindView(R.id.itineraryPlaceholderText)
    TextView itineraryPlaceholderText;
    @BindView(R.id.transportCostTotal)
    TextView transportCostTotal;
    @BindView(R.id.GrandTotal)
    TextView GrandTotal;
    @BindView(R.id.txtPersons)
    TextView txtPersons;
    @BindView(R.id.estimtedcostforlang)
    TextView estimtedcostforlang;
    @BindView(R.id.slectedRoutes)
    TextView slectedRoutes;
    //    @BindView(R.id.repress) TextView repress;
    Button recallBatton;

    @BindView(R.id.selectRouteforLang)
    TextView selectRouteforLang;
    NewRouteListAdapter ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_trip);
        btnaccommodation=(Button)findViewById(R.id.btnaccommodation);
        transportdates =findViewById(R.id.transportdates);
        if (!BaseURL.LANGUAGE_ENG){
            btnaccommodation.setText("পরবর্তী");
            transportdates.setText("পরিবহণ তারিখ ক্রমানুসারে উপর থেকে নির্বাচন করুন। ");
        }
        btnaccommodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTripRouteActivity.this, AccommodationActivity.class);
                startActivity(intent);
            }
        });
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select Transport");

        String routeName = CustomTripPlanDataHolder.selectedRoutesName;
        TripList = (RecyclerView) findViewById(R.id.tripList);
        selectedoperators = (RecyclerView) findViewById(R.id.selectedoperators);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        TripList.setLayoutManager(mLayoutManager);
        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        selectedoperators.setLayoutManager(mLayoutManager2);

        //start
//      if (BaseURL.flag==true){
//          Toast.makeText(context, "yyy", Toast.LENGTH_SHORT).show();
//            NewRouteListAdapter newRouteListAdapter = new NewRouteListAdapter(NewTripRouteActivity.this,routesList);
//            ref = newRouteListAdapter;
//            TripList.setAdapter(newRouteListAdapter);
//
//        }
        //end

        if (routeName.charAt(routeName.length() - 1) == '-') {
            routeName = routeName.substring(0, routeName.length() - 1);
        }
        routeName = CustomTripPlanDataHolder.selectedRoutesName.split("-")[0];
        for (String route : CustomTripPlanDataHolder.districtsName) {
            if (route.length() > 0)
                routeName += "-" + route;
        }
        //slectedRoutes.setText(routeName.charAt(routeName.length()-1) == '-'? routeName.substring(0,routeName.length()-1) : routeName);
        slectedRoutes.setText(routeName);
        String person = CustomTripPlanDataHolder.noOfTourist + " Person " + CustomTripPlanDataHolder.noOfChildren + " Children(s) ";

        if (!BaseURL.LANGUAGE_ENG) {
            person = BanglaNumberParser.getBangla(CustomTripPlanDataHolder.noOfTourist + "") + " ব্যক্তি";
        }
        txtPersons.setText(person);
        if (!BaseURL.LANGUAGE_ENG) {
            this.setTitle("পরিবহন রুট");
            estimtedcostforlang.setText("আনুমানিক পরিবহন খরচ");
            selectRouteforLang.setText("নির্বাচিত রুট সমূহ ");
        }

        routeCallback = new RouteCallback(this);
        context = this;
        routesList = new ArrayList<>();
        list = CustomTripPlanDataHolder.selectedRoutes;

        // Gson g = new JSO

        Gson gson = new Gson();
        String json = gson.toJson(list);
        Log.e("Url", "Null");
        Log.e("JSON", json);


        routeCallback.getRoutestest(list).enqueue(new CustomCallBack<List<CustomTripPlanNewRouteGetModel[]>>(context) {
            @Override
            public void onResponse(Call<List<CustomTripPlanNewRouteGetModel[]>> call, Response<List<CustomTripPlanNewRouteGetModel[]>> response) {
                super.onResponse(call, response);
                Log.e("Package Url", call.request().url().toString());

                if (response.body() != null && response.body().size() > 0) {
                    //  Toast.makeText(context,response.body().size()+"",Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < response.body().size(); i++) {
                        CustomTripPlanNewRouteGetModel[] temp = response.body().get(i);
                        // Toast.makeText(context,temp.length+"",Toast.LENGTH_SHORT).show();
                        //for (int j =0;j< temp.length;j++){
                        //if (temp[j].getStartDistrictId() != null)
                        if (temp.length > 0)
                            routesList.add(temp[0]);
                        //  }
                    }
                    //  Toast.makeText(context,routesList.size()+"",Toast.LENGTH_SHORT).show();
                    if (routesList.size() > 0) {

                        NewRouteListAdapter newRouteListAdapter = new NewRouteListAdapter(NewTripRouteActivity.this, routesList);
                        ref = newRouteListAdapter;
                        TripList.setAdapter(newRouteListAdapter);
                    } else {
                        Toast.makeText(context, "No route found", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(context, "Null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CustomTripPlanNewRouteGetModel[]>> call, Throwable t) {
                super.onFailure(call, t);
                Log.e("Package Url", call.request().url().toString() + " ERROR : " + t.toString());
                //Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String menuName = "Next";
        if (!BaseURL.LANGUAGE_ENG) menuName = "পরবর্তী";
        MenuItem menuItem = menu.add(Menu.NONE, 1, Menu.NONE, menuName);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(NewTripRouteActivity.this, AccommodationActivity.class);
                startActivity(intent);
                break;

            case android.R.id.home:
                onBackPressed();
                break;
            // migration done
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String costInd = (CustomTripPlanDataHolder.transPortcost / CustomTripPlanDataHolder.noOfTourist) + "";
        String costTotal = (CustomTripPlanDataHolder.transPortcost) + "";
        if (!BaseURL.LANGUAGE_ENG) {

            costInd = BanglaNumberParser.getBangla(costInd);
            costTotal = BanglaNumberParser.getBangla(costTotal);
        }

        transportCostTotal.setText(costInd + "৳");
        GrandTotal.setText(costTotal + "৳");

        //Toast.makeText(context,CustomTripPlanDataHolder.transportProviders.size()+"", Toast.LENGTH_SHORT).show();
//
        List<TransportProvider> list = CustomTripPlanDataHolder.transportProviders;
        CustomTripPlanTransportAdapter adapter = new CustomTripPlanTransportAdapter(this, this, list);
        selectedoperators.setAdapter(adapter);
        adapter.setListener(NewTripRouteActivity.this);
    }

    @Override
    public void addTransportCost(TransportProvider transportProvider) {
        String costInd = (CustomTripPlanDataHolder.transPortcost / CustomTripPlanDataHolder.noOfTourist) + "";
        String costTotal = (CustomTripPlanDataHolder.transPortcost) + "";
        if (!BaseURL.LANGUAGE_ENG) {
            costInd = BanglaNumberParser.getBangla(costInd);
            costTotal = BanglaNumberParser.getBangla(costTotal);
        }
        transportCostTotal.setText(costInd + "৳");
        GrandTotal.setText(costTotal + "৳");
    }

    @Override
    public void recreateRoute() {
        NewRouteListAdapter newRouteListAdapter = new NewRouteListAdapter(NewTripRouteActivity.this, routesList);
        ref = newRouteListAdapter;
        TripList.setAdapter(newRouteListAdapter);


    }

    @Override
    public void recreateRouteTwo() {
        Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
        NewRouteListAdapter newRouteListAdapter = new NewRouteListAdapter(NewTripRouteActivity.this, routesList);
        ref = newRouteListAdapter;
        TripList.setAdapter(newRouteListAdapter);
    }

}


