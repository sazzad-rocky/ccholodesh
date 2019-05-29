package model;

import android.app.Activity;

import helpers.RetrofitInitializer;
import listeners.ConfirmOperatorListener;
import retrofit2.Call;
import retrofit2.Retrofit;

public class OperatorConfirmCallback {
    private Retrofit retrofit;
    private ConfirmOperatorListener operatorListener;
    public OperatorConfirmCallback(Activity mContext) {
        retrofit= RetrofitInitializer.initNetwork(mContext);
        operatorListener =retrofit.create(ConfirmOperatorListener.class);
    }
    public Call<String> confirmOperator(ConfirmOperator operator){
        return operatorListener.confirmOperator(operator);
    }
    public Call<LocationResponse> getLatLng(String operator){
        return operatorListener.getLatLng(operator);
    }
}
