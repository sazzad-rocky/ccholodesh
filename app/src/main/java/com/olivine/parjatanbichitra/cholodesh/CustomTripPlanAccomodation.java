/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import adapters.CustomTripRouteAccommodationAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import helpers.CustomTripPlanDataHolder;

public class CustomTripPlanAccomodation extends AppCompatActivity {

    @BindView(R.id.accommodations)
    RecyclerView accommodations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation);
        ButterKnife.bind(this);

        CustomTripRouteAccommodationAdapter customTripRouteAccommodationAdapter = new CustomTripRouteAccommodationAdapter(this,CustomTripPlanDataHolder.routes);
        accommodations.setAdapter(customTripRouteAccommodationAdapter);

        for (int i = 0; i< CustomTripPlanDataHolder.routes.size();i++){

           // Log.e("Start", CustomTripPlanDataHolder.routes.get(i).getStartDistrictId()+"");
            Toast.makeText(this,CustomTripPlanDataHolder.routes.get(i).getEndDistrictName()+" "+CustomTripPlanDataHolder.routes.get(i).getEndDistrictId(),Toast.LENGTH_SHORT).show();
        }


    }

}
