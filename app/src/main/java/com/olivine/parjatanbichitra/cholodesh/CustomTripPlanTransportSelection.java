/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.content.Context;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import adapters.CustomTripPlanTransportAdapter;
import adapters.NewRouteListAdapter;
import callbacks.PlaceCallback;
import callbacks.ProvideCallback;
import callbacks.RouteCallback;
import helpers.BaseURL;
import helpers.CustomCallBack;
import helpers.RouteCallBack;
import helpers.RouteTwoCallBack;
import model.Route;
import model.RouteNew;
import model.TransportInfo;
import model.TransportName;
import model.TransportProvider;
import model.TransportType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomTripPlanTransportSelection extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    Spinner transporType,transportName, transportCategory;
    TextView chooseTransportTypeforLang,close;
    RouteTwoCallBack customCallBack;
    PlaceCallback placeCallback;
    ProvideCallback provideCallBack;
    private List<Route> routes;
    private RouteCallback routeCallback;
    private RouteTwoCallBack twoCallBack;


    private Context context;
    private List<TransportInfo> transportInfos;
    private RecyclerView Operators;
    int routeId;
    String routeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_trip_plan_transport_selection);
        routes = new ArrayList<>();
        //getActionBar().hide();
        transporType = (Spinner) findViewById(R.id.transportType);
        transportName = (Spinner) findViewById(R.id.transportName);
        transportCategory = (Spinner) findViewById(R.id.transportCategory);
        Operators = (RecyclerView) findViewById(R.id.Operators);


        //start
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        Operators.setLayoutManager(mLayoutManager);
        //end
        chooseTransportTypeforLang = (TextView) findViewById(R.id.chooseTransportTypeforLang);
        close = (TextView) findViewById(R.id.close);
        context = this;
        placeCallback=new PlaceCallback(this);
        provideCallBack = new ProvideCallback(this);
        routeCallback = new RouteCallback(this);

        routeId = getIntent().getIntExtra("routeId",0);
        routeName = "";
        //Toast.makeText(getActivity(),"working",Toast.LENGTH_LONG);
        if (!BaseURL.LANGUAGE_ENG){
            chooseTransportTypeforLang.setText("যানবাহন ধরণ ");
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
       // sharedPreferences=getSharedPreferences(getString(R.string.preference_file_key),MODE_PRIVATE);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width*.8), (int) (height*.6));


        transporType.setOnItemSelectedListener(this);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       // transportName.setOnItemSelectedListener(this);
        loadTransportTypes(routeId);
    }

    private void loadTransportTypes (int routeId)
    {
        provideCallBack.getTransportTypes(routeId).enqueue(new CustomCallBack<TransportType[]>(this){
            @Override
            public void onResponse(Call<TransportType[]> call, Response<TransportType[]> response) {
                super.onResponse(call, response);
                Log.e("Package Url",call.request().url().toString());

                if (response.body()!=null && response.body().length > 0)
                {
                    ArrayAdapter<TransportType> placeAdapter=new ArrayAdapter<TransportType>(context,R.layout.layout_spinner,R.id.spinnerText,response.body());
                    transporType.setAdapter(placeAdapter);

                    //loadTransportOperators(response.body()[0].getTransportTypeId(),2);
                }
                else
                {
                    String notFound = "No Transport found";
                    if (!BaseURL.LANGUAGE_ENG){

                        notFound = "কোনো যানবাহন পাওয়া যায় নি ";
                    }
                    Toast.makeText(context,notFound,Toast.LENGTH_SHORT).show();
                    finish();
                    onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<TransportType[]> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }
    private void loadTransportOperators (int type, int route)
    {
        transportProviders.clear();

        provideCallBack.getTransportNames(type,route).enqueue(new CustomCallBack<TransportName[]>(this){

            @Override
            public void onResponse(Call<TransportName[]> call, Response<TransportName[]> response) {
                super.onResponse(call, response);
                Log.e("Operator",call.request().url().toString());
                if (response.body() != null && response.body().length> 0)
                {
                    for (int i = 0; i< response.body().length;i++){

                        makeRoute(response.body()[i].getTransportInfoId(),routeId);
                    }
                }
                else
                {

                }


            }

            @Override
            public void onFailure(Call<TransportName[]> call, Throwable t) {
                super.onFailure(call, t);
            }

        });

       // Toast.makeText(context,transportProviders.size()+"",Toast.LENGTH_SHORT).show();
    }
    List<TransportProvider> transportProviders = new ArrayList<>();
    private void makeRoute(final int infoId, final int routeId){

        provideCallBack.getTransportInfo(infoId,routeId).enqueue(new Callback<TransportInfo[]>() {
            @Override
            public void onResponse(Call<TransportInfo[]> call, Response<TransportInfo[]> response) {
               //for (int i =0; i< )
                Log.e("Test",call.request().url().toString());
                TransportProvider temp = new TransportProvider();
                String operatorName =response.body()[0].getTransportOperatorName();
               if (!BaseURL.LANGUAGE_ENG){
                   operatorName = response.body()[0].getGetTransportOperatorNameBn();
               }
                temp.setTransportInfoOperatorName(operatorName);
                temp.setRouteId(routeId);
                temp.setRouteName(response.body()[0].getRouteInfoName());
                temp.setTransportInfoId(infoId);
                temp.setTransportInfoPrice(response.body()[0].getTransportPrice() == "" ? "0" : response.body()[0].getTransportPrice());
                temp.setTransportInfoEstimatedTime(response.body()[0].getTransportEstimatedTime());
                temp.setTtId(response.body()[0].getTransportTypeid());
                temp.setTtName(response.body()[0].getTransportOperatorType());

                //Toast.makeText(context,temp.getTransportInfoOperatorName(),Toast.LENGTH_SHORT).show();
                transportProviders.add(temp);



                CustomTripPlanTransportAdapter customTripPlanTransportAdapter = new CustomTripPlanTransportAdapter(context,transportProviders);
                Operators.setAdapter(customTripPlanTransportAdapter);


            }

            @Override
            public void onFailure(Call<TransportInfo[]> call, Throwable t) {

            }
        });
    }
    private void loadTransportInfo (final int transpoerInfoId, int routeId)
    {
        //Toast.makeText(getActivity(),transpoerInfoId+" "+routeId,Toast.LENGTH_LONG).show();
        provideCallBack.getTransportInfo(transpoerInfoId,routeId).enqueue(new CustomCallBack<TransportInfo[]>(this)
        {

            @Override
            public void onResponse(Call<TransportInfo[]> call, Response<TransportInfo[]> response) {
                super.onResponse(call, response);
                if (response.body() == null || response.body().length == 0)
                {
                    return;
                }
                transportInfos= Arrays.asList(response.body());


                List <String> transportCategories = new ArrayList<>();
                for (int i = 0; i<transportInfos.size();i++)
                {
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        if (!transportCategories.contains(transportInfos.get(i).getGetTransportOperatorTypeBn()))
                        {

                            transportCategories.add(transportInfos.get(i).getGetTransportOperatorTypeBn());
                        }
                    }
                    else
                    {
                        if (!transportCategories.contains(transportInfos.get(i).getTransportOperatorType()))
                        {

                            transportCategories.add(transportInfos.get(i).getTransportOperatorType());
                        }
                    }



                }

                ArrayAdapter<String> typeAdapter=new ArrayAdapter<String>(context,R.layout.layout_spinner,R.id.spinnerText,transportCategories);
                if (transportCategories.size()== 0)
                {
                    String[] ctgry = new String[1];
                    if (!BaseURL.LANGUAGE_ENG) ctgry[0] = "পাওয়া যায় নি";
                    else ctgry[0] = "N/A";
                    typeAdapter=new ArrayAdapter<String>(context,R.layout.layout_spinner,R.id.spinnerText,ctgry);

                }
                transportCategory.setAdapter(typeAdapter);

            }

            @Override
            public void onFailure(Call<TransportInfo[]> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        switch (spinner.getId()){
            case R.id.transportType:
                TransportType transportType = (TransportType) transporType.getSelectedItem();
                //transportProviders.clear();
                loadTransportOperators (transportType.getTransportTypeId(),routeId);
                BaseURL.triggired=true;

                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
