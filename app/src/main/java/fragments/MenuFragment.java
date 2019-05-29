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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentTransaction;

import com.olivine.parjatanbichitra.cholodesh.AboutUsActivity;
import com.olivine.parjatanbichitra.cholodesh.LocalTourActivity;
import com.olivine.parjatanbichitra.cholodesh.LoginActivity;
import com.olivine.parjatanbichitra.cholodesh.R;

import callbacks.AuthCallback;
import constants.Travel;
import helpers.BaseURL;
import io.realm.Realm;
import listeners.FragmentInteractionListener;
import model.Review;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rhythmshahriar on 7/8/17.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {
    private FragmentInteractionListener mListener;
    Button A;
    LinearLayout createTripPlan, myTrip, pacakages, destination, transportation, accomodation, review, cholodeshcontact, localtour;
    //Button createTripPlan,myTrip,pacakages,destination,transportation,accomodation,review;
    TextView pkgforlang, crtforlang, plansforlang, desforlang, accforlang, trnsforlang, contactcholodesh, localtourtv;
    // Componets initialization
    //@BindView(R.id.tailormadeList)
    //RecyclerView tailormadeList;
    // Adapters
    // TailormadeListAdapter tailormadeListAdapter;
    // data storage
    SharedPreferences sharedPreferences;
    Realm realm;

    public MenuFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_new, container, false);
        createTripPlan = (LinearLayout) view.findViewById(R.id.btn_trip_plan);
        myTrip = (LinearLayout) view.findViewById(R.id.btn_my_trips);
        pacakages = (LinearLayout) view.findViewById(R.id.btn_packages);
        destination = (LinearLayout) view.findViewById(R.id.btn_destination);
        transportation = (LinearLayout) view.findViewById(R.id.btn_transportation);
        accomodation = (LinearLayout) view.findViewById(R.id.btn_accomodation);
        cholodeshcontact = (LinearLayout) view.findViewById(R.id.cholodeshcontact);
        localtour = (LinearLayout) view.findViewById(R.id.localtour);
        contactcholodesh = (TextView) view.findViewById(R.id.contactcholodesh);
        localtourtv = (TextView) view.findViewById(R.id.localtourtv);

//
////        createTripPlan = (Button) view.findViewById(R.id.btn_trip_plan);
////        myTrip = (Button) view.findViewById(R.id.btn_my_trips);
////        pacakages = (Button) view.findViewById(R.id.btn_packages);
////        destination = (Button) view.findViewById(R.id.btn_destination);
////        transportation = (Button) view.findViewById(R.id.btn_transportation);
////        accomodation = (Button) view.findViewById(R.id.btn_accomodation);
//
        pkgforlang = (TextView) view.findViewById(R.id.pkgforlang);
        crtforlang = (TextView) view.findViewById(R.id.crtforlang);
        plansforlang = (TextView) view.findViewById(R.id.plansforlang);
        desforlang = (TextView) view.findViewById(R.id.desforlang);
        accforlang = (TextView) view.findViewById(R.id.accforlang);
        trnsforlang = (TextView) view.findViewById(R.id.trnsforlang);

        if (!BaseURL.LANGUAGE_ENG) {
            getActivity().setTitle("মেনু");
            pkgforlang.setText("প্যাকেজ");
            crtforlang.setText("পরিকল্পনা তৈরী করুন");
            plansforlang.setText("আমার পরিকল্পনা");
            desforlang.setText("গন্তব্য");
            accforlang.setText("বাসস্থান");
            trnsforlang.setText("পরিবহন");
            contactcholodesh.setText("চলদেশ যোগাযোগ");
            localtourtv.setText("লোকাল ট্যুর");

        } else getActivity().setTitle("Menu");
        createTripPlan.setOnClickListener(this);
        myTrip.setOnClickListener(this);
        pacakages.setOnClickListener(this);
        destination.setOnClickListener(this);
        transportation.setOnClickListener(this);
        accomodation.setOnClickListener(this);
        localtour.setOnClickListener(this);
        cholodeshcontact.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cholodeshcontact:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;

            case R.id.localtour:
                startActivity(new Intent(getActivity(), LocalTourActivity.class));
                break;

            case R.id.btn_trip_plan:
                TripPlannerFragment tripplannerFragment = TripPlannerFragment.newInstance();
                loadFragment(tripplannerFragment);
                break;
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                //fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.listFragment,tripplannerFragment,"Third");
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

            case R.id.btn_my_trips:
                sharedPreferences = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                String customerEmail = sharedPreferences.getString(Travel.USER_EMAIL, null);
                if (customerEmail != null) {

                    TailormadeListFragment tailormadeListFragment = TailormadeListFragment.newInstance();
                    loadFragment(tailormadeListFragment);
                } else {
                    BaseURL.FRAGMENT_OF_MAIN = "MY_TRIPS";
                    Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(loginIntent, 110);
                }


//                Intent intent = new Intent(getActivity(), TailorMadeActivity.class);
//                intent.putExtra("TAILOR_MADE_ID","4");
//                startActivity(intent);
                break;

            case R.id.btn_packages:
                PackageFragment packageFragment = PackageFragment.newInstance();
                loadFragment(packageFragment);
                break;

            case R.id.btn_destination:
                DestinationFragment destinationFragment = DestinationFragment.newInstance();
                loadFragment(destinationFragment);
                break;


            case R.id.btn_transportation:
//                BookingFragment bookingFragment=BookingFragment.newInstance();
//                loadFragment(bookingFragment);
                Transportation transportationFragment = Transportation.newInstance();
                loadFragment(transportationFragment);
                break;


            case R.id.btn_accomodation:
                AccomodationFragment accomodationFragment = AccomodationFragment.newInstance();
                loadFragment(accomodationFragment);
                break;


        }
    }

    private void postReview() {
        AuthCallback authCallback = new AuthCallback(getActivity());
        Review review = new Review();
        review.setReviewComments("demo comments");
        review.setReviewId("0");
        review.setReviewRating("3.5");
        review.setReviewTrackId("1");
        review.setReviewType("Accommodation");
        review.setReviewUserId("01680000001");

        authCallback.SendReview(review).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.listFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
