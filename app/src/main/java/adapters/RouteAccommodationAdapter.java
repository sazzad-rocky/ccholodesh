/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.daimajia.swipe.SwipeLayout;
import com.google.gson.Gson;
import com.olivine.parjatanbichitra.cholodesh.AccommodationActivity;
import com.olivine.parjatanbichitra.cholodesh.AccommodationProviderActivity;
import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomTripPlanDataHolder;
import helpers.TailorMadeDataHolder;
import listeners.AccommodationListener;
import model.AccommodationRoom;
import model.PreviewData;

import static com.olivine.parjatanbichitra.cholodesh.AccommodationActivity.ACTION_DECREASE_ROOM;
import static com.olivine.parjatanbichitra.cholodesh.AccommodationActivity.ACTION_DELETE_ROOM;
import static com.olivine.parjatanbichitra.cholodesh.AccommodationActivity.ACTION_INCRESE_ROOM;

/**
 * Created by Olivine on 5/27/2017.
 */
public class RouteAccommodationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int ppsition=BaseURL.checkindates.size();
    int key=0;
    int start =0;
    private static final int TITLEVIEW=0;
    private static final int ACCOMMODATIONVIEW=1;
    private int currentDestination=0;
    private int lastListposition;
    int districtid;
    private Activity mContext;
    private  ArrayList<AccommodationRoom> accommodationRooms;
    private ArrayList<Integer> itemCount=new ArrayList<>();
    private AccommodationListener accommodationListener;
    public RouteAccommodationAdapter setAccommodationListener(AccommodationListener accommodationListener) {
        this.accommodationListener = accommodationListener;
        return this;
    }
    public RouteAccommodationAdapter(Activity mContext, ArrayList<AccommodationRoom> accommodationRooms) {
        this.mContext = mContext;
        this.accommodationRooms = accommodationRooms;
        if (accommodationRooms == null) return;
        // setting up views to show in list
        for(int i=0;i<accommodationRooms.size();i++){
            int tmp_id= accommodationRooms.get(i).getDistrictId();
            if(tmp_id!=currentDestination){
                itemCount.add(TITLEVIEW);
                currentDestination=tmp_id;
            }else{
                itemCount.add(ACCOMMODATIONVIEW);
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        switch (itemCount.get(position)){
            case TITLEVIEW:
                return TITLEVIEW;
            case ACCOMMODATIONVIEW:
                return  ACCOMMODATIONVIEW;
        }
        return 0;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TITLEVIEW:
                // show title
                View titleView=LayoutInflater.from(mContext).inflate(R.layout.layout_global_list_title,parent,false);
                return new GlobaltitleViewholder(titleView);
            case ACCOMMODATIONVIEW:
                // show accommodation
                View itemView=LayoutInflater.from(mContext).inflate(R.layout.layout_selected_room,parent,false);
                return new SelectedAccommodationViewHolder(itemView);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)){
            case TITLEVIEW:
              //  Toast.makeText(mContext, "position "+position , Toast.LENGTH_SHORT).show();
                 start =position-1;
                GlobaltitleViewholder globaltitleViewholder= (GlobaltitleViewholder) holder;
                //if (BaseURL.isEdit)
                globaltitleViewholder.routeTitle.setText(accommodationRooms.get(position).getDistrictName());
                Gson gson = new Gson();
                String json = gson.toJson(accommodationRooms);
                Log.e("JSON",json );
                //else
                Log.e("Size", CustomTripPlanDataHolder.districtsName.size()+"");
                globaltitleViewholder.addHotel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(mContext,AccommodationProviderActivity.class);
                        intent.putExtra("district_id",accommodationRooms.get(position).getDistrictId());
                        (mContext).startActivityForResult(intent,accommodationRooms.get(position).getDistrictId());
                    }
                });
               // Toast.makeText(mContext, ""+accommodationRooms.get(position).getDistrictId(), Toast.LENGTH_SHORT).show();
             //   Toast.makeText(mContext, ""+accommodationRooms.get(position).getDistrictId(), Toast.LENGTH_SHORT).show();
                break;
            case ACCOMMODATIONVIEW:
               // Toast.makeText(mContext, "position text"+position , Toast.LENGTH_SHORT).show();
                start=start+1;
                final AccommodationRoom tmp_provider=accommodationRooms.get(position);
                String providerName = tmp_provider.getProviderName();
                String maxOccupancy = tmp_provider.getAccommodationRoomMaxOccupancy();
                String categoryName = tmp_provider.getAccommodationCategoryName();




