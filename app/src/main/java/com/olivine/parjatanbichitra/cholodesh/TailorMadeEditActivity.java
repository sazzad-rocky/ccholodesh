/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import adapters.CustomTripPlanTransportAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomTripPlanDataHolder;
import helpers.DynamicHeight;
import helpers.TailorMadeDataHolder;
import model.Place;
import userDefinder.TailorMade;

public class TailorMadeEditActivity extends AppCompatActivity {
    TextView removeChild, tvadd;
    LinearLayout childagell;
    EditText editText;
    private LinearLayout mLinearLayoutContainer;
    @BindView(R.id.editText_no_of_tourist)
    EditText editText_no_of_tourist;
    @BindView(R.id.editText_no_of_days) EditText editText_no_of_days;
    @BindView(R.id.departDateTextView)
    TextView departDateTextView;
    @BindView(R.id.returnDateTextView) TextView returnDateTextView;
    @BindView(R.id.locationAutoComplete)
    AutoCompleteTextView locationAutoComplete;
    @BindView(R.id.destinationAutoComplete) AutoCompleteTextView destinationAutoComplete;
    private Context context;
    int id;
    @BindView(R.id.destionforlang)
    TextView destionforlang;
    @BindView(R.id.fromlang)
    TextView fromlang;
    @BindView(R.id.toforlang)
    TextView toforlang;
    @BindView(R.id.trvlrforlang)
    TextView trvlrforlang;
    @BindView(R.id.trpdtforlang)
    TextView trpdtforlang;
    @BindView(R.id.deprtforlang)
    TextView deprtforlang;
    @BindView(R.id.returnforlang)
    TextView returnforlang;
    @BindView(R.id.searchButton)
    Button searchButton;
    @BindView(R.id.srcDes)
    LinearLayout srcDes;

    @BindView(R.id.childrenForLang)
    TextView childrenForLang;

    @BindView(R.id.editText_no_of_childeren)
    EditText editText_no_of_childeren;

    @BindView(R.id.editText_children_details)
    EditText editText_children_details;
    @BindView(R.id.addAnother) TextView addAnother;
    boolean isFirst = true;

    @BindView(R.id.touristsForLang)
    TextView touristsForLang;
    @BindView(R.id.dayForLang)
    TextView dayForLang;
    @BindView(R.id.noOfChilderForLang)
    TextView noOfChilderForLang;
    @BindView(R.id.averageAgeForLang)
    TextView averageAgeForLang;
    @BindView(R.id.departDateForLang)
    TextView departDateForLang;
    @BindView(R.id.returnDateForLang)
    TextView returnDateForLang;

    ArrayList<AutoCompleteTextView> autoCompleteDestinationTextViews;

    private void changeDate(String dt,int days)
    {
        // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(BanglaNumberParser.getEnglish(dt)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, days);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);
        String output = sdf1.format(c.getTime());
        returnDateTextView.setText(output);

        if (!BaseURL.LANGUAGE_ENG)
        {
            departDateTextView.setText(BanglaNumberParser.getBangla(departDateTextView.getText().toString()));
            returnDateTextView.setText(BanglaNumberParser.getBangla(output));
        }

        //Toast.makeText(getActivity(),output,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.departDateLayout)
    public void setTripDate(View view){


        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //updateLabel();
                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                int days=0;
                String selected_days=editText_no_of_days.getText().toString();
                if(selected_days.length()!=0){
                    days=Integer.parseInt(selected_days);
                }

                // Toast.makeText(getActivity(),sdf.format(new Date())+ " " + sdf.format(myCalendar.getTime()),Toast.LENGTH_SHORT).show();
                if (sdf.format(new Date()).equals(sdf.format(myCalendar.getTime()))|| new Date().before(myCalendar.getTime())) {
                    //Toast.makeText(getActivity(),"Outdated",Toast.LENGTH_SHORT).show();
                    //return;
                    departDateTextView.setText(sdf.format(myCalendar.getTime()));
                    myCalendar.add(Calendar.DATE, days);
                    String newDate=sdf.format(myCalendar.getTime());
                    returnDateTextView.setText(newDate);

                    if (!BaseURL.LANGUAGE_ENG)
                    {
                        departDateTextView.setText(BanglaNumberParser.getBangla(departDateTextView.getText().toString()));
                        returnDateTextView.setText(BanglaNumberParser.getBangla(newDate));
                    }
                }
                else
                {
                    String msg = "Please Select a Valid Date";
                    if (!BaseURL.LANGUAGE_ENG) msg = "একটি বৈধ তারিখ নির্বাচন করুন";
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                    return;
                }



            }

        };

