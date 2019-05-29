/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.olivine.parjatanbichitra.cholodesh.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import helpers.BaseURL;
import model.DestinationGallery;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Gallery extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    @BindView(R.id.mDemoSlider)
    SliderLayout mDemoSlider;

    ProvideCallback provideCallback;
    ImageView placeHolderImage;
    TextView placeHolderTextView;
    String name= "";

    private OnFragmentInteractionListener mListener;

    public Gallery() {
        // Required empty public constructor
    }



    public static Gallery newInstance(int sectionNumber ) {
        Gallery fragment = new Gallery();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //name = getArguments().getString("DESTINATION_NAME");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        int destinationId = getArguments().getInt("DESTINATION_ID");
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this,view);
        provideCallback = new ProvideCallback(getActivity());
        placeHolderImage = (ImageView) view.findViewById(R.id.placeHolderImage);
        placeHolderTextView = (TextView) view.findViewById(R.id.placeHolderText);
        if (!BaseURL.LANGUAGE_ENG)
        {
            placeHolderTextView.setText("এই স্থানটির জন্য কোন গ্যালারি ছবি খুঁজে পাওয়া যায় নি");
        }
        loadGalleryImages(destinationId);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void loadGalleryImages(int destinationId){
        provideCallback.getDestinationGallery(destinationId).enqueue(new Callback<DestinationGallery[]>() {
            @Override
            public void onResponse(Call<DestinationGallery[]> call, Response<DestinationGallery[]> response) {

                if(response.body()!= null && response.body().length > 0)
                {
                    loadGalleryImage(response.body());
                }
                else
                {
                    placeHolderImage.setVisibility(View.VISIBLE);
                    placeHolderTextView.setVisibility(View.VISIBLE);
                    //Toast.makeText(getActivity(),"No Gallery Image Found",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<DestinationGallery[]> call, Throwable t) {

                placeHolderImage.setVisibility(View.VISIBLE);
                placeHolderTextView.setVisibility(View.VISIBLE);

                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                placeHolderTextView.setText(meesage);
            }
        });

    }

    private void loadGalleryImage(DestinationGallery [] destinationGalleries){
        mDemoSlider.removeAllSliders();
        for(DestinationGallery destinationGallery : destinationGalleries){
            //String name=destinationGallery.get();
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name) // image description
                    .image(BaseURL.DESTINATION_IMAGE_GALLERY_BASE_URL+destinationGallery.getDestinationGalleryImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra","Name");

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateDown);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
    }
}
