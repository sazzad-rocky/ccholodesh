/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package helpers;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import static com.squareup.picasso.Picasso.LoadedFrom;
/**
 * Created by rhythmshahriar on 7/10/17.
 */

public interface Target {

    /**
     * Callback when an image has been successfully loaded.
     * <p>
     * <strong>Note:</strong> You must not recycle the bitmap.
     */
    void onBitmapLoaded(Bitmap bitmap, LoadedFrom from);

    /**
     * Callback indicating the image could not be successfully loaded.
     * <p>
     * <strong>Note:</strong> The passed {@link Drawable} may be {@code null} if none has been
     * specified via {@link RequestCreator#error(android.graphics.drawable.Drawable)}
     * or {@link RequestCreator#error(int)}.
     */
    void onBitmapFailed(Exception e, Drawable errorDrawable);

    /**
     * Callback invoked right before your request is submitted.
     * <p>
     * <strong>Note:</strong> The passed {@link Drawable} may be {@code null} if none has been
     * specified via {@link RequestCreator#placeholder(android.graphics.drawable.Drawable)}
     * or {@link RequestCreator#placeholder(int)}.
     */
    void onPrepareLoad(Drawable placeHolderDrawable);
}
