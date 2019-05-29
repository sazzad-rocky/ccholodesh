/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package callbacks;

import android.app.Activity;
import android.content.Context;

import helpers.RetrofitInitializer;
import listeners.BookingListerer;

import model.HotelBooking;
import model.HotelRespons;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Olivine on 6/14/2017.
 */

public class BookingCallback {
    private Retrofit retrofit;
    private BookingListerer bookingListerer;

    public BookingCallback(Activity mContext) {
        retrofit= RetrofitInitializer.initNetwork(mContext);
        bookingListerer=retrofit.create(BookingListerer.class);
    }

    public Call<String> confirmBooking(HotelBooking rooms){
        return bookingListerer.confirmBooking(rooms);
    }




}
