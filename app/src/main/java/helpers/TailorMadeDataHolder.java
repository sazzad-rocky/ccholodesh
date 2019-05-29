/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.TailorMadeEditActivity;

import java.util.ArrayList;
import java.util.List;

import callbacks.ProvideCallback;
import model.AccommodationRoom;
import model.CustomTripPlanNewRouteGetModel;
import model.Itenerary;
import model.Route;
import model.TailorMadeAccommodation;
import model.TailorMadeItinerary;
import model.TailorMadeTransport;
import model.TourOperator;
import model.TourOperatorModel;
import model.TransportProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rhythmshahriar on 11/14/17.
 */

public class TailorMadeDataHolder {

    public static ArrayList<String> distrcis;
    public static ArrayList<Route> routes;
    public static ArrayList<TransportProvider> transportProviders;
    public static ArrayList<TransportProvider> prevTransProviders = new ArrayList<>();
    public static List<TourOperator> tourOperator = new ArrayList<>();
    public static ArrayList<AccommodationRoom> accommodationRooms;
    public static ArrayList<AccommodationRoom> routeAccommodations;
    public static ArrayList<String> checkDates;
    public static ArrayList<CustomTripPlanNewRouteGetModel> getNewRoutesModels;
    public static ArrayList<Itenerary> iteneraries;
    public static int tailorMadeId;
    public static String departDate;
    public static String returnDate;
    public static String noOfTourists;
    public static String noOfDays;
    //edit start
    public static String return_status;
    public static String return_massage;
    //edit end


    public static int accommodationCost;
    public static int transPortatationCost;
    public static int prevTransportCost;
    public static int locaTourCost;
    public static int totalCost;
    public static String ROUTE = "";
    public static String Status;
    public static String providerName;
    public static String biddingAmount;
    public static String confDate;
    public static String providerImage;
    public static String noOfChildren;
    public static String childrenDesc;

    //start sazzad
    public static String checkin_date ="";
    public static String checkout_date="";

