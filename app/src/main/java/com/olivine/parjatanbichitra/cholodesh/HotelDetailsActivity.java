/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.InclusionAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import callbacks.ProvideCallback;
import constants.Travel;
import helpers.BaseURL;
import model.AccommodationProvider;
import model.HotelDetails;
import model.HotelGallery;
import model.HotelInclusion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import helpers.CustomCallBack;

import static adapters.HotelListAdapter.SERVICE_ID;

public class HotelDetailsActivity extends AppCompatActivity {
    List<AccommodationProvider> accommodationProviders=new ArrayList<>();
    private static final int ROOM_REQUEST = 120;
    private static final int NEXT_MENU =2 ;
    private static final int PREVIOUS_MENU =1 ;
    private int currentServiceId;
    private int currentSerialNo=0;
    @BindView(R.id.facilitiesTitle) TextView facilitiesTitle;
    @BindView(R.id.mDemoSlider) SliderLayout mDemoSlider;
    @BindView(R.id.inclusions) RecyclerView inclusions;
    @BindView(R.id.providerName) TextView providerName;

    @BindView(R.id.details) TextView showMore;
    @BindView(R.id.addinfoforlang) TextView addinfoforlang;


    @BindView(R.id.hotelAddress) TextView hotelAddress;
    @BindView(R.id.hotelEmail) TextView hotelEmail;
    @BindView(R.id.hotelHotLine) TextView hotelHotLine;
    @BindView(R.id.hotelDetails) TextView hotelDetailsView;
    @BindView(R.id.hotelRating) RatingBar hotelRating;
    @BindView(R.id.hoteldescforlang) TextView hoteldescforlang;

    private ProvideCallback provideCallback;
    private SharedPreferences sharedPreferences;
    private float hotelGotRating;
    String noOfRating = "0";
    Context context;

    @BindView(R.id.review) Button review;
    @BindView(R.id.viewRooms) Button viewRooms;
    String name;

