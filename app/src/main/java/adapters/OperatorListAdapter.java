/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import helpers.BaseURL;
import helpers.CustomTripPlanDataHolder;
import helpers.TailorMadeDataHolder;
import model.AccommodationRoom;
import model.TourOperatorModel;

/**
 * Created by rhythmshahriar on 21/1/18.
 */

public class OperatorListAdapter extends RecyclerView.Adapter<OperatorViewHolder>{

    ArrayList<TourOperatorModel> tourOperatorModels;
    Context context;

    public OperatorListAdapter (Context context,  ArrayList<TourOperatorModel> tourOperatorModels){
        this.context = context;
        this.tourOperatorModels = tourOperatorModels;

    }

    @Override
    public OperatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_operator_list,parent,false);
        OperatorViewHolder viewholder=new OperatorViewHolder(view);
        //Toast.makeText(mContext,"this",Toast.LENGTH_LONG).show();
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final OperatorViewHolder holder, int position) {
        final TourOperatorModel tourOperatorModel = tourOperatorModels.get(position);
        holder.operatorName.setText(tourOperatorModel.getProviderName());
        Picasso.with(context).load(BaseURL.TOUR_OPERATOR_IMAGE_BASE_URL+tourOperatorModel.getProviderImage()).into(holder.operatorImage);
        if (tourOperatorModel.isSelected)
        {
            holder.operatorListParent.setBackground(context.getResources().getDrawable(R.drawable.drawable_border_bottom_selected));
        }
        else
        {
            holder.operatorListParent.setBackground(context.getResources().getDrawable(R.drawable.drawable_border_bottom));
        }

        holder.operatorListParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSelecion(holder,tourOperatorModel);
            }
        });
    }
    private void toggleSelecion(OperatorViewHolder holder, TourOperatorModel tourOperatorModel){
        if(tourOperatorModel.isSelected){
            tourOperatorModel.isSelected = false;
            holder.operatorListParent.setBackground(context.getResources().getDrawable(R.drawable.drawable_border_bottom));
            TailorMadeDataHolder.removeTourOperator(tourOperatorModel);
        }else{
            tourOperatorModel.isSelected = true;
            holder.operatorListParent.setBackground(context.getResources().getDrawable(R.drawable.drawable_border_bottom_selected));
            TailorMadeDataHolder.addTourOperator(tourOperatorModel);
        }
    }

    @Override
    public int getItemCount() {
        return tourOperatorModels.size();
    }
}


class OperatorViewHolder extends RecyclerView.ViewHolder{
    TextView operatorName;
    ImageView operatorImage;
    Button select;
    LinearLayout operatorListParent;

    public OperatorViewHolder(View itemView)
    {
        super(itemView);
        operatorName = (TextView) itemView.findViewById(R.id.operatorName);
        select = (Button) itemView.findViewById(R.id.select);
        operatorImage = (ImageView) itemView.findViewById(R.id.operatorImage);
        operatorListParent = (LinearLayout) itemView.findViewById(R.id.operatorListParent);

    }


}
