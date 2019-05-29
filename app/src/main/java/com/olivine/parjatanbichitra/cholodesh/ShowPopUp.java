/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.app.*;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import callbacks.AuthCallback;
import constants.Travel;
import helpers.BaseURL;
import model.Review;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowPopUp extends Activity {


    TextView details,rating;
    EditText userReview;
    RatingBar ratingBar;
    Button close,submit;
    float userRating;
    SharedPreferences sharedPreferences;



    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String message = getIntent().getStringExtra("DETAILS_ATTRACTION");
        if (message.equals("oPEenFoRrAtInG"))
        {
            setContentView(R.layout.layout_review_user_input);
            rating = (TextView) findViewById(R.id.givenRating);
            userReview = (EditText) findViewById(R.id.userReview);
            ratingBar = (RatingBar) findViewById(R.id.ratingGiving);
            close = (Button) findViewById(R.id.btnCLose);
            submit = (Button) findViewById(R.id.reviewSubmit);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            sharedPreferences=getSharedPreferences(getString(R.string.preference_file_key),MODE_PRIVATE);

            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
            getWindow().setLayout((int) (width*.8), (int) (height*.6));

            float ratingGot = getIntent().getFloatExtra("RATING",0f);
            userRating = ratingGot;
            ratingBar.setRating(ratingGot);
            rating.setText(ratingGot+"");

            submit.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userReview.getText() != null && userReview.getText().length()>0)
                    {
                        postReview(userReview.getText().toString(),userRating+"");
//                        String trackId = getIntent().getStringExtra("REVIEW_TRACK_ID");
//                        String reviewType = getIntent().getStringExtra("REVIEW_TYPE");
//                        String customerEmail=sharedPreferences.getString(Travel.USER_EMAIL,null);
//
//                        Toast.makeText(ShowPopUp.this, "Id: "+ trackId+" Type: "+reviewType+" User: " + customerEmail ,Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ShowPopUp.this,userRating+ " Error" ,Toast.LENGTH_SHORT).show();
                    }
                }
            });

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                    //Toast.makeText(RatingActivity.this,rating+ " Stars" ,Toast.LENGTH_SHORT).show();
                    ShowPopUp.this.rating.setText(rating+"");
                    userRating = rating;

                }
            });

            close.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


        }
        else

        {
            setContentView(R.layout.layout_popup);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
            getWindow().setLayout((int) (width*.8), (int) (height*.6));

            details = (TextView) findViewById(R.id.popUpText);
            close = (Button) findViewById(R.id.btnCLose);

            // Toast.makeText(this,"["+message.length()+"]",Toast.LENGTH_LONG).show();

            if (message == null || message.length()== 0 ) message = "No Details Yet";
            if (!BaseURL.LANGUAGE_ENG) message = "বিস্তারিত নেই ";

            details.setText(Html.fromHtml(message));

            close.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }



    }

    private void postReview (String reviewComments,String rating)
    {
        String trackId = getIntent().getStringExtra("REVIEW_TRACK_ID");
        String reviewType = getIntent().getStringExtra("REVIEW_TYPE");
        String customerEmail=sharedPreferences.getString(Travel.USER_EMAIL,null);


        BaseURL.FoodReviewByUser = Float.valueOf(rating);
        AuthCallback authCallback = new AuthCallback(this);
        Review review = new Review();
        review.setReviewComments(reviewComments);
        review.setReviewId("0");
        review.setReviewRating(rating);
        review.setReviewTrackId(trackId);
        review.setReviewType(reviewType);
        review.setReviewUserId(customerEmail);

        authCallback.SendReview(review).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body()!= null)
                {
                    Toast.makeText(ShowPopUp.this,"Review Posted",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(ShowPopUp.this,"Failure",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
