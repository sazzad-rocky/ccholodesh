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

import model.PackageItinerary;

/**
 * Created by Olivine on 6/13/2017.
 */

public class PackageItineraryAdapter extends RecyclerView.Adapter<PackageItineraryViewholder> {
    private Context mContext;
    private PackageItinerary [] packageItineraries;

    public PackageItineraryAdapter(Context mContext, PackageItinerary[] packageItineraries) {
        this.mContext = mContext;
        this.packageItineraries = packageItineraries;
    }

    @Override
    public PackageItineraryViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.layout_package_itinerary_list_new,parent,false);
        PackageItineraryViewholder packageItineraryViewholder=new PackageItineraryViewholder(view);
        return packageItineraryViewholder;
    }

    @Override
    public void onBindViewHolder(PackageItineraryViewholder holder, int position) {
            PackageItinerary packageItinerary=packageItineraries[position];
        holder.destonationName.setText(packageItinerary.getDestinationName());
        holder.tripTime.setText(packageItinerary.getPackageStartTime()+"-"+packageItinerary.getPackageEndTime());
        holder.transport.setText(packageItinerary.getPackageItineraryTransport());
        holder.meal.setText(packageItinerary.getPackageItineraryMeal());
        holder.planDate.setText(packageItinerary.getPackageItineraryDate());
    }

    @Override
    public int getItemCount() {
        return packageItineraries.length;
    }
}
class PackageItineraryViewholder extends RecyclerView.ViewHolder{
    TextView destonationName;
    TextView transport;
    TextView tripTime;
    TextView meal;
    TextView planDate;
    TextView planDetails;
    public PackageItineraryViewholder(View itemView) {
        super(itemView);
        destonationName= (TextView) itemView.findViewById(R.id.destonationName);
        transport= (TextView) itemView.findViewById(R.id.transport);
        tripTime= (TextView) itemView.findViewById(R.id.tripTime);
        meal= (TextView) itemView.findViewById(R.id.meal);
        planDate= (TextView) itemView.findViewById(R.id.planDate);
    }
}