    //end sazzad
    public static ArrayList<String> destNames;
    public static void makeRoutes(final int tailorMadeId, final Activity context){
        //tailorMadeId = 11;
        routes = new ArrayList<>();
        distrcis = new ArrayList<>();
        getNewRoutesModels = new ArrayList<>();
        transportProviders = new ArrayList<>();
        ProvideCallback provideCallback = new ProvideCallback(context);
        provideCallback.getTailorMadeTransport(tailorMadeId+"").enqueue(new Callback<TailorMadeTransport[]>() {
            @Override
            public void onResponse(Call<TailorMadeTransport[]> call, Response<TailorMadeTransport[]> response) {
                Log.e("Package Url",call.request().url().toString());

                if (response.body()!=null && response.body().length > 0)
                {

                    for (int i =0 ;i<response.body().length;i++)
                    {

                        TailorMadeTransport tailorMadeTransport = response.body()[i];

                        Route route = new Route();
                        CustomTripPlanNewRouteGetModel getNewRoute = new CustomTripPlanNewRouteGetModel();
                        TransportProvider transportProvider = new TransportProvider();
                        route.setRouteName(tailorMadeTransport.getRouteName());
                        if (tailorMadeTransport.getRouteId() != null)route.setRouteId(Integer.parseInt(tailorMadeTransport.getRouteId()));
                        route.setStartDistrictName(tailorMadeTransport.getStartDistrictName());
                        if (tailorMadeTransport.getStartDistrictId()!= null)route.setStartDistrictId(Integer.parseInt(tailorMadeTransport.getStartDistrictId()));
                        route.setEndDistrictName(tailorMadeTransport.getEndDistrictName());
                        if (tailorMadeTransport.getEndDistrictId()!= null) route.setEndDistrictId(Integer.parseInt(tailorMadeTransport.getEndDistrictId()));

                        if (tailorMadeTransport.getRouteId() != null)getNewRoute.setRouteId(Integer.parseInt(tailorMadeTransport.getRouteId()));
                        getNewRoute.setRouteName(tailorMadeTransport.getRouteName());


                        if (tailorMadeTransport.getTailormadeRouteTransportInfoPrice() != null){
                            transportProvider.setRouteId(Integer.parseInt(tailorMadeTransport.getRouteId()));
                            transportProvider.setRouteName(tailorMadeTransport.getRouteName());
                            transportProvider.setTransportInfoOperatorName(tailorMadeTransport.getTailormadeRouteTransportInfoOperatorName());
                            transportProvider.setTransportInfoEstimatedTime(tailorMadeTransport.getTailormadeRouteTransportInfoEstimatedTime());
                            transportProvider.setTransportInfoPrice(tailorMadeTransport.getTailormadeRouteTransportInfoPrice());
                            if (tailorMadeTransport.getTailormadeRouteTransportInfoId() != null)transportProvider.setTransportInfoId(Integer.parseInt(tailorMadeTransport.getTailormadeRouteTransportInfoId()));
                            if (tailorMadeTransport.getTailormadeRouteTransportId() != null)transportProvider.setTtId(Integer.parseInt(tailorMadeTransport.getTailormadeRouteTransportId()));
                            transportProvider.setTtName(tailorMadeTransport.getTailormadeRouteTransportName());

                            if (tailorMadeTransport.getTailormadeRouteTransportInfoPrice() != null){
                                transPortatationCost += Integer.parseInt(tailorMadeTransport.getTailormadeRouteTransportInfoPrice());
                                prevTransportCost+= Integer.parseInt(tailorMadeTransport.getTailormadeRouteTransportInfoPrice());
                            }

                            transportProviders.add(transportProvider);
                            prevTransProviders.add(transportProvider);
                            route.setTransportProvider(transportProvider);


                        }

                        if (tailorMadeTransport.getRouteId() != null)
                        {
                            getNewRoutesModels.add(getNewRoute);
                        }
                        routes.add(route);


                        distrcis.add(tailorMadeTransport.getStartDistrictName());
                        ROUTE += tailorMadeTransport.getStartDistrictName()+"-";
                        //Toast.makeText(context,"adding "+ tailorMadeTransport.getStartDistrictName(),Toast.LENGTH_SHORT).show();
                        if (i == response.body().length - 1){
                            distrcis.add(tailorMadeTransport.getEndDistrictName());
                            ROUTE += tailorMadeTransport.getEndDistrictName();
                        }


                    }
                    //makeDistricts(context);
                    //startActivity(context);
                    totalCost += transPortatationCost;
                    makeAccommodationProviders(tailorMadeId,context);
                    makeItenerary(tailorMadeId,context);



                }



                //Toast.makeText(TailorMadeActivity.this,response.body()[0].getTailormadeRouteTransportInfoOperatorName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TailorMadeTransport[]> call, Throwable t) {

            }
        });


    }
    public static void startActivity (Activity context){
        Intent intent = new Intent(context, TailorMadeEditActivity.class);

        //intent.putExtra("TAILOR_MADE_ID",tailorMade.getTailormadeId());
        //intent.putExtra("DESTINATION_IMAGE_PATH",url);
        //intent.putExtra("COST_TOTAL",tailorMade.getTailormadeTotalCost()+ " ৳");

        context.startActivity(intent);

    }

    public static void makeDistricts (Activity context){
//        distrcis = new ArrayList<>();
//
//        if (routes.size() > 1){
//            String route = routes.get(0).getRouteName();
//            String temp[] = route.split("-");
//            String district = temp[0];
//            distrcis.add(district);
//
//
//            for (int i = 1; i< routes.size()-2;i++){
//                route = routes.get(i).getRouteName();
//                temp = route.split("-");
//                district = temp[0];
//                distrcis.add(district);
//
//
//            }
//
//            route = routes.get(routes.size()-1).getRouteName();
//            temp = route.split("-");
//            district = temp[1];
//            distrcis.add(district);
//
//        }
//
//        else {
//            String route = routes.get(0).getRouteName();
//            String temp[] = route.split("-");
//            String district = temp[0];
//            distrcis.add(district);
//            district = temp[1];
//            distrcis.add(district);
//
//        }
        for (int i =0; i<distrcis.size();i++){
            //Log.e("District: ",distrcis.get(i));
            Toast.makeText(context, i+" "+distrcis.get(i) ,Toast.LENGTH_SHORT).show();
        }
    }

