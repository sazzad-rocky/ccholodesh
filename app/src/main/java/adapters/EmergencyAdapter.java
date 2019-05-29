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

import helpers.BaseURL;
import model.DestinationEmergency;

/**
 * Created by rhythmshahriar on 7/19/17.
 */

public class EmergencyAdapter  extends RecyclerView.Adapter<EmergencyViewHolder> {

    private Context mContext;
    private DestinationEmergency[] destinationEmergencies;

    public EmergencyAdapter(Context mContext, DestinationEmergency[] destinationEmergencies) {
        this.mContext = mContext;
        this.destinationEmergencies = destinationEmergencies;
    }

//

    @Override
    public EmergencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_emergency,parent,false);
        EmergencyViewHolder viewHolder=new EmergencyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(EmergencyViewHolder holder, int position) {

        Object object=destinationEmergencies[position];
        if(object instanceof DestinationEmergency){

            DestinationEmergency destinationEmergency= (DestinationEmergency) object;
            if (BaseURL.LANGUAGE_ENG)
            {
                holder.type.setText(destinationEmergency.getEmergencyTypeName());
                holder.organization.setText(destinationEmergency.getDestinationEmergencyOrgName());
                holder.address.setText(destinationEmergency.getDestinationEmergencyAddress());
                holder.contactInfo.setText(destinationEmergency.getDestinationEmergencyContactInfo());
                holder.hotline.setText(destinationEmergency.getDestinationEmergencyHotline());
            }
            else
            {
                holder.type.setText(destinationEmergency.getEmergencyTypeNameBn());
                holder.organization.setText(destinationEmergency.getDestinationEmergencyOrgNameBn());
                holder.address.setText(destinationEmergency.getDestinationEmergencyAddressBn());
                holder.contactInfo.setText(destinationEmergency.getDestinationEmergencyContactInfo());
                holder.hotline.setText(destinationEmergency.getDestinationEmergencyHotline());
            }


        }

    }

    @Override
    public int getItemCount() {
        return destinationEmergencies.length ;
    }


}

class EmergencyViewHolder extends RecyclerView.ViewHolder{
    TextView type;
    TextView organization;
    TextView address;
    TextView contactInfo;
    TextView hotline;
    public EmergencyViewHolder (View itemView)
    {
        super(itemView);
        type= (TextView) itemView.findViewById(R.id.emergenyType);
        organization= (TextView) itemView.findViewById(R.id.organizationName);
        address= (TextView) itemView.findViewById(R.id.address);
        contactInfo= (TextView) itemView.findViewById(R.id.contactInfo);
        hotline= (TextView) itemView.findViewById(R.id.hotline);
    }



}
