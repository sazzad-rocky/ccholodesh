/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;

import adapters.FoodListAdapter;
import adapters.HotelListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import helpers.BaseURL;
import model.AccommodationProvider;
import retrofit2.Call;
import retrofit2.Response;
import helpers.CustomCallBack;

/**
 * Created by Olivine on 6/22/2017.
 */

public class HotelListFragment extends Fragment {
    @BindView(R.id.hotelListView)
    RecyclerView hotelListView;
    private HotelListAdapter hotelListAdapter;

    // DataStrorage
    SharedPreferences sharedPreferences;
    // Callback
    ProvideCallback provideCallback;
    // Adapter
    FoodListAdapter foodListAdapter;

    public HotelListFragment() {
    }

    public static HotelListFragment newInstance(){
        HotelListFragment hotelListFragment=new HotelListFragment();
        return hotelListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_hotel_list,container,false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("Hotel List");
        initialization();
        return view;
    }
    private void initialization() {
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.preference_file_key), getActivity().MODE_PRIVATE);
        //final String districtId = sharedPreferences.getInt(Travel.TO_LOCATION, 26) + "";
        final String districtId = "26";
        // get all hotel list
        provideCallback = new ProvideCallback(getActivity());
        provideCallback.getDestinationWiseAccommodationList(Integer.parseInt(districtId)).enqueue(new CustomCallBack<AccommodationProvider[]>(getActivity ()) {
              @Override
              public void onResponse(Call<AccommodationProvider[]> call, Response<AccommodationProvider[]> response) {
                  super.onResponse(call,response);
                  Log.e("Package Url",call.request().url().toString());
                  if (response.body()!= null && response.body().length > 0)
                  {

                      hotelListAdapter=new HotelListAdapter(getActivity(),response.body());
                      hotelListView.setAdapter(hotelListAdapter);
                  }
                  else
                  {
                      String meesage ="No Accomodations";
                      if (!BaseURL.LANGUAGE_ENG)
                      {
                          meesage =" কিছুই পাওয়া যায়নি";

                      }
                      Toast.makeText(getActivity(),meesage,Toast.LENGTH_LONG).show();
                  }

              }

              @Override
              public void onFailure(Call<AccommodationProvider[]> call, Throwable t) {
                  String meesage ="Network Error";
                  if (!BaseURL.LANGUAGE_ENG)
                  {
                      meesage =" নেটওয়ার্ক ত্রুটি";

                  }
                  Toast.makeText(getActivity(),meesage,Toast.LENGTH_LONG).show();
              }
          }
        );
    }

}
