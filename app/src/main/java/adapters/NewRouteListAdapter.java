/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.olivine.parjatanbichitra.cholodesh.CustomTripPlanTransportSelection;
import com.olivine.parjatanbichitra.cholodesh.LocalTourActivity;
import com.olivine.parjatanbichitra.cholodesh.NewTripRouteActivity;
import com.olivine.parjatanbichitra.cholodesh.R;
import com.olivine.parjatanbichitra.cholodesh.TailorMadeEditTransport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomTripPlanDataHolder;
import helpers.DynamicHeight;
import helpers.TailorMadeDataHolder;
import model.CustomTripPlanNewRouteGetModel;
import model.PreviewData;
import model.Route;
import model.TransportProvider;

/**
 * Created by rhythmshahriar on 11/5/17.
 */

public class NewRouteListAdapter extends RecyclerView.Adapter<RouteViewHolder> {

    private Activity mContext;
    private List<CustomTripPlanNewRouteGetModel> routes;
    public static int id = 0;


    public NewRouteListAdapter(Activity mContext, List<CustomTripPlanNewRouteGetModel> routes) {
        this.mContext = mContext;
        this.routes = routes;
    }

    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_route_list, parent, false);
        RouteViewHolder routeViewHolder = new RouteViewHolder(view);
        return routeViewHolder;
    }

    @Override
    public void onBindViewHolder(final RouteViewHolder holder, int position) {
        final CustomTripPlanNewRouteGetModel route = routes.get(position);
        // DynamicHeight.setHeight(mContext,holder.parent,4,4);
        if (mContext instanceof NewTripRouteActivity) {
            CustomTripPlanDataHolder.routes.get(position).setRouteId(route.getRouteId());
            CustomTripPlanDataHolder.routes.get(position).setRouteName(route.getRouteName());

        }

        //Toast.makeText(mContext,CustomTripPlanDataHolder.routes.size()+" "+route.getRouteId(),Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        String json = gson.toJson(CustomTripPlanDataHolder.routes);
        Log.e("Url", "Null");
        Log.e("JSON", json);
        holder.txtRouteName.setText(route.getRouteName());


//start
        //  Toast.makeText(mContext, ""+id, Toast.LENGTH_SHORT).show();
        //end

        if (mContext instanceof NewTripRouteActivity && CustomTripPlanDataHolder.doesExist(route.getRouteId())) {
            holder.changeTransportButton.setText("Choosed");
            holder.changeTransportButton.setBackgroundColor(Color.BLUE);
        }


        holder.changeTransportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.changeTransportButton.setText("Choosed");
                holder.changeTransportButton.setBackgroundColor(Color.BLUE);

                if (mContext instanceof TailorMadeEditTransport && TailorMadeDataHolder.doesHaaveTransportProvider(route.getRouteId())) {
                    String err = "Already have a transport provider";
                    if (!BaseURL.LANGUAGE_ENG) {
                        err = "ইতিমধ্যে একটি পরিবহন প্রদানকারী আছে";
                    }
                    Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
                    return;
                } else if (mContext instanceof NewTripRouteActivity && CustomTripPlanDataHolder.doesExist(route.getRouteId())) {
                    holder.changeTransportButton.setVisibility(View.GONE);
                    String err = "Already have a transport provider";
                    if (!BaseURL.LANGUAGE_ENG) {
                        err = "ইতিমধ্যে একটি পরিবহন প্রদানকারী আছে";
                    }
                    Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
                    //   holder.changeTransportButton.setVisibility(View.GONE);
//                    Toast.makeText(mContext, "route id"+ route.getRouteId(), Toast.LENGTH_SHORT).show();
//                    if (id==route.getRouteId())
                    return;
                } else if (mContext instanceof NewTripRouteActivity && !CustomTripPlanDataHolder.doesExist(route.getRouteId())) {
                    holder.changeTransportButton.setVisibility(View.VISIBLE);
                }
                Intent intent = new Intent(mContext, CustomTripPlanTransportSelection.class);
                intent.putExtra("routeId", route.getRouteId());
                mContext.startActivity(intent);
            }
        });
        holder.transportdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        view.setMinDate(System.currentTimeMillis() - 1000);
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        //updateLabel();
                        String myFormat = "dd-MM-yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                        int days = 0;
                        // Toast.makeText(getActivity(),sdf.format(new Date())+ " " + sdf.format(myCalendar.getTime()),Toast.LENGTH_SHORT).show();
                        if (sdf.format(new Date()).equals(sdf.format(myCalendar.getTime())) || new Date().before(myCalendar.getTime())) {
                            //Toast.makeText(getActivity(),"Outdated",Toast.LENGTH_SHORT).show();
                            //return;
                            holder.transportdate.setText(sdf.format(myCalendar.getTime()));
                           // PreviewData.stratDate = sdf.format(myCalendar.getTime());


                            BaseURL.transportdateList.add(sdf.format(myCalendar.getTime()));


                            String newDate = sdf.format(myCalendar.getTime());
                            // previewData.setdepar

                            if (!BaseURL.LANGUAGE_ENG) {
                                holder.transportdate.setText(BanglaNumberParser.getBangla( holder.transportdate.getText().toString()));
                            }
                        }
                        else {
                            String msg = "Please Select a Valid Date";
                            if (!BaseURL.LANGUAGE_ENG) msg = "একটি বৈধ তারিখ নির্বাচন করুন";
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                };

                new DatePickerDialog(mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }
}
