package helpers;

import android.util.Log;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.AccommodationRoom;
import model.CustomTripPlanNewRouteGetModel;
import model.Food;
import model.HotelDate;
import model.Itenerary;
import model.ItineryTailormade;
import model.LatLong;
import model.NearByPlaces;
import model.Route;
import model.TransportProvider;

/**
 * Created by Olivine on 5/5/2017.
 */
public class BaseURL {

    public static String districtid = "";
    public static List<Route> routes = new ArrayList<>();
    public static List<String> districtIdForDestination = new ArrayList<>();

    public static List<LatLong> districtsLocation = new ArrayList<>();

//    public static final String BASE_URL="http://192.168.88.244:1000/api/portal/";
//    public static final String IMAGE_BASE_URL ="http://192.168.88.244:1000/upload/";


    public static final String BASE_URL = "http://cholodesh.com/api/portal/";
    //   public static final String BASE_URL="http://travelbd.net/api/portal/";
    public static final String IMAGE_BASE_URL = "http://cholodesh.com/upload/";
    public static final String FOOD_IMAGE_BASE_URL = IMAGE_BASE_URL + "destination_food_service_provider_image/";
    public static final String PACKAGE_IMAGE_BASE_URL = IMAGE_BASE_URL + "package_gallery_image/";
    public static final String PACKAGE_INCLUSION_BASE_URL = IMAGE_BASE_URL + "package_inclusion_image/";
    public static final String HOTEL_IMAGE_BASE_URL = IMAGE_BASE_URL + "accommodation_gallery_image/";
    public static final String HOTEL_ROOM_IMAGE_BASE_URL = IMAGE_BASE_URL + "accommodation_room_gallery_image/";
    //destinations
    public static final String DESTINATION_IMAGE_BASE_URL = IMAGE_BASE_URL + "destination_image/";
    public static final String DESTINATION_IMAGE_GALLERY_BASE_URL = IMAGE_BASE_URL + "destination_gallery_image/";
    public static final String DESTINATION_NEAR_BY_PLACE_IMAGE_BASE_URL = IMAGE_BASE_URL + "destination_nearby_place_image/";
    public static final String DESTINATION_NEAR_BY_PLACE_GALLERY_BASE_URL = IMAGE_BASE_URL + "destination_nearby_place_gallery_image/";
    public static final String DESTINATION_ATTRACTION_IMAGE_BASE_URL = IMAGE_BASE_URL + "destination_attraction_image/";
    public static final String TOUR_OPERATOR_IMAGE_BASE_URL = IMAGE_BASE_URL + "provider_image/";
    ///language switcher
    public static boolean LANGUAGE_ENG = true;

    public static List<String> transportdateList = new ArrayList<>();
    public static String checkindate;
    public static String checkoutdate;
    public static String startDate;
    public static String enddate;
    public static String accommodationcheckindate;
    public static String accommodationcheckoutdate;


    public static String childagesStr = "";
    public static List<String> childages = new ArrayList<>();
    //start
    public static String lastdate;
    public static boolean triggired = false;
    public static boolean operatorresponsTag = false;

    // public static ArrayList<itinaryItem> items;
    public static ArrayList<Itenerary> items;

    public static ArrayList<String> childAge = new ArrayList<>();


    public static ArrayList<String> itinarydate = new ArrayList<>();

    public static ArrayList<String> itinaryplacename = new ArrayList<>();

    public static ArrayList<Itenerary> itinaryItems = new ArrayList<>();
    public static ArrayList<ItineryTailormade> itinaryItemtailormade = new ArrayList<>();

    public static ArrayList<String> transportname = new ArrayList<>();
    public static ArrayList<String> routess = new ArrayList<>();

    //end

    public static Food food = new Food();

    public static String REVIEWED_FOOD_ID = "";
    public static Float REVIEWED_FOOD_RATING = 0f;
    public static String REVIEWED_ACCOMMODATION_ID = "";
    public static Float REVIEWED_ACCOMMODATION_RATING = 0f;
    public static int totalCost = 0;
    public static int transPortCost = 0;

