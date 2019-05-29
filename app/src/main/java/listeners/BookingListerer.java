/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package listeners;
import java.util.ArrayList;


import model.HotelBooking;
import model.HotelRespons;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
/**
 * Created by Olivine on 6/14/2017.
 */
public interface BookingListerer {
    @POST("provider/accommodationBooking")
    Call<String> confirmBooking(@Body HotelBooking hotelBooking);
}
