/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package callbacks;

import android.app.Activity;

import java.util.List;

import helpers.RetrofitInitializer;
import listeners.ProviderListener;
import listeners.RouteListener;
import model.CustomTripPlanNewRouteGetModel;
import model.CustomTripPlanRoutesListModel;
import model.Route;
import model.TransportProvider;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Olivine on 5/24/2017.
 */

public class RouteCallback {
    private Retrofit retrofit;
    private RouteListener routeListener;

    public RouteCallback(Activity mContext) {
        this.retrofit = RetrofitInitializer.initNetwork(mContext);
        routeListener=retrofit.create(RouteListener.class);

    }
    public Call<Route[]> getRoutes(int locationId, int destinationId) {
        return routeListener.routeWiseInterRoute(locationId, destinationId);
    }

    public Call<List<CustomTripPlanNewRouteGetModel[]>> getRoutestest(CustomTripPlanRoutesListModel routes) {
        return routeListener.test(routes);
    }

}


