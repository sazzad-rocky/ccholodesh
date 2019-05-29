/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.HotelListAdapter;
import adapters.HotelResponsAdapter;
import adapters.NewRouteListAdapter;
import adapters.TailorMadeAccommodationAdapter;
import adapters.TailorMadeTransportAdapter;
import callbacks.ProvideCallback;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import helpers.CustomTripPlanDataHolder;
import helpers.DynamicHeight;
import helpers.TailorMadeDataHolder;
import listeners.FragmentInteractionListener;
import model.AccommodationProvider;
import model.ConfirmOperator;
import model.CustomTripPlanNewRouteGetModel;
import model.HotelGallery;
import model.HotelRespons;
import model.LatLong;
import model.LocationResponse;
import model.OperatorConfirmCallback;
import model.PreviewData;
import model.TailorMadeAccommodation;
import model.TailorMadeItinerary;
import model.TailorMadeTransport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import userDefinder.TailorMade;
public class TailorMadeActivity extends AppCompatActivity implements FragmentInteractionListener, View.OnClickListener {

    public OperatorConfirmCallback callback;
    // made for client requirement but not active
//    @BindView(R.id.stepperLayout) StepperLayout stepperLayout;
//    private RouteSelectionFragment routeSelectionFragment;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    String district_name;
    TextView fromtv, itineraryy, startdatetv, returntv, torriststv, daystv, hotelname, hotelcost, checkindatetv, checkoutdatetv, transport, transport2, transportrout, transportrouttwo, transportcost, hotelname2, hotelcost2, checkindatetv2, checkoutdatetv2, hotelname3, hotelcost3, checkindatetv3, checkoutdatetv3;
    TextView itinery, itinarydatetv, itinery2, itinarydatetv2, itinery3, itinarydatetv3, itinerarycost;
    private List<HotelRespons> hotelRespons;