    public static void makeAccommodationProviders(int id, final Activity context){
        accommodationRooms = new ArrayList<>();
        ProvideCallback provideCallback = new ProvideCallback(context);
        provideCallback.getTailorMadeAccommodation(id+"").enqueue(new Callback<TailorMadeAccommodation[]>() {
            @Override
            public void onResponse(Call<TailorMadeAccommodation[]> call, Response<TailorMadeAccommodation[]> response) {

                Log.e("Package Url",call.request().url().toString());
                if (response.body()!= null && response.body().length > 0)
                {
                    for (int i = 0; i< response.body().length;i++){
                        TailorMadeAccommodation temp = response.body()[i];
                        AccommodationRoom accommodationRoom = new AccommodationRoom();
                        accommodationRoom.setDistrictName(temp.getTailormadeAccommodationDistrictName());
                        CustomTripPlanDataHolder.districtsName.add(temp.getTailormadeAccommodationDistrictName());
                        if (temp.getTailormadeAccommodationDistrictId() != null) accommodationRoom.setDistrictId(Integer.parseInt(temp.getTailormadeAccommodationDistrictId()));
                        if (temp.getTailormadeAccommodationRoomId() != null) accommodationRoom.setAccommodationRoomId(Integer.parseInt(temp.getTailormadeAccommodationRoomId()));
//                        accommodationRoom.setAccommodationRoomType(temp.getTailormadeAccommodationRoomTypeName().toString());
                        accommodationRoom.setAccommodationRoomMaxOccupancy(temp.getTailormadeAccommodationAccommodationRoomMaxOccupancy());
                        accommodationRoom.setAccommodationRoomPrice(temp.getTailormadeAccommodationAccommodationRoomPrice());
                        accommodationRoom.setAccommodationCategoryId(Integer.parseInt(temp.getTailormadeAccommodationAccommodationCategoryId()));
                        accommodationRoom.setAccommodationCategoryName(temp.getTailormadeAccommodationAccommodationCategoryName());
                        accommodationRoom.setProviderName(temp.getTailormadeAccommodationAccommodationName());
//                        accommodationRoom.setCheck_in_date("yes");
//                        accommodationRoom.setCheck_out_date("yes");


                        accommodationRoom.setProviderId(Integer.parseInt(temp.getTailormadeAccommodationAccommodationId()));
                        accommodationRoom.bedTypeName = "Something";
                        accommodationRoom.setSelected(true);
                        //accommodationRoom.setSelected(true);
                        accommodationRoom.accommodationRoomGalleryImage = temp.getImage();
                        accommodationRoom.accommodationRoomGalleryImage = temp.getImage();
                        accommodationCost+= Integer.parseInt(temp.getTailormadeAccommodationAccommodationRoomPrice());
                        accommodationRooms.add(accommodationRoom);

                    }


                }
                totalCost += accommodationCost;
                makeRouteAccommodationAdapter(context);


            }

            @Override
            public void onFailure(Call<TailorMadeAccommodation[]> call, Throwable t) {

            }
        });


    }

    public static void makeRouteAccommodationAdapter(Context context){
        routeAccommodations = new ArrayList<>();
        Log.e("ROute : ", routes.size()+"");
        for (int i = 0; i< routes.size();i++){

            Route tempRoute = routes.get(i);
            List<AccommodationRoom> routeAccommodations = getAccommodationRoomsForDistrict(tempRoute.getEndDistrictId());
            int size = routeAccommodations.size();

            AccommodationRoom must = new AccommodationRoom();
            must.setDistrictId(tempRoute.getEndDistrictId());
            must.setDistrictName(tempRoute.getEndDistrictName());
            must.setAccommodationCategoryId(0);
            must.setAccommodationCategoryName("");
            must.setAccommodationRoomId(0);
           // must.set
            TailorMadeDataHolder.routeAccommodations.add(must);
            Log.e("must : ", must.getDistrictName()+" "+must.getDistrictId());
            for (int j = 0; j< size;j++){

                AccommodationRoom accommodationRoom;
                //try {
                    accommodationRoom = routeAccommodations.get(j);

                Log.e("Dis : ", accommodationRoom.getDistrictName()+" "+accommodationRoom.getDistrictId());
                TailorMadeDataHolder.routeAccommodations.add(accommodationRoom);

            }

//            Toast.makeText(context,size+"",Toast.LENGTH_SHORT).show();



        }
        Log.e("Dis : ", routeAccommodations.size()+"");

    }

