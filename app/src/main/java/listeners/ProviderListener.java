package listeners;

import java.util.ArrayList;
import java.util.List;

import model.AccommodationProvider;
import model.AccommodationRoom;
import model.DestinationAttraction;
import model.DestinationEmergency;
import model.DestinationGallery;
import model.DestinationLocalTour;
import model.DestinationName;
import model.DestinationNew;
import model.Food;
import model.HotelBooking;
import model.HotelBookingToken;
import model.HotelDetails;
import model.HotelGallery;
import model.HotelInclusion;
import model.HotelRespons;
import model.NearByPlaceGallerInfo;
import model.NearByPlaceTypeInfo;
import model.NearByPlaces;
import model.NearByPlacesInfo;
import model.Package;
import model.PackageBookingToken;
import model.PackageDetails;
import model.PackageGallery;
import model.PackageInclusion;
import model.PackageItinerary;
import model.PostedReview;
import model.Review;
import model.RouteNew;
import model.TailorMadeAccommodation;
import model.TailorMadeItinerary;
import model.TailorMadeList;
import model.TailorMadeTransport;
import model.TourOperatorModel;
import model.TransportInfo;
import model.TransportName;
import model.TransportType;
import model.TransportProvider;
import model.ProviderTransport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Olivine on 5/13/2017.
 */
public interface ProviderListener {
    @GET("provider/TailormadeReply/{user_id}")
    Call<HotelRespons[]> gethotelrespons(@Path("user_id") int id);

    @GET("provider/accommodationGallery/{service_id}")
    Call<HotelGallery[]> getHotelGalley(@Path("service_id") int id);
    //start sazzad
    @GET("provider/accommodationBooking/{service_id}")
    Call<HotelBooking[]> getCheck_in_date(@Path("service_id") int idd);
    //end sazzad
    @GET("provider/routeWiseTransportType/{route_from}/{route_to}")
    Call<TransportProvider[]> getTransportProviders(@Path("route_from") int route_to,@Path("route_to") int route_from);

    @GET("provider/routeWiseTransportType/{route_from}/{route_to}")
    Call<ProviderTransport[]> get(@Path("route_from") int route_to,@Path("route_to") int route_from);

    @GET("provider/routeAccommodationList/{location}")
    Call<AccommodationProvider[]> getDestinationWiseAccommodationList(@Path("location") int locationId);
    @GET("provider/accommodationRoom/{provider_id}")
    Call<AccommodationRoom[]> getAccommodationRooms(@Path("provider_id") int providerId);

    @GET("district/food/{districtId}")
    Call<Food[]> getFoodRestaurentList(@Path("districtId") String id);

    @GET("provider/allPackages")
    Call<Package[]> getAllPackages();



    @GET("provider/accommodationDetails/{service_id}")
    Call<HotelDetails> getHotelDetails(@Path("service_id") int id);

    @GET("provider/accommodationFacilities/{service_id}")
    Call<HotelInclusion[]> getHotelInclusions(@Path("service_id") int id);

    @GET("provider/packageDetails/{package_id}")
    Call<PackageDetails> getPackageDetails(@Path("package_id") int id);

    @GET("provider/packageGallery/{package_id}")
    Call<PackageGallery[]> getPackageImages(@Path("package_id") int id);

    @GET("provider/packageInclusion/{package_id}")
    Call<PackageInclusion[]> getPackageInclusions(@Path("package_id") int id);

    @GET("provider/packageItinerary/{package_id}")
    Call<PackageItinerary[]> getPackageItineraries(@Path("package_id") int id);


    //////////////////
    @GET("transportInfo/routeList")
    Call<RouteNew[]> getRoutes();

    @GET("transportInfo/transportTypeList/{transport_info_route_id}")
    Call<TransportType[]> getTransportTypes(@Path("transport_info_route_id") int id);

    @GET("provider/transportInfo/transportNameList/{transport_info_transport_type_id}/{transport_info_route_id}")
    Call<TransportName[]> getTransportNames(@Path("transport_info_transport_type_id") int typeId, @Path("transport_info_route_id") int routeId);

    @GET("provider/transportInfo/{transport_info_id}/{transport_info_route_id}")
    Call<TransportInfo[]> getTransportInfo(@Path("transport_info_id") int infoId, @Path("transport_info_route_id") int routeId);

