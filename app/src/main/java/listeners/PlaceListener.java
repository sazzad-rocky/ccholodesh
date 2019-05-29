/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package listeners;
import model.Destination;
import model.Place;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Olivine on 5/16/2017.
 */

public interface PlaceListener {
    @GET("allDestination/list")
    Call<Place[]> getAllPlaces();
    @GET("district/destination/{district_id}")
    Call<Destination[]> getDestinations(@Path("district_id") int districtId);
}
