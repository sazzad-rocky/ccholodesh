/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;

import constants.Travel;
import helpers.BaseURL;
import model.PostedReview;

/**
 * Created by rhythmshahriar on 9/11/17.
 */

public class ReviewByUserAdapter extends RecyclerView.Adapter<ReviewHolder>{

    private Context mContext;
    private PostedReview[] reviewByUsers;
    SharedPreferences sharedPreferences;
    String customerEmail;
    String reviewFor;

    public ReviewByUserAdapter(Context mContext, PostedReview[] reviewByUsers,String reviewFor) {
        this.mContext = mContext;
        this.reviewByUsers = reviewByUsers;
        sharedPreferences= mContext.getSharedPreferences(mContext.getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        customerEmail = sharedPreferences.getString(Travel.USER_EMAIL,null);
        this.reviewFor = reviewFor;
        //BaseURL.REVIEW = 0f;
        //BaseURL.REVIEW_COUNT = 0;
    }
    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_user_rating,parent,false);
        ReviewHolder viewholder=new ReviewHolder(view);
        //Toast.makeText(mContext,"this",Toast.LENGTH_LONG).show();
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final ReviewHolder holder, int position) {
        final PostedReview reviewByUser = reviewByUsers[position];
        if (customerEmail != null && customerEmail.equals(reviewByUser.getReviewUserId()))
        {

            if (reviewFor.equals("ACCOMMODATION"))
            {
                BaseURL.REVIEWED_ACCOMMODATION_ID = reviewByUser.getReviewTrackId();
                BaseURL.REVIEWED_ACCOMMODATION_RATING = Float.valueOf(reviewByUser.getReviewRating());

            }else if (reviewFor.equals("FOOD"))
            {
                BaseURL.REVIEWED_FOOD_ID = reviewByUser.getReviewTrackId();
                BaseURL.REVIEWED_FOOD_RATING = Float.valueOf(reviewByUser.getReviewRating());

            }
        }

        holder.review.setText(reviewByUser.getReviewComments());
        if (reviewByUser.getCustomerName()!= null)holder.userName.setText(reviewByUser.getCustomerName());
        holder.ratingBar.setRating(Float.valueOf(reviewByUser.getReviewRating()));
        holder.date.setText(reviewByUser.getCreatedAt().substring(0,10));
        BaseURL.REVIEW += Float.valueOf(reviewByUser.getReviewRating());
        //Toast.makeText(mContext,"adding : "+ BaseURL.REVIEW,Toast.LENGTH_SHORT).show();
        holder.review.post(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(mContext,holder.review.getLineCount()+"",Toast.LENGTH_SHORT).show();
                if (holder.review.getLineCount()>1)
                {
                    holder.showMore.setVisibility(View.VISIBLE);
                }

            }
        });



        holder.showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollaspe(holder);

            }
        });
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollaspe(holder);

            }
        });

    }

    private void expandCollaspe (final ReviewHolder holder)
    {


        ViewGroup.LayoutParams params = holder.review.getLayoutParams();
        if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT)
        {
            final float scale = mContext.getResources().getDisplayMetrics().density;
            int pixels = (int) (20 * scale + 0.5f);
            params.height = pixels;
            holder.showMore.setText("Show More");
        }
        else
        {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.showMore.setText("Show Less");
        }

        holder.review.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return reviewByUsers.length;
    }
}


class ReviewHolder extends RecyclerView.ViewHolder{
    LinearLayout parentView;
    TextView userName;
    //TextView accommodationCategory;
    RatingBar ratingBar;
    TextView date;
    TextView review;
    TextView showMore;

    public ReviewHolder(View itemView) {
        super(itemView);
        parentView = (LinearLayout) itemView.findViewById(R.id.parent);
        userName = (TextView) itemView.findViewById(R.id.username);
        showMore = (TextView) itemView.findViewById(R.id.details);
        date = (TextView) itemView.findViewById(R.id.date);
        review = (TextView) itemView.findViewById(R.id.review);
        ratingBar = (RatingBar) itemView.findViewById(R.id.rating);

    }
}