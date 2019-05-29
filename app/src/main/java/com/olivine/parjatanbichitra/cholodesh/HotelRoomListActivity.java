/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import adapters.AccommodationRoomListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.BookingCallback;
import callbacks.ProvideCallback;
import constants.Travel;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import io.realm.Realm;
import model.AccommodationRoom;
import model.HotelBooking;
import model.HotelGallery;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import helpers.CustomCallBack;

import static adapters.HotelListAdapter.SERVICE_ID;

public class HotelRoomListActivity extends AppCompatActivity {
    //start sazzad
    ProgressDialog progressDialog;
    TextView returnDateTextView, departDateTextView, editText_no_of_days, hotel_nametv;
    int year;
    int month;
    int day;
    int dayout;
    Calendar calendar;
    String checkInStr;
    String checkOutStr;
    int checkOut_month = 0;
    int checkIn_month = 0;
    int dayin=0;
    int dayOut=0;
    //end
    // For Separate Hotel Room Booking outside Tailor made.
    private List<AccommodationRoom> accommodationRooms;
    private static final int BOOK_MENU = 1;
    ProvideCallback provideCallback;
    BookingCallback bookingCallback;
    @BindView(R.id.min)
    EditText min;
    @BindView(R.id.max)
    EditText max;
    @BindView(R.id.search)
    Button search;
    Context context;
    // Data strorage
    SharedPreferences sharedPreferences;
    Realm realm;
    //View
    @BindView(R.id.hotelRooms)
    RecyclerView hotelRooms;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String menuName = "Confirm Room";
        if (!BaseURL.LANGUAGE_ENG) {
            menuName = "কনফার্ম ";
            min.setHint("সর্বনিম্ন");
            max.setHint("সর্বোচ্চ");
            search.setText("অনুসন্ধান");
        }
        MenuItem menuItem = menu.add(Menu.NONE, BOOK_MENU, Menu.NONE, menuName);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case BOOK_MENU:
                final AlertDialog.Builder roomConfirmDialog = new AlertDialog.Builder(this);
                String no = "No";
                String yes = "Yes";
                String title = "Confirmation";
                String message = "Do You want to confirm Booking ?";

                if (!BaseURL.LANGUAGE_ENG) {
                    title = "অনুমোদন";
                    message = "আপনি বুকিং নিশ্চিত করতে চান?";
                    yes = "হ্যা";
                    no = "না";
                }


