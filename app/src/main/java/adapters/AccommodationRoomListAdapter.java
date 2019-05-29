/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomTripPlanDataHolder;
import model.AccommodationRoom;
import model.PreviewData;

/**
 * Created by Tanveer on 5/27/2017.
 */

public class AccommodationRoomListAdapter extends RecyclerView.Adapter<AccommodationRoomViewholder> {
    //start sazzad
    AccommodationRoom a;
    ProgressDialog progressDialog;
    TextView returnDateTextView, departDateTextView, editText_no_of_days, hotel_nametv, city;
    private LinearLayout returnDateTextViewll;
    int year;
    int month;
    int day;
    int dayout;
    Calendar calendar;
    String checkInStr;
    String checkOutStr;
    int checkOut_month = 0;
    int checkIn_month = 0;
    int dayin = 0;
    int dayOut = 0;
    private String hotel_name;
    //end

    private Context mContext;
    private List<AccommodationRoom> accommodationRooms;
    AccommodationRoomViewholder ref;
    public AccommodationRoomListAdapter(Context mContext, List<AccommodationRoom> accommodationRooms) {
        this.mContext = mContext;
        this.accommodationRooms = new ArrayList<>();
        filterRooms(accommodationRooms);
    }

    void filterRooms(List<AccommodationRoom> accommodationRooms) {
        for (AccommodationRoom accommodationRoom : accommodationRooms) {

            if (!doesExist(accommodationRoom)) {
                this.accommodationRooms.add(accommodationRoom);
            }
        }

    }

