/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package helpers;

/**
 * Created by rhythmshahriar on 7/10/17.
 */

import android.app.ProgressDialog;
import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * CustomCallBack is a custom build class to show loader and dissmiss loader when the data is load
 * it is a generic class
 * receives the activity as a context and shows loading screen in time of construction
 * dissmiss the laoder in response
 * @param <T>
 */

public class CustomCallBack<T> implements Callback<T> {

    private ProgressDialog mProgressDialog;
    Context context;

    /**
     * parameterized constructor takes activity as a parameter
     * @param context
     */
    public CustomCallBack(Context context) {

        this.context = context; //activity as a context
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        if (!BaseURL.LANGUAGE_ENG)
        {
            mProgressDialog.setMessage("লোডিং...");
        }
        else mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();

        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();

        }

    }
}