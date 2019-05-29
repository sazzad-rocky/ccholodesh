/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;

import model.HotelBookingToken;
import model.PackageBookingToken;

/**
 * Created by Olivine on 6/14/2017.
 */

public class BookingListAdapter extends RecyclerView.Adapter<BookinglistViewholder> {
    private Context mContext;
    private Object [] bookings;


    public BookingListAdapter(Context mContext, Object[] bookings) {
        this.mContext = mContext;
        this.bookings = bookings;
    }

    @Override
    public BookinglistViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_booking_list,parent,false);
        BookinglistViewholder bookinglistViewholder=new BookinglistViewholder(view);

        return bookinglistViewholder;
    }

    @Override
    public void onBindViewHolder(BookinglistViewholder holder, int position) {

            Object booking=bookings[position];

            if(booking instanceof HotelBookingToken) {
                HotelBookingToken hotelBooking = (HotelBookingToken) booking;
                holder.hotelName.setText(hotelBooking.getProviderName());
                holder.bookingToken.setText(hotelBooking.getAccommodationBookingToken());
            }
            if(booking instanceof PackageBookingToken){
                PackageBookingToken packageReservation= (PackageBookingToken) booking;
                holder.packageName.setText("By "+packageReservation.getProviderName());
                holder.hotelName.setText(packageReservation.getPackageName());
                holder.bookingToken.setText(packageReservation.getPackageBookingToken());

            }

    }

    @Override
    public int getItemCount() {
        return bookings.length;
    }
}
class BookinglistViewholder extends RecyclerView.ViewHolder{
    public BookinglistViewholder(View itemView) {
        super(itemView);
        hotelName= (TextView) itemView.findViewById(R.id.hotelName);
        bookingToken= (TextView) itemView.findViewById(R.id.bookingToken);
        packageName= (TextView) itemView.findViewById(R.id.packageName);
    }
    TextView hotelName;
    TextView bookingToken;
    TextView packageName;

}