        new DatePickerDialog(context, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

//        ViewAssist.setDate(getActivity(),departDateTextView,returnDateTextView,days,"Select Trip date");
    }

    @OnClick(R.id.searchButton)
    public void searchRoute(View view)
    {

        CustomTripPlanDataHolder.noOfTourist = Integer.parseInt(editText_no_of_tourist.getText().toString());
        CustomTripPlanDataHolder.noOfDays = Integer.parseInt(editText_no_of_days.getText().toString());
        CustomTripPlanDataHolder.returnDate = returnDateTextView.getText().toString();
        CustomTripPlanDataHolder.startingDate = departDateTextView.getText().toString();
        CustomTripPlanDataHolder.districtsName.add(destinationAutoComplete.getText().toString());
        if (!editText_no_of_childeren.getText().toString().equals("")){

            CustomTripPlanDataHolder.noOfChildren = Integer.parseInt(editText_no_of_childeren.getText().toString());
        }

        for (AutoCompleteTextView t : autoCompleteDestinationTextViews){

            CustomTripPlanDataHolder.districtsName.add(t.getText().toString());
        }

        CustomTripPlanDataHolder.childrenDetails = editText_children_details.getText().toString();
        BaseURL.isEdit = true;

        for (int loop = 0; loop < mLinearLayoutContainer.getChildCount()-1; loop++) {
            for (int j = 0; j < mLinearLayoutContainer.getChildCount(); j++) {
                if (mLinearLayoutContainer.getChildAt(j) instanceof EditText) {
                    EditText textET = (EditText) mLinearLayoutContainer.getChildAt(j);
                    CustomTripPlanDataHolder.childrenDetails=textET.getText().toString()+",";
                }
            }
        }



        Intent intent = new Intent(this,TailorMadeEditTransport.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_planner_new);


        mLinearLayoutContainer = (LinearLayout)findViewById(R.id.childage);
        removeChild = (TextView)findViewById(R.id.removeChild);
        tvadd = (TextView)findViewById(R.id.add);


        removeChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinearLayoutContainer.removeViewAt(mLinearLayoutContainer.getChildCount() - 1);
                if (mLinearLayoutContainer.getChildCount()<2){
                    removeChild.setVisibility(View.GONE);
                }else {
                    removeChild.setVisibility(View.VISIBLE);
                }
            }
        });

        tvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = new EditText(context);
                editText.getText();
                LinearLayout.LayoutParams p = new
                        LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                p.setMargins(20, 5, 20, 5);
                editText.setLayoutParams(p);
                editText.setHint("Enter Child " + (mLinearLayoutContainer.getChildCount() ) + " Age");
                editText.setBackgroundColor(Color.parseColor("#eeeeee"));
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setId(R.id.edittext_hello + mLinearLayoutContainer.getChildCount());
                mLinearLayoutContainer.addView(editText);
                if (mLinearLayoutContainer.getChildCount()>1){
                    removeChild.setVisibility(View.VISIBLE);
                }
                //
                //Toast.makeText(context, ""+et.getText().toString(), Toast.LENGTH_SHORT).show();