    HotelResponsAdapter hra;
    private ProvideCallback provideCallback;
    List<HotelRespons> hotelResponslist = new ArrayList<>();
    List<HotelRespons> hotelResponslisttwo = new ArrayList<>();
    private HotelResponsAdapter hotelListAdapter;
    RecyclerView.LayoutManager layoutManager;
    TextView selectedtransports, selectedhotelrooms, selectedtours, totalcost, confirmedOperatorforLang, operatorName, confirmationDate, biddingAmount, massage;
    RecyclerView itineries, rooms, transports;
    RecyclerView ReturnedHotels;
    LinearLayout operatorListParent;
    Button confirm, edit, delete, showMap, selectOperator;
    ImageView destinationIamge;
    Context context;
    String id;
    TextView preview;
    ImageView leftTransport, rightTransport, leftAccommodation, rightAccommodation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("TAILOR_MADE_ID");
        context = this;
        TailorMadeDataHolder.makeRoutes(Integer.parseInt(id), (Activity) context);
        setContentView(R.layout.activity_tailor_made_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //start
        provideCallback = new ProvideCallback(this);
        hotelRespons = new ArrayList<>();
        ReturnedHotels = (RecyclerView) findViewById(R.id.returnedHotels);
        callback = new OperatorConfirmCallback(this);

       LatLong latLong = new LatLong();
       latLong.setLatitude("23.734243");
       latLong.setLongitude("90.378062");
       latLong.setDistrictname("pilkhana");
       BaseURL.districtsLocation.add(latLong);
        latLong.setLatitude("23.821647");
        latLong.setLongitude("90.459751");
        latLong.setDistrictname("Bashundhara");
        BaseURL.districtsLocation.add(latLong);
        latLong.setLatitude("23.719000");
        latLong.setLongitude("90.397760");
        latLong.setDistrictname("puran dhaka");
        BaseURL.districtsLocation.add(latLong);
        // end
//showMap
        preview = (TextView) findViewById(R.id.itenerarypreview);
        if (!BaseURL.LANGUAGE_ENG) {
            preview.setText("প্রিভিউ");
        }
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callpreview();
            }
        });

        itineries = (RecyclerView) findViewById(R.id.itineries);
        rooms = (RecyclerView) findViewById(R.id.rooms);
        transports = (RecyclerView) findViewById(R.id.transports);
        destinationIamge = (ImageView) findViewById(R.id.destinationIamge);
        selectedtours = (TextView) findViewById(R.id.selectedtours);
        selectedtransports = (TextView) findViewById(R.id.selectedtransports);
        selectedhotelrooms = (TextView) findViewById(R.id.selectedhotelrooms);
        totalcost = (TextView) findViewById(R.id.totalcost);
        confirmedOperatorforLang = (TextView) findViewById(R.id.confirmedOperatorforLang);
        operatorName = (TextView) findViewById(R.id.operatorName);
        confirmationDate = (TextView) findViewById(R.id.confirmationDate);
        biddingAmount = (TextView) findViewById(R.id.biddingAmount);
        leftAccommodation = (ImageView) findViewById(R.id.leftArrowHotel);
        leftTransport = (ImageView) findViewById(R.id.leftArrowTransport);
        rightTransport = (ImageView) findViewById(R.id.rightArrowTransport);
        rightAccommodation = (ImageView) findViewById(R.id.rightArrowHotel);
        operatorListParent = (LinearLayout) findViewById(R.id.operatorListParent);
        confirm = (Button) findViewById(R.id.confirm);
        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);
        showMap = (Button) findViewById(R.id.showMap);
        if (!BaseURL.LANGUAGE_ENG) {
            showMap.setText("ম্যাপ");
        }
        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLocationPermission()) {
                    startActivity(new Intent(TailorMadeActivity.this, MapsActivity2.class)
                            .putExtra("district_name", district_name));
                }
            }
        });
        selectOperator = (Button) findViewById(R.id.selectOperator);
        delete.setText("Delete Trip");
        if (TailorMadeDataHolder.Status.equalsIgnoreCase("confirm")) {
            edit.setVisibility(View.GONE);
            //delete.setVisibility(View.GONE);
            selectOperator.setEnabled(false);
            selectOperator.setText("Confirmed");
            BaseURL.operatorresponsTag=true;
        } else if (TailorMadeDataHolder.Status.equalsIgnoreCase("disable")) {
            selectOperator.setVisibility(View.GONE);
            edit.setVisibility(View.GONE);
            // delete.setVisibility(View.INVISIBLE);
            delete.setText("Disabled");
            delete.setEnabled(false);
            BaseURL.operatorresponsTag=false;
        }else {
            BaseURL.operatorresponsTag=false;
        }
        int version = Build.VERSION.SDK_INT;
        //Toast.makeText(context,version+"",Toast.LENGTH_SHORT).show();
        if (version <= 19) {
            selectOperator.setBackgroundColor(Color.parseColor("#32CD32"));
            edit.setBackgroundColor(Color.parseColor("#5F5DCB"));
            delete.setBackgroundColor(Color.parseColor("#B22222"));
            //  showMap.setBackgroundColor(Color.parseColor("#FF113355"));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(30, 30, 30, 30);
            selectOperator.setLayoutParams(params);
            edit.setLayoutParams(params);
            delete.setLayoutParams(params);
            //   showMap.setLayoutParams(params);
        }
        confirm.setOnClickListener(this);
        edit.setOnClickListener(this);
        delete.setOnClickListener(this);
        //   showMap.setOnClickListener(this);
        selectOperator.setOnClickListener(this);
        String confirmDate = "Confirmation Date";
        if (!BaseURL.LANGUAGE_ENG) {
            this.setTitle("ট্রিপ পরিকল্পনা বিস্তারিত");
            selectedtours.setText("নির্বাচিত ট্যুর সমূহ ");
            selectedtransports.setText("নির্বাচিত পরিবহন সমূহ");
            selectedhotelrooms.setText("নির্বাচিত হোটেল রুম সমূহ");
            selectOperator.setText("অপারেটর নির্বাচন করুন");
            edit.setText("সম্পাদনা করুন");
            delete.setText("ডিসেবল করুন");
            //  showMap.setText("মানচিত্রে দেখুন");
            confirmedOperatorforLang.setText("নিশ্চিত অপারেটর");
            confirmDate = "নিশ্চিতকরণ তারিখ";
            TailorMadeDataHolder.biddingAmount = BanglaNumberParser.getBangla(TailorMadeDataHolder.biddingAmount == null ? "0" : TailorMadeDataHolder.biddingAmount);
        } else this.setTitle("Trip Plan Details");

        if (TailorMadeDataHolder.providerName == null) {
            confirmedOperatorforLang.setVisibility(View.GONE);
            operatorListParent.setVisibility(View.GONE);

        } else {

            operatorName.setText(TailorMadeDataHolder.providerName);
            confirmationDate.setText(confirmDate + " " + TailorMadeDataHolder.confDate);
            biddingAmount.setText(TailorMadeDataHolder.biddingAmount + "৳");
            // biddingAmount.setText(TailorMadeDataHolder.biddingAmount+"৳")‎;
        }

        totalcost.setText("Total estimated amount : "+getIntent().getStringExtra("COST_TOTAL") + "৳");


        final String url = getIntent().getStringExtra("DESTINATION_IMAGE_PATH");
        district_name = getIntent().getStringExtra("DESTINATION_DISTRICT_NAME");

        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(destinationIamge);

        DynamicHeight.setHeight(this, destinationIamge, 4, 3);

        ProvideCallback provideCallback = new ProvideCallback(this);
        layoutManager = new LinearLayoutManager(this);
        ReturnedHotels.setLayoutManager(layoutManager);
        ReturnedHotels.setHasFixedSize(true);

        int mmm = Integer.parseInt(id);
        loadAccommodation(mmm);
