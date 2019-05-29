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

import adapters.EmergencyAdapter;
import callbacks.ProvideCallback;
import helpers.BaseURL;
import model.DestinationEmergency;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Emergency extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //GridView emergencies;
    RecyclerView emergencies;
    ProvideCallback provideCallback;
    ImageView placeHolderImage;
    TextView placeHolderTextView;
    private OnFragmentInteractionListener mListener;

    public Emergency() {
        // Required empty public constructor
    }

    public static Emergency newInstance(int sectionNumber ) {
        Emergency fragment = new Emergency();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        int destinationId = getArguments().getInt("DESTINATION_ID");
        View view =inflater.inflate(R.layout.fragment_emergency, container, false);

        provideCallback = new ProvideCallback(getActivity());
        emergencies = (RecyclerView) view.findViewById(R.id.emergencies);
        placeHolderImage = (ImageView) view.findViewById(R.id.placeHolderImage);
        placeHolderTextView = (TextView) view.findViewById(R.id.placeHolderText);

        loadEmergencies (destinationId);
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

    private void loadEmergencies (int destinationId)
    {
        provideCallback=new ProvideCallback(getActivity());

        provideCallback.getDestinationEmergencies(destinationId).enqueue(new Callback<DestinationEmergency[]>() {
            @Override
            public void onResponse(Call<DestinationEmergency[]> call, Response<DestinationEmergency[]> response) {

                //Log.e("Package Url",call.request().url().toString());
                if(response.body()!= null && response.body().length > 0)
                {
                    //placeHolderImage.setVisibility(View.VISIBLE);
                    EmergencyAdapter packageAdapter=new EmergencyAdapter(getActivity(),response.body());
                    emergencies.setAdapter(packageAdapter);

                }
                else
                {
                    placeHolderImage.setVisibility(View.VISIBLE);
                    placeHolderTextView.setVisibility(View.VISIBLE);

                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        placeHolderTextView.setText("এই স্থানটির জন্য কোন জরুরী যোগাযোগ খুঁজে পাওয়া যায় নি");
                    }
                    else
                    {
                        placeHolderTextView.setText("No Emergency Found for this Place");
                    }
                    //Toast.makeText(getActivity(),"No Emergency Found for this Place",Toast.LENGTH_LONG).show();
                }
                //placeHolderImage.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<DestinationEmergency[]> call, Throwable t) {

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
