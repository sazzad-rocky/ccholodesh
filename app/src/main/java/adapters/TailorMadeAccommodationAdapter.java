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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import model.HotelDate;
import model.ItineryTailormade;
import model.TailorMadeAccommodation;
import model.TailorMadeItinerary;
/**
 * Created by rhythmshahriar on 9/13/17.
 */
public class TailorMadeAccommodationAdapter extends RecyclerView.Adapter<TailorMadeAccommodationViewHolder> {

    private Context mContext;
    private Object[] objects;

    // private PackageInclusion[] inclusionsOfPackage;
    public TailorMadeAccommodationAdapter(Context mContext, Object[] objects) {
        this.mContext = mContext;
        this.objects = objects;
    }

    @Override
    public TailorMadeAccommodationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (objects[0] instanceof TailorMadeAccommodation)
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_tailor_made_accommodation, parent, false);
        else if (objects[0] instanceof TailorMadeItinerary)
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_itenerary_new, parent, false);
        else view = null;
        TailorMadeAccommodationViewHolder tailorMadeAccommodationViewHolder = new TailorMadeAccommodationViewHolder(view);
        return tailorMadeAccommodationViewHolder;
    }

    @Override
    public void onBindViewHolder(TailorMadeAccommodationViewHolder holder, int position) {
        Object object = objects[position];
        if (object instanceof TailorMadeAccommodation) {
            TailorMadeAccommodation tailorMadeAccommodation = (TailorMadeAccommodation) object;
            String url = BaseURL.HOTEL_ROOM_IMAGE_BASE_URL + tailorMadeAccommodation.getImage();
            Picasso.with(mContext)
                    .load(url)
                    .placeholder(R.drawable.image_placeholder)
                    .fit()
                    .into(holder.image);
            holder.provider.setText(tailorMadeAccommodation.getTailormadeAccommodationAccommodationName());
            holder.occupancy.setText("Max Occupancy: " + tailorMadeAccommodation.getTailormadeAccommodationAccommodationRoomMaxOccupancy());
            holder.category.setText(tailorMadeAccommodation.getTailormadeAccommodationAccommodationCategoryName());

            holder.in.setText("In :" + tailorMadeAccommodation.getTailormadeAccommodationCheckindate());
            holder.out.setText("Out :" + tailorMadeAccommodation.getTailormadeAccommodationCheckOutdate());
            if (tailorMadeAccommodation.getTailormadeAccommodationAccommodationName().length() > 2) {
                HotelDate date = new HotelDate();
                date.setHotelName(tailorMadeAccommodation.getTailormadeAccommodationAccommodationName());
                date.setCheckindate(tailorMadeAccommodation.getTailormadeAccommodationCheckindate());
                date.setCheckoutDate(tailorMadeAccommodation.getTailormadeAccommodationCheckOutdate());

                BaseURL.dates.add(date);

            }
            // Toast.makeText(mContext, "hotel "+tailorMadeAccommodation.getTailormadeAccommodationCheckindate(), Toast.LENGTH_SHORT).show();


            holder.count.setText(tailorMadeAccommodation.getTailormadeAccommodationDistrictName());
            String cost = "Cost: " + tailorMadeAccommodation.getTailormadeAccommodationAccommodationRoomPrice() + " ৳";
            if (!BaseURL.LANGUAGE_ENG) {
                cost = "মূল্য: " + BanglaNumberParser.getBangla(tailorMadeAccommodation.getTailormadeAccommodationAccommodationRoomPrice() + " ৳");
            }
            holder.price.setText(cost);

        }
        if (object instanceof TailorMadeItinerary) {
            TailorMadeItinerary tailorMadeItinerary = (TailorMadeItinerary) object;
            holder.provider.setText(tailorMadeItinerary.getItineraryPlaceName());
            //BaseURL.itinaryItemtailormade
            ItineryTailormade tailormade = new ItineryTailormade();
            tailormade.setProvidername(tailorMadeItinerary.getItineraryPlaceName());
            tailormade.setDate(tailorMadeItinerary.getItineraryDayplan());
            BaseURL.itinaryItemtailormade.add(tailormade);

            //holder.occupancy.setVisibility(View.GONE);
            holder.category.setText(tailorMadeItinerary.getItineraryDayplan().split("-")[0]);
            holder.month.setText(getMonth(tailorMadeItinerary.getItineraryDayplan().split("-")[1]));
            if (tailorMadeItinerary.getItineraryTime().equals("AM"))
                holder.count.setText("Morning");
            holder.count.setText(tailorMadeItinerary.localTourDuration);
            DestinationTagAdapter adapter = new DestinationTagAdapter(mContext, tailorMadeItinerary.localTourTags.split(","));
            holder.tags.setAdapter(adapter);
//            else if (tailorMadeItinerary.getItineraryTime().equals("PM"))
//            {
//                holder.count.setText("Plan of Noon");
//            }
//            else
//            {
//                holder.count.setText("Plan of Whole Day");
//            }
            holder.price.setText("Cost: " + tailorMadeItinerary.getItineraryPerPersonCost() + " ৳");
        }

    }

    String getMonth(String month) {
        String mon = month;
        switch (month) {
            case "01":
                mon = "JAN";
                break;
            case "02":
                mon = "FEB";
                break;
            case "03":
                mon = "MAR";
                break;
            case "04":
                mon = "APR";
                break;
            case "05":
                mon = "MAY";
                break;
            case "06":
                mon = "JUN";
                break;
            case "07":
                mon = "JUL";
                break;
            case "08":
                mon = "AUG";
                break;
            case "09":
                mon = "SEP";
                break;
            case "10":
                mon = "OCT";
                break;
            case "11":
                mon = "NOV";
                break;
            case "12":
                mon = "DEC";
                break;
        }
        return mon;
    }

    @Override
    public int getItemCount() {
        return objects.length;
    }
}

class TailorMadeAccommodationViewHolder extends RecyclerView.ViewHolder {

    TextView provider, occupancy, category, count, price, month, in, out;
    ImageView image;
    RecyclerView tags;


    public TailorMadeAccommodationViewHolder(View itemView) {
        super(itemView);
        in = (TextView) itemView.findViewById(R.id.in);
        out = (TextView) itemView.findViewById(R.id.out);
        provider = (TextView) itemView.findViewById(R.id.provider);
        occupancy = (TextView) itemView.findViewById(R.id.occupancy);
        category = (TextView) itemView.findViewById(R.id.category);
        count = (TextView) itemView.findViewById(R.id.count);
        price = (TextView) itemView.findViewById(R.id.price);
        image = (ImageView) itemView.findViewById(R.id.foodimage);
        tags = (RecyclerView) itemView.findViewById(R.id.tags);
        month = (TextView) itemView.findViewById(R.id.month);

    }

}
