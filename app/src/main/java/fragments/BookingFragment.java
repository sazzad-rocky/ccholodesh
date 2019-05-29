/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.LoginActivity;
import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.ArrayList;
import java.util.List;

import adapters.BookingListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import constants.Travel;
import helpers.BaseURL;
import helpers.CustomCallBack;
import io.realm.Realm;
import model.HotelBookingToken;
import model.PackageBookingToken;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Olivine on 6/22/2017.
 */

public class BookingFragment extends Fragment {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    public BookingFragment() {
    }

    public static BookingFragment newInstance() {
        BookingFragment bookingFragment=new BookingFragment();
        return bookingFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_booking_container,container,false);
        if (!BaseURL.LANGUAGE_ENG)
        {
            getActivity().setTitle("টোকেন সমূহ");
        }
        else getActivity().setTitle("Tokens");
        // Create tabs
        ReservedHotelFragment reservedPackageFragment= ReservedHotelFragment.newInstance(1);
        ReservedHotelFragment reservedHotelFragment= ReservedHotelFragment.newInstance(0);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        mSectionsPagerAdapter.addFragment(reservedHotelFragment);
        mSectionsPagerAdapter.addFragment(reservedPackageFragment);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(mViewPager);
        return view;
    }



    public static class ReservedHotelFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private List<Object> bookings=new ArrayList<>();
        Realm realm;
        private ProvideCallback provideCallback ;
        String customerEmail = "";
        @BindView(R.id.placeHolderText)
        TextView placeHolderText;

        @BindView(R.id.placeHolderImage)
        ImageView placeHolderImage;

        @BindView(R.id.bookinList)
        RecyclerView bookinList;
        public ReservedHotelFragment() {
        }

        public static BookingFragment.ReservedHotelFragment newInstance(int sectionNumber) {
            BookingFragment.ReservedHotelFragment fragment = new BookingFragment.ReservedHotelFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_booking, container, false);
            ButterKnife.bind(this,rootView);


//            customerEmail = sharedPreferences.getString(Travel.USER_EMAIL,null);
//            if(customerEmail==null){
//                Intent loginIntent=new Intent(getActivity(),LoginActivity.class);
//                startActivityForResult(loginIntent,110);
//                //works are pending here login
//                //will show login page and return after successful login
//
//            }



            return rootView;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Realm.init(getActivity());
            realm=Realm.getDefaultInstance();

            Bundle bundle = this.getArguments();
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
            customerEmail = sharedPreferences.getString(Travel.USER_EMAIL,null);
            if (customerEmail != null) {
                if (bundle != null) {
                    int type = bundle.getInt(ARG_SECTION_NUMBER, 0);
                    // Check if it is hotel or package booking
                    provideCallback = new ProvideCallback(getActivity());
                    if (type == 0) {
                        provideCallback.getHotelBookings(customerEmail).enqueue(new CustomCallBack<HotelBookingToken[]>(getActivity()) {
                            @Override
                            public void onResponse(Call<HotelBookingToken[]> call, Response<HotelBookingToken[]> response) {
                                super.onResponse(call, response);
                                if (response.body()!= null && response.body().length > 0) {
                                    Log.e("Package Url", call.request().url().toString());
                                    BookingListAdapter bookingListAdapter = new BookingListAdapter(getActivity(), response.body());
                                    bookinList.setAdapter(bookingListAdapter);
                                } else {
                                    String meesage ="No Token Found";
                                    if (!BaseURL.LANGUAGE_ENG)
                                    {
                                        meesage =" কোনো টোকেন নেই ";
                                    }
                                    //Toast.makeText(getActivity(),meesage,Toast.LENGTH_LONG).show();
                                   placeHolderText.setVisibility(View.VISIBLE);
                                   placeHolderText.setText(meesage);
                                }
                            }
                            @Override
                            public void onFailure(Call<HotelBookingToken[]> call, Throwable t) {
                                super.onFailure(call, t);
                                String meesage ="Network Error";
                                if (!BaseURL.LANGUAGE_ENG)
                                {
                                    meesage =" নেটওয়ার্ক ত্রুটি";

                                }
                                placeHolderText.setVisibility(View.VISIBLE);
                                placeHolderText.setText(meesage);

                            }
                        }
                    );


                    } else if (type == 1) {
//
                        provideCallback.getPackageBookings(customerEmail).enqueue(new CustomCallBack<PackageBookingToken[]>(getActivity()) {
                          @Override
                          public void onResponse(Call<PackageBookingToken[]> call, Response<PackageBookingToken[]> response) {
                              super.onResponse(call, response);
                              if (response.body()!= null && response.body().length > 0) {
                                  Log.e("Package Url", call.request().url().toString());
                                  BookingListAdapter bookingListAdapter = new BookingListAdapter(getActivity(), response.body());
                                  bookinList.setAdapter(bookingListAdapter);
                              } else {
                                  String meesage ="No Token Found";
                                  if (!BaseURL.LANGUAGE_ENG)
                                  {
                                      meesage =" কোনো টোকেন নেই ";
                                  }
                                 //Toast.makeText(getActivity(),meesage,Toast.LENGTH_LONG).show();
                                  placeHolderText.setVisibility(View.VISIBLE);
                                  placeHolderText.setText(meesage);

                              }


                          }

                          @Override
                          public void onFailure(Call<PackageBookingToken[]> call, Throwable t) {
                              super.onFailure(call, t);
                              String meesage ="Network Error";
                              if (!BaseURL.LANGUAGE_ENG)
                              {
                                  meesage =" নেটওয়ার্ক ত্রুটি";

                              }
                              placeHolderText.setVisibility(View.VISIBLE);
                              placeHolderText.setText(meesage);
                              //Toast.makeText(getActivity(),meesage,Toast.LENGTH_LONG).show();
                          }
                      }
                        );

                    }

                }
            }

            else
            {
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(loginIntent, 110);
            }


            super.onCreate(savedInstanceState);
        }
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments=new ArrayList<>();
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    if (!BaseURL.LANGUAGE_ENG) return "হোটেল বুকিং";
                    else return "Hotel Bookings";
                case 1:
                    if (!BaseURL.LANGUAGE_ENG) return "প্যাকেজ বুকিং";
                    else return "Package Bookings";

            }
            return null;
        }
        public void addFragment(Fragment fragment){
            fragments.add(fragment);
        }

    }
}
