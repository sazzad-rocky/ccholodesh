/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.olivine.parjatanbichitra.cholodesh.AccommodationProviderActivity;
import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.List;

import model.Route;

/**
 * Created by rhythmshahriar on 11/6/17.
 */

public class CustomTripRouteAccommodationAdapter extends RecyclerView.Adapter<GlobaltitleViewholder>{

    List<Route> routes;
    Context context;

    public CustomTripRouteAccommodationAdapter(Context context,List<Route> routes){
        this.context = context;
        this.routes = routes;
    }
    @Override
    public GlobaltitleViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_global_list_title,parent,false);
        GlobaltitleViewholder routeViewHolder=new GlobaltitleViewholder(view);
        return routeViewHolder;

    }

    @Override
    public void onBindViewHolder(GlobaltitleViewholder holder, int position) {
        final Route route = routes.get(position);
        holder.routeTitle.setText(route.getEndDistrictName());
        holder.addHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AccommodationProviderActivity.class);
                intent.putExtra("district_id",route.getEndDistrictId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return routes.size();
    }
}
