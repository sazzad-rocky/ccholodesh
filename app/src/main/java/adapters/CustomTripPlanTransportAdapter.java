/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.olivine.parjatanbichitra.cholodesh.CustomTripPlanTransportSelection;
import com.olivine.parjatanbichitra.cholodesh.NewTripRouteActivity;
import com.olivine.parjatanbichitra.cholodesh.R;
import com.olivine.parjatanbichitra.cholodesh.TailorMadeEditActivity;
import com.olivine.parjatanbichitra.cholodesh.TailorMadeEditTransport;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomTripPlanDataHolder;
import helpers.RouteCallBack;
import helpers.TailorMadeDataHolder;
import listeners.TransportCostListener;
import model.CustomTripPlanNewRouteGetModel;
import model.PreviewData;
import model.TransportProvider;
import userDefinder.TailorMade;

/**
 * Created by rhythmshahriar on 11/5/17.
 */

public class CustomTripPlanTransportAdapter extends RecyclerView.Adapter<NewTransportListViewHolder> {

    private Context context;
    public static List<TransportProvider> transportProviders;
    CustomTripPlanTransportAdapter ref;
    TransportCostListener listener;
    RouteCallBack customCallBack;
    public static int id;
    public CustomTripPlanTransportAdapter (Context context,RouteCallBack customCallBack, List<TransportProvider> transportProviders){
        this.customCallBack= customCallBack;
        ref = this;
        this.context = context;
        this.transportProviders = transportProviders;
        if (context instanceof TailorMadeEditTransport) {
            //Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
        }
    }
    public CustomTripPlanTransportAdapter (Context context, List<TransportProvider> transportProviders){

        ref = this;
        this.context = context;
        this.transportProviders = transportProviders;
        if (context instanceof TailorMadeEditTransport) {
            //Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
        }
    }
    public void setListener(TransportCostListener listener){
        this.listener = listener;
    }
    @Override
    public NewTransportListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_transport_list,parent,false);
        NewTransportListViewHolder transportListViewHolder =new NewTransportListViewHolder(view);
      //  BaseURL.transportname.clear();
        return transportListViewHolder;
    }

    @Override
    public void onBindViewHolder(NewTransportListViewHolder holder, int position) {
        PreviewData.item++;
        final TransportProvider transportProvider = transportProviders.get(position);
        String operatorName = transportProvider.getTransportInfoOperatorName();
        String cost = transportProvider.getTransportInfoPrice();
        String estimatedTime = "Estimated Time: ";
        holder.delete.setVisibility(View.GONE);
        String trnsEstimatedTime = transportProvider.getTransportInfoEstimatedTime();
        //Toast.makeText(context, " operatorName  "+operatorName, Toast.LENGTH_SHORT).show();
        String hrs = " hrs";
//       PreviewData.transportname=operatorName;
       // Toast.makeText(context, ""+operatorName, Toast.LENGTH_SHORT).show();
       // Toast.makeText(context, ""+transportProviders.size(), Toast.LENGTH_SHORT).show();
        PreviewData.transportcost=cost;
        if (!BaseURL.LANGUAGE_ENG){
            //operatorName = transportProvider.
            cost = BanglaNumberParser.getBangla(cost);
            estimatedTime = "আনুমানিক সময় : ";
            trnsEstimatedTime = BanglaNumberParser.getBangla(trnsEstimatedTime);
            hrs = "ঘন্টা";
        }
        holder.txtProviderName.setText(operatorName);
        holder.txtCost.setText(cost+ " ৳ ");
        holder.txtEstimatedHour.setText(estimatedTime + trnsEstimatedTime + hrs);
//        holder.txtEstimatedHour.setText("Route: "+transportProvider.getRouteName() + "\n"+
//                                        "Estimated Time: " + transportProvider.getTransportInfoEstimatedTime() + " hrs");
        if (context instanceof TailorMadeEditTransport || context instanceof NewTripRouteActivity){
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    BaseURL.transportdateList.clear();
                    transportProviders.clear();
                   transportProviders.remove(transportProvider);
                    if (context instanceof TailorMadeEditTransport)
                    {
                        TailorMadeDataHolder.removeTransportProvider(transportProvider);
                        //Toast.makeText(context,TailorMadeDataHolder.transportProviders.size()+"",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        CustomTripPlanDataHolder.removeTransportProvider(transportProvider);
                    }
                    CustomTripPlanDataHolder.transPortcost -= Integer.parseInt(transportProvider.getTransportInfoPrice())* CustomTripPlanDataHolder.noOfTourist;
                    listener.addTransportCost(transportProvider);
                    ref.notifyDataSetChanged();
              //      customCallBack.recreateRoute();
                    //9-3-19
                }
            });
        }
        else if (context instanceof CustomTripPlanTransportSelection){
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomTripPlanDataHolder.addTransportProvider(transportProvider);
                    if (BaseURL.isEdit){
                        TailorMadeDataHolder.transportProviders.add(transportProvider);
                    }
                    ((Activity)context).finish();
                    BaseURL.transportname.add(transportProvider.getTransportInfoOperatorName());
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return transportProviders.size();
    }
}
class NewTransportListViewHolder extends RecyclerView.ViewHolder{
    ImageView transportSignature;
    TextView txtProviderName;
    TextView txtEstimatedHour;
    TextView txtCost;
    TextView delete;
    LinearLayout linearLayout;

    public NewTransportListViewHolder(View itemView) {
        super(itemView);
        txtProviderName = (TextView)itemView.findViewById(R.id.txtProviderName);
        txtCost = (TextView) itemView.findViewById(R.id.txtCost);
        txtEstimatedHour = (TextView) itemView.findViewById(R.id.txtEstimatedHour);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.parent);
        delete = (TextView) itemView.findViewById(R.id.delete);

    }

    public void onClick(View paramView) {
        int i = getAdapterPosition();

    }
}