//        hotelListAdapter= new HotelResponsAdapter(this,hotelRespons);
//        ReturnedHotels.setAdapter(hotelListAdapter);


        provideCallback.getTailorMadeItinerary(id).enqueue(new CustomCallBack<TailorMadeItinerary[]>(this) {
            @Override
            public void onResponse(Call<TailorMadeItinerary[]> call, Response<TailorMadeItinerary[]> response) {
                Log.e("Package Url", call.request().url().toString());
                super.onResponse(call, response);
                //Toast.makeText(TailorMadeActivity.this,response.body()[0].getItineraryPlaceName(),Toast.LENGTH_SHORT).show();
                if (response.body() != null && response.body().length > 0) {
                    itineries.setAdapter(new TailorMadeAccommodationAdapter(TailorMadeActivity.this, response.body()));
                    String selectedLlocalTour = "Selected Local Tour(" + response.body().length + ")";
                    if (!BaseURL.LANGUAGE_ENG) {
                        selectedLlocalTour = "নির্বাচিত ট্যুর সমূহ (" + BanglaNumberParser.getBangla(response.body().length + "") + ")";
                    }
                    selectedtours.setText(selectedLlocalTour);
                } else {
                    selectedtours.setVisibility(View.GONE);
                    // showMap.setEnabled(false);
                    showMap.setAlpha(.5f);
                }
            }

            @Override
            public void onFailure(Call<TailorMadeItinerary[]> call, Throwable t) {
                super.onFailure(call, t);
                String meesage = "Network Error";
                if (!BaseURL.LANGUAGE_ENG) {
                    meesage = " নেটওয়ার্ক ত্রুটি";
                }
                Toast.makeText(TailorMadeActivity.this, meesage, Toast.LENGTH_LONG).show();

            }
        });

        provideCallback.getTailorMadeAccommodation(id).enqueue(new Callback<TailorMadeAccommodation[]>() {
            @Override
            public void onResponse(Call<TailorMadeAccommodation[]> call, final Response<TailorMadeAccommodation[]> response) {
                //Toast.makeText(TailorMadeActivity.this,response.body()[0].getTailormadeAccommodationAccommodationName(),Toast.LENGTH_SHORT).show();
                Log.e("Package Url", call.request().url().toString());
                if (response.body() != null && response.body().length > 0) {

                    rooms.setAdapter(new TailorMadeAccommodationAdapter(TailorMadeActivity.this, response.body()));
                    String selectedAcc = "Selected Hotel Rooms(" + response.body().length + ")";

                    if (!BaseURL.LANGUAGE_ENG) {
                        selectedAcc = "নির্বাচিত হোটেল রুম সমূহ";
                    }

                    selectedhotelrooms.setText(selectedAcc);


                    rooms.post(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(mContext,holder.review.getLineCount()+"",Toast.LENGTH_SHORT).show();

                            LinearLayoutManager layoutManager = ((LinearLayoutManager) rooms.getLayoutManager());
                            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                            int lastVsiblePosition = layoutManager.findLastVisibleItemPosition();
                            //Toast.makeText(context,lastVsiblePosition + " " + tailorMadeTransport.size(),Toast.LENGTH_SHORT).show();
                            if (lastVsiblePosition == response.body().length - 1) {
                                leftTransport.setVisibility(View.GONE);
                                rightTransport.setVisibility(View.GONE);

                            }
                        }
                    });

                } else {
                    selectedhotelrooms.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TailorMadeAccommodation[]> call, Throwable t) {

            }
        });
        provideCallback.getTailorMadeTransport(id).enqueue(new Callback<TailorMadeTransport[]>() {
            @Override
            public void onResponse(Call<TailorMadeTransport[]> call, final Response<TailorMadeTransport[]> response) {
                Log.e("Package Url", call.request().url().toString());
                final List<TailorMadeTransport> tailorMadeTransport = new ArrayList<TailorMadeTransport>();
                if (response.body() != null && response.body().length > 0) {
                    for (int i = 0; i < response.body().length; i++) {
                        if (response.body()[i].getTailormadeRouteTransportInfoOperatorName() != null) {
                            tailorMadeTransport.add(response.body()[i]);
                        }
                    }
                    if (tailorMadeTransport.size() == 0) {
                        selectedtransports.setVisibility(View.GONE);
                    }
                    transports.setAdapter(new TailorMadeTransportAdapter(TailorMadeActivity.this, tailorMadeTransport));
                    String selectedTransports = "Selected Transports(" + tailorMadeTransport.size() + ")";
                    if (!BaseURL.LANGUAGE_ENG) {
                        selectedTransports = "নির্বাচিত পরিবহন সমূহ(" + BanglaNumberParser.getBangla(tailorMadeTransport.size() + "") + ")";
                    }
                    selectedtransports.setText(selectedTransports);
                    transports.post(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(mContext,holder.review.getLineCount()+"",Toast.LENGTH_SHORT).show();
                            LinearLayoutManager layoutManager = ((LinearLayoutManager) transports.getLayoutManager());
                            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                            int lastVsiblePosition = layoutManager.findLastVisibleItemPosition();
                            //Toast.makeText(context,lastVsiblePosition + " " + tailorMadeTransport.size(),Toast.LENGTH_SHORT).show();
                            if (lastVsiblePosition == tailorMadeTransport.size() - 1) {
                                leftTransport.setVisibility(View.GONE);
                                rightTransport.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                //Toast.makeText(TailorMadeActivity.this,response.body()[0].getTailormadeRouteTransportInfoOperatorName(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<TailorMadeTransport[]> call, Throwable t) {
            }
        });
        //ButterKnife.bind(this);
//        routeSelectionFragment=new RouteSelectionFragment();
//        StepperAdapter stepperAdapter=new StepperAdapter(getSupportFragmentManager(),this);
//        stepperLayout.setAdapter(stepperAdapter);
    }

    private void callpreview() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.itinerary_preview_tailormade);
        LinearLayout hoteltwo, hotelthree;
        TextView children;
        children = (TextView) dialog.findViewById(R.id.children);
        // children.setText(PreviewData.noOfchild + "(" + PreviewData.avgage + ")");
        children.setText(PreviewData.noOfchild);
        Button cancle;
        cancle = (Button) dialog.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        hoteltwo = (LinearLayout) dialog.findViewById(R.id.hoteltwo);
        hotelthree = (LinearLayout) dialog.findViewById(R.id.hotelthree);
        Button btnchooseoperator;
        btnchooseoperator = (Button) dialog.findViewById(R.id.btnchooseoperator);
        if (BaseURL.operatorresponsTag){
            btnchooseoperator.setText("Confirmed");
            btnchooseoperator.setEnabled(false);
        }
        btnchooseoperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TOurOperatorSelectionActivity.class));
            }
        });
        LinearLayout llSecondTransport = (LinearLayout) dialog.findViewById(R.id.llSecondTransport);
        hotelname2 = (TextView) dialog.findViewById(R.id.hotelName2);
        hotelcost2 = (TextView) dialog.findViewById(R.id.hotelcost2);
        checkindatetv2 = (TextView) dialog.findViewById(R.id.checkinDatep2);
        checkoutdatetv2 = (TextView) dialog.findViewById(R.id.CheckoutDate2);
        hotelname3 = (TextView) dialog.findViewById(R.id.hotelName3);
        hotelcost3 = (TextView) dialog.findViewById(R.id.hotelcost3);
        checkindatetv3 = (TextView) dialog.findViewById(R.id.checkinDatep3);
        checkoutdatetv3 = (TextView) dialog.findViewById(R.id.CheckoutDate3);

        itineraryy = (TextView) dialog.findViewById(R.id.itineraryy);
        fromtv = (TextView) dialog.findViewById(R.id.tvform);
        startdatetv = (TextView) dialog.findViewById(R.id.departuredate);
        returntv = (TextView) dialog.findViewById(R.id.returnDate);
        torriststv = (TextView) dialog.findViewById(R.id.torist);
        daystv = (TextView) dialog.findViewById(R.id.days);
        hotelname = (TextView) dialog.findViewById(R.id.hotelName);
        hotelcost = (TextView) dialog.findViewById(R.id.hotelcost);
        checkindatetv = (TextView) dialog.findViewById(R.id.checkinDatep);
        checkoutdatetv = (TextView) dialog.findViewById(R.id.CheckoutDate);
        transport = (TextView) dialog.findViewById(R.id.transporttv);
        transport2 = (TextView) dialog.findViewById(R.id.transporttv2);
        transportrout = (TextView) dialog.findViewById(R.id.tvformtransport);
        transportrouttwo = (TextView) dialog.findViewById(R.id.tvformtransporttwo);
        transportcost = (TextView) dialog.findViewById(R.id.transportCosttv);
        itinerarycost = (TextView) dialog.findViewById(R.id.itinerarycost);
        itinarydatetv = (TextView) dialog.findViewById(R.id.itinarydate);
        itinery = (TextView) dialog.findViewById(R.id.itinery);
        itinarydatetv2 = (TextView) dialog.findViewById(R.id.itinarydate2);
        itinery2 = (TextView) dialog.findViewById(R.id.itinery2);
        itinarydatetv3 = (TextView) dialog.findViewById(R.id.itinarydate3);
        itinery3 = (TextView) dialog.findViewById(R.id.itinery3);


        TextView tvchildrenn = (TextView) dialog.findViewById(R.id.tvchildrenn);
        TextView tvdays = (TextView) dialog.findViewById(R.id.tvdays);
        TextView tvreturndate = (TextView) dialog.findViewById(R.id.tvreturndate);
        TextView tvtorist = (TextView) dialog.findViewById(R.id.tvtorist);
        TextView tvtranportname = (TextView) dialog.findViewById(R.id.tvtranportname);
        TextView tvtranportname1 = (TextView) dialog.findViewById(R.id.tvtranportname1);
        TextView tvstartdatee = (TextView) dialog.findViewById(R.id.tvstartdatee);
        TextView tvtransportinfo = (TextView) dialog.findViewById(R.id.tvtransportinfo);
        TextView tvhotelinfo = (TextView) dialog.findViewById(R.id.tvhotelinfo);
        TextView tvhotelone = (TextView) dialog.findViewById(R.id.tvhotelone);
        TextView tvhotelonecheckin = (TextView) dialog.findViewById(R.id.tvhotelonecheckin);
        TextView tvhotelonecheckoutdate = (TextView) dialog.findViewById(R.id.tvhotelonecheckoutdate);
        TextView tvhoteltwo = (TextView) dialog.findViewById(R.id.tvhoteltwo);
        TextView tvhoteltwocheckin = (TextView) dialog.findViewById(R.id.tvhoteltwocheckin);
        TextView tvhotelcheckoudatetwo = (TextView) dialog.findViewById(R.id.tvhotelcheckoudatetwo);


        TextView tvhotelthree = (TextView) dialog.findViewById(R.id.tvhotelthree);
        TextView tvcheckinthree = (TextView) dialog.findViewById(R.id.tvcheckinthree);
        TextView tvcheckoutthree = (TextView) dialog.findViewById(R.id.tvcheckoutthree);
        TextView tvnearby = (TextView) dialog.findViewById(R.id.tvnearby);
        TextView tvchilddetails = (TextView) dialog.findViewById(R.id.tvchilddetails);
        TextView childrendetails = (TextView) dialog.findViewById(R.id.childrendetails);

        TextView editPlan = (TextView) dialog.findViewById(R.id.editPlan);
        LinearLayout llitineraryOne = (LinearLayout) dialog.findViewById(R.id.llitineraryOne);
        LinearLayout llitineraryTwo = (LinearLayout) dialog.findViewById(R.id.llitineraryTwo);
        LinearLayout llitineraryThree = (LinearLayout) dialog.findViewById(R.id.llitineraryThree);
        LinearLayout llhotelOne = (LinearLayout) dialog.findViewById(R.id.llhotelOne);


        LinearLayout llhotelFour = (LinearLayout) dialog.findViewById(R.id.llhotelFour);
        TextView hotelName4 = (TextView) dialog.findViewById(R.id.hotelName4);
        TextView tvcheckinFour = (TextView) dialog.findViewById(R.id.tvcheckinFour);
        TextView checkinDatep4 = (TextView) dialog.findViewById(R.id.checkinDatep4);
        TextView tvcheckoutFour = (TextView) dialog.findViewById(R.id.tvcheckoutFour);
        TextView CheckoutDate4 = (TextView) dialog.findViewById(R.id.CheckoutDate4);

        childrendetails.setText(TailorMadeDataHolder.childrenDesc);

        if (!BaseURL.LANGUAGE_ENG) {
            tvchilddetails.setText("শিশুর বয়স : ");
            tvchildrenn.setText("শিশুর সংখ্যা : ");
            tvdays.setText("দিন সংখ্যা : ");
            tvreturndate.setText("ফিরে আসার তারিখ : ");
            tvtorist.setText("পর্যটক সংখ্যা : ");
            tvtranportname.setText("ট্রান্সপোর্ট নাম :");
            tvtranportname1.setText("ট্রান্সপোর্ট নাম :");
            tvstartdatee.setText("শুরুর তারিখ :");
            tvtransportinfo.setText("ট্রান্সপোর্ট ইনফো  :");
            tvhotelinfo.setText("হোটেল ইনফো :");
            tvhotelone.setText("হোটেল : ");
            tvhotelthree.setText("হোটেল : ");
            tvhotelonecheckin.setText("চেক ইন তারিখ : ");
            tvcheckinFour.setText("চেক ইন তারিখ : ");
            tvcheckinthree.setText("চেক ইন তারিখ : ");
            tvhotelonecheckoutdate.setText("চেক আউট তারিখ : ");
            tvcheckoutFour.setText("চেক আউট তারিখ : ");
            tvhoteltwo.setText("হোটেল : ");
            tvhoteltwocheckin.setText("চেক ইন তারিখ : ");
            tvhotelcheckoudatetwo.setText("চেক আউট তারিখ : ");
            tvcheckoutthree.setText("চেক আউট তারিখ : ");
            tvnearby.setText("আশেপাশের এলাকা  : ");

        }


        if (BaseURL.dates.size() == 1) {
            llhotelOne.setVisibility(View.VISIBLE);
            hotelname.setText(BaseURL.dates.get(0).getHotelName());
            checkindatetv.setText(BaseURL.dates.get(0).getCheckindate());
            checkoutdatetv.setText(BaseURL.dates.get(0).getCheckoutDate());
        }
        if (BaseURL.dates.size() == 2) {
            hoteltwo.setVisibility(View.VISIBLE);
            llhotelOne.setVisibility(View.VISIBLE);
            hotelname.setText(BaseURL.dates.get(0).getHotelName());
            checkindatetv.setText(BaseURL.dates.get(0).getCheckindate());
            checkoutdatetv.setText(BaseURL.dates.get(0).getCheckoutDate());
            hotelname2.setText(BaseURL.dates.get(1).getHotelName());
            checkindatetv2.setText(BaseURL.dates.get(1).getCheckindate());
            checkoutdatetv2.setText(BaseURL.dates.get(1).getCheckoutDate());
        }
        if (BaseURL.dates.size() == 3) {
            llhotelOne.setVisibility(View.VISIBLE);

            hotelthree.setVisibility(View.VISIBLE);
            hoteltwo.setVisibility(View.VISIBLE);
            hotelname.setText(BaseURL.dates.get(0).getHotelName());
            checkindatetv.setText(BaseURL.dates.get(0).getCheckindate());
            checkoutdatetv.setText(BaseURL.dates.get(0).getCheckoutDate());


            hotelname2.setText(BaseURL.dates.get(1).getHotelName());
            checkindatetv2.setText(BaseURL.dates.get(1).getCheckindate());
            checkoutdatetv2.setText(BaseURL.dates.get(1).getCheckoutDate());


            hotelname3.setText(BaseURL.dates.get(2).getHotelName());
            checkindatetv3.setText(BaseURL.dates.get(2).getCheckindate());
            checkoutdatetv3.setText(BaseURL.dates.get(2).getCheckoutDate());
        }

        if (BaseURL.dates.size() > 3) {
            llhotelOne.setVisibility(View.VISIBLE);
            llhotelFour.setVisibility(View.VISIBLE);
            hotelthree.setVisibility(View.VISIBLE);
            hoteltwo.setVisibility(View.VISIBLE);
            hotelname.setText(BaseURL.dates.get(0).getHotelName());
            checkindatetv.setText(BaseURL.dates.get(0).getCheckindate());
            checkoutdatetv.setText(BaseURL.dates.get(0).getCheckoutDate());


            hotelname2.setText(BaseURL.dates.get(1).getHotelName());
            checkindatetv2.setText(BaseURL.dates.get(1).getCheckindate());
            checkoutdatetv2.setText(BaseURL.dates.get(1).getCheckoutDate());


            hotelname3.setText(BaseURL.dates.get(2).getHotelName());
            checkindatetv3.setText(BaseURL.dates.get(2).getCheckindate());
            checkoutdatetv3.setText(BaseURL.dates.get(2).getCheckoutDate());

            hotelName4.setText(BaseURL.dates.get(3).getHotelName());
            checkinDatep4.setText(BaseURL.dates.get(3).getCheckindate());
            CheckoutDate4.setText(BaseURL.dates.get(3).getCheckoutDate());

        }




        if (BaseURL.itinaryItemtailormade.size() ==1) {
            llitineraryOne.setVisibility(View.VISIBLE);
            itinery.setText(BaseURL.itinaryItemtailormade.get(0).getProvidername());
            itinarydatetv.setText("Date :"+BaseURL.itinaryItemtailormade.get(0).getDate());
        }else if (BaseURL.itinaryItemtailormade.size() ==2) {
            llitineraryOne.setVisibility(View.VISIBLE);
            llitineraryTwo.setVisibility(View.VISIBLE);

            itinery.setText(BaseURL.itinaryItemtailormade.get(0).getProvidername());
            itinarydatetv.setText("Date :"+BaseURL.itinaryItemtailormade.get(0).getDate());
            itinery2.setText(BaseURL.itinaryItemtailormade.get(1).getProvidername());
            itinarydatetv2.setText("Date :"+BaseURL.itinaryItemtailormade.get(1).getDate());
        }
      else   if (BaseURL.itinaryItemtailormade.size() > 2) {

            llitineraryOne.setVisibility(View.VISIBLE);
            llitineraryTwo.setVisibility(View.VISIBLE);
            llitineraryThree.setVisibility(View.VISIBLE);
            itinery.setText(BaseURL.itinaryItemtailormade.get(0).getProvidername());
            itinarydatetv.setText("Date :"+BaseURL.itinaryItemtailormade.get(0).getDate());
            itinery2.setText(BaseURL.itinaryItemtailormade.get(1).getProvidername());
            itinarydatetv2.setText("Date :"+BaseURL.itinaryItemtailormade.get(1).getDate());
            itinery3.setText(BaseURL.itinaryItemtailormade.get(2).getProvidername());
            itinarydatetv3.setText("Date :"+BaseURL.itinaryItemtailormade.get(2).getDate());

        }
        itinerarycost.setText(PreviewData.itinererycost);
