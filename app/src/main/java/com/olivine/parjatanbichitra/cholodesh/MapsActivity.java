/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Objects;

import helpers.GMapV2Direction;
import helpers.GetDirectionsData;
import helpers.TailorMadeDataHolder;
import model.Itenerary;
import model.LocalTour;
import userDefinder.TailorMade;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude, longitude;
    double end_latitude, end_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        latitude = 23.8288;
        longitude = 90.4185;
        end_latitude = 23.7940;
        end_longitude = 90.4043;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<MarkerOptions> markers = new ArrayList<>();
        ArrayList<LatLng> arrayLat = new ArrayList<>();

        for (Itenerary itenerary : TailorMadeDataHolder.iteneraries){

            final LatLng temp = new LatLng(itenerary.lattitude,itenerary.longitude);
            arrayLat.add(temp);
            Log.e("lnogitude:", itenerary.longitude+"");
            MarkerOptions tempMArker = new MarkerOptions().position(temp).title(itenerary.getPlaceName());
            mMap.addMarker(tempMArker);
            markers.add(tempMArker);
            mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
                }
            });

        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (MarkerOptions marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();

        int padding = 0; // offset from edges of the map in pixels
        final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.moveCamera(cu);
            }
        });

       // mMap.moveCamera(cu);

//
//        Polyline line = mMap.addPolyline(new PolylineOptions()
//                .add(
//                       arrayLat.toArray(new LatLng[arrayLat.size()])
//                    )
//                .width(5)
//                .color(Color.RED));

        try
        {
            latitude = TailorMadeDataHolder.iteneraries.get(0).lattitude;
            longitude = TailorMadeDataHolder.iteneraries.get(0).longitude;

            end_latitude = TailorMadeDataHolder.iteneraries.get(1).lattitude;
            end_longitude = TailorMadeDataHolder.iteneraries.get(1).longitude;
        }catch (Exception e)
        {
            latitude = 23.8288;
            longitude = 90.4185;
            end_latitude = 23.7940;
            end_longitude = 90.4043;
        }

        showRoute();

    }

    private void showRoute(){

        Object dataTransfer[] = new Object[3];
        String url = getDirectionsUrl();
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;
        dataTransfer[2] = new LatLng(end_latitude,end_longitude);
        GetDirectionsData getDirectionsData = new GetDirectionsData();
        getDirectionsData.execute(dataTransfer);
        //Toast.makeText(getApplicationContext(),"Called",Toast.LENGTH_SHORT).show();
    }

    private String getDirectionsUrl()
    {

        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googleDirectionsUrl.append("origin="+latitude+","+longitude);
        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
        googleDirectionsUrl.append("&key="+"AIzaSyCAcfy-02UHSu2F6WeQ1rhQhkCr51eBL9g");

        return googleDirectionsUrl.toString();
    }


}
