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
import android.support.v4.view.GestureDetectorCompat;
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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import adapters.DestinationTagAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import model.NearByPlaceGallerInfo;
import model.NearByPlacesInfo;
import retrofit2.Call;
import retrofit2.Response;


public class NearByPlaceDetailsActivity extends AppCompatActivity {

    private static final int NEXT_MENU =2 ;
    private static final int PREVIOUS_MENU =1 ;
    private int currentServiceId;
    private int currentSerialNo=0;
    @BindView(R.id.mDemoSlider)
    SliderLayout mDemoSlider;
    ProvideCallback provideCallback;

    @BindView(R.id.swipe)
    LinearLayout swipe;

    @BindView(R.id.placeName)
    TextView placeName;

    @BindView(R.id.aboutforlang)
    TextView aboutforlang;


    @BindView(R.id.placeDetails)
    TextView placeDetails;
    @BindView(R.id.count)
    TextView count;

    @BindView(R.id.tags)
    RecyclerView tags;
    String name = "";

    @BindView(R.id.details)
    TextView showMore;

    Context context;

    private GestureDetector mGesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_place_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_clear_24);


        //mGesture = new GestureDetector(this, mOnGesture);
        mGesture = new GestureDetector(this,new MyGestureListener());

        int placeId = getIntent().getIntExtra("NEAR_BY_PLACE_ID",0);
        name= getIntent().getStringExtra("NEAR_BY_PLACE_NAME");
        this.setTitle(BaseURL.desname);
        context = this;


        //Toast.makeText(this,placeId+"",Toast.LENGTH_LONG).show();

        ButterKnife.bind(this);
        swipe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGesture.onTouchEvent(event);
            }
        });

