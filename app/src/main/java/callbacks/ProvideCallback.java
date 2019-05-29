/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package callbacks;

import android.app.Activity;

import java.util.ArrayList;

import helpers.RetrofitInitializer;
import listeners.ProviderListener;
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
import retrofit2.Retrofit;

/**
 * Created by Olivine on 5/13/2017.
 */


public class ProvideCallback {
    private Retrofit retrofit;
    private ProviderListener providerListener;
    private Activity mContext;


    Call<Package[]> Package;

    private TransportProvider [] transportProviders;

    public ProvideCallback(Activity mContext) {
        this.mContext = mContext;
        this.retrofit = RetrofitInitializer.initNetwork(mContext);
        providerListener=retrofit.create(ProviderListener.class);

    }


    public Call<HotelDetails> getHotelDetails(int serviceId){
        return  providerListener.getHotelDetails(serviceId);
    }
    //start sazzad
    public Call<HotelBooking[]> getCheck_in_date(int serviceIdkd){
        return  providerListener.getCheck_in_date(serviceIdkd);
    }
    //end

    public Call<TransportProvider[]> getTransportProviders(int route_id,int transport_type_id){
        return  providerListener.getTransportProviders(route_id,transport_type_id);
    }
    public Call<AccommodationProvider[]> getDestinationWiseAccommodationList(int locationId){
        return  providerListener.getDestinationWiseAccommodationList(locationId);
    }
    public Call<AccommodationRoom[]> getAccommodationRoom(int providerId){
        return  providerListener.getAccommodationRooms(providerId);
    }
    public Call<Food[]> getFoodRestairents(String districtId){
        return  providerListener.getFoodRestaurentList(districtId);
    }
    public Call<Package[]> getAllPackages(){

        Call<Package[]> Package;
        Package = providerListener.getAllPackages();
        return Package;
    }

    public Call<HotelGallery[]> getHotelGalley( int serviceId){
        return  providerListener.getHotelGalley(serviceId);
    }

    public Call<HotelRespons[]> gethotelrespons(int serviceId){
        return  providerListener.gethotelrespons(serviceId);
    }


    public Call<HotelInclusion[]> getHotelInclusions(int serviceId){
        return  providerListener.getHotelInclusions(serviceId);
    }
    public Call<PackageDetails> getPackageDetails(int packageId){
        return  providerListener.getPackageDetails(packageId);
    }

    public Call<PackageGallery[]> getPackageImages(int packageId){
        return  providerListener.getPackageImages(packageId);
    }

    public Call<PackageInclusion[]> getPackageInclusions(int packageId){
        return  providerListener.getPackageInclusions(packageId);
    }

    public Call<PackageItinerary[]> getPackageItineraries(int packageId){
        return  providerListener.getPackageItineraries(packageId);
    }
    public Call <ProviderTransport[]>get(int sourceId, int destinationId)
    { return providerListener.get (sourceId,destinationId);}

    ////////////
    public Call<RouteNew[]> getRoutes ()
    {
        return providerListener.getRoutes();
    }

    public Call<TransportType[]> getTransportTypes (int routeId)
    {
        return  providerListener.getTransportTypes(routeId);
    }

    public Call<TransportName[]> getTransportNames (int transportType, int routeId)
    {
        return providerListener.getTransportNames(transportType,routeId);
    }
    public Call<TransportInfo[]> getTransportInfo (int transportInfo, int routeId)
    {
        return providerListener.getTransportInfo(transportInfo,routeId);
    }

    public Call<DestinationNew[]> getDestinationInfo (int destinationId)
    {
        return  providerListener.getDestinationInfo(destinationId);
    }

    public Call<DestinationName[]> getDestinations ()
    {
        return providerListener.getDestinations();
    }

    public Call<DestinationGallery[]> getDestinationGallery (int destinationId)
    {
        return providerListener.getDestinationGallery(destinationId);
    }

    public Call<NearByPlaces[]> getDestinationNearByPlaces (int destinationId)
    {
        return providerListener.getDestinationNearByPlaces(destinationId);
    }

    public Call<DestinationLocalTour[]> getDestinationLocalTours (int destinationId)
    {
        return providerListener.getDestinationLocalTours(destinationId);
    }

    public Call<DestinationAttraction[]> getDestinationAttractions (int destinationId)
    {
        return providerListener.getDestinationAttractions(destinationId);
    }

    public Call<DestinationLocalTour[]> getDestinationLocalTour (int destinationId)
    {
        return providerListener.getDestinationLocalTour(destinationId);
    }

    public Call<DestinationEmergency[]> getDestinationEmergencies (int destinationId)
    {
        return providerListener.getDestinationEmergencies(destinationId);
    }

    public Call<NearByPlacesInfo[]> getNearByPlaceInfo (int nearByPlaceId)
    {
        return providerListener.getNearByPlaceInfo(nearByPlaceId);
    }

    public Call<NearByPlaceGallerInfo[]> getNearByPlaceGalleryInfo (int nearByPlaceId)
    {
        return providerListener.getNearByPlaceGalleryInfo(nearByPlaceId);
    }

    public Call<NearByPlaceTypeInfo[]> getNearByPlaceTypeInfo (int nearByPlaceId)
    {
        return providerListener.getNearByPlaceTypeInfo(nearByPlaceId);
    }

    public Call<HotelBookingToken[]> getHotelBookings (String customerPhn)
    {
        return providerListener.getHotelBookings(customerPhn);
    }

    public Call<PackageBookingToken[]> getPackageBookings (String customerPhn)
    {
        return providerListener.getPackageBookings(customerPhn);
    }

    public Call<Food[]> getDestinationFoods (int destinationId)
    {
        return providerListener.getDestinationFoods(destinationId);
    }

    public Call<PostedReview[]> getAllAccomodationReview ()
    {
        return providerListener.getAllAccomodationReview();
    }

    public Call<PostedReview[]> getAllFoodReview ()
    {
        return providerListener.getAllFoodReview();
    }

    public Call<PostedReview[]> getAccomodationReview (String id)
    {
        return providerListener.getAccomodationReview(id);
    }

    public Call<PostedReview[]> getFoodReview (String id)
    {
        return providerListener.getFoodReview(id);
    }

    public Call<TailorMadeItinerary[]> getTailorMadeItinerary (String id)
    {
        return providerListener.getTailorMadeItinerary(id);
    }

    public Call<TailorMadeAccommodation[]> getTailorMadeAccommodation (String id)
    {
        return providerListener.getTailorMadeAccommodation(id);
    }

    public Call<TailorMadeTransport[]> getTailorMadeTransport (String id)
    {
        return providerListener.getTailorMadeTransport(id);
    }

    public Call<TailorMadeList[]> getTailorMadeList (String id)
    {
        return providerListener.getTailorMadeList(id);
    }

    public Call<String> confirmTailorMade (String id)
    {
        return providerListener.confirmTailorMade(id);
    }

    public Call<String> disableTailorMade (String id)
    {
        return providerListener.disableTailorMade(id);
    }

    public Call<ArrayList<TourOperatorModel>>getAllTourOperators ()
    {

        return providerListener.getAllTourOperators();
    }






}

