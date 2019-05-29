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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.DestinationActivity;
import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.ArrayList;
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

public class DestinationFragment extends Fragment {



    TextView destinatinoForChangeLang, whereToGooForChangeLang;
    Button btnSearch;


    @BindView(R.id.destinationSearchAutoComplete)
    AutoCompleteTextView destinationAutoComplete;
    // callback
    PlaceCallback placeCallback;
    ProvideCallback provideCallback;
    // TODO local storage
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private List<DestinationName> destinationNames;
    private Realm realm;
    private Context context;


    public DestinationFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DestinationFragment newInstance() {
        DestinationFragment fragment = new DestinationFragment();
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
        ButterKnife.bind(this,view);
        getActivity().setTitle("Destination");

        // Data storage init
        sharedPreferences=getActivity().getSharedPreferences(getString(R.string.preference_file_key),getActivity().MODE_PRIVATE);
        editor=sharedPreferences.edit();
        Realm.init(getActivity());
        realm=Realm.getDefaultInstance();
        // TODO callback init
        placeCallback=new PlaceCallback(getActivity());
        provideCallback = new ProvideCallback(getActivity());

        destinatinoForChangeLang = (TextView) view.findViewById(R.id.destinationNameForChange);
        whereToGooForChangeLang = (TextView) view.findViewById(R.id.whereToGoForChange);
        btnSearch = (Button) view.findViewById(R.id.searchDestinationButton);

        changeLangView();
        return view;
    }

    private void changeLangView()
    {
        if (!BaseURL.LANGUAGE_ENG)
        {
            destinatinoForChangeLang.setText("গন্তব্য");
            whereToGooForChangeLang.setText("আপনি কোথায় যাবেন?");
            destinationAutoComplete.setHint("ঢাকা");
            btnSearch.setText("অনুসন্ধান");
            getActivity().setTitle("গন্তব্য");
            destinationAutoComplete.setText("");

        }
        else

        {
            destinatinoForChangeLang.setText("Location");
            whereToGooForChangeLang.setText("Where do you want to go?");
            destinationAutoComplete.setHint("Dhaka");
            btnSearch.setText("Search");
            getActivity().setTitle("Destination");
            destinationAutoComplete.setText("");
        }
        loadLocations();
    }
    @OnClick(R.id.searchDestinationButton)
    public void searchRoute(View view)

    {

        if (destinationNames == null)
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
        String destinationDistrictName = "";
        Intent intent=new Intent(getActivity(), DestinationActivity.class);
        for (DestinationName destinationName:destinationNames){


            //Toast.makeText(getActivity(),"["+destinationName.getDestinationName()+"]"+"  "+"{"+destinationlocation+"}",Toast.LENGTH_LONG).show();
            if (BaseURL.LANGUAGE_ENG && destinationName.getDestinationName().equalsIgnoreCase(destinationlocation.trim())){
                destinationDistrictId=destinationName.getDestinationId();
                destinationDistrictName = destinationName.getDestinationName();
                break;
            }
            else if ( !BaseURL.LANGUAGE_ENG &&destinationName.getDestinationNameBn()!= null && destinationName.getDestinationNameBn().trim().equalsIgnoreCase(destinationlocation.trim()))
            {
                destinationDistrictId=destinationName.getDestinationId();
                destinationDistrictName = destinationName.getDestinationName();
                break;
            }
        }

        if (destinationDistrictId != -1)
        {
            intent.putExtra("DESTINATION_ID", destinationDistrictId);
            intent.putExtra("DESTINATION_NAME",destinationDistrictName);

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


    @Override
    public void onResume() {
        super.onResume();
//        DestinationFragment nextFrag= new DestinationFragment();
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.listFragment, nextFrag,"Seventh")
//                .addToBackStack(null)
//                .commit();
       // changeLangView();

    }

    // load all districts in spinner
    private void loadLocations(){


        ////main
        provideCallback.getDestinations().enqueue(new CustomCallBack<DestinationName[]>(getActivity())

        {
            @Override
            public void onResponse(Call<DestinationName[]> call, Response<DestinationName[]> response) {
                super.onResponse(call, response);
                if (response.body()!= null && response.body().length>0)
                {
                    destinationNames= Arrays.asList(response.body());
                    //ArrayAdapter<DestinationName> placeAdapter=new ArrayAdapter<DestinationName>(getActivity().getApplicationContext(),R.layout.layout_spinner,R.id.spinnerText,destinationNames);
                    String test2 [] = {"রামু","সেন্টমার্টিন","মহেশখালী","টেকনাফ","খাগড়াছড়ি"};
                    String test3 [] = new String[3];
                    String test [] = new String[destinationNames.size()];
                    ArrayList<String> listchk = new ArrayList<String>();
                    if (BaseURL.LANGUAGE_ENG)
                    {
                        for (int i= 0; i< destinationNames.size();i++)
                        {
                            listchk.add( destinationNames.get(i).getDestinationName());
                        }
                        //listchk = Arrays.asList(test);

                    }
                    else  //bangla
                    {
                        for (int i= 0; i<destinationNames.size();i++)
                       {
                             //test[i] = destinationNames.get(i).getDestinationNameBn();
                            //listchk.add(test2[i]);
                           //Toast.makeText(getActivity(),"Adding "+ test[i] + " from "+test2[i],Toast.LENGTH_SHORT).show();
                           if (destinationNames.get(i).getDestinationNameBn()!= null )listchk.add(destinationNames.get(i).getDestinationNameBn());
                       }
                        test = test2;

                    }

                    ArrayAdapter<String> testAdapter = new ArrayAdapter<String>(context,R.layout.layout_spinner,R.id.spinnerText,listchk);
                    //locationAutoComplete.setAdapter(placeAdapter);
                    destinationAutoComplete.setAdapter(testAdapter);
                    //Toast.makeText(getActivity(),destinationNames.get(0).toString(),Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String meesage ="Could Not Load Loacations";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage =" গন্তব্য লোড করা যায় নি ";

                    }
                    Toast.makeText(getActivity(),meesage,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DestinationName[]> call, Throwable t) {
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
