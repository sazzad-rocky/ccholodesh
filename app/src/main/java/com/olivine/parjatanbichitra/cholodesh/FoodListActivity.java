/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import adapters.FoodListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import constants.Travel;
import helpers.BaseURL;
import helpers.CustomCallBack;
import model.Food;
import retrofit2.Call;
import retrofit2.Response;

public class FoodListActivity extends AppCompatActivity {
    private static final int FINISH_MENU = 1;
    private List<Food> foodLists;
    @BindView(R.id.foodList) RecyclerView foodList;
    // DataStrorage
    SharedPreferences sharedPreferences;
    // Callback
    ProvideCallback provideCallback;
    // Adapter
    FoodListAdapter foodListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (!BaseURL.LANGUAGE_ENG)
        {
            this.setTitle("খাবার তালিকা  ");
        }
        else this.setTitle("Available Foods");
        initialization();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       // MenuItem finishItem=menu.add(Menu.NONE,FINISH_MENU,Menu.NONE,"Done");
       // finishItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();
                break;

//            case FINISH_MENU:
//
//                ArrayList<Food> foods=new ArrayList<>();
//
//                for (Food food:foodLists){
//                    if(food.isSelected()){
//                        foods.add(food);
//                    }
//                }
//
//                //Toast.makeText(this,foods.size()+" ",Toast.LENGTH_LONG).show();
//                //Toast.makeText(this,rooms.size()+" activity",Toast.LENGTH_LONG).show();
//                Intent intent=new Intent();
//                Bundle bundle=new Bundle();
//                ListWrapper listWrapper =new ListWrapper();
//                listWrapper.setFoods(foods);
//                bundle.putString("FOOD_LIST",new Gson().toJson(listWrapper));
//                intent.putExtras(bundle);
//                setResult(200,intent);
//                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void initialization(){
        // Load Local foods of the selected district
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_key),MODE_PRIVATE);
        //String districtId=sharedPreferences.getInt(Travel.TO_LOCATION,26)+"";
        String districtId=getIntent().getStringExtra("DISTRICT_ID");
        provideCallback=new ProvideCallback(this);

        provideCallback.getFoodRestairents(districtId).enqueue(new CustomCallBack<Food[]>(this) {
            @Override
            public void onResponse(Call<Food[]> call, Response<Food[]> response) {
                super.onResponse(call,response);
                if (response.body() != null && response.body().length > 0)

                {
                    foodLists = Arrays.asList(response.body());
                    foodListAdapter=new FoodListAdapter(getApplicationContext(),foodLists);
                    foodList.setAdapter(foodListAdapter);
                }
                else

                {
                    String meesage ="No food Item found for this place";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage ="এই স্থানটির জন্য কোন খাবার খুঁজে পাওয়া যায় নি";

                    }
                    Toast.makeText(FoodListActivity.this,meesage,Toast.LENGTH_LONG).show();
                    onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<Food[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(FoodListActivity.this,meesage,Toast.LENGTH_LONG).show();


            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