    @GET("destination/info/{destination_id}")
    Call<DestinationNew[]> getDestinationInfo(@Path("destination_id") int destinationId);

    @GET("destination/listDestination")
    Call<DestinationName[]> getDestinations();

    @GET("destination/gallery/info/{destination_gallery_destination_id}")
    Call<DestinationGallery[]> getDestinationGallery(@Path("destination_gallery_destination_id") int destinationId);



    @GET("destination/nearByPlace/info/{destination_id}")
    Call<NearByPlaces[]> getDestinationNearByPlaces(@Path("destination_id") int destinationId);

    @GET("destination/localtour/info/{destination_id}")
    Call<DestinationLocalTour[]> getDestinationLocalTours(@Path("destination_id") int destinationId);

    @GET("destination/attraction/info/{destination_id}")
    Call<DestinationAttraction[]> getDestinationAttractions(@Path("destination_id") int destinationId);

    @GET("localtour/details/{destination_id}")
    Call<DestinationLocalTour[]> getDestinationLocalTour(@Path("destination_id") int destinationId);

    @GET("destination/emergency/info/{destination_id}")
    Call<DestinationEmergency[]> getDestinationEmergencies(@Path("destination_id") int destinationId);

    @GET("nearByPlace/info/{destination_nearby_place_id}")
    Call<NearByPlacesInfo[]> getNearByPlaceInfo(@Path("destination_nearby_place_id") int nearByPlaces);

    @GET("nearByPlace/gallery/info/{destination_nearby_place_id}")
    Call<NearByPlaceGallerInfo[]> getNearByPlaceGalleryInfo(@Path("destination_nearby_place_id") int nearByPlaces);

    @GET("nearByPlace/placeType/info/{destination_nearby_place_id}")
    Call<NearByPlaceTypeInfo[]> getNearByPlaceTypeInfo(@Path("destination_nearby_place_id") int nearByPlaces);

    @GET("accommodationBooking/info/{customer_phone}")
    Call<HotelBookingToken[]> getHotelBookings(@Path("customer_phone") String customerPhone);

    @GET("packageBooking/info/{customer_phone}")
    Call<PackageBookingToken[]> getPackageBookings(@Path("customer_phone") String customerPhone);
    @GET("destination/food/info/{destination_food_service_destination_id}")
    Call<Food[]> getDestinationFoods(@Path("destination_food_service_destination_id") int destinationId);

    @GET("provider/review/accommodation")
    Call<PostedReview[]> getAllAccomodationReview ();
    @GET("provider/review/food")
    Call<PostedReview[]> getAllFoodReview ();

    @GET("provider/review/accommodation/{review_track_id}")
    Call<PostedReview[]> getAccomodationReview (@Path("review_track_id")String id);
    @GET("provider/review/food/{review_track_id}")
    Call<PostedReview[]> getFoodReview (@Path("review_track_id")String id);

    @GET("provider/tailormade/{itinerary_tailormade_id}/itinerary/show")
    Call<TailorMadeItinerary[]> getTailorMadeItinerary (@Path("itinerary_tailormade_id")String id);

    @GET("provider/tailormade/{tailormade_accommodation_tailormade_id}/accommodation/show")
    Call<TailorMadeAccommodation[]> getTailorMadeAccommodation (@Path("tailormade_accommodation_tailormade_id")String id);

    @GET("provider/tailormade/{tailormade_route_tailormade_id}/transport/show")
    Call<TailorMadeTransport[]> getTailorMadeTransport (@Path("tailormade_route_tailormade_id")String id);

    @GET("provider/customerTailormadeInfo/{tailormade_customer_id}")
    Call<TailorMadeList[]> getTailorMadeList (@Path("tailormade_customer_id")String id);

    @GET("provider/tailormadeinfo/{tailormade_id}/Confirm")
    Call<String> confirmTailorMade (@Path("tailormade_id")String id);
    @GET("provider/tailormadeinfo/{tailormade_id}/Disable")
    Call<String> disableTailorMade (@Path("tailormade_id")String id);
    @GET("provider/list/tourOperator")
    Call<ArrayList<TourOperatorModel>> getAllTourOperators();





}
