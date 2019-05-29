/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import adapters.ReviewByUserAdapter;
import callbacks.ProvideCallback;
import constants.Travel;
import helpers.BaseURL;
import helpers.CustomCallBack;
import model.PostedReview;
import model.ReviewByUser;
import retrofit2.Call;
import retrofit2.Response;

import static adapters.HotelListAdapter.SERVICE_ID;

public class RatingActivity extends AppCompatActivity {

    RatingBar ratingBar, ratingGiving;
    RecyclerView ratings;
    SharedPreferences sharedPreferences;
    float userRating;
    ProvideCallback provideCallback;
    TextView nofRevies,reviewforlang,wrtarevwforlang,tapforlang,tapAgainforlang;
    int serviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingGiving = (RatingBar) findViewById(R.id.ratingGiving);
        ratingBar.setRating(getIntent().getFloatExtra("AVG_RATING",0));
        ratings = (RecyclerView) findViewById(R.id.ratings);
        nofRevies = (TextView) findViewById(R.id.nofRevies);
        reviewforlang = (TextView) findViewById(R.id.reviewforlang);
        wrtarevwforlang = (TextView) findViewById(R.id.wrtarevwforlang);
        tapforlang = (TextView) findViewById(R.id.tapforlang);
        tapAgainforlang = (TextView) findViewById(R.id.tapAgainforlang);
        if (!BaseURL.LANGUAGE_ENG)
        {
            this.setTitle("রেটিং");
            reviewforlang.setText("রেটিং সমূহ ");
            wrtarevwforlang.setText("রিভিউ লিখুন");
            tapforlang.setText("রিভিউ লিখতে ট্যাপ করুন");
            tapAgainforlang.setText("সংশোধন করতে পুনরায় ট্যাপ করতে পারেন");

        }
        else this.setTitle("Rating");

        nofRevies.setText(getIntent().getStringExtra("NO_OF_RATING")+" Review(s)");
        provideCallback = new ProvideCallback(this);

        ReviewByUser[] reviewByUsers = new ReviewByUser[2];
        ReviewByUser test = new ReviewByUser();
        ReviewByUser test2 = new ReviewByUser();
        test2.review = "Some demo text for checking purpose";
        reviewByUsers[0] = test2;
        reviewByUsers[1] = test;
         serviceId = getIntent().getIntExtra(SERVICE_ID, 0);
        loadreviews();



        ratingGiving.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String id = RatingActivity.this.serviceId+"";
//                if (id.equals(BaseURL.REVIEWED_ACCOMMODATION_ID))
//                {
//                    String meesage ="You Reviewed for this Hotel";
//                    if (!BaseURL.LANGUAGE_ENG)
//                    {
//                        meesage =" আপনি এই হোটেল এর জন্য রিভিউ করেছেন";
//
//                    }
//                    Toast.makeText(RatingActivity.this,meesage,Toast.LENGTH_LONG).show();
//
//                    ratingGiving.setRating(BaseURL.REVIEWED_ACCOMMODATION_RATING);
//                }
//                else
//                {
                    //Toast.makeText(RatingActivity.this, id+ "["+BaseURL.REVIEWED_ACCOMMODATION_ID+"]", Toast.LENGTH_SHORT).show();
                    userRating = rating;
                    String customerEmail = sharedPreferences.getString(Travel.USER_EMAIL, null);
                    if (customerEmail == null) {
                        Intent loginIntent = new Intent(RatingActivity.this, LoginActivity.class);
                        startActivityForResult(loginIntent, 110);
                        //works are pending here login
                        //will show login page and return after successful login
                        //return;
                    } else {
                        startRatingActivity();
                    }
               // }


                //Toast.makeText(RatingActivity.this,rating+ " Stars" ,Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void startRatingActivity() {
        Intent intent = new Intent(RatingActivity.this, ShowPopUp.class);
        int serviceId = getIntent().getIntExtra(SERVICE_ID, 0);
        String id = serviceId + "";
        intent.putExtra("DETAILS_ATTRACTION", "oPEenFoRrAtInG");
        intent.putExtra("RATING", userRating);
        intent.putExtra("REVIEW_TRACK_ID", id);
        intent.putExtra("REVIEW_TYPE", "Accommodation");
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if user registered immediately  redirect to requested activity
        super.onActivityResult(requestCode, resultCode, data);

        String customerEmail = sharedPreferences.getString(Travel.USER_EMAIL, null);
        if (customerEmail == null) {

            String meesage ="Login Needed to Rate";
            if (!BaseURL.LANGUAGE_ENG)
            {
                meesage ="লগইন প্রয়োজন";

            }
            Toast.makeText(RatingActivity.this,meesage,Toast.LENGTH_LONG).show();


        } else {
            startRatingActivity();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (BaseURL.FoodReviewByUser != 0)
        {
            float currentRating = getIntent().getFloatExtra("AVG_RATING",0);
            int currentCount = Integer.parseInt( getIntent().getStringExtra("NO_OF_RATING"));

            float gotRating =currentRating *currentCount + BaseURL.FoodReviewByUser;
            int countRating = currentCount;
            String id = RatingActivity.this.serviceId+"";
            if (!id.equals(BaseURL.REVIEWED_ACCOMMODATION_ID))
            {
                countRating++;
            }
            float newRating = gotRating/countRating;
            // Toast.makeText(this,"gotrating:" + gotRating + "countrating: "+ countRating+ "newRating :" + newRating,Toast.LENGTH_LONG).show();
            ratingBar.setRating(newRating);
            nofRevies.setText(countRating+" Review(s)");
            BaseURL.FoodReviewByUser = 0f;
            loadreviews();

        }
    }

    private void loadreviews()
    {
        provideCallback.getAccomodationReview(serviceId+"").enqueue(new CustomCallBack<PostedReview[]>(this) {
            @Override
            public void onResponse(Call<PostedReview[]> call, Response<PostedReview[]> response) {
                super.onResponse(call, response);
                if (response.body() != null && response.body().length>0) {
                    ratings.setAdapter(new ReviewByUserAdapter(RatingActivity.this, response.body(),"ACCOMMODATION"));


                }
                else
                {
                    String meesage ="No Review Found";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage ="কোন রিভিউ  পাওয়া যায় নি";

                    }
                    Toast.makeText(RatingActivity.this,meesage,Toast.LENGTH_LONG).show();



                }

            }

            @Override
            public void onFailure(Call<PostedReview[]> call, Throwable t) {
                super.onFailure(call, t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(RatingActivity.this,meesage,Toast.LENGTH_LONG).show();

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

