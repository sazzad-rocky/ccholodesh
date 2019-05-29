/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package callbacks;

import android.app.Activity;

import helpers.RetrofitInitializer;
import listeners.PlaceListener;
import listeners.ProviderListener;
import model.Destination;
import model.Place;
import model.TransportProvider;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Olivine on 5/16/2017.
 */

public class PlaceCallback {
    private Retrofit retrofit;
    private PlaceListener placeListener;

    private TransportProvider[] transportProviders;

    public PlaceCallback(Activity mContext) {
        this.retrofit = RetrofitInitializer.initNetwork(mContext);
        placeListener=retrofit.create(PlaceListener.class);

    }
    public Call<Place[]> getLocations(){
        return  placeListener.getAllPlaces();
    }
    public Call<Destination[]> getDestinations(int districtId){
        return  placeListener.getDestinations(districtId);
    }
}
