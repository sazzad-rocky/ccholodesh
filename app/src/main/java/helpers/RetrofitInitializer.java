package helpers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Olivine on 12/8/2016.
 */

public class RetrofitInitializer {
    public static final int REQUEST_EXTERNAL_SERVICE =225 ;

    public static Retrofit initNetwork(Activity mContext){
        Retrofit retrofit;
        int permission= ActivityCompat.checkSelfPermission(mContext, Manifest.permission.INTERNET);
        if(permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    mContext,
                    new String[]{ Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE},
                    REQUEST_EXTERNAL_SERVICE

            );
        }

        retrofit=new Retrofit.Builder()
                .baseUrl(BaseURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .build();
        return retrofit;


    }
}