    public static ArrayList<AccommodationRoom> getAccommodationRoomsForDistrict(int id){
        ArrayList<AccommodationRoom> tempAccommodationRooms = new ArrayList<>();
        for (int i = 0; i< accommodationRooms.size();i++){
            AccommodationRoom temp = accommodationRooms.get(i);
            if (temp.getDistrictId() == id){
                tempAccommodationRooms.add(temp);
            }
        }
        return tempAccommodationRooms;
    }
    public static void makeItenerary(int id, Activity context){
        iteneraries = new ArrayList<>();
        ProvideCallback provideCallback = new ProvideCallback(context);

        provideCallback.getTailorMadeItinerary(id+"").enqueue(new CustomCallBack<TailorMadeItinerary[]>(context) {
            @Override
            public void onResponse(Call<TailorMadeItinerary[]> call, Response<TailorMadeItinerary[]> response) {
                Log.e("Package Url",call.request().url().toString());
                super.onResponse(call,response);

                if (response.body()!= null && response.body().length > 0){
                    for (int i = 0; i< response.body().length;i++){
                        TailorMadeItinerary temp = response.body()[i];
                        Itenerary itenerary = new Itenerary();
                        itenerary.setLocalTourId(Integer.parseInt(temp.getItineraryLocalTourId()));
                        itenerary.setDayplan(temp.getItineraryDayplan());
                        itenerary.setPlaceName(temp.getItineraryPlaceName());
                        itenerary.setPerPersonCost(temp.getItineraryPerPersonCost());

                        itenerary.setTime(temp.getItineraryTime());
                        if (temp.lattitude != null && temp.lattitude.length() > 0)itenerary.lattitude = Float.parseFloat(temp.lattitude);
                        if (temp.longiTude != null && temp.longiTude.length() > 0) itenerary.longitude = Float.parseFloat(temp.longiTude);
                        if (temp.getItineraryPerPersonCost() != null){
                            locaTourCost += Integer.parseInt(temp.getItineraryPerPersonCost());
                        }
                        iteneraries.add(itenerary);
                    }
                    totalCost += locaTourCost;
                    Log.e("locaTourCost : ",locaTourCost+"");
                }
            }
            @Override
            public void onFailure(Call<TailorMadeItinerary[]> call, Throwable t) {
                super.onFailure(call,t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }


            }
        });

    }

    public static void removeTransportProvider(TransportProvider provider){

        for (Route route : TailorMadeDataHolder.routes){

            if (provider.getRouteId() == route.getRouteId()){

                route.setTransportProvider(null);
            }
        }
    }

    public static boolean doesHaaveTransportProvider(int id){
        boolean result = false;
        for (Route route : TailorMadeDataHolder.routes){
            if (route.getRouteId() == id && route.getTransportProvider() != null) {

                result = true;
                break;
            }
        }
        return result;
    }

    public static void clearAll(){

        distrcis = new ArrayList<>();
        routes = new ArrayList<>();
        transportProviders = new ArrayList<>();
        accommodationRooms = new ArrayList<>();
        routeAccommodations = new ArrayList<>();
        iteneraries =new ArrayList<>();
        departDate="";
        returnDate="";
        noOfTourists="";
        noOfDays="";
        //edit start
        return_status ="";
        return_massage ="";
        //edit end

        accommodationCost = 0;
        transPortatationCost = 0;
        locaTourCost = 0;
         totalCost = 0;
        ROUTE = "";
        getNewRoutesModels = new ArrayList<>();
        tailorMadeId = 0;
        prevTransProviders = new ArrayList<>();
        prevTransportCost = 0;
        tourOperator = new ArrayList<>();
        providerImage = "";
        providerName = "";
        confDate = "";
        biddingAmount = "";
        noOfChildren = "";
        childrenDesc = "";

        checkin_date="";
        checkout_date="";

    }

    public static void addTourOperator(TourOperatorModel model){

        TourOperator operator = new TourOperator();
        operator.setProviderId(model.getProviderId()+"");
        tourOperator.add(operator);

    }

    public static void removeTourOperator (TourOperatorModel model){

        for (TourOperator t : tourOperator){
            if (t.getProviderId().equalsIgnoreCase(model.getProviderId()+"")){

                tourOperator.remove(t);
            }

        }
    }

}
