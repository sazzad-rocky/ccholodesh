package listeners;

import android.location.Location;

import model.ConfirmOperator;
import model.LocationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ConfirmOperatorListener {
    @POST("provider/TailormadeConfirm")
    Call<String> confirmOperator(@Body ConfirmOperator confirmOperator);
    @POST("provider/customerTailormadeInfo/{destination_id}")
    Call<LocationResponse> getLatLng(@Body String destinationid);
}