//        itinery.setText(BaseURL.itinarydate + "");
//        itinery.setText(BaseURL.itinarydate + "");
        //  hotelname.setText(PreviewData.hotelName+ "  " +PreviewData.hotelNameTwo);
        transportcost.setText(PreviewData.transportcost);
        transportrout.setText(PreviewData.fromRoute + " - " + PreviewData.toRoute);
        //
        if (TailorMadeDataHolder.routes.size()>1) {
            llSecondTransport.setVisibility(View.VISIBLE);
        }else {
            llSecondTransport.setVisibility(View.GONE);

        }
        editPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TailorMadeDataHolder.startActivity((Activity) context);
            }
        });

        for (int i = 0; i < TailorMadeDataHolder.routes.size(); i++) {
            if (i == 0) {
                transportrout.setText(TailorMadeDataHolder.routes.get(i).getStartDistrictName() + " - " + TailorMadeDataHolder.routes.get(i).getEndDistrictName());

                BaseURL.routess.add(TailorMadeDataHolder.routes.get(i).getStartDistrictName());
                BaseURL.routess.add(TailorMadeDataHolder.routes.get(i).getEndDistrictName());
            }
            if (i == 1) {
                transportrouttwo.setText(TailorMadeDataHolder.routes.get(i).getStartDistrictName() + " - " + TailorMadeDataHolder.routes.get(i).getEndDistrictName());
                BaseURL.routess.add(TailorMadeDataHolder.routes.get(i).getEndDistrictName());
            }
        }
        for (int i = 0; i < BaseURL.routess.size(); i++) {
            if (i == 0) {
                fromtv.setText(BaseURL.routess.get(0));
            }
            if (i == 1) {

                fromtv.setText(BaseURL.routess.get(0) + " - " + BaseURL.routess.get(1));
            }
            if (i == 2) {

                fromtv.setText(BaseURL.routess.get(0) + " - " + BaseURL.routess.get(1) + " - " + BaseURL.routess.get(2));
            }
            if (i > 2) {

                fromtv.setText(BaseURL.routess.get(0) + " - " + BaseURL.routess.get(1) + " - " + BaseURL.routess.get(2) + " - " + BaseURL.routess.get(3));


            }

        }


        //