//        placeDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandCollaspe();
//            }
//        });
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollaspe();
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
        loadGallery (placeId);
        currentSerialNo = BaseURL.getCurrentNearByPlaceInArray(currentServiceId);
        String footer = currentSerialNo+1 + " of " + BaseURL.nearByPlaceids.size();
        if (!BaseURL.LANGUAGE_ENG){
            footer = BanglaNumberParser.getBangla(BaseURL.nearByPlaceids.size()+"") + "  টির মধ্যে # " + BanglaNumberParser.getBangla(currentSerialNo+1+"");
        }
        count.setText( footer);


    }

    private void loadGallery (int placeId)
    {
        provideCallback.getNearByPlaceGalleryInfo(placeId).enqueue(new CustomCallBack<NearByPlaceGallerInfo[]>(this) {
            @Override
            public void onResponse(Call<NearByPlaceGallerInfo[]> call, Response<NearByPlaceGallerInfo[]> response) {
                super.onResponse(call, response);
                if(response.body()!=null){
                    loadGallery(response.body());
                }
            }

            @Override
            public void onFailure(Call<NearByPlaceGallerInfo[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(NearByPlaceDetailsActivity.this,meesage,Toast.LENGTH_LONG).show();


            }
        });
    }

    private void loadGallery (NearByPlaceGallerInfo[] galleries)
    {
        mDemoSlider.removeAllSliders();
        for(NearByPlaceGallerInfo nearByPlaceGallerInfo : galleries){
            //String name=destinationGallery.get();
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    //.description(name) // image description
                    .image(BaseURL.DESTINATION_NEAR_BY_PLACE_GALLERY_BASE_URL+nearByPlaceGallerInfo.getDestinationNearbyPlaceGalleryImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra","Name");

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateDown);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
    }

    private void loadTextViews (int placeId)
    {
        provideCallback.getNearByPlaceInfo(placeId).enqueue(new CustomCallBack<NearByPlacesInfo[]>(this) {
            @Override
            public void onResponse(Call<NearByPlacesInfo[]> call, Response<NearByPlacesInfo[]> response) {
                super.onResponse(call, response);
                if(response.body()!=null){
                    //loadGallery(response.body());
                    NearByPlacesInfo [] nearByPlaceInfo = response.body();
                    if (!BaseURL.LANGUAGE_ENG && nearByPlaceInfo[0].getDestinationNearbyPlaceNameBn() != null)
                    {
                        placeName.setText(nearByPlaceInfo[0].getDestinationNearbyPlaceNameBn());
                    }
                    else
                    {
                        placeName.setText(nearByPlaceInfo[0].getDestinationNearbyPlaceName());
                    }

                    if (!BaseURL.LANGUAGE_ENG && nearByPlaceInfo[0].getDestinationNearbyPlaceDetailsBn() != null)
                    {
                        placeDetails.setText(Html.fromHtml(nearByPlaceInfo[0].getDestinationNearbyPlaceDetailsBn()));
                    }
                    else
                    {
                        placeDetails.setText(Html.fromHtml(nearByPlaceInfo[0].getDestinationNearbyPlaceDetails()));
                    }




                    if (BaseURL.LANGUAGE_ENG && nearByPlaceInfo[0].getDestinationNearbyPlaceTag()!= null)
                    {
                        String [] destinationTags = nearByPlaceInfo[0].getDestinationNearbyPlaceTag().split(",");
                        // Toast.makeText(DestinationActivity.this,destinationNews[0].getDestinationId()+"",Toast.LENGTH_LONG).show();
                        if (destinationTags.length>0 && destinationTags[0] != "")
                        {
                            DestinationTagAdapter adapter = new DestinationTagAdapter(getApplicationContext(),destinationTags);
                            tags.setAdapter(adapter);
                        }

                    }
                    else if (!BaseURL.LANGUAGE_ENG && nearByPlaceInfo[0].getDestinationNearbyPlaceTagBn()!= null)
                    {
                        String [] destinationTags = nearByPlaceInfo[0].getDestinationNearbyPlaceTagBn().split(",");
                        // Toast.makeText(DestinationActivity.this,destinationNews[0].getDestinationId()+"",Toast.LENGTH_LONG).show();
                        if (destinationTags.length>0 && destinationTags[0] != "")
                        {
                            DestinationTagAdapter adapter = new DestinationTagAdapter(getApplicationContext(),destinationTags);
                            tags.setAdapter(adapter);
                        }
                    }

                    placeDetails.post(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(mContext,holder.review.getLineCount()+"",Toast.LENGTH_SHORT).show();
                            if (placeDetails.getLineCount()>3)
                            {
                                //showMore.setVisibility(View.VISIBLE);
                            }

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<NearByPlacesInfo[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(NearByPlaceDetailsActivity.this,meesage,Toast.LENGTH_LONG).show();


            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (BaseURL.nearByPlaceids.size() > 1){

            MenuItem previous=menu.add(Menu.NONE,PREVIOUS_MENU,Menu.NONE,"<");
            previous.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            previous.setIcon(R.drawable.ic_left);
            MenuItem next=menu.add(Menu.NONE,NEXT_MENU,Menu.NONE,">");
            next.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            next.setIcon(R.drawable.ic_right);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean handled;
//        handled = mGesture.onTouchEvent(ev);
//        return handled;
//    }

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
            expandCollaspe();
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
//
//    private GestureDetector.OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener() {
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return false;
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            //Log.v("fling", "Flinged.");
//
//            if (e1.getX() > e2.getX())
//            {
//                Toast.makeText(context,"Right to left",Toast.LENGTH_SHORT).show();
//
//            }
//            else if (e1.getX() < e2.getX())
//            {
//                Toast.makeText(context,"Left to right", Toast.LENGTH_SHORT).show();
//            }
//            return true;
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            return false;
//        }
//    };

    private void expandCollaspe ()
    {


        ViewGroup.LayoutParams params = placeDetails.getLayoutParams();
        if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT)
        {
            final float scale = getResources().getDisplayMetrics().density;
            int pixels = (int) (60 * scale + 0.5f);
            params.height = pixels;
            showMore.setText("Show More");
        }
        else
        {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            showMore.setText("Show Less");


        }

        //description.setLayoutParams(params);
    }


    private void loadPrevious()
    {
        currentSerialNo = BaseURL.getCurrentNearByPlaceInArray(currentServiceId);
        if(currentSerialNo>0){
            currentSerialNo--;
            currentServiceId=BaseURL.nearByPlaceids.get(currentSerialNo);
            loadTextViews(currentServiceId);
            loadGallery(currentServiceId);
            String footer = currentSerialNo+1 + " of " + BaseURL.nearByPlaceids.size();
            if (!BaseURL.LANGUAGE_ENG){
                footer = BanglaNumberParser.getBangla(BaseURL.nearByPlaceids.size()+"") + "  টির মধ্যে # " + BanglaNumberParser.getBangla(currentSerialNo+1+"");
            }
            count.setText(footer);
        }
    }

    private void loadNext ()
    {
        currentSerialNo = BaseURL.getCurrentNearByPlaceInArray(currentServiceId);
        //Toast.makeText(context, currentServiceId +" " + currentSerialNo + " " + (BaseURL.nearByPlaceids.size()-1),Toast.LENGTH_SHORT).show();
        if(currentSerialNo<BaseURL.nearByPlaceids.size()-1){
            currentSerialNo++;
            currentServiceId=BaseURL.nearByPlaceids.get(currentSerialNo);
            loadTextViews(currentServiceId);
            loadGallery(currentServiceId);
            String footer = currentSerialNo+1 + " of " + BaseURL.nearByPlaceids.size();
            if (!BaseURL.LANGUAGE_ENG){
                footer = BanglaNumberParser.getBangla(BaseURL.nearByPlaceids.size()+"") + "  টির মধ্যে # " + BanglaNumberParser.getBangla(currentSerialNo+1+"");
            }
            count.setText(footer);
        }
    }


}