    public static boolean isEdit = false;

    public static Float FoodReviewByUser = 0f;


    public static Float REVIEW = 0f;
    public static int REVIEW_COUNT = 0;
    public static String desname = "";

    public static String FRAGMENT_OF_MAIN = "";
    public boolean loginSuccess = false;

    public static ArrayList<AccommodationRoom> accommodationRooms = new ArrayList<>();
    public static ArrayList<HotelDate> dates = new ArrayList<>();


    public static String accommodationname = "";
    public static int providerId = 0;
    public static String districtname = "";
    public static boolean flag = false;

    public static ArrayList<String> checkindates = new ArrayList<>();
    public static ArrayList<String> checkoutdates = new ArrayList<>();


    public static ArrayList<String> datecheckHotel = new ArrayList<>();
    public static ArrayList<Integer> nearByPlaceids = new ArrayList<>();
    public static ArrayList<Integer> hotelServiceIds = new ArrayList<>();
    public static ArrayList<Food> foodServiceIds = new ArrayList<>();
    public static ArrayList<Integer> localTourIds = new ArrayList<>();
    public static ArrayList<Itenerary> iteneraries = new ArrayList<>();

    public static int getCurrentPositionInArray(Integer serviceId, ArrayList<Integer> ids) {
        int index = -1;
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) == serviceId) index = i;
        }
        return index;
    }

    public static int getCurrentHotelPositionInArray(Integer serviceId) {
        int index = -1;
        for (int i = 0; i < hotelServiceIds.size(); i++) {
            if (hotelServiceIds.get(i) == serviceId) {

                index = i;
            }
        }
        return index;
    }

    public static int getCurrentNearByPlaceInArray(Integer serviceId) {
        int index = -1;
        for (int i = 0; i < nearByPlaceids.size(); i++) {
            int temp = nearByPlaceids.get(i);
            if (temp == serviceId) {
                index = i;
            }
        }
        return index;
    }

    public static int getCurrentFoodInArray(Integer serviceId) {
        int index = -1;
        for (int i = 0; i < foodServiceIds.size(); i++) {
            if (foodServiceIds.get(i).getDestinationFoodServiceId() == serviceId) index = i;
        }
        return index;
    }

    public static String HOTEL_NAME = "";

    public static void clearAll() {
        accommodationRooms.clear();
        nearByPlaceids.clear();
        hotelServiceIds.clear();
        foodServiceIds.clear();
        localTourIds.clear();
        iteneraries.clear();
    }

    public static void removeAcc(AccommodationRoom ac) {
        ArrayList<AccommodationRoom> temp = new ArrayList<>();
        for (AccommodationRoom a : BaseURL.accommodationRooms) {
            if (a.getAccommodationRoomId() != ac.getAccommodationRoomId()) {
                temp.add(a);
            }
        }
        BaseURL.accommodationRooms = temp;
    }

    public static void removeaccodate(int ac) {
        ArrayList<HotelDate> temp = new ArrayList<>();
        for (HotelDate a : BaseURL.dates) {

//            for (int i = 0; i < BaseURL.dates.size(); i++)
//            { for (int j = i + 1 ; j < BaseURL.dates.size(); j++)
//            {
//                if (BaseURL.dates.get(i).getProviderdate()==BaseURL.dates.get(j).getProviderdate())
//            {
//                Log.e("eee",BaseURL.dates.get(i).getProviderdate()+"");
//                // got the duplicate element
//                 }
//                 else {
//                    Log.e("eee","No");
//
//                }
//            } }


            if (a.getProviderdate() != ac) {
                temp.add(a);
            }
        }
        BaseURL.dates = temp;
    }

    public static void removeDate(int ac) {
        ArrayList<String> temp = new ArrayList<>();

        for (int i = 0; i < checkindates.size(); i++) {
            if (checkindates.get(i) != checkindates.get(ac)) {
                temp.add(checkindates.get(ac));
            }
        }

        BaseURL.checkindates = temp;
    }
}
