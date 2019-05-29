/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package helpers;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import model.CustomTripPlanNewRouteGetModel;
import model.CustomTripPlanRoutesListModel;
import model.LocalTour;
import model.Route;
import model.TransportProvider;

/**
 * Created by rhythmshahriar on 11/5/17.
 */

public class CustomTripPlanDataHolder {

    public static List<CustomTripPlanNewRouteGetModel> interRoutes;
    public static CustomTripPlanRoutesListModel selectedRoutes;
    public static String selectedRoutesName = "";
    public static String selectedRoutesNameTwo = "";
    public static List<Route> routes;
    public static int noOfTourist = 0;
    public static int noOfDays = 0;
    public static int noOf = 0;


    public static String checkindatee ="";
    public static String checkoutdatee="" ;


    public static String transportName="";
    public static String transportCost="";

    public static int noOfChildren = 0;
    public static String childrenDetails = "";
    public static List<TransportProvider> transportProviders = new ArrayList<>();
    public static String startingDate="" ;
    public static String lastDate="";
    public static String leastdate="";
    public static String returnDate="";
    public static int transPortcost;
    public static ArrayList<LocalTour> localTourCost = new ArrayList<>();
    public static ArrayList<String> districtsName = new ArrayList<>();
    public static void addTransportProvider(TransportProvider transportProvider){
        transportProviders.add(transportProvider);
        for(int i=0;i<routes.size();i++){

            if (routes.get(i).getRouteId() == transportProvider.getRouteId()){
                routes.get(i).setTransportProvider(transportProvider);
            }
        }

        transPortcost += noOfTourist * Integer.parseInt(transportProvider.getTransportInfoPrice());
        if (BaseURL.isEdit){
            TailorMadeDataHolder.prevTransportCost += Integer.parseInt(transportProvider.getTransportInfoPrice());
        }

    }

    public static boolean doesExist(int routeid){
        //boolean result =
        //Log.e("Count",routes.size()+"");
        for (Route route : routes){
            if (route.getRouteId() != null && route.getRouteId() == routeid && route.getTransportProvider() == null) {
               return false;
            }

        }
        return true;
    }

    public static void removeTransportProvider(TransportProvider provider){
        for (Route route : routes){
            if (provider.getRouteId() == route.getRouteId()){

                route.setTransportProvider(null);
            }
        }
    }

    public static void clearAll(){
        interRoutes = new ArrayList<>();
        selectedRoutes = new CustomTripPlanRoutesListModel();
        selectedRoutesName = "";
        routes = new ArrayList<>();
         noOfTourist = 0;
        noOfDays = 0;
        transportProviders = new ArrayList<>();
        startingDate ="";
        returnDate ="";
        checkindatee="";
        checkoutdatee="";
        lastDate="";

        transPortcost = 0;
        localTourCost = new ArrayList<>();
        noOfChildren = 0;
        childrenDetails = "";
        districtsName = new ArrayList<>();
    }


}
