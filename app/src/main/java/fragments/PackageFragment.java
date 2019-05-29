/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;

import adapters.PackageAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import helpers.BaseURL;
import listeners.FragmentInteractionListener;
import model.Package;
import retrofit2.Call;
import retrofit2.Response;
import helpers.CustomCallBack;



public class PackageFragment extends Fragment {
    private FragmentInteractionListener mListener;
    int hitCount=0;
    long time=0;
    private ProvideCallback provideCallback;
    // Componets initialization
    @BindView(R.id.packages)
    RecyclerView packages;
    @BindView(R.id.placeHolderImage) ImageView placeHolderImage;
    public PackageFragment() {
        // Required empty public constructor
    }

    /**
     *@name PackageFragment
     * used for creating an object of PackageFragment and returning the object to the caller
     * @return
     */

    // TODO: Rename and change types and number of parameters
    public static PackageFragment newInstance() {
        PackageFragment fragment = new PackageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadPackages() {
        provideCallback=new ProvideCallback(getActivity());

        provideCallback.getAllPackages().enqueue(new CustomCallBack<Package[]>(getActivity()) {
            @Override
            public void onResponse(Call<Package[]> call, Response<Package[]> response) {
                super.onResponse(call, response);
                Log.e("Package Url",call.request().url().toString());
                if(response.body()!= null && response.body().length > 0)
                {
                    placeHolderImage.setVisibility(View.GONE);
                    PackageAdapter packageAdapter=new PackageAdapter(getActivity(),response.body());
                    packages.setAdapter(packageAdapter);

                }
                else
                {
                    placeHolderImage.setVisibility(View.VISIBLE);
                    return;
                }


            }

            @Override
            public void onFailure(Call<Package[]> call, Throwable t) {
                super.onFailure(call, t);
                placeHolderImage.setVisibility(View.VISIBLE);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(getActivity(), meesage, Toast.LENGTH_SHORT).show();

        }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!BaseURL.LANGUAGE_ENG)
        {
            getActivity().setTitle("প্যাকেজ");
        }
        else getActivity().setTitle("Packages");
        View view=inflater.inflate(R.layout.activity_package,container,false);
        ButterKnife.bind(this,view);
        loadPackages();
       //Toast.makeText(getActivity(), BanglaNumberParser.getEnglish("10/02/2017"),Toast.LENGTH_SHORT).show();
        //new Load().execute();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}

