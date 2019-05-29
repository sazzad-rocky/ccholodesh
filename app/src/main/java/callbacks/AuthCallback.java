/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package callbacks;

import android.app.Activity;
import android.content.Context;

import helpers.RetrofitInitializer;
import listeners.AuthenticationListener;
import listeners.TailormadeListener;
import model.Auth;
import model.ForgotPassword;
import model.LocalTaurSynk;
import model.Review;
import model.TourOperatorPostModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import userDefinder.TailormadeSync;

/**
 * Created by Olivine on 6/10/2017.
 */

public class AuthCallback {
    Retrofit retrofit;
    AuthenticationListener authenticationListener;
    TailormadeListener tailormadeListener;

    public AuthCallback(Activity mContext) {
    retrofit= RetrofitInitializer.initNetwork(mContext);
        authenticationListener=retrofit.create(AuthenticationListener.class);
    }

  public Call<String> register(Auth auth){
      return authenticationListener.signUp(auth);
  }
  public Call<String> SendTailorMade (TailormadeSync tailormadeSync) {return authenticationListener.testTailorMade(tailormadeSync);}



  public Call<String> SendLocalTour (LocalTaurSynk tailormadeSync) {return authenticationListener.localTour(tailormadeSync);}


  public Call<String> SendReview (Review review) {return authenticationListener.testReview(review);}

    public Call<String>  postForgotPassword (ForgotPassword email) {return authenticationListener.postForgotPassword(email);}

    public Call<String> postOperator (TourOperatorPostModel model) {return authenticationListener.getReturn(model);}

}
