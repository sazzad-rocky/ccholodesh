/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.squareup.picasso.Picasso;

import adapters.DestinationTagAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import helpers.BaseURL;
import helpers.CustomCallBack;
import model.DestinationLocalTour;
import model.NearByPlacesInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationLocalTourDetails extends AppCompatActivity {

    private static final int NEXT_MENU =2 ;
    private static final int PREVIOUS_MENU =1 ;
    private int currentServiceId;
    private int currentSerialNo=0;
    @BindView(R.id.mDemoSlider)
    ImageView mDemoSlider;
    ProvideCallback provideCallback;

    @BindView(R.id.swipe)
    LinearLayout swipe;

    @BindView(R.id.placeName)
    TextView placeName;

    @BindView(R.id.aboutforlang)
    TextView aboutforlang;


    @BindView(R.id.placeDetails)
    TextView placeDetails;

    @BindView(R.id.tags)
    RecyclerView tags;
    String name = "";

    Context context;

    private GestureDetector mGesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_local_tour_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mGesture = new GestureDetector(this,new MyGestureListener());

        int placeId = getIntent().getIntExtra("NEAR_BY_PLACE_ID",0);
        name= getIntent().getStringExtra("NEAR_BY_PLACE_NAME");
        this.setTitle("Details");
        context = this;


       // Toast.makeText(this,placeId+"",Toast.LENGTH_LONG).show();

        ButterKnife.bind(this);
        swipe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGesture.onTouchEvent(event);
            }
        });

        if (!BaseURL.LANGUAGE_ENG)
        {
            aboutforlang.setText("বিস্তারিত");
            this.setTitle("বিস্তারিত");
        }
        provideCallback = new ProvideCallback(this);
        currentServiceId = placeId;
        loadTextViews (placeId);
       // loadGallery (placeId);

    }



    private void loadTextViews (int placeId)


    {


        provideCallback.getDestinationLocalTour(placeId).enqueue(new CustomCallBack<DestinationLocalTour[]>(this) {
            @Override
            public void onResponse(Call<DestinationLocalTour[]> call, Response<DestinationLocalTour[]> response) {
                super.onResponse(call, response);
                if(response.body()!=null && response.body().length > 0){
                    DestinationLocalTour [] nearByPlaceInfo = response.body();
                    String url= BaseURL.DESTINATION_NEAR_BY_PLACE_IMAGE_BASE_URL+nearByPlaceInfo[0].getDestinationNearbyPlaceImage();

                    Picasso.with(context)
                            .load(url)
                            .placeholder(R.drawable.image_placeholder)
                            .fit()
                            .into(mDemoSlider);
                    if (!BaseURL.LANGUAGE_ENG && nearByPlaceInfo[0].getDestinationNearbyPlaceNameBn() != null)
                    {
                        placeName.setText(nearByPlaceInfo[0].getDestinationNearbyPlaceNameBn());
                    }
                    else
                    {
                        placeName.setText(nearByPlaceInfo[0].getDestinationNearbyPlaceName());
                    }

                    if (!BaseURL.LANGUAGE_ENG && nearByPlaceInfo[0].getDestinationNearbyPlaceNameBn() != null)
                    {
                        placeDetails.setText(Html.fromHtml(nearByPlaceInfo[0].getDestinationNearbyPlaceNameBn()));
                    }
                    else
                    {
                        placeDetails.setText(Html.fromHtml(nearByPlaceInfo[0].getLocalTourDetails()));
                    }

                }
            }

            @Override
            public void onFailure(Call<DestinationLocalTour[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(DestinationLocalTourDetails.this,meesage,Toast.LENGTH_LONG).show();


            }
        });
    }


    private void loadPrevious()
    {
        currentSerialNo = BaseURL.getCurrentPositionInArray(currentServiceId,BaseURL.localTourIds);
        if(currentSerialNo>0){
            currentSerialNo--;
            currentServiceId=BaseURL.localTourIds.get(currentSerialNo);
            loadTextViews(currentServiceId);
            //loadGallery(currentServiceId);
        }
    }

    private void loadNext ()
    {
       // Toast.makeText(context,BaseURL.localTourIds.size()+"",Toast.LENGTH_SHORT).show();
        currentSerialNo = BaseURL.getCurrentPositionInArray(currentServiceId,BaseURL.localTourIds);
        if(currentSerialNo<BaseURL.localTourIds.size()-1){
            currentSerialNo++;
            currentServiceId=BaseURL.localTourIds.get(currentSerialNo);
            loadTextViews(currentServiceId);
            //loadGallery(currentServiceId);
        }
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;

            case PREVIOUS_MENU:
                // load prevous hotel
                loadPrevious();
                break;
            case NEXT_MENU:
                // load prevous hotel
                loadNext();

        }
        return true;
    }





    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("TAG","onDown: ");

            // don't return false here or else none of the other
            // gestures will work
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("TAG", "onLongPress: ");
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TAG", "onDoubleTap: ");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("TAG", "onScroll: ");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //Log.v("fling", "Flinged.");

            if (e1.getX() > e2.getX())
            {
                loadNext();


            }
            else if (e1.getX() < e2.getX())
            {
                loadPrevious();
            }
            return true;
        }
//
    }
}
