/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import helpers.BaseURL;
import model.HotelInclusion;
import model.PackageInclusion;

/**
 * Created by Olivine on 6/11/2017.
 */

public class InclusionAdapter extends RecyclerView.Adapter<InclusionViewHolder> {
    private Context mContext;
    private Object[] inclusions;
    private PackageInclusion[] inclusionsOfPackage;

    public InclusionAdapter(Context mContext, Object[] inclusions) {
        this.mContext = mContext;
        this.inclusions = inclusions;
    }

    @Override
    public InclusionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_inclusion_list,parent,false);
        InclusionViewHolder inclusionViewHolder=new InclusionViewHolder(view);
        return inclusionViewHolder;
    }

    @Override
    public void onBindViewHolder(InclusionViewHolder holder, int position) {
        Object object=inclusions[position];
        if(object instanceof PackageInclusion){
            PackageInclusion packageInclusion= (PackageInclusion) object;
            holder.inclusionImage.setVisibility(View.VISIBLE);
            holder.incParetn.setPadding(5,5,5,5);
            holder.inclusionName.setText(packageInclusion.getPackageInclusionName());
            Picasso.with(mContext).load(BaseURL.PACKAGE_INCLUSION_BASE_URL+packageInclusion.getPackageInclusionImage())
                    .resize(64,64).into(holder.inclusionImage);
        }
        if(object instanceof  HotelInclusion){

            HotelInclusion hotelInclusion= (HotelInclusion) object;
            String faciliytName = hotelInclusion.getHotelFacilityName();
            if (!BaseURL.LANGUAGE_ENG){

                faciliytName = hotelInclusion.hotelFacilityNameBn;
            }
        holder.inclusionName.setText(faciliytName);
            Picasso.with(mContext).load(R.drawable.icon_plus_white)
                    .resize(64,64).into(holder.inclusionImage);
        holder.incParetn.setBackgroundColor(Color.WHITE);
            holder.incParetn.setPadding(0,0,0,0);

        }
    }

    @Override
    public int getItemCount() {
        return inclusions.length ;
    }
}
class InclusionViewHolder extends RecyclerView.ViewHolder{
    TextView inclusionName;
    ImageView inclusionImage;
    LinearLayout incParetn;
    public InclusionViewHolder(View itemView) {
        super(itemView);
        inclusionName= (TextView) itemView.findViewById(R.id.inclusionName);
        inclusionImage= (ImageView) itemView.findViewById(R.id.inclusionImage);
        incParetn = (LinearLayout) itemView.findViewById(R.id.incParetn);
    }
}