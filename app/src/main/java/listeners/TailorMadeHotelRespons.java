package listeners;

import model.HotelGallery;
import model.HotelRespons;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import userDefinder.TailormadeSync;

public interface TailorMadeHotelRespons {
    @POST("provider/tailormadeinfo/")
    Call<String> getReturn(@Body HotelRespons tailormadeSync);

    @GET("provider/accommodationGallery/{service_id}")
    Call<HotelGallery[]> getHotelGalley(@Path("service_id") int id);
}
