/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;

import model.TransportProvider;

/**
 * Created by Olivine on 5/13/2017.
 */

public class TransportListAdapter extends ArrayAdapter<TransportProvider> {
    Context mContext;
    TransportProvider [] transportProviders;
    private int [] drawables=new int[]{R.drawable.icon_bus_white,R.drawable.icon_airoplane,R.drawable.icon_train,R.drawable.icon_ship};

    public TransportListAdapter(@NonNull Context mContext,@NonNull TransportProvider[] transportProviders) {
        super(mContext, R.layout.layout_transport_list, transportProviders);
        this.mContext = mContext;
        this.transportProviders = transportProviders;
    }

    @Override
    public int getCount() {
       return transportProviders.length;
    }

    @Nullable
    @Override
    public TransportProvider getItem(int position) {
        return transportProviders[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TransportProvider transportProvider=transportProviders[position];
        TransportListViewHolder viewHolder=null;
        if(convertView==null){
            View view=LayoutInflater.from(mContext).inflate(R.layout.layout_transport_list,parent,false);
            convertView=view;
            viewHolder=new TransportListViewHolder();
            viewHolder.transportSignature= (ImageView) convertView.findViewById(R.id.transportSignature);
            viewHolder.txtProviderName= (TextView) convertView.findViewById(R.id.txtProviderName);
            viewHolder.txtCost= (TextView) convertView.findViewById(R.id.txtCost);
            viewHolder.txtEstimatedHour= (TextView) convertView.findViewById(R.id.txtEstimatedHour);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (TransportListViewHolder) convertView.getTag();
        }
        String providerName=transportProvider.getTransportInfoOperatorName();
//        if (providerName == null || providerName == "")
//        {
//            Toast.makeText(mContext,"No Transport Available at this moment",Toast.LENGTH_LONG).show();
//            return convertView;
//        }
        if (providerName == null || providerName == "")
        {
            viewHolder.txtProviderName.setText("NA");
            viewHolder.txtEstimatedHour.setText("NA");
            viewHolder.txtCost.setText("0");
            viewHolder.transportSignature.setImageResource(R.drawable.icon_dummy_transport);
            return convertView;
        }

        else
        {
            viewHolder.txtProviderName.setText(providerName);
            viewHolder.txtEstimatedHour.setText(transportProvider.getTransportInfoEstimatedTime()+" Hour Journey");
            viewHolder.txtCost.setText(transportProvider.getTransportInfoPrice()+"৳");
            viewHolder.transportSignature.setImageResource(R.drawable.icon_dummy_transport);
            String tranpostType=transportProvider.getTtName();
            if(tranpostType==null || tranpostType.length()<1){
                tranpostType="NA";
            }
            switch (tranpostType.substring(0,1)){
                case "B" :
                    viewHolder.transportSignature.setImageResource(drawables[0]);
                    break;
                case "A":
                    viewHolder.transportSignature.setImageResource(drawables[1]);
                    break;
                case "T":
                    viewHolder.transportSignature.setImageResource(drawables[2]);
                    break;
                case "C":
                    viewHolder.transportSignature.setImageResource(drawables[3]);
            }

            return convertView;
        }

    }
}
class TransportListViewHolder{
    ImageView transportSignature;
    TextView txtProviderName;
    TextView txtEstimatedHour;
    TextView txtCost;

}