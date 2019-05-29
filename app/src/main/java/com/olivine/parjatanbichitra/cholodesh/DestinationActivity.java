/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

import adapters.DestinationTagAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import callbacks.ProvideCallback;
import fragments.Attraction;

import fragments.FoodFragment;
import fragments.Gallery;
import fragments.LocalTourFragment;
import fragments.NearByPlacesFragment;
import fragments.Emergency;
import helpers.BaseURL;
import helpers.CustomCallBack;
import model.DestinationLocalTour;
import model.DestinationNew;
import retrofit2.Call;
import retrofit2.Response;
/**
 * Created by rhythmshahriar on 7/10/17.
 */

/**
 * this activity is used for viewing destination details to the user
 * usess fragments for tab details showing
 */

public class DestinationActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    //private CustomPager mViewPager;
    private FragmentManager fragmentManager;
    private ProvideCallback provideCallback;
    private List<DestinationNew> info;
    TextView divisionName,distrcitName,details;
    boolean LangEnglish = true;
    boolean execuTeOnResume;
    RecyclerView tags;
    ImageView destinationImage;
    TextView divisionForLang, districtForLang,destinationName,bdforlangchnge,aboutforlang,placement,showMore;
    String division,district;

    @BindView(R.id.mDemoSlider)
    SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        districtForLang = (TextView) findViewById(R.id.distrcitForLangChange);
        divisionForLang = (TextView) findViewById(R.id.divisionForLangChange);
        destinationName = (TextView) findViewById(R.id.destinationName);
        bdforlangchnge = (TextView) findViewById(R.id.bdforlangchnge);
        aboutforlang = (TextView) findViewById(R.id.aboutforlang);
        showMore = (TextView) findViewById(R.id.details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_clear_24);
        provideCallback = new ProvideCallback(this);
        LangEnglish = BaseURL.LANGUAGE_ENG;
        divisionName = (TextView) findViewById(R.id.textViewDivision);
        distrcitName = (TextView) findViewById(R.id.textViewDistrict);
        details = (TextView) findViewById(R.id.destinationDetails);
        placement = (TextView) findViewById(R.id.placement);

        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollaspe();

            }
        });
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollaspe();

            }
        });
        execuTeOnResume = false;

        //destinationImage = (ImageView) findViewById(R.id.imgDestination) ;

        tags = (RecyclerView) findViewById(R.id.tags);
        if (!BaseURL.LANGUAGE_ENG)
        {
            districtForLang.setText("জেলা");
            divisionForLang.setText("বিভাগ");
            bdforlangchnge.setText("বাংলাদেশে অবস্থান");
            aboutforlang.setText("বিস্তারিত");
            destinationName.setText("লোডিং");
            showMore.setText("বিস্তারিত");
            //district = "জেলা: ";
            //division = "বিভাগ: ";

        }
        else
        {
            //district = "DISTRICT: ";
            //division = "DIVISION: ";
        }


        district = "";
        division = "";

        int intValue = getIntent().getIntExtra("DESTINATION_ID", 0);
        loadDestinationDetails(intValue);

        Bundle bundle = new Bundle();
        bundle.putInt("DESTINATION_ID", intValue);


        // Create tabs

        NearByPlacesFragment nearByPlacesFragment = NearByPlacesFragment.newInstance(0);
        Attraction attraction= Attraction.newInstance(1);
        LocalTourFragment localTour = LocalTourFragment.newInstance(2);
        //Gallery gallery= Gallery.newInstance(1);
        FoodFragment foodFragment = FoodFragment.newInstance(3);
        Emergency emergency= Emergency.newInstance(4);


        nearByPlacesFragment.setArguments(bundle);
        localTour.setArguments(bundle);
        //gallery.setArguments(bundle);
        emergency.setArguments(bundle);
        attraction.setArguments(bundle);
        foodFragment.setArguments(bundle);
        //adding tabs
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //mSectionsPagerAdapter.addFragment(gallery);
        mSectionsPagerAdapter.addFragment(nearByPlacesFragment);
        mSectionsPagerAdapter.addFragment(attraction);
        mSectionsPagerAdapter.addFragment(localTour);
        mSectionsPagerAdapter.addFragment(foodFragment);
        mSectionsPagerAdapter.addFragment(emergency);



        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    private void loadDestinationDetails(int destinationId) {

        provideCallback.getDestinationInfo(destinationId).enqueue(new CustomCallBack<DestinationNew[]>(this)
        {

            @Override
            public void onResponse(Call<DestinationNew[]> call, Response<DestinationNew[]> response)
            {


                if (response.body() != null && response.body().length > 0)
                {
                    DestinationNew[] destinationNews = response.body();
                    if (!BaseURL.LANGUAGE_ENG)
                    {

                        if (destinationNews[0].getDestinationNameBn() != null)
                        {
                            DestinationActivity.this.setTitle(destinationNews[0].getDestinationNameBn());
                            destinationName.setText(destinationNews[0].getDestinationNameBn());
                            BaseURL.desname = destinationNews[0].getDestinationNameBn();
                        }
                        if (destinationNews[0].getDistrictNameBn() != null)distrcitName.setText(destinationNews[0].getDistrictNameBn());
                        if (destinationNews[0].getDivisionNameBn() != null)divisionName.setText(destinationNews[0].getDivisionNameBn());
                        if (destinationNews[0].getDestinationDetailsBn() != null)details.setText(Html.fromHtml(destinationNews[0].getDestinationDetailsBn()));
                        else
                        {
                            details.setText(Html.fromHtml(destinationNews[0].getDestinationDetails()));
                        }

                        placement.setText(district+distrcitName.getText()+"   "+division+divisionName.getText());
                    }
                    else
                    {
                        DestinationActivity.this.setTitle(destinationNews[0].getDestinationName());
                        BaseURL.desname = destinationNews[0].getDestinationName();
                        distrcitName.setText(destinationNews[0].getDistrictName());
                        divisionName.setText(destinationNews[0].getDivisionName());
                        details.setText(Html.fromHtml(destinationNews[0].getDestinationDetails()));
                        destinationName.setText(destinationNews[0].getDestinationName());

                        placement.setText(district+distrcitName.getText()+"   "+division+divisionName.getText());
                    }

                    details.post(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(mContext,holder.review.getLineCount()+"",Toast.LENGTH_SHORT).show();
                            if (details.getLineCount()>3)
                            {
                                showMore.setVisibility(View.VISIBLE);
                            }

                        }
                    });

                    //Toast.makeText(DestinationActivity.this,name,Toast.LENGTH_LONG).show();

                    mDemoSlider.removeAllSliders();
                    TextSliderView textSliderView = new TextSliderView(DestinationActivity.this);
                    // initialize a SliderLayout
                    textSliderView
                            .image(BaseURL.DESTINATION_IMAGE_BASE_URL+destinationNews[0].getDestinationImage())
                            .setScaleType(BaseSliderView.ScaleType.Fit);
                   if(!BaseURL.LANGUAGE_ENG && destinationNews[0].getDestinationNameBn()!= null)
                    {
                        textSliderView.description(destinationNews[0].getDestinationNameBn());
                    }
                    else
                   {
                       textSliderView.description(destinationNews[0].getDestinationName());
                   }
                    mDemoSlider.addSlider(textSliderView);
                    mDemoSlider.getPagerIndicator().setVisibility(View.INVISIBLE);
                    mDemoSlider.stopAutoCycle();
                   // String url = BaseURL.DESTINATION_IMAGE_BASE_URL+destinationNews[0].getDestinationImage();
//                    Picasso.with(DestinationActivity.this)
//                            .load(url)
//                            .fit()
//                            .into(destinationImage);

                     if (!BaseURL.LANGUAGE_ENG && destinationNews[0].getDestinationTagsBn()!= null )
                    {
                        String [] destinationTags = destinationNews[0].getDestinationTagsBn().split(",");
                        // Toast.makeText(DestinationActivity.this,destinationNews[0].getDestinationId()+"",Toast.LENGTH_LONG).show();
                        if (destinationTags.length>0 && destinationTags[0] != "")
                        {
                            DestinationTagAdapter adapter = new DestinationTagAdapter(getApplicationContext(),destinationTags);
                            tags.setAdapter(adapter);
                        }
                    }
                    else
                     {
                         if (destinationNews[0].getDestinationTags()!= null)
                         {
                             String [] destinationTags = destinationNews[0].getDestinationTags().split(",");
                             // Toast.makeText(DestinationActivity.this,destinationNews[0].getDestinationId()+"",Toast.LENGTH_LONG).show();
                             if (destinationTags.length>0 && destinationTags[0] != "")
                             {
                                 DestinationTagAdapter adapter = new DestinationTagAdapter(getApplicationContext(),destinationTags);
                                 tags.setAdapter(adapter);
                             }

                         }
                     }

                }
                else
                {
                    String meesage ="Could not Load Details";
                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        meesage ="বিস্তারিত লোড করা যায়নি";

                    }
                    Toast.makeText(DestinationActivity.this,meesage,Toast.LENGTH_LONG).show();

                }

                super.onResponse(call, response);


            }

            @Override
            public void onFailure(Call<DestinationNew[]> call, Throwable t) {
                super.onFailure(call, t);
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }
                Toast.makeText(DestinationActivity.this,meesage,Toast.LENGTH_LONG).show();

            }
        }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        MenuItem lang =menu.add(Menu.NONE,2,Menu.NONE,"lang");
