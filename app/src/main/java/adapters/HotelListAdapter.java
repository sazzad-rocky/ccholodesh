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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.HotelDetailsActivity;
import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import model.AccommodationProvider;

/**
 * Created by Olivine on 6/11/2017.
 */

public class HotelListAdapter extends RecyclerView.Adapter<HotelViewholder> {
    public static final String SERVICE_ID="service_id";
    private Context mContext;
    private AccommodationProvider [] accommodationProviders;

    public HotelListAdapter(Context mContext, AccommodationProvider[] accommodationProviders) {
        this.mContext = mContext;
        this.accommodationProviders = accommodationProviders;
        BaseURL.hotelServiceIds.clear();
    }

    @Override
    public HotelViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_accomodation_list_new,parent,false);
        HotelViewholder viewholder=new HotelViewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(HotelViewholder holder, int position) {
        final AccommodationProvider accommodationProvider=accommodationProviders[position];

        String providerName = accommodationProvider.getProviderName();
        String typeName = accommodationProvider.getAccommodationTypeName();
        String of = " of ";
        String hotelsIn = " Hotels in ";
        String destinationName = accommodationProvider.getDestinationName();
        String nofHotels = "#" + (position+1) + of+ getItemCount()+hotelsIn+ destinationName;
        String priceRange = "Price Range: "+accommodationProvider.minPrice +" ৳ - "+accommodationProvider.maxPrice+" ৳";

        if (!BaseURL.LANGUAGE_ENG){

            providerName = accommodationProvider.providerNameBn;
            typeName = accommodationProvider.typeBn;
            destinationName = accommodationProvider.districtNameBn;
            of = " এর ";
            hotelsIn = " মধ্যে হোটেল ";
            nofHotels =  BanglaNumberParser.getBangla(getItemCount()+"")+" টি  "+ "হোটেলের মধ্যে #" + BanglaNumberParser.getBangla((position+1)+"") ;
            priceRange = "মূল্য পরিসীমা: "+BanglaNumberParser.getBangla(accommodationProvider.minPrice +" ৳ - "+accommodationProvider.maxPrice+" ৳");
            //of =
        }

        BaseURL.hotelServiceIds.add(accommodationProvider.getAccommodationServiceId());
        holder.hotelNameTextView.setText(providerName);
        String url= BaseURL.IMAGE_BASE_URL+"provider_image/"+accommodationProvider.getProviderImage();
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(holder.hotelImage);
        if(!BaseURL.LANGUAGE_ENG)
        {
            holder.viewDetails.setText("বিস্তারিত");
        }


        holder.hotelAddress.setText(typeName);
        holder.hotelCount.setText(nofHotels);
        holder.priceRange.setText(priceRange);

//        try {
//           String rating = accommodationProvider.getAccommodationTypeName();
//            rating = rating.substring(0,1);
//            //rating = BanglaNumberParser.getEnglish(rating);
//
//            holder.hotelRating.setRating(Integer.parseInt(rating));
//        }catch (Exception ex)
//        {
//
//        }
        try
        {
            holder.hotelRating.setRating(Float.parseFloat(accommodationProvider.reviewRatingAverage));
        }
        catch (Exception ex){
            holder.hotelRating.setRating(0);

        }

        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailsIntent=new Intent(mContext, HotelDetailsActivity.class);
                detailsIntent.putExtra(SERVICE_ID,accommodationProvider.getAccommodationServiceId());
                detailsIntent.putExtra("NAME",accommodationProvider.getProviderName());
                //Toast.makeText(mContext,accommodationProvider.getProviderName(),Toast.LENGTH_SHORT).show();
                detailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(detailsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accommodationProviders.length;
    }
}
class HotelViewholder extends RecyclerView.ViewHolder{
    //CardView parentView;
    TextView hotelNameTextView;
    //TextView accommodationCategory;
    TextView hotelAddress;
    TextView hotelCount;
    ImageView hotelImage;
    RatingBar hotelRating;
    Button viewDetails;
    TextView hotline;
    TextView priceRange;

    public HotelViewholder(View itemView) {
        super(itemView);
        //parentView = (CardView) itemView.findViewById(R.id.parentView);
        hotelNameTextView = (TextView) itemView.findViewById(R.id.hotelNameTextView);
       // accommodationCategory = (TextView) itemView.findViewById(R.id.accommodationCategory);
        hotelAddress = (TextView) itemView.findViewById(R.id.address);
        hotelCount = (TextView) itemView.findViewById(R.id.hotelCount);
        hotelImage = (ImageView) itemView.findViewById(R.id.hotelImage);
        hotelRating = (RatingBar) itemView.findViewById(R.id.hotelRating);
        viewDetails = (Button) itemView.findViewById(R.id.viewDetails);
        hotline = (TextView) itemView.findViewById(R.id.hotline);
        priceRange = (TextView) itemView.findViewById(R.id.priceRange);
    }
}