//        fromtv.setText();
        //Toast.makeText(context, ""+BaseURL.transportname.size(), Toast.LENGTH_SHORT).show();
        if (BaseURL.transportname.size() == 1) {
            transport.setText(BaseURL.transportname.get(0));
        }
        //  Toast.makeText(TailorMadeActivity.this, ""+BaseURL.dates.size(), Toast.LENGTH_SHORT).show();
        if (BaseURL.transportname.size() > 1) {
            transport.setText(BaseURL.transportname.get(0));
            transport2.setText(BaseURL.transportname.get(1));
        }

        //    fromtv.setText(PreviewData.fromRoute+" - "+PreviewData.toRoute);
        startdatetv.setText(PreviewData.stratDate);
        returntv.setText(PreviewData.returnDate);
        torriststv.setText(PreviewData.torrists);
        daystv.setText(PreviewData.days);


        hotelcost.setText(PreviewData.hotelcost);
//        checkindatetv.setText(CustomTripPlanDataHolder.checkindatee);
//        checkoutdatetv.setText(CustomTripPlanDataHolder.checkoutdatee);


        if (BaseURL.isEdit) {
            transportrout.setText(TailorMadeDataHolder.providerName);
        }
//        if (BaseURL.isEdit) {
//            startdatetv.setText(TailorMadeDataHolder.departDate);
//            returntv.setText(TailorMadeDataHolder.returnDate);
//            torriststv.setText(TailorMadeDataHolder.noOfTourists);
//            daystv.setText(TailorMadeDataHolder.noOfDays);
//            transportrout.setText(TailorMadeDataHolder.providerName);
//            fromtv.setText(TailorMadeDataHolder.ROUTE);
//            transportrout.setText(TailorMadeDataHolder.ROUTE);
//            checkindatetv.setText(TailorMadeDataHolder.checkin_date);
//            checkoutdatetv.setText(TailorMadeDataHolder.checkout_date);
//        }
        // fromtv,totv,startdatetv,returntv,torriststv,daystv,childrentv,avgagetv,checkindatetv,checkoutdatetv;
        dialog.show();
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TailorMadeActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return false;

        } else {
            return true;
        }
    }

    private void submit() {
        final ConfirmOperator confirmOperator = new ConfirmOperator();
        confirmOperator.settailormade_customer_id("sazzad");
        confirmOperator.settailormade_id("rocky");
        confirmOperator.settailormade_operator_id("arafat");
//start
        callback.confirmOperator(confirmOperator).enqueue(new Callback<String>() {
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

                    //Toast.makeText(HotelRoomListActivity.this,response.body(),Toast.LENGTH_LONG).show();
                } else {
                    String meesage = "Something Went Wrong";
                    if (!BaseURL.LANGUAGE_ENG) {
                        meesage = " কিছু ভুল হয়েছে";
                    }
                    Toast.makeText(TailorMadeActivity.this, meesage, Toast.LENGTH_LONG).show();
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
                Toast.makeText(TailorMadeActivity.this, meesage, Toast.LENGTH_LONG).show();

            }
        });


    }

    private void loadAccommodation(int id) {
        provideCallback.gethotelrespons(id).enqueue(new CustomCallBack<HotelRespons[]>(this) {
                                                        @Override
                                                        public void onResponse(Call<HotelRespons[]> call, Response<HotelRespons[]> response) {
                                                            super.onResponse(call, response);
                                                            Log.e("Package Url", call.request().url().toString());
                                                            if (response.body() != null && response.body().length > 0) {
                                                                hotelResponslist = Arrays.asList(response.body());

//                                                                                 for (int i=0;i<response.body().length;i++){
//
//                                                                                   if (hotelResponslist.get(i).gettailormade_operator_id()=="27"){
//                                                                                       hotelResponslisttwo=hotelResponslist.get(i);
//                                                                                   }
//
//                                                                                 }

                                                                layoutManager = new LinearLayoutManager(TailorMadeActivity.this);
                                                                ReturnedHotels.setLayoutManager(layoutManager);
                                                                ReturnedHotels.setHasFixedSize(true);

                                                                hotelListAdapter = new HotelResponsAdapter(TailorMadeActivity.this, hotelResponslist);
                                                                ReturnedHotels.setAdapter(hotelListAdapter);

                                                                // ReturnedHotels.setAdapter(hotelListAdapter);
                                                            } else {
//                                                                                    String meesage ="No Operator Response";
//                                                                                    if (!BaseURL.LANGUAGE_ENG)
//                                                                                    {
//                                                                                        meesage ="কোন অপারেটরের সাড়া আসে নি";
//
//                                                                                    }
//                                                                                    Toast.makeText(TailorMadeActivity.this,meesage,Toast.LENGTH_LONG).show();


                                                            }

                                                        }

                                                        @Override
                                                        public void onFailure(Call<HotelRespons[]> call, Throwable t) {
                                                            String meesage = "Network Error";
                                                            if (!BaseURL.LANGUAGE_ENG) {
                                                                meesage = " নেটওয়ার্ক ত্রুটি";

                                                            }
                                                            Toast.makeText(TailorMadeActivity.this, meesage, Toast.LENGTH_LONG).show();
                                                        }
                                                    }
        );

        View view = TailorMadeActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    void confirmTalorMade() {
        ProvideCallback provideCallback = new ProvideCallback(this);
        provideCallback.confirmTailorMade(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("Package Url", call.request().url().toString());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    void deleteTailorMade() {
        ProvideCallback provideCallback = new ProvideCallback(this);
        provideCallback.disableTailorMade(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("Package Url", call.request().url().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        ProvideCallback provideCallback = new ProvideCallback(this);
        switch (v.getId()) {
            case R.id.confirm:
                String title = "Confirmation";
                String message = "Do You Want to Confirm your Tailormade?";
                String yes = "Yes";
                String no = "No";

                if (!BaseURL.LANGUAGE_ENG) {
                    title = "অনুমোদন";
                    message = "আপনি আপনার টেইলর মেডটি  সংরক্ষণ করতে চান?";
                    yes = "হ্যা";
                    no = "না";
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirmTalorMade();
                        finish();
                    }
                });
                dialog.setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Intent intent=new Intent(ItineraryPlanner.this,MainActivity.class);
                        //startActivity(intent);
                    }
                });
                dialog.setMessage(message);
                dialog.setTitle(title);
                dialog.show();

                break;
            case R.id.edit:
                //Toast.makeText(context,"Edit",Toast.LENGTH_SHORT).show();

//                TailorMadeDataHolder.noOfDays = tailorMade.getTailormadeDays();
//                TailorMadeDataHolder.noOfTourists = tailorMade.getTailormadePerson();
//                TailorMadeDataHolder.departDate = tailorMade.getTailormadeFromDate();
//                TailorMadeDataHolder.returnDate = tailorMade.getTailormadeToDate();
                //TailorMadeDataHolder.makeRoutes(Integer.parseInt(id),(Activity) context);
                TailorMadeDataHolder.startActivity((Activity) context);
                break;
            case R.id.delete:

                title = "Confirmation";
                message = "Do You Want to Disable your Tailormade?";
                yes = "Yes";
                no = "No";

                if (!BaseURL.LANGUAGE_ENG) {
                    title = "অনুমোদন";
                    message = "আপনি আপনার টেইলর মেডটি অপসারণ করতে চান?";
                    yes = "হ্যা";
                    no = "না";
                }
                dialog = new AlertDialog.Builder(this);
                dialog.setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTailorMade();
                        finish();
                    }
                });
                dialog.setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Intent intent=new Intent(ItineraryPlanner.this,MainActivity.class);
                        //startActivity(intent);
                    }
                });
                dialog.setMessage(message);
                dialog.setTitle(title);
                dialog.show();

                break;


            case R.id.selectOperator:
                Intent intent = new Intent(context, MapsActivity.class);
                //Toast.makeText(context,"Select Operator",Toast.LENGTH_SHORT).show();
                intent = new Intent(context, TOurOperatorSelectionActivity.class);
                startActivity(intent);
                break;
        }

    }


}
