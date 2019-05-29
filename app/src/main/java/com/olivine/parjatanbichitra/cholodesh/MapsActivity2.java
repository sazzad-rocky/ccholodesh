package com.olivine.parjatanbichitra.cholodesh;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import helpers.BaseURL;
import model.CustomInfoWindowGoogleMap;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback ,LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
    Marker marker;
    LocationRequest locationRequest;
    Location location;
    GoogleApiClient mGoogleApiClient;
    Marker mainMarker;
    Map<String, Integer> mMarkers;
    Double latm;
    Double lngm;


    EditText et;
    private GoogleMap mMap;
    String destination;
    Button go;
    LocationManager locationManager;
    String provider;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private Button btnpopup;

   // ArrayList markerPoints= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        btnpopup =findViewById(R.id.btnpopup);

        destination = getIntent().getStringExtra("district_name");
        if (googlePlayServicesAvaliable()) {
           // Toast.makeText(this, "perfect", Toast.LENGTH_SHORT).show();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        et = (EditText) findViewById(R.id.edittext);
        et.setText(destination);
        gpsCheck();

        btnpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MapsActivity2.this, v);
                // popup.setOnMenuItemClickListener(MapsActivity.this);
                popup.inflate(R.menu.main_menu);
                popup.show();

//                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
//                 // MenuInflater menuInflater = popupMenu.getMenuInflater();
//                  popupMenu.inflate(R.menu.main_menu);
//
//                popupMenu.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.mapTypeNone:
                                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                                break;

                            case R.id.mapTypeNormal:
                                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                break;

                            case R.id.mapTypeSatellite:
                                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                                break;
                            case R.id.mapTypeTerrain:
                                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                                break;

                            case R.id.mapTypeHybrid:
                                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                break;
                        }
                        return false;
                    }
                });
            }
        });
//        try {
//         //   gotodestination();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void gpsCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else {
            connectGoogleApiClient();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 2);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private void connectGoogleApiClient() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();

        //Toast.makeText(this, "mGoogleApiClient  ", Toast.LENGTH_SHORT).show();

    }



    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void gotodestination() throws IOException {
        String locaiton = et.getText().toString();
        if (!(et.getText().toString().isEmpty())) {
            Geocoder gc = new Geocoder(this);
            //List<Address> mm =  gc.getFromLocationName(locaiton, 1);
            List<Address> kk = gc.getFromLocationName(locaiton, 1);
            if (!kk.isEmpty()) {
                Address address = kk.get(0);
                String locality = address.getLocality();
                Toast.makeText(this, locality, Toast.LENGTH_SHORT).show();
                Double lat = address.getLatitude();
                Double lng = address.getLongitude();

                gotoLocationZoom(lat, lng, 20);
            } else {
                Toast.makeText(this, "no address found", Toast.LENGTH_SHORT).show();
            }
        } else Toast.makeText(this, "enter address", Toast.LENGTH_SHORT).show();


    }

    public boolean googlePlayServicesAvaliable() {

        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Can not connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        }
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            locationManager.removeUpdates((android.location.LocationListener) MapsActivity2.this);
//        }
//    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        //Request location updates:
                        ////  locationManager.requestLocationUpdates(provider, 400, 1,  this);
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "on", Toast.LENGTH_SHORT).show();
            connectGoogleApiClient();

        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getProjection().getVisibleRegion();
        // Add a marker in Sydney and move the camera


        //mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        goToLocationZoom2(23.746265, 90.386411, 13);
        setMainMarker();

        MarkerOptions options;
        if (BaseURL.districtsLocation.size()>0){
            for (int i=0;i<BaseURL.districtsLocation.size();i++){

                Toast.makeText(this, ""+BaseURL.districtsLocation.size(), Toast.LENGTH_SHORT).show();


                latm = Double.parseDouble(BaseURL.districtsLocation.get(i).getLongitude());
                lngm =   Double.parseDouble(BaseURL.districtsLocation.get(i).getLatitude());
               // Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
                options  = getMarkerOptions(i, latm, lngm);
                LatLng locaiotn = new LatLng(latm, 151.211);
                googleMap.addMarker(new MarkerOptions().position(locaiotn)
                        .title("Marker in Sydney"));
                mMap.addMarker(options);
                CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
                mMap.setInfoWindowAdapter(customInfoWindow);
                Marker mm = mMap.addMarker(options);
//                mm.setTag(info);
                mm.showInfoWindow();
            }
        }
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.734243, 90.378062),
                        new LatLng(23.821647, 90.459751),
                        new LatLng(23.719000, 90.397760),
                        new LatLng(-33.501, 150.217),
                        new LatLng(-32.306, 149.248),
                        new LatLng(-32.491, 147.309)));



        //from unicef