                roomConfirmDialog.setTitle(title);
                roomConfirmDialog.setMessage(message);
                roomConfirmDialog.setNegativeButton(no,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                // If user select YES then redirect to the login if not logged in or confirm booking
                roomConfirmDialog.setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String customerEmail=sharedPreferences.getString(Travel.USER_EMAIL,null);
                        if(customerEmail==null){
                            Intent loginIntent=new Intent(HotelRoomListActivity.this,LoginActivity.class);
                            startActivityForResult(loginIntent,110);
                            return;
                        }else {
                            reserveRoom();

                        }

                    }
                });
                boolean flag = false;

                for (int itr = 0; itr < accommodationRooms.size(); itr++) {
                    if (accommodationRooms.get(itr).isSelected()) {
                        flag = true;
                        break;
                    }
                }
                if (flag) roomConfirmDialog.show();
                if (flag) {
                   // callReady();
                } else {
                    String meesage = "No Room is Selected";
                    if (!BaseURL.LANGUAGE_ENG) {
                        meesage = " কোন রুম নির্বাচন করা হয়নি ";
                    }
                    Toast.makeText(HotelRoomListActivity.this, meesage, Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    DatePickerDialog dpd;
    public void checkinDate() {
         dpd = new DatePickerDialog(this, dateListenerclubentry, year, month, day);
        dpd.show();
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }
    private DatePickerDialog.OnDateSetListener dateListenerclubentry = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            checkIn_month = i1 + 1;
            dayin=i2;
            checkInStr = i2 + "-" + checkIn_month + "-" + i;
            departDateTextView.setText(checkInStr);
            checkIn_month = 0;
        }
    };
    public void checkOutDate() {
        DatePickerDialog dpd = new DatePickerDialog(this, dateOut, year, month, dayout);
        dpd.show();
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }
    private DatePickerDialog.OnDateSetListener dateOut = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            //  String s = i+"/"+i1+"/"+i2;
            checkOut_month = i1 + 1;
            i2=dayin+1;
            checkOutStr = i2 + "-" + checkOut_month + "-" + i;
            returnDateTextView.setText(checkOutStr);
            checkOut_month = 0;

        }
    };


    public void callReady() {
        final Dialog dialog = new Dialog(this);
        //, android.R.style.Theme_Black_NoTitleBar_Fullscreen
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.call_dialog);

        hotel_nametv = (TextView) dialog.findViewById(R.id.hotelName);
        hotel_nametv.setText(hotel_name);

        // LinearLayout llTakePhoto, llGallery, llsupervisor;
        // final TextView returnDateTextView, departDateTextView, editText_no_of_days;
        final Button confarm, cancel;
        // editText_no_of_days = (EditText) dialog.findViewById(R.id.editText_no_of_days);

        departDateTextView = (TextView) dialog.findViewById(R.id.departDateTextView);
        returnDateTextView = (TextView) dialog.findViewById(R.id.returnDateTextViewc);
        departDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkinDate();
            }
        });


        returnDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutDate();

            }
        });

        confarm = (Button) dialog.findViewById(R.id.confarm);
        cancel = (Button) dialog.findViewById(R.id.cancel);

        // private String ,,p2name,p2num,supname,supnumber;


        confarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Loading.....");
                progressDialog.show();

                reserveRoom();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });


        dialog.show();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            reserveRoom();
        }
    }

    private String hotel_name;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_room);
        //sazzad start
        calendar = Calendar.getInstance(Locale.getDefault());
        // k=1;
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dayout = calendar.get(Calendar.DAY_OF_MONTH)+1;

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);


        //end
        Intent intent = getIntent();
        hotel_name = intent.getStringExtra("NAME");
        dpd = new DatePickerDialog(this);

        int dayin=0;
        int dayOut=0;

        ButterKnife.bind(this);
        context = getApplicationContext();
        String title = "Rooms";
        if (!BaseURL.LANGUAGE_ENG) {
            this.setTitle("রুম সমূহ");
        } else this.setTitle(title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Storage init
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        int serviceId = getIntent().getIntExtra(SERVICE_ID, 0);
        // callback init
        provideCallback = new ProvideCallback(this);
        bookingCallback = new BookingCallback(this);
        // get hotel rooms
        provideCallback.getAccommodationRoom(serviceId).enqueue(new CustomCallBack<AccommodationRoom[]>(this) {
            @Override
            public void onResponse(Call<AccommodationRoom[]> call, Response<AccommodationRoom[]> response) {
                super.onResponse(call, response);
                Log.e("Package Url", call.request().url().toString());
                if (response.body() != null && response.body().length > 0) {
                    accommodationRooms = Arrays.asList(response.body());
                    if (!BaseURL.LANGUAGE_ENG) {
                        HotelRoomListActivity.this.setTitle(response.body()[0].providerNameBn);
                    } else
                        HotelRoomListActivity.this.setTitle(response.body()[0].getProviderName());
                   // Toast.makeText(HotelRoomListActivity.this, "room size"+accommodationRooms, Toast.LENGTH_SHORT).show();
                    AccommodationRoomListAdapter accommodationRoomListAdapter = new AccommodationRoomListAdapter(HotelRoomListActivity.this, accommodationRooms);
                    hotelRooms.setAdapter(accommodationRoomListAdapter);
                } else {
                    String meesage = "Nothing Found";
                    if (!BaseURL.LANGUAGE_ENG) {
                        meesage = " কিছুই পাওয়া যায়নি";
                    }
                    Toast.makeText(HotelRoomListActivity.this, meesage, Toast.LENGTH_LONG).show();

                    HotelRoomListActivity.this.onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<AccommodationRoom[]> call, Throwable t) {
                super.onFailure(call, t);
                String meesage = "Network Error";
                if (!BaseURL.LANGUAGE_ENG) {
                    meesage = " নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(HotelRoomListActivity.this, meesage, Toast.LENGTH_LONG).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AccommodationRoomlistActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                if (min.getText().length() == 0 || max.getText().length() == 0) {
                    String error = "Please enter the range of search";
                    if (!BaseURL.LANGUAGE_ENG) {
                        error = "অনুসন্ধানের পরিসীমা লিখুন দয়া করে।";
                    }
                    Toast.makeText(HotelRoomListActivity.this, error, Toast.LENGTH_SHORT).show();
                    return;
                }
                filterSearch();
                View view = HotelRoomListActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        hotelRooms.requestFocus();

    }

    private void filterSearch() {
        List<AccommodationRoom> temp = new ArrayList<>();
        int frstRange = Integer.parseInt(min.getText().toString());
        int lastRange = Integer.parseInt(max.getText().toString());

        for (int i = 0; i < accommodationRooms.size(); i++) {

            AccommodationRoom accommodationRoom = new AccommodationRoom();
            accommodationRoom = accommodationRooms.get(i);
            int price = Integer.parseInt(accommodationRoom.getAccommodationRoomPrice());
            if (price >= frstRange && price < lastRange) {

                temp.add(accommodationRoom);
            }
        }

        AccommodationRoomListAdapter accommodationRoomListAdapter = new AccommodationRoomListAdapter(HotelRoomListActivity.this, temp);
        hotelRooms.setAdapter(accommodationRoomListAdapter);


    }

    private void reserveRoom() {
        final List<AccommodationRoom> selectedAccommodationRooms = new ArrayList<AccommodationRoom>();
//start sazzad
//      AccommodationRoom accommodationRoom = new AccommodationRoom();
//      accommodationRoom.setCheck_in_date(checkInStr);
//      accommodationRoom.setCheck_out_date(checkOutStr);
        //end sazzad
        // Get selected Roooms
        for (AccommodationRoom selected_room : accommodationRooms) {
            if (selected_room.isSelected()) {
                selectedAccommodationRooms.add(selected_room);
                //sazzad start
//                selected_room.setCheck_in_date("yes");
//                selected_room.setCheck_out_date("yes");
                //end sazzad
                // selectedAccommodationRooms.
            }
        }
        String customerEmail = sharedPreferences.getString(Travel.USER_EMAIL, null);
        final HotelBooking rooms = new HotelBooking();
        rooms.setCustomerEmail(customerEmail);

        //sazzad
        rooms.setAccommodationRoomList(selectedAccommodationRooms);

        rooms.setCheck_in_date(BaseURL.accommodationcheckindate);
        rooms.setCheck_out_date(BaseURL.accommodationcheckoutdate);
        Gson gson = new Gson();
        String json = gson.toJson(rooms);
        Log.e("Url", "Null");
        Log.e("JSON", json);
        bookingCallback.confirmBooking(rooms).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //realm.beginTransaction();
                //Booking booking=new Booking();
                // Get Booking token from server
                // booking.tokenNO=response.body();
                if (response.body() != null) {
                    String meesage = "Your Booking was succesful. Find your token in view token section.";
                    if (!BaseURL.LANGUAGE_ENG) {
                        meesage = " আপনার বুকিং সফল হয়েছে ";
                    }
                    progressDialog.cancel();
                    Toast.makeText(HotelRoomListActivity.this, meesage, Toast.LENGTH_LONG).show();
                    Intent bookinListintent = new Intent(context, MainActivity.class);
                    bookinListintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(bookinListintent);
                    //Toast.makeText(HotelRoomListActivity.this,response.body(),Toast.LENGTH_LONG).show();
                } else {
                    String meesage = "Something Went Wrong";
                    if (!BaseURL.LANGUAGE_ENG) {
                        meesage = " কিছু ভুল হয়েছে";
                    }
                    Toast.makeText(HotelRoomListActivity.this, meesage, Toast.LENGTH_LONG).show();
                    //Toast.makeText(HotelRoomListActivity.this,"Null",Toast.LENGTH_LONG).show();
                }


                // Save to database
                //booking.accommodationRooms=new RealmList<>();
                //booking.accommodationRooms.addAll(selectedAccommodationRooms);
                //realm.copyToRealm(booking);
                //realm.commitTransaction();


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String meesage = "Network Error";
                if (!BaseURL.LANGUAGE_ENG) {
                    meesage = " নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(HotelRoomListActivity.this, meesage, Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
