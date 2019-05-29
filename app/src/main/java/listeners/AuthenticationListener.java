/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package listeners;

import model.Auth;
import model.AuthResult;
import model.ForgotPassword;
import model.LocalTaurSynk;
import model.Review;
import model.TourOperatorPostModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import userDefinder.TailormadeSync;
/**
 * Created by Olivine on 6/9/2017.
 */
public interface AuthenticationListener {
    @POST("customer/login")
    Call<AuthResult> authenTication(@Body Auth auth);
    @POST("customer/signup")
    Call<String> signUp(@Body Auth auth);

    @POST("provider/tailormadeinfo")
    Call<String> testTailorMade (@Body TailormadeSync test);

    @POST("provider/localTripPlan")
    Call<String> settestLocaltour (@Body LocalTaurSynk test);


    @POST("provider/localTripPlan")
    Call<String> localTour (@Body LocalTaurSynk test);


    @POST("provider/review")
    Call<String> testReview (@Body Review review);

    @POST("customer/forgot/password")
    Call<String> postForgotPassword (@Body ForgotPassword email);

    @POST("provider/confirm/tailormade/tourOperatorInfo")
    Call<String> getReturn(@Body TourOperatorPostModel model);


    @POST("contact")
    @FormUrlEncoded
    Call<String> sendContact(@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("number") String number,
                                  @Field("message") String message);
}
