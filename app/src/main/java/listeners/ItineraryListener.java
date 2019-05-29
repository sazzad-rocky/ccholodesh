package listeners;

import model.LocalTour;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
/**
 * Created by Olivine on 5/9/2017.
 */
public interface ItineraryListener {
    @GET("destination/nearbyplaceLocalTour/{destination}/{time}")
    Call<LocalTour[]> getLocalTourList(@Path("destination") String destination, @Path("time") String time);
}
