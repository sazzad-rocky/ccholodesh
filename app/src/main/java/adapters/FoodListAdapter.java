/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import helpers.BaseURL;
import model.Food;

/**
 * Created by Olivine on 6/11/2017.
 */

public class FoodListAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    private Context mContext;
    private List<Food> foods;

    public FoodListAdapter(Context mContext, List<Food> foods) {
        this.mContext = mContext;
        this.foods = foods;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_food_list,parent,false);
        FoodViewHolder foodViewHolder=new FoodViewHolder(view);
        return foodViewHolder;
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        final Food food=foods.get(position);

        holder.foodProviderName.setText(food.getDestinationFoodServiceProviderName());
        holder.foodProviderType.setText(food.getFoodCategoryName());
        holder.foodProviderAddress.setText(food.getDestinationFoodServiceProviderAddress());
        holder.foodProviderPrice.setText(food.getDestinationFoodServiceProviderPriceRange()+"৳");
        Picasso.with(mContext).load(BaseURL.FOOD_IMAGE_BASE_URL+food.getDestinationFoodServiceProviderImage()).into(holder.foodProviderImage);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toggleSelecion(v,food);

            }
        });

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    private void toggleSelecion(View view, Food food){
        if(food.isSelected()){
            view.setBackgroundColor(Color.parseColor("#ffffff"));
            food.setSelected(false);
        }else{
            view.setBackgroundColor(Color.parseColor("#90c468"));
            food.setSelected(true);
        }

    }
}
class FoodViewHolder extends RecyclerView.ViewHolder{
    ImageView foodProviderImage;
    TextView foodProviderName;
    TextView foodProviderType;
    TextView foodProviderAddress;
    TextView foodProviderPrice;
    LinearLayout parent;
    public FoodViewHolder(View itemView) {
        super(itemView);
        foodProviderImage= (ImageView) itemView.findViewById(R.id.foodProviderImage);
        foodProviderName= (TextView) itemView.findViewById(R.id.foodProviderName);
        foodProviderType= (TextView) itemView.findViewById(R.id.foodProviderType);
        foodProviderAddress= (TextView) itemView.findViewById(R.id.foodProviderAddress);
        foodProviderPrice= (TextView) itemView.findViewById(R.id.foodProviderPrice);
        parent = (LinearLayout) itemView.findViewById(R.id.parent);
    }
}
