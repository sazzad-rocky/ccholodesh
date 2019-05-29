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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.CustomTripPlanTransportAdapter;
import adapters.NewRouteListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomTripPlanDataHolder;
import helpers.TailorMadeDataHolder;
import listeners.TransportCostListener;
import model.TransportProvider;
import userDefinder.TailorMade;

public class TailorMadeEditTransport extends AppCompatActivity implements TransportCostListener{

    private Button btnaccommodation;
    RecyclerView selectedoperators;
    Context context;
    @BindView(R.id.tripList)
    RecyclerView tripList;
    @BindView(R.id.slectedRoutes)
    TextView slectedRoutes;
    @BindView(R.id.GrandTotal) TextView GrandTotal;
    @BindView(R.id.txtPersons) TextView txtPersons;
    @BindView(R.id.transportCostTotal) TextView transportCostTotal;
    @BindView(R.id.estimtedcostforlang) TextView estimtedcostforlang;
    @BindView(R.id.selectRouteforLang) TextView selectRouteforLang;

    boolean isFirst = false;
    int initCost = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_custome_trip);
        btnaccommodation=(Button) findViewById(R.id.btnaccommodation);
        btnaccommodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTripPlanDataHolder.routes = TailorMadeDataHolder.routes;
                Intent intent=new Intent(TailorMadeEditTransport.this,AccommodationActivity.class);
                intent.putExtra("FROM_EDIT",true);
                startActivity(intent);
            }
        });
        if (!BaseURL.LANGUAGE_ENG) {
            btnaccommodation.setText("পরবর্তী");
        }
        context = this;
        selectedoperators = (RecyclerView) findViewById(R.id.selectedoperators);
        ButterKnife.bind(this);

        CustomTripPlanDataHolder.routes = TailorMadeDataHolder.routes;
        BaseURL.accommodationRooms = TailorMadeDataHolder.accommodationRooms;
       // BaseURL.totalCost = TailorMadeDataHolder.accommodationCost;

        initCost = TailorMadeDataHolder.transPortatationCost;
        TailorMadeDataHolder.transPortatationCost = TailorMadeDataHolder.transPortatationCost*CustomTripPlanDataHolder.noOfTourist;
        CustomTripPlanDataHolder.transPortcost = TailorMadeDataHolder.transPortatationCost;

        GrandTotal.setText(TailorMadeDataHolder.transPortatationCost+"৳");
        //txtPersons.setText(CustomTripPlanDataHolder.noOfTourist+" Persons(s)");
        transportCostTotal.setText(TailorMadeDataHolder.transPortatationCost/CustomTripPlanDataHolder.noOfTourist+"৳");

        String person = CustomTripPlanDataHolder.noOfTourist+" Person(s)";
        if (!BaseURL.LANGUAGE_ENG){
            person = BanglaNumberParser.getBangla(CustomTripPlanDataHolder.noOfTourist+"") + " ব্যক্তি";
        }
        txtPersons.setText(person);
        if (!BaseURL.LANGUAGE_ENG)
        {
            this.setTitle("পরিবহন রুট");
            estimtedcostforlang.setText("আনুমানিক পরিবহন খরচ");

            selectRouteforLang.setText("নির্বাচিত রুট সমূহ ");
        }

        slectedRoutes.setText(TailorMadeDataHolder.ROUTE);
        CustomTripPlanTransportAdapter adapter = new CustomTripPlanTransportAdapter(this, TailorMadeDataHolder.transportProviders);
        selectedoperators.setAdapter(adapter);
        adapter.setListener(TailorMadeEditTransport.this);

        NewRouteListAdapter newRouteListAdapter = new NewRouteListAdapter(this,TailorMadeDataHolder.getNewRoutesModels);
        tripList.setAdapter(newRouteListAdapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String menuName = "Next";
        if (!BaseURL.LANGUAGE_ENG) menuName = "পরবর্তী";
        MenuItem menuItem=menu.add(Menu.NONE,1, Menu.NONE,menuName);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                //CustomTripPlanDataHolder.routes = TailorMadeDataHolder.routes;
                //Toast.makeText(context,TailorMadeDataHolder.routes.size()+"",Toast.LENGTH_SHORT).show();
                CustomTripPlanDataHolder.routes = TailorMadeDataHolder.routes;

                Intent intent=new Intent(TailorMadeEditTransport.this,AccommodationActivity.class);
                intent.putExtra("FROM_EDIT",true);
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
        if (!isFirst){
            isFirst = true;
            CustomTripPlanDataHolder.transportProviders = new ArrayList<>();
            return;
        }

        transportCostTotal.setText(CustomTripPlanDataHolder.transPortcost / CustomTripPlanDataHolder.noOfTourist + "৳");
        GrandTotal.setText(CustomTripPlanDataHolder.transPortcost + "৳");

        List<TransportProvider> list = CustomTripPlanDataHolder.transportProviders;
        if (BaseURL.isEdit){
            list = TailorMadeDataHolder.transportProviders;
        }        CustomTripPlanTransportAdapter adapter = new CustomTripPlanTransportAdapter(this, list);
        initCost = CustomTripPlanDataHolder.transPortcost/CustomTripPlanDataHolder.noOfTourist;
        selectedoperators.setAdapter(adapter);
        adapter.setListener(TailorMadeEditTransport.this);

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        TailorMadeDataHolder.transPortatationCost = initCost;

    }



    @Override
    public void addTransportCost(TransportProvider transportProvider) {
        transportCostTotal.setText(CustomTripPlanDataHolder.transPortcost/CustomTripPlanDataHolder.noOfTourist + "৳");
        initCost = CustomTripPlanDataHolder.transPortcost;
        //TailorMadeDataHolder.transPortatationCost = initCost;
        GrandTotal.setText(CustomTripPlanDataHolder.transPortcost+"৳");
    }
}
