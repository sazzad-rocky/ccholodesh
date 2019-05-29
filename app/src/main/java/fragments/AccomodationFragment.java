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
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.HotelListActivity;
import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import callbacks.PlaceCallback;
import callbacks.ProvideCallback;
import helpers.BaseURL;
import helpers.CustomCallBack;
import io.realm.Realm;
import model.*;
import retrofit2.Call;
import retrofit2.Response;
/**
 * Created by rhythmshahriar on 7/10/17.
 */
public class AccomodationFragment extends Fragment {
    TextView destinatinoForChangeLang, whereToGooForChangeLang;
    Button btnSearch;
    ScrollView scrl;
    @BindView(R.id.destinationSearchAutoComplete)
    AutoCompleteTextView destinationAutoComplete;
    // callback
    PlaceCallback placeCallback;
    ProvideCallback provideCallback;
    // TODO local storage
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<DestinationName> destinationNames;
    private List <Place> places;
    private Realm realm;
    private Context context;
    public AccomodationFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static AccomodationFragment newInstance() {
        AccomodationFragment fragment = new AccomodationFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_destination_new,container,false);
        //scrl.setBackground(R.drawable.img_destination_cover);
        //scrl.setBackgroundDrawable( getResources().getDrawable(R.drawable.img_destination_cover) );
        scrl = (ScrollView) view.findViewById(R.id.scrollViewBack);
        scrl.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.hotel_background));
        ButterKnife.bind(this,view);
        getActivity().setTitle("Accomodation");
        BaseURL.accommodationRooms.clear();
        // Data storage init
        sharedPreferences=getActivity().getSharedPreferences(getString(R.string.preference_file_key),getActivity().MODE_PRIVATE);
        editor=sharedPreferences.edit();
        Realm.init(getActivity());
        realm=Realm.getDefaultInstance();
        // TODO callback init
        placeCallback=new PlaceCallback(getActivity());
        provideCallback = new ProvideCallback(getActivity());
        destinationAutoComplete.setText("");
        destinatinoForChangeLang = (TextView) view.findViewById(R.id.destinationNameForChange);
        whereToGooForChangeLang = (TextView) view.findViewById(R.id.whereToGoForChange);
        btnSearch = (Button) view.findViewById(R.id.searchDestinationButton);
        if (!BaseURL.LANGUAGE_ENG)
        {
            destinatinoForChangeLang.setText("গন্তব্য");
            whereToGooForChangeLang.setText("আপনি কোথায় যাবেন?");
            destinationAutoComplete.setHint("ঢাকা");
            btnSearch.setText("অনুসন্ধান");
            getActivity().setTitle("বাসস্থান");
        }
        else{
            destinatinoForChangeLang.setText("Location");
        }
        loadLocations();
        return view;
    }
    @OnClick(R.id.searchDestinationButton)
    public void searchRoute(View view)
    {
        if (places == null)
        {
            String message = "No Location Loaded";
            if (!BaseURL.LANGUAGE_ENG){
                message = "কোনো জায়গা লোড হয় নি   ";
            }
            Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
            return;
        }
        String destinationlocation=destinationAutoComplete.getText().toString();
        int destinationDistrictId = -1;
        Intent intent=new Intent(getActivity(), HotelListActivity.class);
//        for (DestinationName destinationName:destinationNames){
//            if (destinationName.getDestinationName().equalsIgnoreCase(destinationlocation)){
//                destinationDistrictId=destinationName.getDestinationId();
//                Toast.makeText(getActivity(),destinationDistrictId+ " ",Toast.LENGTH_LONG).show();
//                break;
//            }
//        }

        for (Place place:places){
            //Toast.makeText(getActivity(),"{"+place.getDistrictNameBn()+"}"+ " "+"["+destinationlocation+"]",Toast.LENGTH_LONG).show();
            if (place.getDistrictName().equalsIgnoreCase(destinationlocation) || place.getDistrictNameBn().equalsIgnoreCase(destinationlocation)){
                destinationDistrictId=place.getDistrictId();

                //Toast.makeText(getActivity(),destinationDistrictId+ " ",Toast.LENGTH_LONG).show();
                break;
            }
        }

        if (destinationDistrictId != -1)
        {
            intent.putExtra("DESTINATION_ID", destinationDistrictId);
            startActivity(intent);
        }
        else
        {
            String meesage ="Destination Doesn't exist";
            if (!BaseURL.LANGUAGE_ENG)
            {
                meesage =" গন্তব্য নেই";

            }
            Toast.makeText(getActivity(),meesage,Toast.LENGTH_LONG).show();

        }




    }


    // load all districts in spinner
    private void loadLocations(){

        placeCallback.getLocations().enqueue(new CustomCallBack<Place[]>(getActivity())

        {
            @Override
            public void onResponse(Call<Place[]> call, Response<Place[]> response) {
                super.onResponse(call, response);
                if (response.body()!= null && response.body().length>0)
                {
                    places= Arrays.asList(response.body());
                    ArrayAdapter<Place> placeAdapter=new ArrayAdapter<Place>(context,R.layout.layout_spinner,R.id.spinnerText,response.body());
                    //locationAutoComplete.setAdapter(placeAdapter);
                    destinationAutoComplete.setAdapter(placeAdapter);
                }
                else
                {
                    String meesage ="Could Not Load Loacations";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage ="গন্তব্য লোড করা যায় নি ";

                    }
                    Toast.makeText(getActivity(),meesage,Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Place[]> call, Throwable t) {
                super.onFailure(call, t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(getActivity(),meesage,Toast.LENGTH_LONG).show();
            }
        });




    }
}