//        if (appControler.arrayListMap.size() >= 1) {
//            for (int i = 0; i < appControler.arrayListMap.size(); i++) {
//                latm = Double.parseDouble(appControler.arrayListMap.get(i).latitude);
//                lngm = Double.parseDouble(appControler.arrayListMap.get(i).longitude);
//                MarkerOptions options = getMarkerOptions(i, latm, lngm);
//                mMap.addMarker(options);
//                CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
//                mMap.setInfoWindowAdapter(customInfoWindow);
//                Marker mm = mMap.addMarker(options);
//                mm.setTag(info);
//                mm.showInfoWindow();
//                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//                    @Override
//                    public void onInfoWindowClick(Marker marker) {
////                        Intent intent = new Intent(MapsActivity2.this, AfterMapActivity.class);
////                        intent.putExtra("union", marker.getAlpha());
////                        intent.putExtra("bb", marker.getAlpha());
////                        intent.putExtra("snippet", marker.getSnippet());
////                        startActivity(intent);
//                    }
//                });
//                for (int m = 0; m < appControler.arrayListMap.size(); m++) {
//                    goToLocationZoom2(lngm, latm, 13);
//                    break;
//                }
//            }
//        } else {
//            goToLocationZoom2(23.773903, 90.505529, 8);
//        }


        //end unicef

    }

    @NonNull
    private MarkerOptions getMarkerOptions(int i, Double lat, Double lng) {

        if (BaseURL.LANGUAGE_ENG) {
            return new MarkerOptions()
                 //   .title(appControler.arrayListMap.get(i).clubname + " " + club)
                    // .alpha(Float.parseFloat(appControler.arrayListMap.get(i).clubid))
                   // .alpha(appControler.arrayListMap.get(i).clubid)

                    //  .snippet(appControler.arrayListMap.get(i).address+",\n"+appControler.arrayListMap.get(i).unionName+"। ")

                    //.snippet()
                     .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    //.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("image_name", 80, 80)))
                    .position(new LatLng(lng, lat));
        } else {
            return new MarkerOptions()
                  //  .title(appControler.arrayListMap.get(i).clubname + " " + club)
                    // .alpha(Float.parseFloat(appControler.arrayListMap.get(i).clubid))
                   // .alpha(appControler.arrayListMap.get(i).clubid)
                    //  .snippet(appControler.arrayListMap.get(i).address+",\n"+appControler.arrayListMap.get(i).unionName+"। ")
                   // .snippet()
                     .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    //.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("image_name", 80, 80)))
                    .position(new LatLng(lng, lat));
        }
    }


    public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap imageBitmap2 = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.marker);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(b, width, height, false);
        return resizedBitmap;
    }
    private void goToLocationZoom2(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mMap.moveCamera(update);
        //mMap.addMarker(new MarkerOptions().position(ll).title("Marker in Bashundhara city shopping complex"));
    }
    private void gotoLocationZoom(Double lat, Double lng, int i) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, i);
        mMap.moveCamera(update);
    }
    public void geolocatee(View view) throws IOException {
        String locaiton = et.getText().toString();
        if (!(et.getText().toString().isEmpty())) {
            Geocoder gc = new Geocoder(this);
            //List<Address> mm =  gc.getFromLocationName(locaiton, 1);
            List<Address> kk = gc.getFromLocationName(locaiton, 1);
            if (!kk.isEmpty()) {
                Address address = kk.get(0);
                String locality = address.getLocality();
                Toast.makeText(this, locality, Toast.LENGTH_SHORT).show();
                Double lat = address.getLatitude();
                Double lng = address.getLongitude();
                gotoLocationZoom(lat, lng, 20);
            } else {
                Toast.makeText(this, "no address found", Toast.LENGTH_SHORT).show();
            }
        } else Toast.makeText(this, "enter address", Toast.LENGTH_SHORT).show();
    }


    private void gotoLocationZoom(double lat, double lng, int i) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, i);
        mMap.moveCamera(update);
        mMap.animateCamera(update);
        //  mGoogleMap.addMarker(new MarkerOptions().position(ll).title("Marker in Bashundhara city shopping complex"));
    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            this.location = location;
            //  Toast.makeText(this, "onLocationChanged " + location.getLongitude(), Toast.LENGTH_SHORT).show();
            setMainMarker();
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //  Toast.makeText(this, "no onConnected  ", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }

        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {
            setMainMarker();
            locationRequest = new LocationRequest();
            locationRequest.setInterval(3000);
            locationRequest.setFastestInterval(3000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            LocationServices.FusedLocationApi
                    .requestLocationUpdates(mGoogleApiClient, locationRequest, this);

        } else {
        }

    }


    private void setMainMarker() {
        if (mainMarker != null) {
            mainMarker.remove();
        }
        LatLng sydney;
        if (location == null) {
            sydney = new LatLng(23.767368, 90.368605);
        } else {
            sydney = new LatLng(location.getLatitude(), location.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