    @Override
    public AccommodationRoomViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_hotel_room_list_new, parent, false);
        AccommodationRoomViewholder accommodationViewholder = new AccommodationRoomViewholder(itemView);
        return accommodationViewholder;
    }

    @Override
    public void onBindViewHolder(final AccommodationRoomViewholder holder, final int position) {
        final AccommodationRoom accommodationRoom = accommodationRooms.get(position);
        //Toast.makeText(mContext,accommodationRoom.UniqueId,Toast.LENGTH_SHORT).show();
        String url = BaseURL.HOTEL_ROOM_IMAGE_BASE_URL + accommodationRoom.getAccommodationRoomGalleryImage();
        String providerName = accommodationRoom.getProviderName();
        String maxOccu = "Max Occupancy: ";
        String maxOccupancy = accommodationRoom.getAccommodationRoomMaxOccupancy();
        String categoryType = accommodationRoom.getAccommodationCategoryName();
        String priceTxt = "Price: ";
        String roomPrice = accommodationRoom.getAccommodationRoomPrice();
        String noTxt = "#" + (position + 1) + " of " + getItemCount() + " Rooms ";
        String bedType = accommodationRoom.bedTypeName.replace(",", "\n\u2022 ");
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(holder.roomImage);
        if (!BaseURL.LANGUAGE_ENG) {
            holder.select.setText("বাছাই করুন");
            providerName = accommodationRoom.providerNameBn;
            maxOccu = "সর্বোচ্চ আবাসন: ";
            maxOccupancy = BanglaNumberParser.getBangla(maxOccupancy);
            categoryType = accommodationRoom.categoryNameBn;
            priceTxt = "মূল্য: ";
            roomPrice = BanglaNumberParser.getBangla(roomPrice);
            noTxt = BanglaNumberParser.getBangla(getItemCount() + "") + " টি  " + "রুমের মধ্যে #" + BanglaNumberParser.getBangla((position + 1) + "");
            bedType = accommodationRoom.bedTypeNameBn.replace(",", "\n\u2022 ");

        }
        holder.accommodationName.setText(providerName);
        holder.occupancy.setText(maxOccu + maxOccupancy + "");
        holder.roomType.setText(categoryType);
        holder.roomPrice.setText(priceTxt + roomPrice + "৳");
        holder.count.setText(noTxt);

        holder.bedType.setText("\u2022 " + bedType);
//        holder.bedType.setText("Single Bed");

        if (accommodationRoom.isSelected()) {
            holder.roomListParent.setBackground(mContext.getResources().getDrawable(R.drawable.drawable_border_bottom_selected));
            if (!BaseURL.LANGUAGE_ENG) holder.select.setText("নির্বাচিত");
            else holder.select.setText("Selected");
        } else {
            holder.roomListParent.setBackground(mContext.getResources().getDrawable(R.drawable.drawable_border_bottom));
            if (!BaseURL.LANGUAGE_ENG) holder.select.setText("বাছাই করুন");
            else holder.select.setText("Select");
        }
        ref = holder;
        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (doesExist(accommodationRoom)) {
                    String message = "Already booked";
                    if (!BaseURL.LANGUAGE_ENG) {
                        message = "ইতিমধ্যে নেয়া  হয়েছে";
                    }
                  //  Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    holder.select.setVisibility(View.INVISIBLE);
                    // Toast.makeText(mContext,accommodationRoom.getAccommodationRoomId()+"",Toast.LENGTH_SHORT).show();
                } else {
                    //accommodationRoom.setSelected(!accommodationRoom.isSelected());
                    if (holder.select.getText().equals("Select") || holder.select.getText().equals("বাছাই করুন")) {
                        if (!BaseURL.LANGUAGE_ENG) holder.select.setText("নির্বাচিত");
                        else holder.select.setText("Selected");
                        callReady();
                        holder.roomListParent.setBackground(mContext.getResources().getDrawable(R.drawable.drawable_border_bottom_selected));
                        //BaseURL.accommodationRooms.add(accommodationRoom);
                        BaseURL.totalCost += Integer.parseInt(accommodationRoom.getAccommodationRoomPrice());

                        // Toast.makeText(mContext,BaseURL.totalCost+"",Toast.LENGTH_SHORT).show();
                    } else {
                        if (!BaseURL.LANGUAGE_ENG) holder.select.setText("বাছাই করুন");
                        else holder.select.setText("Select");
                        holder.roomListParent.setBackground(mContext.getResources().getDrawable(R.drawable.drawable_border_bottom));
                        BaseURL.totalCost -= Integer.parseInt(accommodationRoom.getAccommodationRoomPrice());

                    }
                    toggleSelecion(view, accommodationRoom, holder);
                }
            }
        });
    }
    public void callReady() {

        BaseURL.datecheckHotel.clear();
        BaseURL.accommodationcheckoutdate = "";
        BaseURL.accommodationcheckindate = "";
        //start
        ArrayList<AccommodationRoom> rooms = new ArrayList<>();
        //end
        final Dialog dialog = new Dialog(mContext);
        //, android.R.style.Theme_Black_NoTitleBar_Fullscreen
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.call_dialog);
        hotel_nametv = (TextView) dialog.findViewById(R.id.hotelName);
        city = (TextView) dialog.findViewById(R.id.cityy);
        for (AccommodationRoom ar : accommodationRooms) {
            hotel_nametv.setText(ar.getProviderName());
            city.setText(ar.getDistrictName());
            if (ar.isSelected()) {
                rooms.add(ar);
                BaseURL.accommodationRooms.add(ar);
            }
        }
        final Button confarm, cancel;
        departDateTextView = (TextView) dialog.findViewById(R.id.departDateTextView);
        returnDateTextView = (TextView) dialog.findViewById(R.id.returnDateTextViewc);
        returnDateTextViewll = (LinearLayout) dialog.findViewById(R.id.returnDateLayoutll);
        TextView returnforlang;
        returnforlang= (TextView)dialog.findViewById(R.id.returnforlang);

        departDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDateTextView.setEnabled(true);
                checkinDate();
            }
        });

        returnDateTextViewll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  checkOutDate();
                checkOutDate();
            }
        });

        confarm = (Button) dialog.findViewById(R.id.confarm);
        cancel = (Button) dialog.findViewById(R.id.cancel);
        TextView tvcheckin =(TextView)dialog.findViewById(R.id.tvcheckin);
        if (!BaseURL.LANGUAGE_ENG)
        {
            hotel_nametv.setText("হোটেল এর নাম ");
            city.setText("শহর");
            confarm.setText("নিশ্চিত করুন");
            cancel.setText("বাতিল করুন");
            tvcheckin.setText("চেক ইন");
            returnforlang.setText("চেক আউট");
        }

        confarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<AccommodationRoom> rooms = new ArrayList<>();
                for (AccommodationRoom ar : accommodationRooms) {
                    if (ar.isSelected()) {
                        rooms.add(ar);
                        BaseURL.accommodationRooms.add(ar);
                        BaseURL.datecheckHotel.add(checkInStr);
                        BaseURL.datecheckHotel.add(checkOutStr);
                        ar.setCheck_in_date(checkInStr);
                        ar.setCheck_out_date(checkOutStr);
                        CustomTripPlanDataHolder.checkindatee = checkInStr;
                        CustomTripPlanDataHolder.checkoutdatee = checkOutStr;
                       // Toast.makeText(mContext, ""+checkInStr, Toast.LENGTH_SHORT).show();

                    }
                }

                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    DatePickerDialog dpd;
    public void checkinDate() {
        dpd = new DatePickerDialog(mContext, dateListenerclubentry, year, month, day);
        dpd.show();
        //
        SimpleDateFormat sdfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = null;
        Date d1 = null;
        try {
            if (BaseURL.enddate != null) {
                d = sdfm.parse(BaseURL.enddate);
                d1 = sdfm.parse(BaseURL.startDate);
                dpd.getDatePicker().setMaxDate(d.getTime());
                dpd.getDatePicker().setMinDate(d1.getTime());

            } else {
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private DatePickerDialog.OnDateSetListener dateListenerclubentry = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            checkIn_month = i1 + 1;
            dayin = i2;
            checkInStr = i2 + "-" + checkIn_month + "-" + i;
            departDateTextView.setText(checkInStr);
            CustomTripPlanDataHolder.checkindatee = checkInStr;
            checkIn_month = 0;
            PreviewData.checkindate = checkInStr;
            BaseURL.accommodationcheckindate = checkInStr;
            BaseURL.lastdate = checkInStr;
        }
    };

    public String dateincrease(String date) {
        String dt = date;  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        String output = sdf1.format(c.getTime());
        return output;

    }

    public void checkOutDate() {
        DatePickerDialog dpd = new DatePickerDialog(mContext, dateOut, year, month, dayout);
        dpd.show();
        //dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        SimpleDateFormat sdfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = null;
        Date d1 = null;
        String firstdate = dateincrease(BaseURL.lastdate);

        try {
            if (BaseURL.enddate != null) {
                d = sdfm.parse(BaseURL.enddate);
                d1 = sdfm.parse(firstdate);
                dpd.getDatePicker().setMaxDate(d.getTime());
                dpd.getDatePicker().setMinDate(d1.getTime());
            } else {
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private DatePickerDialog.OnDateSetListener dateOut = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            checkOut_month = i1 + 1;
            checkOutStr = i2 + "-" + checkOut_month + "-" + i;
            returnDateTextView.setText(checkOutStr);
            checkOut_month = 0;
            BaseURL.accommodationcheckoutdate = checkOutStr;

            CustomTripPlanDataHolder.lastDate = BanglaNumberParser.getEnglish(checkOutStr);
            BaseURL.lastdate = checkOutStr;
            CustomTripPlanDataHolder.checkoutdatee = checkOutStr;
            PreviewData.checkoutDate = checkOutStr;
        }
    };
    //end sazzad

    boolean doesExist(AccommodationRoom accommodationRoom) {
        boolean result = false;
        for (int i = 0; i < BaseURL.accommodationRooms.size(); i++) {
            if (accommodationRoom.equals(BaseURL.accommodationRooms.get(i))) {
                //Toast.makeText(mContext,"matching : " +accommodationRoom.getAccommodationRoomId() + " with "+ BaseURL.accommodationRooms.get(i).getAccommodationRoomId(),Toast.LENGTH_SHORT).show();
                result = true;
            }
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return accommodationRooms.size();
    }

    // select rooms fom list and diselect
    private void toggleSelecion(View view, AccommodationRoom accommodationRoom, AccommodationRoomViewholder holder) {
        if (accommodationRoom.isSelected()) {
            //holder.select.setText("Select");
            accommodationRoom.setSelected(false);
        } else {
            //holder.select.setText("Selected");
            accommodationRoom.setSelected(true);
        }
    }
}

class AccommodationRoomViewholder extends RecyclerView.ViewHolder {
    LinearLayout roomListParent;
    TextView occupancy;
    TextView accommodationName; //// STOPSHIP: 6/2/2017  
    TextView roomType; //s
    TextView bedType;
    TextView roomPrice;
    TextView count;
    Button select;
    ImageView roomImage;
    CheckBox checkBox;
    TextView bdTypeForLang;

    public AccommodationRoomViewholder(View itemView) {
        super(itemView);
        roomListParent = (LinearLayout) itemView.findViewById(R.id.roomListParent);
        occupancy = (TextView) itemView.findViewById(R.id.occupancy);
        roomType = (TextView) itemView.findViewById(R.id.roomType);
        accommodationName = (TextView) itemView.findViewById(R.id.accommodationName);
        //bedType= (TextView) itemView.findViewById(R.id.bedType);
        roomPrice = (TextView) itemView.findViewById(R.id.roomPrice);
        count = (TextView) itemView.findViewById(R.id.count);
        select = (Button) itemView.findViewById(R.id.select);
        roomImage = (ImageView) itemView.findViewById(R.id.hotelroom);
        bedType = (TextView) itemView.findViewById(R.id.bedType);
        bdTypeForLang = (TextView) itemView.findViewById(R.id.bdTypeForLang);

        if (!BaseURL.LANGUAGE_ENG) {

            bdTypeForLang.setText("বেড টাইপ");
        }
        //checkBox = (CheckBox) itemView.findViewById(R.id.chekbox);
    }
}
