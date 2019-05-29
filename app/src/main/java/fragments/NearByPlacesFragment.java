/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.ArrayList;

import adapters.NearByPlaceAdapter;
import callbacks.ProvideCallback;
import helpers.BaseURL;
import helpers.CustomCallBack;
import model.NearByPlaces;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NearByPlacesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NearByPlacesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NearByPlacesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView demo;
    //GridView nearByPlaces;
    RecyclerView nearByPlaces;
    ImageView placeHolderImage;
    TextView placeHolderTextView;

    ProvideCallback provideCallback;

    private OnFragmentInteractionListener mListener;

    public NearByPlacesFragment() {
        // Required empty public constructor
    }


    public static NearByPlacesFragment newInstance(int sectionNumber ) {
        NearByPlacesFragment fragment = new NearByPlacesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        int destinationId = getArguments().getInt("DESTINATION_ID");
        View view =inflater.inflate(R.layout.fragment_near_by_places, container, false);

        provideCallback = new ProvideCallback(getActivity());
        nearByPlaces = (RecyclerView) view.findViewById(R.id.nearByPlacses);
        placeHolderImage = (ImageView) view.findViewById(R.id.placeHolderImage);
        placeHolderTextView = (TextView) view.findViewById(R.id.placeHolderText);
        if (!BaseURL.LANGUAGE_ENG)
        {
            placeHolderTextView.setText("এই স্থানটির জন্য কোন কাছাকাছি জায়গা খুঁজে পাওয়া যায় নি");
        }

        loadNearByPlaces(destinationId);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void loadNearByPlaces(int destinationId) {
        BaseURL.nearByPlaceids = new ArrayList<>();
        provideCallback=new ProvideCallback(getActivity());

        provideCallback.getDestinationNearByPlaces(destinationId).enqueue(new CustomCallBack<NearByPlaces[]>(getActivity()) {
            @Override
            public void onResponse(Call<NearByPlaces[]> call, Response<NearByPlaces[]> response) {


                if(response.body()!= null && response.body().length > 0){
                    NearByPlaceAdapter packageAdapter=new NearByPlaceAdapter(getActivity(),response.body());
                    for (int i =0 ; i < response.body().length;i++)
                    {

                        BaseURL.nearByPlaceids.add(response.body()[i].getDestinationNearbyPlaceId());
                        //Toast.makeText(getContext(),"Adding "+ response.body()[i].getDestinationNearbyPlaceId(),Toast.LENGTH_SHORT).show();
                    }
                    nearByPlaces.setAdapter(packageAdapter);

                }
                else
                {
                    placeHolderImage.setVisibility(View.VISIBLE);
                    placeHolderTextView.setVisibility(View.VISIBLE);
                    //Toast.makeText(getActivity(),"No Near by Place Found for this Place",Toast.LENGTH_LONG).show();
                }
                //placeHolderImage.setVisibility(View.GONE);
                super.onResponse(call, response);


            }

            @Override
            public void onFailure(Call<NearByPlaces[]> call, Throwable t) {
                super.onFailure(call, t);
                placeHolderImage.setVisibility(View.VISIBLE);
                placeHolderTextView.setVisibility(View.VISIBLE);

                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

               }
                placeHolderTextView.setText(meesage);
               // Toast.makeText(getActivity(), meesage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