//                int positio =position-1;
//                Toast.makeText(mContext, ""+ppsition, Toast.LENGTH_SHORT).show();

//               final String checkindateBind =BaseURL.checkindates.get(position-1);
//                final String checkOutdateBind =BaseURL.checkoutdates.get(position-1);
//                start=start+1;

                if (PreviewData.hotelName==null){
                    PreviewData.hotelName =providerName;
                }else if (!PreviewData.hotelName.equals(providerName)){
                    PreviewData.hotelNameTwo=providerName;
                }
              //  Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                String checkindate = tmp_provider.getCheck_in_date();
                String checkoutdate = tmp_provider.getCheck_out_date();
                String qty = tmp_provider.getQuantity()+"";
                String price = tmp_provider.getQuantity()*Integer.parseInt(tmp_provider.getAccommodationRoomPrice())+"";
                //String bedType = tmp_provider.bedTypeName.replace(",","\n\u2022 ");
                if (!BaseURL.LANGUAGE_ENG){
                    providerName = tmp_provider.providerNameBn == null ? tmp_provider.getProviderName() : tmp_provider.providerNameBn;
                    maxOccupancy = BanglaNumberParser.getBangla(maxOccupancy);
                    categoryName = tmp_provider.categoryNameBn == null ? tmp_provider.getAccommodationCategoryName() : tmp_provider.categoryNameBn;
                    qty = BanglaNumberParser.getBangla(qty);
                    price = BanglaNumberParser.getBangla(price);
                    //bedType = tmp_provider.bedTypeNameBn.replace(",","\n\u2022 ");
                }

                final SelectedAccommodationViewHolder selectedAccommodationViewHolder= (SelectedAccommodationViewHolder) holder;
                selectedAccommodationViewHolder.accommodationName.setText(providerName);
                selectedAccommodationViewHolder.occupency.setText(maxOccupancy+"");
                if (BaseURL.isEdit){
                    selectedAccommodationViewHolder.checkin.setText("In " +TailorMadeDataHolder.checkin_date);
                    selectedAccommodationViewHolder.checkout.setText("Out "+TailorMadeDataHolder.checkout_date);
                }else {
                    selectedAccommodationViewHolder.checkin.setText("In " + "");
                    selectedAccommodationViewHolder.checkout.setText("Out "+"");
//                    selectedAccommodationViewHolder.checkin.setText("In " + CustomTripPlanDataHolder.checkindatee);
//                    selectedAccommodationViewHolder.checkout.setText("Out "+CustomTripPlanDataHolder.checkoutdatee);
                }

                selectedAccommodationViewHolder.accommodationName.setText(providerName);

                String url= BaseURL.HOTEL_ROOM_IMAGE_BASE_URL+tmp_provider.getAccommodationRoomGalleryImage();
                Log.e("imgurl",url);

                Picasso.with(mContext)
                        .load(url)
                        .placeholder(R.drawable.image_placeholder)
                        .fit()
                        .into(selectedAccommodationViewHolder.hotelroom);


                if(tmp_provider.getQuantity()==1){
                    selectedAccommodationViewHolder.roomType.setText(categoryName);
                }
                selectedAccommodationViewHolder.roomType.setText(qty+" "+categoryName);
                selectedAccommodationViewHolder.roomPrice.setText(price+"৳");
                PreviewData.hotelcost=price;
                //selectedAccommodationViewHolder.bedType.setText(bedType);
                selectedAccommodationViewHolder.increaseRoom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int qty = tmp_provider.getQuantity()+1;
                        String roomtype = qty+" "+tmp_provider.getAccommodationCategoryName();
                        String price= tmp_provider.getQuantity()*Integer.parseInt(tmp_provider.getAccommodationRoomPrice())+"";
                        if (!BaseURL.LANGUAGE_ENG){
                            roomtype = BanglaNumberParser.getBangla(tmp_provider.getQuantity()+"")+ " " + tmp_provider.categoryNameBn;
                            price = BanglaNumberParser.getBangla(price);
                        }
                        tmp_provider.setQuantity(qty);
                        selectedAccommodationViewHolder.roomType.setText(roomtype);
                        selectedAccommodationViewHolder.roomPrice.setText(price+"৳");
                        accommodationListener.calculateAccommodationCost(tmp_provider,ACTION_INCRESE_ROOM);
                    }
                });
                // decrease room
                selectedAccommodationViewHolder.decreaseRoom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(tmp_provider.getQuantity()>1){
                            tmp_provider.setQuantity(tmp_provider.getQuantity()-1);
                            String roomtype = tmp_provider.getQuantity()+" "+tmp_provider.getAccommodationCategoryName();
                            String price= tmp_provider.getQuantity()*Integer.parseInt(tmp_provider.getAccommodationRoomPrice())+"";
                            if (!BaseURL.LANGUAGE_ENG){
                                roomtype = BanglaNumberParser.getBangla(tmp_provider.getQuantity()+"")+ " " + tmp_provider.categoryNameBn;
                                price = BanglaNumberParser.getBangla(price);
                            }
                            selectedAccommodationViewHolder.roomType.setText(roomtype);
                            selectedAccommodationViewHolder.roomPrice.setText(price+"৳");
                            accommodationListener.calculateAccommodationCost(tmp_provider,ACTION_DECREASE_ROOM);
                        }
                    }
                });