//                String[] strings = new String[](allEds.size());
//
//                for(int i=0; i < allEds.size(); i++){
//                    et.getText().toString()
//                    string[i] = et.get(i).getText().toString();
//                }
                //
            }
        });

        ButterKnife.bind(this);
        context = this;


        autoCompleteDestinationTextViews = new ArrayList<>();
        id = getIntent().getIntExtra("TAILOR_MADE_ID",0);
        List<String> districts = TailorMadeDataHolder.distrcis;
        departDateTextView.setText(TailorMadeDataHolder.departDate);
        returnDateTextView.setText(TailorMadeDataHolder.returnDate);
        editText_no_of_days.setText(TailorMadeDataHolder.noOfDays);
        editText_no_of_tourist.setText(TailorMadeDataHolder.noOfTourists);
        editText_no_of_childeren.setText(TailorMadeDataHolder.noOfChildren);
        editText_children_details.setText(TailorMadeDataHolder.childrenDesc);
        BaseURL.isEdit = true;
        addAnother.setVisibility(View.GONE);

        if (!BaseURL.LANGUAGE_ENG)
        {
            //Typeface face=Typeface.createFromAsset(getActivity().getAssets(), "siyamrupali.ttf");
            // destionforlang .setTypeface(face);
            this.setTitle("আপনার পরিকল্পনা সম্পাদনা করুন ");
            // destionforlang.setText("FONT CHECK");
            destionforlang.setText("গন্তব্য ");
            fromlang.setText("কোথা থেকে শুরু করবেন?");
            toforlang.setText("কোথায় যাবেন?");
            trvlrforlang.setText("ভ্রমণ বিবরণ");
            trpdtforlang.setText("সময়কাল");
            deprtforlang.setText("যাত্রা শুরুর তারিখ");
            returnforlang.setText("ফেরার তারিখ");
            editText_no_of_days.setHint("দিন");
            editText_no_of_tourist.setHint("পর্যটক");
            returnDateTextView.setText(BanglaNumberParser.getBangla(returnDateTextView.getText().toString()));
            departDateTextView.setText(BanglaNumberParser.getBangla(departDateTextView.getText().toString()));
            destinationAutoComplete.setHint("পর্যন্ত");
            locationAutoComplete.setHint("থেকে");
            searchButton.setText("পরবর্তী");
            addAnother.setText("+অন্য একটি যোগ করুন");
            childrenForLang.setText("শিশুদের বিবরণ");
            editText_no_of_childeren.setHint("শিশুদের সংখ্যা");
            editText_children_details.setHint("গড় বয়স");

            touristsForLang.setText("পর্যটক সংখ্যা");
            dayForLang.setText("দিন");
            noOfChilderForLang.setText("শিশুদের সংখ্যা");
            averageAgeForLang.setText("গড় বয়স");
            departDateForLang.setText("যাত্রা শুরুর তারিখ");
            returnDateForLang.setText("ফেরার তারিখ");



        }
        else this.setTitle("Edit Your Plan");
        locationAutoComplete.setText(districts.get(0));
        locationAutoComplete.setEnabled(false);
        locationAutoComplete.setTextColor(Color.BLACK);
        int i;
        if (districts.size() > 2){
            for ( i= 0; i<districts.size()-2;i++){
                srcDes.addView(createNew());
                if (i == 0) {
                    destinationAutoComplete.setText(districts.get(1));
                    destinationAutoComplete.setEnabled(false);
                    destinationAutoComplete.setTextColor(Color.BLACK);
                }
                else {
                    AutoCompleteTextView temp = autoCompleteDestinationTextViews.get(i-1);
                    temp.setText(districts.get(i+1));
                    temp.setEnabled(false);
                    temp.setTextColor(Color.BLACK);
                }
            }
            autoCompleteDestinationTextViews.get(i-1).setText(districts.get(i+1));
            autoCompleteDestinationTextViews.get(i-1).setEnabled(false);
            autoCompleteDestinationTextViews.get(i-1).setTextColor(Color.BLACK);
        }
        else {
            locationAutoComplete.setText(districts.get(0));
            destinationAutoComplete.setText(districts.get(1));
        }


        editText_no_of_days.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String departDate = departDateTextView.getText().toString();

                if (!departDate.equals("DD-MM-YYYY") && !departDate.equals("দিন-মাস-বসর"))
                {
                    //  Toast.makeText(getActivity(),departDate,Toast.LENGTH_SHORT).show();
                    int days = 0;
                    try {
                        days = Integer.parseInt(editText_no_of_days.getText().toString());
                    }catch (Exception ex)
                    {

                    }
                    changeDate(departDate,days);
                }
                //Toast.makeText(getActivity(),"Change Date to :"+ departDate,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
    private AutoCompleteTextView createNew(){
        DisplayMetrics displayMetrics =  new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int top = DynamicHeight.dpToPixels(8,displayMetrics);
        int bottom = DynamicHeight.dpToPixels(0,displayMetrics);
        int left = DynamicHeight.dpToPixels(20,displayMetrics);
        int right = DynamicHeight.dpToPixels(20,displayMetrics);
        lparams.setMargins(left,top,right,bottom);

        final AutoCompleteTextView textView = new AutoCompleteTextView(this);

        textView.setLayoutParams(lparams);
        int padding = DynamicHeight.dpToPixels(10,displayMetrics);
        textView.setPadding(padding,padding,padding,padding);
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        textView.setHint("To");
        textView.setHintTextColor(Color.parseColor("#888888"));

        //ArrayAdapter<Place> placeAdapter=new ArrayAdapter<Place>(context,R.layout.layout_spinner,R.id.spinnerText,places);
        //textView.setAdapter(placeAdapter);
        autoCompleteDestinationTextViews.add(textView);
        //Toast.makeText(context,autoCompleteDestinationTextViews.size()+"",Toast.LENGTH_SHORT).show();
        return textView;

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

}
