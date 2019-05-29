/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package helpers;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Tanveer on 9/15/2017.
 */

public class DynamicHeight {


    public static void setHeight (Context context,View view,float aspectRatioWidth, float aspectRatioHeight )
    {

        DisplayMetrics displayMetrics =  new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        float logicalDensity = displayMetrics.density;
        int widthInPixels = displayMetrics.widthPixels;
        double imageWidthDp = widthInPixels/logicalDensity;
        float aspectRatio = aspectRatioHeight/aspectRatioWidth;
        double targetedHeightInDp = aspectRatio * imageWidthDp;
        view.getLayoutParams().height = dpToPixels((int)targetedHeightInDp,displayMetrics);
        //Toast.makeText(context,dpToPixels((int)targetedHeightInDp,displayMetrics)+" "+aspectRatio,Toast.LENGTH_LONG).show();

    }

    public static int dpToPixels(int dp,DisplayMetrics displayMetrics) {

        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}

