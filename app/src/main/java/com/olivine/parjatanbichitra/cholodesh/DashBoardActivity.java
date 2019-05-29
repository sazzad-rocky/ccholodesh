package com.olivine.parjatanbichitra.cholodesh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.Dash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.DashboardAdapter;
import adapters.TailormadeListAdapter;
import callbacks.ProvideCallback;
import constants.Travel;
import fragments.TailormadeListFragment;
import helpers.BaseURL;
import helpers.CustomCallBack;
import model.TailorMadeList;
import retrofit2.Call;
import retrofit2.Response;
import userDefinder.TailorMade;

public class DashBoardActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private RecyclerView.LayoutManager layoutManager;
    private List<TailorMadeList> tailorMades = new ArrayList<>();
    private List<TailorMadeList> tailorMadeFiltered = new ArrayList<>();
    private DashboardAdapter dashboardAdapter;
    private RecyclerView recyclerView;
    ProvideCallback provideCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dashboard");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);
        sharedPreferences= DashBoardActivity.this.getSharedPreferences(getString(R.string.preference_file_key), DashBoardActivity.this.MODE_PRIVATE);



        String customerEmail = sharedPreferences.getString(Travel.USER_EMAIL, null);

        provideCallback = new ProvideCallback(DashBoardActivity.this);

        provideCallback.getTailorMadeList(customerEmail).enqueue(new CustomCallBack<TailorMadeList[]>(DashBoardActivity.this) {
            @Override
            public void onResponse(Call<TailorMadeList[]> call, Response<TailorMadeList[]> response) {
                Log.e("Package Url",call.request().url().toString());
                super.onResponse(call,response);
                //Toast.makeText(getActivity(),response.body().length+"",Toast.LENGTH_LONG).show();
                //if (response.body()!= null && response.body().length == 0) {getActivity().onBackPressed(); return;}
                if (response.body()!= null && response.body().length > 0)
                {
                    tailorMades =Arrays.asList(response.body());
                    for (int i=0;i<tailorMades.size();i++){
                        if (Integer.parseInt(tailorMades.get(i).gettailormade_confirm_status())==1 || Integer.parseInt(tailorMades.get(i).gettailormade_confirm_status())==2){
                           // Toast.makeText(DashBoardActivity.this, "yes", Toast.LENGTH_SHORT).show();
                            tailorMadeFiltered.add(tailorMades.get(i));
                        }
                    }

                    DashboardAdapter dashboardAdapter = new DashboardAdapter(DashBoardActivity.this,tailorMadeFiltered);
                    recyclerView.setAdapter(dashboardAdapter);
                }

                else
                {
                    String message = "No Tailormade Found";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        message =" কোনো পরিকল্পনা তালিকা পাওয়া যায় নি   ";

                    }
                    Toast.makeText(DashBoardActivity.this,message,Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(getContext(), tailormadeList.getAdapter().getItemCount()+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TailorMadeList[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";
                }
                Toast.makeText(DashBoardActivity.this, meesage, Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    }



//    public void loadFragment (Fragment fragment)
//    {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.listFragment, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }

