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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;

import adapters.AttractionAdapter;
import callbacks.ProvideCallback;
import helpers.BaseURL;
import model.DestinationAttraction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Attraction extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //GridView attractions;
    RecyclerView attractions;
    ImageView placeHolderImage;
    TextView placeHolderTextView;
    ProvideCallback provideCallback;

    private OnFragmentInteractionListener mListener;

    public Attraction() {
        // Required empty public constructor
    }


    public static Attraction newInstance(int sectionNumber ) {
        Attraction fragment = new Attraction();
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


        int destinationId = getArguments().getInt("DESTINATION_ID");

        View view =inflater.inflate(R.layout.fragment_attraction, container, false);

        provideCallback = new ProvideCallback(getActivity());
        attractions = (RecyclerView) view.findViewById(R.id.attractions);
        placeHolderImage = (ImageView) view.findViewById(R.id.placeHolderImage);
        placeHolderTextView = (TextView) view.findViewById(R.id.placeHolderText);
        loadAttractions(destinationId);
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

    private void loadAttractions(int destinationId) {
        provideCallback=new ProvideCallback(getActivity());

        provideCallback.getDestinationAttractions(destinationId).enqueue(new Callback<DestinationAttraction[]>() {
            @Override
            public void onResponse(Call<DestinationAttraction[]> call, Response<DestinationAttraction[]> response) {

                //Log.e("Package Url",call.request().url().toString());
                if(response.body()!= null && response.body().length > 0)
                {
                    AttractionAdapter attractionAdapter=new AttractionAdapter(getActivity(),response.body());
                    attractions.setAdapter(attractionAdapter);
                }
                //placeHolderImage.setVisibility(View.GONE);

                else
                {
                    placeHolderImage.setVisibility(View.VISIBLE);
                    placeHolderTextView.setVisibility(View.VISIBLE);
                    if(!BaseURL.LANGUAGE_ENG)
                    {
                        placeHolderTextView.setText("এই স্থানটির জন্য কোন আকর্ষণ খুঁজে পাওয়া যায় নি");
                    }
                    else
                    {
                        placeHolderTextView.setText("No Attraction Found for this Place");
                    }

                    //Toast.makeText(getActivity(),"No Attraction Found for this Place",Toast.LENGTH_LONG);
                }


            }

            @Override
            public void onFailure(Call<DestinationAttraction[]> call, Throwable t) {

                //placeHolderImage.setVisibility(View.VISIBLE);
                placeHolderImage.setVisibility(View.VISIBLE);
                placeHolderTextView.setVisibility(View.VISIBLE);

                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                placeHolderTextView.setText(meesage);
            }
        });
    }
}