    @OnClick(R.id.review)
    public void showReview(View view){
//        view Roooms of selected hotel
        int serviceId=getIntent().getIntExtra(SERVICE_ID,0);
        Intent intent=new Intent(HotelDetailsActivity.this,RatingActivity.class);
        intent.putExtra(SERVICE_ID,serviceId);
        intent.putExtra("AVG_RATING",hotelGotRating);
        intent.putExtra("NO_OF_RATING",noOfRating);
        startActivity(intent);
        //Toast.makeText(this,"Working",Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.viewRooms)
    public void viewRooms(View view){
//        view Roooms of selected hotel
        int serviceId=getIntent().getIntExtra(SERVICE_ID,0);
        Intent intent=new Intent(HotelDetailsActivity.this,HotelRoomListActivity.class);
        intent.putExtra(SERVICE_ID,serviceId);
        intent.putExtra("NAME",getIntent().getStringExtra("NAME"));
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ButterKnife.bind(this);
        context = getApplicationContext();

        if (!BaseURL.LANGUAGE_ENG)
        {
            this.setTitle("হোটেল বিস্তারিত");
            facilitiesTitle.setText("সুবিধাসমূহ ");
            hoteldescforlang.setText("হোটেল বিস্তারিত");
            addinfoforlang.setText("ঠিকানা");
            review.setText("রিভিউ সমূহ");
            viewRooms.setText("রুম সমূহ");
            showMore.setText("বিস্তারিত");
            providerName.setText("লোডিং");
            hotelDetailsView.setText("লোডিং");

        }

        else {

            this.setTitle("Hotel Details");
            addinfoforlang.setText("Address");
        }
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_key),MODE_PRIVATE);
        currentServiceId=getIntent().getIntExtra(SERVICE_ID,0);
        provideCallback=new ProvideCallback(this);
        loadHotelDetailsInfo(currentServiceId);
        // get All accommodation providers which is used for loading next accommodation
        getAllProviders(currentServiceId);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollaspe();

            }
        });
        hotelDetailsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollaspe();

            }
        });
    }

    private void expandCollaspe ()
    {


        ViewGroup.LayoutParams params = hotelDetailsView.getLayoutParams();
        if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT)
        {
            final float scale = getResources().getDisplayMetrics().density;
            int pixels = (int) (60 * scale + 0.5f);
            params.height = pixels;
            String showmore = "Show More";
            if (!BaseURL.LANGUAGE_ENG){
                showmore = "বিস্তারিত";
            }
            showMore.setText(showmore);
        }
        else
        {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            String showless = "Show Less";
            if (!BaseURL.LANGUAGE_ENG){
                showless = "সংক্ষিপ্ত";
            }
            showMore.setText(showless);

        }

        //description.setLayoutParams(params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem previous=menu.add(Menu.NONE,PREVIOUS_MENU,Menu.NONE,"<");
        previous.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        previous.setIcon(R.drawable.ic_left);
        MenuItem next=menu.add(Menu.NONE,NEXT_MENU,Menu.NONE,">");
        next.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        next.setIcon(R.drawable.ic_right);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;

            case PREVIOUS_MENU:
                // load prevous hotel
                currentSerialNo = BaseURL.getCurrentHotelPositionInArray(currentServiceId);
                if(currentSerialNo>0){
                    currentSerialNo--;
                    currentServiceId=BaseURL.hotelServiceIds.get(currentSerialNo);


                    //Toast.makeText(mContext,accommodationProvider.getProviderName(),Toast.LENGTH_SHORT).show();
                    loadHotelDetailsInfo(currentServiceId);
                    getIntent().putExtra(SERVICE_ID,currentServiceId);
                    getIntent().putExtra("NAME",name);
                }
                break;
            case NEXT_MENU:
                // load prevous hotel
                currentSerialNo = BaseURL.getCurrentHotelPositionInArray(currentServiceId);
                if(currentSerialNo<BaseURL.hotelServiceIds.size()-1){
                    currentSerialNo++;
                    currentServiceId=BaseURL.hotelServiceIds.get(currentSerialNo);
                    loadHotelDetailsInfo(currentServiceId);
                    getIntent().putExtra(SERVICE_ID,currentServiceId);
                    getIntent().putExtra("NAME",name);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadHotelDetailsInfo(int serviceId){
        provideCallback.getHotelGalley(serviceId).enqueue(new CustomCallBack<HotelGallery[]>(this) {
            @Override
            public void onResponse(Call<HotelGallery[]> call, Response<HotelGallery[]> response) {
                super.onResponse(call, response);
                Log.e("Package Url",call.request().url().toString());
                if(response.body() != null && response.body().length > 0)
                {
                    loadGalleryImage(response.body());
                }
                else
                {
                    Toast.makeText(HotelDetailsActivity.this,"No Image Found for this hotel",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<HotelGallery[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(HotelDetailsActivity.this,meesage,Toast.LENGTH_LONG).show();

            }
        });
        provideCallback.getHotelDetails(serviceId).enqueue(new Callback<HotelDetails>() {
            @Override
            public void onResponse(Call<HotelDetails> call, Response<HotelDetails> response) {
                Log.e("Package Url",call.request().url().toString());
                if(response.body()!=null){
                    loadHotelDetails(response.body());
                }
                else
                {
                    String meesage ="No Details Found";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage =" কোন বিস্তারিত পাওয়া যায়নি";

                    }
                    Toast.makeText(HotelDetailsActivity.this,meesage,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<HotelDetails> call, Throwable t) {

                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(HotelDetailsActivity.this,meesage,Toast.LENGTH_LONG).show();

            }
        });
        provideCallback.getHotelInclusions(serviceId).enqueue(new Callback<HotelInclusion[]>() {
            @Override
            public void onResponse(Call<HotelInclusion[]> call, Response<HotelInclusion[]> response) {
                Log.e("Package Url",call.request().url().toString());
                if(response.body() != null && response.body().length==0){
                    if (!BaseURL.LANGUAGE_ENG) {

                        facilitiesTitle.setText("কোন সুবিধা নেই ");
                    }
                    else facilitiesTitle.setText("No Facilities");
                }
                InclusionAdapter inclusionAdapter=new InclusionAdapter(context,response.body());
                inclusions.setAdapter(inclusionAdapter);
            }

            @Override
            public void onFailure(Call<HotelInclusion[]> call, Throwable t) {

            }
        });


    }
    private void getAllProviders(int serviceId){
        String districtId = sharedPreferences.getInt(Travel.TO_LOCATION, 26) + "";
        provideCallback.getDestinationWiseAccommodationList(Integer.parseInt(districtId)).enqueue(new Callback<AccommodationProvider[]>() {
            @Override
            public void onResponse(Call<AccommodationProvider[]> call, Response<AccommodationProvider[]> response) {
                if (response.body() != null)
                {
                    accommodationProviders= Arrays.asList(response.body());
                    // Getting Current hotel position in list
                    for (int i=0;i<accommodationProviders.size();i++){
                        AccommodationProvider tmp_provoder=accommodationProviders.get(i);
                        if(tmp_provoder.getAccommodationServiceId()==currentServiceId){
                            currentSerialNo=i;
                            break;
                        }
                     }
                    }
                }


            @Override
            public void onFailure(Call<AccommodationProvider[]> call, Throwable t) {

            }
        });
    }


    private void loadGalleryImage(HotelGallery [] hotelgalleries){
        mDemoSlider.removeAllSliders();
        for(HotelGallery hotelgallery : hotelgalleries){
            String name=hotelgallery.getProviderName();
            this.name = hotelgallery.getProviderName();
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                   // .description(name) // image description
                    .image(BaseURL.HOTEL_IMAGE_BASE_URL+hotelgallery.getAccommodationGalleryImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateDown);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
    }

    private void loadHotelDetails(final HotelDetails hotelDetails){
        //getSupportActionBar().setTitle(hotelDetails.getProviderName());
        //hotelGotRating = Float.parseFloat(hotelDetails.getRatingAverage());
        //noOfRating = hotelDetails.getRatingCount();
        if (hotelDetails.getRatingCount()!= null)
        {
            hotelGotRating = Float.parseFloat(hotelDetails.getRatingAverage());
            noOfRating = hotelDetails.getRatingCount();
        }
        //Toast.makeText(this,hotelDetails.getAccommodationServiceId()+"",Toast.LENGTH_SHORT).show();
        if (!BaseURL.LANGUAGE_ENG && hotelDetails.getProviderNameBn() != null)
        {
            providerName.setText(hotelDetails.getProviderNameBn());

        }
        else {
            providerName.setText(hotelDetails.getProviderName());


        }

        if (!BaseURL.LANGUAGE_ENG && hotelDetails.getServiceAddressBn() != null)
        {
            hotelAddress.setText(hotelDetails.getServiceAddressBn());
        }
        else
        {
            hotelAddress.setText(hotelDetails.getAccommodationServiceAddress());
        }

       hotelEmail.setText(hotelDetails.getAccommodationServiceEmail());
          hotelHotLine.setText(hotelDetails.getAccommodationServiceHotline());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if (!BaseURL.LANGUAGE_ENG && hotelDetails.getAccommodationServiceDetailsBn() != null)
            {
                hotelDetailsView.setText(Html.fromHtml(hotelDetails.getAccommodationServiceDetailsBn(),Html.FROM_HTML_MODE_LEGACY));
            }
            else hotelDetailsView.setText(Html.fromHtml(hotelDetails.getAccommodationServiceDetails(),Html.FROM_HTML_MODE_LEGACY));
        }
        else
        {
            if (!BaseURL.LANGUAGE_ENG && hotelDetails.getAccommodationServiceDetailsBn() != null)
            {
                hotelDetailsView.setText(Html.fromHtml(hotelDetails.getAccommodationServiceDetailsBn()));
            }
            else
                hotelDetailsView.setText(Html.fromHtml(hotelDetails.getAccommodationServiceDetails()));
        }
        //Toast.makeText(this,hotelDetails.getAccommodationServiceId()+hotelDetails.getProviderNameBn(),Toast.LENGTH_SHORT).show();
        // hotel ration default 2

        hotelDetailsView.post(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(mContext,holder.review.getLineCount()+"",Toast.LENGTH_SHORT).show();
                if (hotelDetailsView.getLineCount()>3)
                {
                    showMore.setVisibility(View.VISIBLE);
                }

            }
        });
        try {
            String rating = hotelDetails.getAccommodationTypeName().substring(0,1);
            hotelRating.setRating(Integer.parseInt(rating));
        }catch (Exception ex)
        {
            hotelRating.setRating(0);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHotelDetailsInfo(currentServiceId);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
