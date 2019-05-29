/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.FoodDetailsActivity;
import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import helpers.BaseURL;
import model.Food;

/**
 * Created by rhythmshahriar on 9/11/17.
 */

public class DestinationFoodAdapter extends RecyclerView.Adapter<DestinationFoodViewHolder>{

    private Context mContext;
    private List<Food> foods;


    public DestinationFoodAdapter(Context mContext, List<Food> foods) {
        this.mContext = mContext;
        this.foods = foods;

    }
    @Override
    public DestinationFoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(mContext).inflate(R.layout.layout_destination_food,parent,false);
        DestinationFoodViewHolder destinationFoodViewHolder=new DestinationFoodViewHolder(itemView);
        return destinationFoodViewHolder;
    }

    @Override
    public void onBindViewHolder(DestinationFoodViewHolder holder, int position) {

        final Food food=foods.get(position);
        String url= BaseURL.FOOD_IMAGE_BASE_URL+food.getDestinationFoodServiceProviderImage();
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(holder.foodImage);

        if (!BaseURL.LANGUAGE_ENG && food.getDestinationFoodServiceProviderNameBn()!= null)
        {
            holder.provider.setText(food.getDestinationFoodServiceProviderNameBn());
        }
        else holder.provider.setText(food.getDestinationFoodServiceProviderName());

        if (!BaseURL.LANGUAGE_ENG && food.getFoodCategoryNameBn() != null)
        {
            holder.category.setText(food.getFoodCategoryNameBn());
        }
        else holder.category.setText(food.getFoodCategoryName());
        holder.price.setText(food.getDestinationFoodServiceProviderPriceRange()+ "৳");
        holder.count.setText("#"+ (position+1) +" of " + getItemCount());


        holder.foodListParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoodDetailsActivity.class);
                BaseURL.food = food;
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}

class DestinationFoodViewHolder extends RecyclerView.ViewHolder{
    LinearLayout foodListParent;
    TextView provider;
    TextView category; //// STOPSHIP: 6/2/2017
    //s
    TextView price;
    TextView count;

    ImageView foodImage;

    public DestinationFoodViewHolder(View itemView) {
        super(itemView);
        foodListParent= (LinearLayout) itemView.findViewById(R.id.foodlistparent);
        provider= (TextView) itemView.findViewById(R.id.provider);
        category= (TextView) itemView.findViewById(R.id.category);
        price= (TextView) itemView.findViewById(R.id.price);
        count = (TextView) itemView.findViewById(R.id.count);
        foodImage = (ImageView) itemView.findViewById(R.id.foodimage);

    }

}
