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
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.List;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import model.TailorMadeTransport;
/**
 * Created by rhythmshahriar on 9/13/17.
 */
public class TailorMadeTransportAdapter extends RecyclerView.Adapter<TransportViewHolder> {
    private Context mContext;
    private List<TailorMadeTransport> tailorMadeTransports;

    public TailorMadeTransportAdapter(Context mContext, List<TailorMadeTransport> tailorMadeTransports) {
        this.mContext = mContext;
        this.tailorMadeTransports = tailorMadeTransports;
    }

    @Override
    public TransportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_tailor_made_transport, parent, false);
        TransportViewHolder transportViewHolder = new TransportViewHolder(itemView);
        return transportViewHolder;
    }

    @Override
    public void onBindViewHolder(TransportViewHolder holder, int position) {
        TailorMadeTransport tailorMadeTransport = tailorMadeTransports.get(position);
        String cost = "Cost: ";
        String costPrice = tailorMadeTransport.getTailormadeRouteTransportInfoPrice();
        if (!BaseURL.LANGUAGE_ENG) {
            cost = "মূল্য: ";
            costPrice = BanglaNumberParser.getBangla(costPrice);
        }
        holder.providerName.setText("Operator: " + tailorMadeTransport.getTailormadeRouteTransportInfoOperatorName());
        holder.price.setText(cost + costPrice + " ৳");
        holder.route.setText(tailorMadeTransport.getRouteName());
        holder.transportdate.setText(tailorMadeTransports.get(position).gettransport_date());
        Toast.makeText(mContext, "" + tailorMadeTransports.get(position).gettransport_date(), Toast.LENGTH_SHORT).show();
        holder.time.setText(tailorMadeTransport.getTailormadeRouteTransportInfoEstimatedTime() + " hrs");
        BaseURL.transportname.add(tailorMadeTransport.getTailormadeRouteTransportInfoOperatorName());
        BaseURL.districtid = tailorMadeTransport.getEndDistrictId();
    }

    @Override
    public int getItemCount() {
        return tailorMadeTransports.size();
    }
}

class TransportViewHolder extends RecyclerView.ViewHolder {
    TextView route, providerName, time, price, transportdate;
    public TransportViewHolder(View itemView) {
        super(itemView);
        route = (TextView) itemView.findViewById(R.id.route);
        providerName = (TextView) itemView.findViewById(R.id.providerName);
        time = (TextView) itemView.findViewById(R.id.time);
        price = (TextView) itemView.findViewById(R.id.price);
        transportdate = (TextView) itemView.findViewById(R.id.transportdate);
    }
}