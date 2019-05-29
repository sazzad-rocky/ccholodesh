/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package listeners;

import java.util.List;

import model.CustomTripPlanNewRouteGetModel;
import model.CustomTripPlanRoutesListModel;
import model.ForgotPassword;
import model.Route;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Olivine on 5/24/2017.
 */

public interface RouteListener {
    @GET("provider/routeWiseInterRoute/{location_id}/{destination_id}")
    Call<Route[]> routeWiseInterRoute(@Path("location_id") int locId,@Path("destination_id") int destinationId);

    @POST("provider/routeWiseDirectInterRoute")
    Call<List<CustomTripPlanNewRouteGetModel[]>> test (@Body CustomTripPlanRoutesListModel routes);
}
