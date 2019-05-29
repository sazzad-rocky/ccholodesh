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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import listeners.AccommodationListener;
import model.AccommodationProvider;

/**
 * Created by Olivine on 5/27/2017.
 */
public class AccommodationListAdapter extends RecyclerView.Adapter<AccommodationListViewholder> {
    private Context mContext;
    private List<AccommodationProvider> accommodationProviders;
    private AccommodationListener accommodationListener;
    public AccommodationListAdapter setAccommodationListener(AccommodationListener accommodationListener) {
        this.accommodationListener = accommodationListener;
        return this;
    }

    public AccommodationListAdapter(Context mContext, List<AccommodationProvider> accommodationProviders) {
        this.mContext = mContext;
        this.accommodationProviders = accommodationProviders;
    }
    @Override
    public AccommodationListViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(mContext).inflate(R.layout.layout_accomodation_list_new,parent,false);
        AccommodationListViewholder accommodationViewholder=new AccommodationListViewholder(itemView);
        return accommodationViewholder;
    }
    @Override
    public void onBindViewHolder(AccommodationListViewholder holder, final int position) {
        final AccommodationProvider accommodationProvider=accommodationProviders.get(position);
        String providerName = accommodationProvider.getProviderName();
        String typeName = accommodationProvider.getAccommodationTypeName();
        String noOfCount = "#" + (position+1) + " of " + getItemCount()+" Hotels in "+ accommodationProvider.getDestinationName();
        String priceRange = "Price Range: "+accommodationProvider.minPrice +" ৳ - "+accommodationProvider.maxPrice+" ৳";
        String url= BaseURL.IMAGE_BASE_URL+"provider_image/"+accommodationProvider.getProviderImage();
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(holder.hotelImage);


        if (!BaseURL.LANGUAGE_ENG){
            providerName = accommodationProvider.providerNameBn;
            typeName = accommodationProvider.typeBn;
            noOfCount = BanglaNumberParser.getBangla(getItemCount()+"")+" টি  "+ "হোটেলের মধ্যে #" + BanglaNumberParser.getBangla((position+1)+"") ;
            priceRange = "মূল্য পরিসীমা: "+BanglaNumberParser.getBangla(accommodationProvider.minPrice +" ৳ - "+accommodationProvider.maxPrice+" ৳");
        }
        holder.hotelNameTextView.setText(providerName);
        holder.hotelAddress.setText(typeName);
        holder.hotelCount.setText(noOfCount);
        holder.priceRange.setText(priceRange);

        try
        {
            holder.hotelRating.setRating(Float.parseFloat(accommodationProvider.reviewRatingAverage));
        }
        catch (Exception ex){
            holder.hotelRating.setRating(0);

        }
//        try {
//            String rating = accommodationProvider.getAccommodationTypeName();
//            rating = rating.substring(0,1);
//            accommodationProvider.ctStart = Integer.parseInt(rating);
//
//            holder.hotelRating.setRating(Integer.parseInt(rating));
//        }catch (Exception ex)
//        {
//            holder.hotelRating.setRating(0);
//        }
        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext,position+"",Toast.LENGTH_SHORT).show();
                accommodationListener.accommodationActivityResult(accommodationProvider.getAccommodationServiceId());

                BaseURL.HOTEL_NAME = accommodationProvider.getProviderName();
                if (!BaseURL.LANGUAGE_ENG){
                    BaseURL.HOTEL_NAME = accommodationProvider.providerNameBn;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return accommodationProviders.size();
    }
}
class AccommodationListViewholder extends RecyclerView.ViewHolder{
    TextView hotelNameTextView;
    //TextView accommodationCategory;
    TextView hotelAddress;
    TextView hotelCount;
    ImageView hotelImage;
    RatingBar hotelRating;
    Button viewDetails;
    TextView hotline;
    TextView priceRange;
   // RatingBar hotelRating;

    public AccommodationListViewholder(View itemView) {
        super(itemView);
        hotelNameTextView = (TextView) itemView.findViewById(R.id.hotelNameTextView);
        hotelAddress = (TextView) itemView.findViewById(R.id.address);
        hotelCount = (TextView) itemView.findViewById(R.id.hotelCount);
        hotelImage = (ImageView) itemView.findViewById(R.id.hotelImage);
        hotelRating = (RatingBar) itemView.findViewById(R.id.hotelRating);
        viewDetails = (Button) itemView.findViewById(R.id.viewDetails);
        hotline = (TextView) itemView.findViewById(R.id.hotline);
        priceRange = (TextView) itemView.findViewById(R.id.priceRange);
        if (!BaseURL.LANGUAGE_ENG)
        {
            viewDetails.setText("বিস্তারিত");
        }
    }

}