//        lang.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        //lang.setIcon(R.drawable.ic_user_new);
//        if (LangEnglish)
//        {
//            lang.setIcon(R.drawable.ic_lang_bn);
//        }
//        else
//        {
//            lang.setIcon(R.drawable.ic_lang_en);
//        }


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }




    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments=new ArrayList<>();
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    if (BaseURL.LANGUAGE_ENG)
                    return "Nearby Places";
                    else
                        return "কাছাকাছি স্থান";
                case 2:
//                    if (BaseURL.LANGUAGE_ENG)
//                    return "Gallery";
//                    else return "গ্যালারি";
                    if (BaseURL.LANGUAGE_ENG)
                        return "Local Tour";
                    else return "স্থানীয় সফর";
                case 4:
                    if (BaseURL.LANGUAGE_ENG)
                    return " Emergency";
                    else return "জরুরী যোগাযোগ";

                case 1:
                    if (BaseURL.LANGUAGE_ENG)
                    return " Attraction";
                    else return "আকর্ষণ";
                case 3:
                    if (BaseURL.LANGUAGE_ENG)
                        return " Uniqueness";
                    else return "অনন্যতা";
            }
            return null;
        }


        public void addFragment(Fragment fragment){
            fragments.add(fragment);
        }


    }

    private void expandCollaspe ()
    {


        ViewGroup.LayoutParams params = details.getLayoutParams();
        if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT)
        {
            final float scale = getResources().getDisplayMetrics().density;
            int pixels = (int) (60 * scale + 0.5f);
            params.height = pixels;
            String showmore = "Show More";
            if (!BaseURL.LANGUAGE_ENG){
                showmore = "বিস্তারিত";
            }
            showMore.setText(showmore);
        }
        else
        {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            String showless = "Show Less";
            if (!BaseURL.LANGUAGE_ENG){
                showless = "সংক্ষিপ্ত";
            }
            showMore.setText(showless);


        }

        //description.setLayoutParams(params);
    }

    void show()
    {
        //Toast.makeText(this,info.getDistrictName(),Toast.LENGTH_LONG).show();
        //distrcitName.setText(info.getDistrictName());
        String meesage ="Success";
        if (!BaseURL.LANGUAGE_ENG)
        {
            meesage =" সাফল্য";

        }
        Toast.makeText(this,meesage,Toast.LENGTH_LONG).show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



}
