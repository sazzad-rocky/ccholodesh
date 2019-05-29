/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import 	java.util.ArrayList;

import callbacks.PlaceCallback;
import callbacks.ProvideCallback;
import callbacks.RouteCallback;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import model.Place;
import model.RouteNew;
import model.TransportInfo;
import model.TransportName;
import model.TransportType;
import model.TransportProvider;
import model.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Transportation.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Transportation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Transportation extends Fragment implements  AdapterView.OnItemSelectedListener{

    ExpandableListView route;

    Spinner chooseRoute,transporType,transportName, transportCategory;
    TextView interRoutes, transportCost,transportEstimatedTime,totalTrips,firstTrip,lastTrip,estimatedTime,fare,chooseRouteforLang,chooseTransportTypeforLang,chooseTransportNameforLang
    ,chooseTransportCtgryforLang,IntrroutesforLang,totaltripforLang,firsttripforLang,lasttripforLang,estimatedtimeforLang,fareforLang;
    private OnFragmentInteractionListener mListener;
    private List<Place> places;
    private List<TransportInfo> transportInfos;
    private List<RouteNew> routesNew;
    private List<TransportProvider> providers;
    PlaceCallback placeCallback;
    ProvideCallback provideCallBack;
    private List<Route> routes;
    private RouteCallback routeCallback;
    private Context context;

    public Transportation() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Transportation newInstance() {
        Transportation fragment = new Transportation();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.transportation_new, container, false);
        context = getActivity().getApplicationContext();


        //route = (ExpandableListView) view.findViewById(R.id.chooseRoute);
        chooseRoute = (Spinner) view.findViewById(R.id.cRoute);
        transporType = (Spinner) view.findViewById(R.id.transportType);
        transportName = (Spinner) view.findViewById(R.id.transportName);
        transportCategory = (Spinner) view.findViewById(R.id.transportCategory);



        chooseRouteforLang = (TextView) view.findViewById(R.id.chooseRouteforLang);
        chooseTransportTypeforLang = (TextView) view.findViewById(R.id.chooseTransportTypeforLang);
        chooseTransportNameforLang = (TextView) view.findViewById(R.id.chooseTransportNameforLang);
        chooseTransportCtgryforLang = (TextView) view.findViewById(R.id.chooseTransportCtgryforLang);
        IntrroutesforLang = (TextView) view.findViewById(R.id.IntrroutesforLang);
        totaltripforLang = (TextView) view.findViewById(R.id.totaltripforLang);
        firsttripforLang = (TextView) view.findViewById(R.id.firsttripforLang);
        lasttripforLang = (TextView) view.findViewById(R.id.lasttripforLang);
        estimatedtimeforLang = (TextView) view.findViewById(R.id.estimatedtimeforLang);
        fareforLang = (TextView) view.findViewById(R.id.fareforLang);

        if (!BaseURL.LANGUAGE_ENG)
        {
            getActivity().setTitle("রুট");
            chooseRouteforLang.setText("রুট নির্বাচন করুন");
            chooseTransportTypeforLang.setText("পরিবহন প্রকার");
            chooseTransportNameforLang.setText("পরিবহন নাম");
            chooseTransportCtgryforLang.setText("পরিবহন শ্রেণী");
            IntrroutesforLang.setText("আন্তঃ রুট");
            totaltripforLang.setText("মোট ট্রিপ: ");
            firsttripforLang.setText("প্রথম ট্রিপ: ");
            lasttripforLang.setText("সর্বশেষ ট্রিপ: ");
            estimatedtimeforLang.setText("আনুমানিক সময়: ");
            fareforLang.setText("ভাড়া: ");

        }
        else
        {
            getActivity().setTitle("Routes");
            chooseRouteforLang.setText("Choose Route");
            chooseTransportTypeforLang.setText("Transport Type");
            chooseTransportNameforLang.setText("Transport Name");
            chooseTransportCtgryforLang.setText("Transport Category");
            IntrroutesforLang.setText("INTER ROUTES");
            totaltripforLang.setText("TOTAL TRIP: ");
            firsttripforLang.setText("FIRST TRIP:  ");
            lasttripforLang.setText("LAST TRIP:   ");
            estimatedtimeforLang.setText("ESTIMATED TIME: ");
            fareforLang.setText("FARE: ");
        }

        interRoutes = (TextView) view.findViewById(R.id.interRoutes);
        transportCost = (TextView) view.findViewById(R.id.fare);
        transportEstimatedTime = (TextView) view.findViewById(R.id.estimatedTime);
        totalTrips = (TextView) view.findViewById(R.id.totalTrip);
        firstTrip = (TextView) view.findViewById(R.id.firstTrip);
        lastTrip = (TextView) view.findViewById(R.id.lastTrip);
        estimatedTime = (TextView) view.findViewById(R.id.estimatedTime);
        fare = (TextView) view.findViewById(R.id.fare);

        reset();

        placeCallback=new PlaceCallback(getActivity());
        provideCallBack = new ProvideCallback(getActivity());
        routeCallback = new RouteCallback(getActivity());
        //Toast.makeText(getActivity(),"working",Toast.LENGTH_LONG);



        chooseRoute.setOnItemSelectedListener(this);
        transporType.setOnItemSelectedListener(this);
        transportName.setOnItemSelectedListener(this);
        loadLocations();
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void loadLocations(){


        provideCallBack.getRoutes().enqueue(new CustomCallBack<RouteNew[]>(getActivity()) {
            @Override
            public void onResponse(Call<RouteNew[]> call, Response<RouteNew[]> response) {
                super.onResponse(call,response);
                if (response.body() != null)
                {
                    ArrayAdapter<RouteNew> placeAdapter=new ArrayAdapter<RouteNew>(context,R.layout.layout_spinner,R.id.spinnerText,response.body());
                    chooseRoute.setAdapter(placeAdapter);
                }
                Log.e("Package Url",call.request().url().toString());
                //routesNew = Arrays.asList(response.bo)


            }

            @Override
            public void onFailure(Call<RouteNew[]> call, Throwable t) {
                super.onFailure(call, t);
            }
        });


    }

    private void loadTransportTypes (int routeId)
    {
        provideCallBack.getTransportTypes(routeId).enqueue(new CustomCallBack<TransportType[]>(getActivity()){

            @Override
            public void onResponse(Call<TransportType[]> call, Response<TransportType[]> response) {
                super.onResponse(call, response);
                Log.e("Package Url",call.request().url().toString());
                if (response.body()!=null && response.body().length > 0)
                {
                    ArrayAdapter<TransportType> placeAdapter=new ArrayAdapter<TransportType>(context,R.layout.layout_spinner,R.id.spinnerText,response.body());
                    transporType.setAdapter(placeAdapter);
                }
                else
                {

                    TransportType [] NULL = new TransportType[1];
                    NULL[0] = new TransportType();
                    //NULL[0].setTypeName("N/A");
                    ArrayAdapter<TransportType> placeAdapter=new ArrayAdapter<TransportType>(context,R.layout.layout_spinner,R.id.spinnerText,NULL);
                    transporType.setAdapter(placeAdapter);

                    TransportName[] transportNames = new TransportName[1];
                    transportNames[0] = new TransportName();
                    ArrayAdapter<TransportName> nameAdapter=new ArrayAdapter<TransportName>(context,R.layout.layout_spinner,R.id.spinnerText,transportNames);
                    transportName.setAdapter(nameAdapter);
                    String[] ctgry = new String[1];
                    if (!BaseURL.LANGUAGE_ENG)ctgry[0] = "পাওয়া যায়নি";
                    else ctgry[0] = "N/A";
                    ArrayAdapter<String> ctgryName = new ArrayAdapter<String>(context,R.layout.layout_spinner,R.id.spinnerText,ctgry);
                   // transportName.setAdapter(null);
                    transportCategory.setAdapter(ctgryName);
                    reset();
                    String meesage ="Nothing Found for This Route";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage =" এই রাস্তা জন্য কিছুই পাওয়া যায়নি";

                    }
                    Toast.makeText(getActivity(), meesage, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<TransportType[]> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

//

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        Spinner spinner = (Spinner) parent;
        switch (spinner.getId()){


            case R.id.cRoute:
                RouteNew nw = (RouteNew) chooseRoute.getSelectedItem();
                //Toast.makeText(getActivity(),nw.getRouteId().toString(),Toast.LENGTH_LONG).show();
                loadTransportTypes(nw.getRouteId());

                break;

            case R.id.transportType:

                nw = (RouteNew) chooseRoute.getSelectedItem();
                TransportType transportType = (TransportType) transporType.getSelectedItem();
                loadTransportOperators (transportType.getTransportTypeId(),nw.getRouteId());
                break;

            case R.id.transportName:
                //Toast.makeText(getActivity(),"wo",Toast.LENGTH_LONG).show();
                nw = (RouteNew) chooseRoute.getSelectedItem();
                transportType = (TransportType) transporType.getSelectedItem();
                TransportName transportNames = (TransportName) transportName.getSelectedItem();
                loadTransportInfo (transportNames.getTransportInfoId(),nw.getRouteId());
                //loadTransportOperators (transportType.getTransportTypeId(),nw.getRouteId());
                break;



        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void loadTransportOperators (int type, int route)
    {

        provideCallBack.getTransportNames(type,route).enqueue(new CustomCallBack<TransportName[]>(getActivity()){

            @Override
            public void onResponse(Call<TransportName[]> call, Response<TransportName[]> response) {
                super.onResponse(call, response);
                Log.e("Name Url",call.request().url().toString());
                if (response.body() != null && response.body().length> 0)
                {

                    ArrayAdapter<TransportName> placeAdapter=new ArrayAdapter<TransportName>(context,R.layout.layout_spinner,R.id.spinnerText,response.body());
                    transportName.setAdapter(placeAdapter);
                }
                else
                {
                    TransportName[] transportNames = new TransportName[1];
                    transportNames[0] = new TransportName();
                    ArrayAdapter<TransportName> placeAdapter=new ArrayAdapter<TransportName>(context,R.layout.layout_spinner,R.id.spinnerText,transportNames);
                    transportName.setAdapter(placeAdapter);
                    String[] ctgry = new String[1];
                    if (!BaseURL.LANGUAGE_ENG)ctgry[0] = "পাওয়া যায়নি";
                    else ctgry[0] = "N/A";
                    ArrayAdapter<String> ctgryName = new ArrayAdapter<String>(context,R.layout.layout_spinner,R.id.spinnerText,ctgry);
                    // transportName.setAdapter(null);
                    transportCategory.setAdapter(ctgryName);
                    //transportName.setAdapter(null);
                    //transportCategory.setAdapter(null);
                    reset();
                    String meesage ="No Operator Found for This Route";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage =" এই রুট জন্য কোন অপারেটর পাওয়া যায় নি ";

                    }
                    Toast.makeText(getActivity(), meesage, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(),"No Operator Found for This Route",Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<TransportName[]> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    private void loadTransportInfo (final int transpoerInfoId, int routeId)
    {
        //Toast.makeText(getActivity(),transpoerInfoId+" "+routeId,Toast.LENGTH_LONG).show();
        provideCallBack.getTransportInfo(transpoerInfoId,routeId).enqueue(new CustomCallBack<TransportInfo[]>(getActivity())
        {

            @Override
            public void onResponse(Call<TransportInfo[]> call, Response<TransportInfo[]> response) {
                super.onResponse(call, response);
                if (response.body() == null || response.body().length == 0)
                {
                    return;
                }
                transportInfos= Arrays.asList(response.body());


                List <String> transportCategories = new ArrayList<>();
                for (int i = 0; i<transportInfos.size();i++)
                {
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        if (!transportCategories.contains(transportInfos.get(i).getGetTransportOperatorTypeBn()))
                        {

                            transportCategories.add(transportInfos.get(i).getGetTransportOperatorTypeBn());
                        }
                    }
                    else
                    {
                        if (!transportCategories.contains(transportInfos.get(i).getTransportOperatorType()))
                        {

                            transportCategories.add(transportInfos.get(i).getTransportOperatorType());
                        }
                    }



                }

                    ArrayAdapter<String> typeAdapter=new ArrayAdapter<String>(context,R.layout.layout_spinner,R.id.spinnerText,transportCategories);
                    if (transportCategories.size()== 0)
                    {
                        String[] ctgry = new String[1];
                        if (!BaseURL.LANGUAGE_ENG) ctgry[0] = "পাওয়া যায় নি";
                        else ctgry[0] = "N/A";
                        typeAdapter=new ArrayAdapter<String>(context,R.layout.layout_spinner,R.id.spinnerText,ctgry);

                    }
                    transportCategory.setAdapter(typeAdapter);



                //getting the start place and destination form inter routes
                if (transportInfos.size()==0)
                {
                    return;
                }
                try{
                    int sourceId = transportInfos.get(0).getRouteInfoStartId();
                    int destinationId = transportInfos.get(transportInfos.size()-1).getRouteInfoEndId();

                    loadInterRoutes(sourceId,destinationId);

                }catch (Exception ex){
                    interRoutes.setText("N/A");

                }

                //int sourceId = transportInfos.get(0).getRouteInfoStartId();
                //int destinationId = transportInfos.get(transportInfos.size()-1).getRouteInfoEndId();

                //loadInterRoutes(sourceId,destinationId);

                //am pm identification



                String firstTripTime="";
                String lastTripTime = "";
                try {
                    String _24HourTime = transportInfos.get(0).getTransportFirstTrip();
                    SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                    Date _24HourDt = _24HourSDF.parse(_24HourTime);

                    firstTripTime = _12HourSDF.format(_24HourDt);
                    _24HourTime = transportInfos.get(0).getTransportLaststTrip();
                    _24HourSDF = new SimpleDateFormat("HH:mm");
                    _12HourSDF = new SimpleDateFormat("hh:mm a");
                    _24HourDt = _24HourSDF.parse(_24HourTime);
                    lastTripTime = _12HourSDF.format(_24HourDt);

                } catch (Exception e) {
                    e.printStackTrace();
                }



                String trips = transportInfos.get(0).getNoOfTrips();

                if (!BaseURL.LANGUAGE_ENG){
                    trips = BanglaNumberParser.getBangla(trips);
                    firstTripTime = BanglaNumberParser.getBangla(firstTripTime);
                    lastTripTime = BanglaNumberParser.getBangla(lastTripTime);
                }
                //
                totalTrips.setText(trips);
                firstTrip.setText(firstTripTime);
                lastTrip.setText(lastTripTime);
                String hrs = "hrs";
                if (!BaseURL.LANGUAGE_ENG) hrs = "ঘন্টা";
                estimatedTime.setText(transportInfos.get(0).getTransportEstimatedTime()+" "+hrs);
                String cost = transportInfos.get(0).getTransportPrice();
                if (!BaseURL.LANGUAGE_ENG){
                    cost = BanglaNumberParser.getBangla(cost);
                }
                fare.setText(cost+ " ৳");









            }

            @Override
            public void onFailure(Call<TransportInfo[]> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    private void loadInterRoutes (int sourceId, int destinationId)
    {
        routeCallback.getRoutes(sourceId,destinationId).enqueue(new Callback<Route[]>() {
            @Override
            public void onResponse(Call<Route[]> call, Response<Route[]> response) {

                Log.e("Name Url",call.request().url().toString());
                if (response.body()!= null && response.body().length > 0)
                {
                    routes=Arrays.asList (response.body());
                    String interRoute = "";
                    for (int i = 0; i<routes.size();i++)
                    {
                        interRoute += " – "+routes.get(i).getStartDistrictName() + " - " +routes.get(i).getEndDistrictName() + "\n";
                    }
                    interRoutes.setText(interRoute.substring(0,interRoute.length()-1));
                }

                else
                {
                    reset();
                    String meesage ="No Inter Routes Found";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage =" কোন আন্তঃ রুট  পাওয়া যায় নি ";

                    }
                    Toast.makeText(getActivity(), meesage, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(),"No Inter Routes Found",Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<Route[]> call, Throwable t) {
                //Toast.makeText(TripRouteActivity.this, "Could not get Route Information", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void reset ()
    {
        if (!BaseURL.LANGUAGE_ENG)
        {

            interRoutes.setText("পাওয়া যায় নি");
            totalTrips.setText("পাওয়া যায় নি");
            firstTrip.setText("পাওয়া যায় নি");
            lastTrip.setText("পাওয়া যায় নি");
            estimatedTime.setText("পাওয়া যায় নি");
            fare.setText("পাওয়া যায় নি");

        }
        else
        {
            interRoutes.setText("N/A");
            totalTrips.setText("N/A");
            firstTrip.setText("N/A");
            lastTrip.setText("N/A");
            estimatedTime.setText("N/A");
            fare.setText("N/A");
        }

    }





}