//                delete room

                selectedAccommodationViewHolder.deleteAccomodation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(mContext,tmp_provider.getProviderName(),Toast.LENGTH_SHORT).show();
                        int position=accommodationRooms.indexOf(tmp_provider);
                        itemCount.remove(position);
                        //Toast.makeText(mContext,accommodationRooms.size()+"",Toast.LENGTH_SHORT).show();
                        accommodationRooms.remove(tmp_provider);
                        BaseURL.removeAcc(tmp_provider);
                        //start
                        BaseURL.removeaccodate(tmp_provider.getProviderId());


                            BaseURL.dates.clear();
                            BaseURL.accommodationRooms.clear();
                            mContext.finish();
//

                        RouteAccommodationAdapter routeAccommodationAdapter=new RouteAccommodationAdapter(mContext,accommodationRooms);
                      routeAccommodationAdapter.notifyDataSetChanged();

                     //   BaseURL.removeDate(start);
                        //TailorMadeDataHolder.accommodationRooms.remove(tmp_provider);
                        notifyDataSetChanged();
                        accommodationListener.calculateAccommodationCost(tmp_provider,ACTION_DELETE_ROOM);
                    }
                });
                break;
        }
    }
    @Override
    public int getItemCount() {
        return accommodationRooms.size();
    }
}
class GlobaltitleViewholder extends RecyclerView.ViewHolder{
    TextView routeTitle;
    Button addHotel;
    public GlobaltitleViewholder(View itemView) {
        super(itemView);
        routeTitle= (TextView) itemView.findViewById(R.id.routeTitle);
        addHotel= (Button) itemView.findViewById(R.id.addHotel);
        if (!BaseURL.LANGUAGE_ENG){
            addHotel.setText("যোগ করুন");
        }
    }
}

class SelectedAccommodationViewHolder extends RecyclerView.ViewHolder{
    LinearLayout roomListParent;
    TextView checkin;
    TextView checkout;
    TextView decreaseRoom;
    TextView increaseRoom;
    TextView deleteAccomodation;
    TextView occupency;
    TextView accommodationName;
    TextView roomType;
    TextView bedType;
    TextView roomPrice;
    ImageView hotelroom;
    SwipeLayout swipeLayout;

    public SelectedAccommodationViewHolder(View itemView) {
        super(itemView);
        //Swipe
        swipeLayout= (SwipeLayout) itemView.findViewById(R.id.swipeLayout);
        decreaseRoom= (TextView) itemView.findViewById(R.id.decreaseRoom);
        increaseRoom= (TextView) itemView.findViewById(R.id.increaseRoom);
        deleteAccomodation= (TextView) itemView.findViewById(R.id.deleteAccomodation);
        roomListParent= (LinearLayout) itemView.findViewById(R.id.roomListParent);
        occupency= (TextView) itemView.findViewById(R.id.availableRooms);
        roomType= (TextView) itemView.findViewById(R.id.roomTypenQuantity);
        accommodationName= (TextView) itemView.findViewById(R.id.accommodationName);
        bedType= (TextView) itemView.findViewById(R.id.occupency);
        roomPrice= (TextView) itemView.findViewById(R.id.roomPrice);
        hotelroom= (ImageView) itemView.findViewById(R.id.hotelroom);
        checkin= (TextView) itemView.findViewById(R.id.checkindate);
        checkout= (TextView) itemView.findViewById(R.id.checkoutdate);
      //  cross=(TextView)itemView.findViewById(R.id.emititem);

    }

}
