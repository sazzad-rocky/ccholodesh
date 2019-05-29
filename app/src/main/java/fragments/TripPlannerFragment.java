/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.olivine.parjatanbichitra.cholodesh.LoginActivity;
import com.olivine.parjatanbichitra.cholodesh.NewTripRouteActivity;
import com.olivine.parjatanbichitra.cholodesh.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import callbacks.PlaceCallback;
import callbacks.RouteCallback;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import helpers.CustomTripPlanDataHolder;
import helpers.DynamicHeight;
import helpers.TailorMadeDataHolder;
import io.realm.Realm;
import model.CustomTripPlanNewRouteGetModel;
import model.CustomTripPlanRouteModel;
import model.CustomTripPlanRoutesListModel;
import model.MySharedPreparences;
import model.Place;
import model.PreviewData;
import model.Route;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Olivine on 6/21/2017.
 */
public class TripPlannerFragment extends Fragment {
    //


    TextView removeChild, tvadd,tvchildren;
    LinearLayout childagell;
    private LinearLayout mLinearLayoutContainer;
    EditText childageet;
    EditText editText;
    String childageStr = "";
    private EditText childageone;
    static int i;
    LinearLayout.LayoutParams layoutParams;
    private MySharedPreparences mySharedPreparences;
    @BindView(R.id.editText_no_of_tourist)
    EditText editText_no_of_tourist;
    @BindView(R.id.editText_no_of_days)
    EditText editText_no_of_days;
    @BindView(R.id.departDateTextView)
    TextView departDateTextView;
    @BindView(R.id.returnDateTextView)
    TextView returnDateTextView;
    @BindView(R.id.locationAutoComplete)
    AutoCompleteTextView locationAutoComplete;
    @BindView(R.id.destinationAutoComplete)
    AutoCompleteTextView destinationAutoComplete;
    private Context context;
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
    @BindView(R.id.childrenForLang)
    TextView childrenForLang;
    @BindView(R.id.editText_no_of_childeren)
    EditText editText_no_of_childeren;
    @BindView(R.id.editText_children_details)
    EditText editText_children_details;
    @BindView(R.id.removeOne)
    TextView removeOne;


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
    private RouteCallback routeCallback;
    private List<CustomTripPlanNewRouteGetModel> routesList;
    ArrayList<AutoCompleteTextView> autoCompleteDestinationTextViews;
    @BindView(R.id.addAnother)
    TextView addAnother;

//    @BindView(R.id.add)
//    TextView add;

    @BindView(R.id.srcDes)
    LinearLayout srcDes;
//
//    @BindView(R.id.childage)
//    LinearLayout childage;

    //
//    @BindView(R.id.childage)
//    LinearLayout childage;

    LinearLayout child;
    private ScrollView scrollView;
    // callback
    PlaceCallback placeCallback;
    // TODO local storage
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<Place> places;
    private Realm realm;

    private void changeDate(String dt, int days) {
        // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(BanglaNumberParser.getEnglish(dt)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, days);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String output = sdf1.format(c.getTime());
        returnDateTextView.setText(output);
        if (!BaseURL.LANGUAGE_ENG) {
            departDateTextView.setText(BanglaNumberParser.getBangla(departDateTextView.getText().toString()));
            returnDateTextView.setText(BanglaNumberParser.getBangla(output));
        }
        //Toast.makeText(getActivity(),output,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.removeOne)
    public void removeOne(View view) {
        //Toast.makeText(context,"Ok",Toast.LENGTH_SHORT).show();
        try {
            AutoCompleteTextView var = autoCompleteDestinationTextViews.get(autoCompleteDestinationTextViews.size() - 1);
            srcDes.removeView(var);
            autoCompleteDestinationTextViews.remove(var);
            if (autoCompleteDestinationTextViews.size() < 1) {
                removeOne.setVisibility(View.GONE);
            }
        } catch (Exception ex) {

        }


    }
//    @OnClick(R.id.remove)
//    public void removechild(View view) {
//        //Toast.makeText(context,"Ok",Toast.LENGTH_SHORT).show();
//        try {
//            AutoCompleteTextView var = autoCompleteDestinationTextViews.get(autoCompleteDestinationTextViews.size() - 1);
//            childage.removeView(var);
//            autoCompleteDestinationTextViews.remove(var);
//            if (autoCompleteDestinationTextViews.size() < 1) {
//
//                remove.setVisibility(View.GONE);
//            }
//        } catch (Exception ex) {
//
//        }
//    }


    @OnClick(R.id.addAnother)
    public void addAnother(View view) {
        //  if (autoCompleteDestinationTextViews.size()> 0)
        //Toast.makeText(context,"Ok",Toast.LENGTH_SHORT).show();
        if (autoCompleteDestinationTextViews.size() > 8) {


            String msg = "Limit exceeds";
            if (!BaseURL.LANGUAGE_ENG) msg = "সীমা অতিক্রম করেছে";
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            return;
        }

        srcDes.addView(createNew());


        if (autoCompleteDestinationTextViews.size() > 0) {
            removeOne.setVisibility(View.VISIBLE);
        }

    }

//    @OnClick(R.id.add)
//    public void addChild(View view) {
//        //  if (autoCompleteDestinationTextViews.size()> 0)
//        //Toast.makeText(context,"Ok",Toast.LENGTH_SHORT).show();
//        if (autoCompleteDestinationTextViews.size() > 8) {
//            String msg = "Limit exceeds";
//            if (!BaseURL.LANGUAGE_ENG) msg = "সীমা অতিক্রম করেছে";
//            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//            return;
//        }
//        childage.addView(createNew());
//        if (autoCompleteDestinationTextViews.size() > 0) {
//            removeChild.setVisibility(View.VISIBLE);
//        }
//
//    }


    private AutoCompleteTextView createNew() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int top = DynamicHeight.dpToPixels(8, displayMetrics);
        int bottom = DynamicHeight.dpToPixels(0, displayMetrics);
        int left = DynamicHeight.dpToPixels(20, displayMetrics);
        int right = DynamicHeight.dpToPixels(20, displayMetrics);
        lparams.setMargins(left, top, right, bottom);

        final AutoCompleteTextView textView = new AutoCompleteTextView(getActivity());

        textView.setLayoutParams(lparams);
        int padding = DynamicHeight.dpToPixels(10, displayMetrics);
        textView.setPadding(padding, padding, padding, padding);
        textView.setBackgroundColor(Color.parseColor("#eeeeee"));
        String to = "To";
        if (!BaseURL.LANGUAGE_ENG) {
            to = "পর্যন্ত";
        }
        textView.setHint(to);
        textView.setHintTextColor(Color.parseColor("#888888"));

        ArrayAdapter<Place> placeAdapter = new ArrayAdapter<Place>(context, R.layout.layout_spinner, R.id.spinnerText, places);
        textView.setAdapter(placeAdapter);
        autoCompleteDestinationTextViews.add(textView);
        //Toast.makeText(context,autoCompleteDestinationTextViews.size()+"",Toast.LENGTH_SHORT).show();
        return textView;

    }

    @OnClick(R.id.departDateLayout)
    public void setTripDate(View view) {
        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                view.setMinDate(System.currentTimeMillis() - 1000);
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //updateLabel();
                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                int days = 0;
                String selected_days = editText_no_of_days.getText().toString();
                if (selected_days.length() != 0) {
                    days = Integer.parseInt(selected_days);
                }

                // Toast.makeText(getActivity(),sdf.format(new Date())+ " " + sdf.format(myCalendar.getTime()),Toast.LENGTH_SHORT).show();
                if (sdf.format(new Date()).equals(sdf.format(myCalendar.getTime())) || new Date().before(myCalendar.getTime())) {

                    //Toast.makeText(getActivity(),"Outdated",Toast.LENGTH_SHORT).show();
                    //return;
                    departDateTextView.setText(sdf.format(myCalendar.getTime()));
                    PreviewData.stratDate = sdf.format(myCalendar.getTime());
                    BaseURL.startDate = sdf.format(myCalendar.getTime());
                    BaseURL.checkindate = sdf.format(myCalendar.getTime());
                    myCalendar.add(Calendar.DATE, days);
                    String newDate = sdf.format(myCalendar.getTime());
                    returnDateTextView.setText(newDate);
                    PreviewData.returnDate = newDate;
                    // previewData.setdepar
                    BaseURL.enddate = newDate;

                    if (!BaseURL.LANGUAGE_ENG) {
                        departDateTextView.setText(BanglaNumberParser.getBangla(departDateTextView.getText().toString()));
                        returnDateTextView.setText(BanglaNumberParser.getBangla(newDate));
                    }
                } else {
                    String msg = "Please Select a Valid Date";
                    if (!BaseURL.LANGUAGE_ENG) msg = "একটি বৈধ তারিখ নির্বাচন করুন";
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    return;
                }


            }

        };

        new DatePickerDialog(getActivity(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//        ViewAssist.setDate(getActivity(),departDateTextView,returnDateTextView,days,"Select Trip date");
    }

    @OnClick(R.id.returnDateLayout)
    public void setReturnDate(View view) {
        //ViewAssist.setDate(getActivity(),returnDateTextView,"Select Return date");
    }
    public TripPlannerFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static TripPlannerFragment newInstance() {
        TripPlannerFragment fragment = new TripPlannerFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        BaseURL.isEdit = false;
        BaseURL.clearAll();
        BaseURL.districtIdForDestination.clear();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.trip_planner_new, container, false);
        ButterKnife.bind(this, view);
        BaseURL.dates.clear();
        BaseURL.transportdateList.clear();
//        childageone =(EditText)view.findViewById(R.id.)
//        childageStr=
        childageet = (EditText) view.findViewById(R.id.childageet);
        tvchildren =(TextView)view.findViewById(R.id.tvchildren);
        mLinearLayoutContainer = (LinearLayout) view.findViewById(R.id.childage);
        removeChild = (TextView) view.findViewById(R.id.removeChild);
        tvadd = (TextView) view.findViewById(R.id.add);
        //                for (int loop = 0; loop < mLinearLayoutContainer.getChildCount()-1; loop++) {
//                    for (int j = 0; j < mLinearLayoutContainer.getChildCount(); j++) {
//                        if (mLinearLayoutContainer.getChildAt(j) instanceof EditText) {
//                            EditText textET = (EditText) mLinearLayoutContainer.getChildAt(j);
//
//                            Toast.makeText(getActivity(), ""+textET.getText().toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
        removeChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinearLayoutContainer.removeViewAt(mLinearLayoutContainer.getChildCount() - 1);
                if (mLinearLayoutContainer.getChildCount() < 2) {
                    removeChild.setVisibility(View.GONE);
                } else {
                    removeChild.setVisibility(View.VISIBLE);
                }
            }
        });
        tvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = new EditText(getActivity());
                editText.getText();
                LinearLayout.LayoutParams p = new
                        LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                p.setMargins(20, 5, 20, 5);
                editText.setLayoutParams(p);
                if (BaseURL.LANGUAGE_ENG) {
                    editText.setHint("Enter Child " + (mLinearLayoutContainer.getChildCount()) + " Age");
                    editText.setPadding(7,7,7,7);


                }else {
                    editText.setHint("শিশু " + (mLinearLayoutContainer.getChildCount()) + " বয়স এর বয়স");
                }
                editText.setBackgroundColor(Color.parseColor("#eeeeee"));
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setId(R.id.edittext_hello + mLinearLayoutContainer.getChildCount());
                mLinearLayoutContainer.addView(editText);
                if (mLinearLayoutContainer.getChildCount() > 1) {
                    removeChild.setVisibility(View.VISIBLE);
                }

            }
        });
        childagell = (LinearLayout) view.findViewById(R.id.childage);

        layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//tvadd.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        LinearLayout view = new LinearLayout(getActivity());
//
//        scrollview.addView(view);
//    }
//});
//tvremove.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        TextView view1 = new TextView(getActivity());
//        view1.setText(--i+" view");
//        //linearLayout.addView(view, layoutParams);
//        childagell.removeViewAt(i);
//    }
//});

        BaseURL.routess.clear();
        BaseURL.transportname.clear();
        BaseURL.itinaryItems.clear();
        BaseURL.dates.clear();
        BaseURL.accommodationRooms.clear();

        mySharedPreparences = new MySharedPreparences(getActivity());
        if (mySharedPreparences.isLogedIn() == false) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        autoCompleteDestinationTextViews = new ArrayList<>();
        scrollView = (ScrollView) view.findViewById(R.id.scrollVtripPlanner);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                return false;
            }
        });

        routesList = new ArrayList<>();

        if (!BaseURL.LANGUAGE_ENG) {
            //Typeface face=Typeface.createFromAsset(getActivity().getAssets(), "siyamrupali.ttf");
            // destionforlang .setTypeface(face);
            tvchildren.setText("শিশু");

            getActivity().setTitle("আপনার প্ল্যান তৈরী করুন ");
            // destionforlang.setText("FONT CHECK");
            destionforlang.setText("গন্তব্য ");
            fromlang.setText("কোথা থেকে শুরু করবেন?");
            toforlang.setText("কোথায় যাবেন?");
            trvlrforlang.setText("ভ্রমণ বিবরণ");
            trpdtforlang.setText("সময়কাল");
            deprtforlang.setText("যাত্রা শুরুর তারিখ");
            returnforlang.setText("ফেরার তারিখ");
            editText_no_of_days.setHint("দিন");
            editText_no_of_tourist.setHint("পর্যটক সংখ্যা");
            returnDateTextView.setText("দিন-মাস-বসর");
            departDateTextView.setText("দিন-মাস-বসর");
            destinationAutoComplete.setHint("পর্যন্ত");
            locationAutoComplete.setHint("থেকে");
            searchButton.setText("পরবর্তী");
            addAnother.setText("+অন্য একটি যোগ করুন");
            childrenForLang.setText("শিশুদের বিবরণ");
            editText_no_of_childeren.setHint("শিশুদের সংখ্যা");
            editText_children_details.setHint("গড় বয়স");
            removeOne.setText("-অপসারণ করুন");
            touristsForLang.setText("পর্যটক সংখ্যা");
            dayForLang.setText("দিন");
            noOfChilderForLang.setText("শিশুদের সংখ্যা");
            averageAgeForLang.setText("গড় বয়স");
            departDateForLang.setText("যাত্রা শুরুর তারিখ");
            returnDateForLang.setText("ফেরার তারিখ");
        } else getActivity().setTitle("Create Your Plan");
        // Data storage init
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.preference_file_key), getActivity().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();
        // TODO callback init
        placeCallback = new PlaceCallback(getActivity());
        routeCallback = new RouteCallback(getActivity());
        BaseURL.totalCost = 0;
        loadLocations();
        editText_no_of_days.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String departDate = departDateTextView.getText().toString();

                if (!departDate.equals("DD-MM-YYYY") && !departDate.equals("দিন-মাস-বসর")) {
                    //  Toast.makeText(getActivity(),departDate,Toast.LENGTH_SHORT).show();
                    int days = 0;
                    try {
                        days = Integer.parseInt(editText_no_of_days.getText().toString());
                    } catch (Exception ex) {

                    }
                    changeDate(departDate, days);

                }
                //Toast.makeText(getActivity(),"Change Date to :"+ departDate,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }


    @OnClick(R.id.searchButton)
    public void searchRoute(View view) {



            for (int j = 0; j < mLinearLayoutContainer.getChildCount(); j++) {
                if (mLinearLayoutContainer.getChildAt(j) instanceof EditText) {
                    EditText textET = (EditText) mLinearLayoutContainer.getChildAt(j);
                    BaseURL.childagesStr= BaseURL.childagesStr+","+textET.getText().toString();
                }
            }

        //  mySharedPreparences.setLogedInData(true);

        if (mySharedPreparences.isLogedIn()) {
            CustomTripPlanDataHolder.clearAll();
            TailorMadeDataHolder.clearAll();
            if (!validate()) {
                return;
            }

            //Toast.makeText(context,"ok",Toast.LENGTH_SHORT).show();
            CustomTripPlanDataHolder.routes = new ArrayList<>();
            final ArrayList<CustomTripPlanRouteModel> routes = new ArrayList<CustomTripPlanRouteModel>();
            CustomTripPlanRouteModel temp = new CustomTripPlanRouteModel();
            temp.setRouteFrom(getDistrcitID(locationAutoComplete.getText().toString()));
            CustomTripPlanDataHolder.selectedRoutesName = locationAutoComplete.getText().toString() + "-";
            temp.setRouteTo(getDistrcitID(destinationAutoComplete.getText().toString()));
            PreviewData.fromRoute = locationAutoComplete.getText().toString();

            PreviewData.toRoute = destinationAutoComplete.getText().toString();

            routes.add(temp);
            Route route = new Route();
            route.setStartDistrictId(getDistrcitID(locationAutoComplete.getText().toString()));
            route.setStartDistrictName(locationAutoComplete.getText().toString());
            route.setEndDistrictId(temp.getRouteTo());
            route.setEndDistrictName(destinationAutoComplete.getText().toString());
            CustomTripPlanDataHolder.districtsName.add(destinationAutoComplete.getText().toString());
            CustomTripPlanDataHolder.routes.add(route);


            for (int i = 0; i < autoCompleteDestinationTextViews.size(); i++) {
                temp = new CustomTripPlanRouteModel();
                // Toast.makeText(context,autoCompleteDestinationTextViews.get(i - 1).getText(),Toast.LENGTH_SHORT).show();
                route = new Route();
                if (i == 0)

                {
                    temp.setRouteFrom(routes.get(0).getRouteTo());
                    CustomTripPlanDataHolder.selectedRoutesName += destinationAutoComplete.getText().toString() + "-";
                    route.setStartDistrictId(routes.get(0).getRouteTo());
                    route.setStartDistrictName(destinationAutoComplete.getText().toString());
                } else {
                    String dest = autoCompleteDestinationTextViews.get(i - 1).getText().toString();
                    // Toast.makeText(context, ""+dest, Toast.LENGTH_SHORT).show();

                    temp.setRouteFrom(getDistrcitID(dest));
                    CustomTripPlanDataHolder.selectedRoutesName += dest + "-";
                    route.setStartDistrictId(getDistrcitID(dest));
                    route.setStartDistrictName(dest);


//               route = new Route();
//               route.setEndDistrictId(getDistrcitID(dest));
//               route.setEndDistrictName(dest);
//               CustomTripPlanDataHolder.routes.add(route);


                }
                temp.setRouteTo(getDistrcitID(autoCompleteDestinationTextViews.get(i).getText().toString()));
                CustomTripPlanDataHolder.selectedRoutesName += autoCompleteDestinationTextViews.get(i).getText().toString() + "-";
                routes.add(temp);

                //route = new Route();
                route.setEndDistrictId(getDistrcitID(autoCompleteDestinationTextViews.get(i).getText().toString()));
                route.setEndDistrictName(autoCompleteDestinationTextViews.get(i).getText().toString());
                CustomTripPlanDataHolder.routes.add(route);
                CustomTripPlanDataHolder.districtsName.add(autoCompleteDestinationTextViews.get(i).getText().toString());

            }
            CustomTripPlanRoutesListModel list = new CustomTripPlanRoutesListModel();
            list.setRouteList(routes);
            CustomTripPlanDataHolder.selectedRoutes = list;
            Gson gson = new Gson();
            String json = gson.toJson(CustomTripPlanDataHolder.routes);
            Log.e("Url", "Null");
            Log.e("JSON", json);
            if (list.getRouteList().size() == 1)
                CustomTripPlanDataHolder.selectedRoutesName += destinationAutoComplete.getText().toString() + "-";
            CustomTripPlanDataHolder.selectedRoutesName = CustomTripPlanDataHolder.selectedRoutesName.substring(0, CustomTripPlanDataHolder.selectedRoutesName.length() - 1);
            CustomTripPlanDataHolder.startingDate = BanglaNumberParser.getEnglish(departDateTextView.getText().toString());
            CustomTripPlanDataHolder.noOfDays = Integer.parseInt(BanglaNumberParser.getEnglish(editText_no_of_days.getText().toString()));
            CustomTripPlanDataHolder.noOfTourist = Integer.parseInt(BanglaNumberParser.getEnglish(editText_no_of_tourist.getText().toString()));
            CustomTripPlanDataHolder.returnDate = BanglaNumberParser.getEnglish(returnDateTextView.getText().toString());
            PreviewData.torrists = BanglaNumberParser.getEnglish(editText_no_of_tourist.getText().toString());
            PreviewData.days = BanglaNumberParser.getEnglish(editText_no_of_days.getText().toString());
            PreviewData.fromPlace = CustomTripPlanDataHolder.selectedRoutesName.substring(0, CustomTripPlanDataHolder.selectedRoutesName.length());
            if (!editText_no_of_childeren.getText().toString().equals("")) {

                CustomTripPlanDataHolder.noOfChildren = Integer.parseInt(editText_no_of_childeren.getText().toString());
                PreviewData.noOfchild = editText_no_of_childeren.getText().toString();
            }
            for (int loop = 0; loop < mLinearLayoutContainer.getChildCount() - 1; loop++) {
                for (int j = 0; j < mLinearLayoutContainer.getChildCount(); j++) {
                    if (mLinearLayoutContainer.getChildAt(j) instanceof EditText) {
                        EditText textET = (EditText) mLinearLayoutContainer.getChildAt(j);
                        CustomTripPlanDataHolder.childrenDetails = textET.getText().toString() + ",";
                    }
                }
            }
            int count = mLinearLayoutContainer.getChildCount() - 1;
            PreviewData.noOfchild = count + "";
            // CustomTripPlanDataHolder.childrenDetails = editText_children_details.getText().toString();
            PreviewData.avgage = editText_children_details.getText().toString();
            Intent intent = new Intent(context, NewTripRouteActivity.class);
            startActivity(intent);
        } else {
            //Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

    private int getDistrcitID(String name) {
        int districtID = 0;

        for (Place place : places) {
            if (place.getDistrictName().equalsIgnoreCase(name) || place.getDistrictNameBn().equalsIgnoreCase(name)) {

                districtID = place.getDistrictId();
                //destinationDistrictName=place.getDistrictName();
                break;
            }
        }
        return districtID;

    }
//////////////////////////////////////////////////


//
//    @OnClick(R.id.searchButton)
//    public void searchRoute(View view)
//    {
//
//        Intent intent=new Intent(context,TripRouteActivity.class);
//        //get data from user
//        String destinationlocation=destinationAutoComplete.getText().toString();
//        String departlocation=locationAutoComplete.getText().toString();
//
//        String tmp_tourist=editText_no_of_tourist.getText().toString();
//        String tmp_days=editText_no_of_days.getText().toString();
//        String fromDate=departDateTextView.getText().toString();
//        String toDate=returnDateTextView.getText().toString();
//        if(!validate()){
//            return;
//        }
//        int numberOfTourist=Integer.parseInt(tmp_tourist);
//        int numberOfDays=Integer.parseInt(tmp_days);
//        int destinationDistrictId=0;
//        String destinationDistrictName="";
//        int departDistrictId=0;
//        String DepartDistrictName="";
//
//        for (Place place:places){
//            if (place.getDistrictName().equalsIgnoreCase(destinationlocation) || place.getDistrictNameBn().equalsIgnoreCase(destinationlocation)){
//                destinationDistrictId=place.getDistrictId();
//                destinationDistrictName=place.getDistrictName();
//                break;
//            }
//        }
//        for (Place place:places){
//            if (place.getDistrictName().equalsIgnoreCase(departlocation)|| place.getDistrictNameBn().equalsIgnoreCase(departlocation)){
//                departDistrictId=place.getDistrictId();
//                DepartDistrictName=place.getDistrictName();
//                break;
//            }
//        }
//        // save user inputs
//        editor.putInt(Travel.DEPART_LOCATION,departDistrictId);
//        editor.putInt(Travel.TO_LOCATION,destinationDistrictId);
//        editor.putInt(Travel.NUMBER_OF_TOUIST,numberOfTourist);
//        editor.putInt(Travel.NUMBER_OF_DAYS,numberOfDays);
//        editor.putString(Travel.DEPART_DATE,BanglaNumberParser.getEnglish(fromDate));
//        editor.putString(Travel.RETURN_DATE,BanglaNumberParser.getEnglish(toDate));
//        editor.commit();
//        // TODO save tailormade data
//        int current_tailormade_id =sharedPreferences.getInt(Travel.CURRENT_TAILORMADE_ID,0);
//        RealmResults<TailorMade> tailormades=realm.where(TailorMade.class).equalTo("tailormade_id",current_tailormade_id).findAll();
//        TailorMade tailorMade=new TailorMade();
//        tailorMade.tailormade_id=current_tailormade_id;
//        tailorMade.deapartDistrictId=departDistrictId;
//        tailorMade.destinationDistrictId=destinationDistrictId;
//        tailorMade.departDistrictName=DepartDistrictName;
//        tailorMade.destinationDistrictName=destinationDistrictName;
//        tailorMade.departDate=BanglaNumberParser.getEnglish(fromDate);
//        tailorMade.returnDate=BanglaNumberParser.getEnglish(toDate);
//        tailorMade.numberOFDays=numberOfDays;
//        tailorMade.numberOFTourists=numberOfTourist;
//        // check if data is already saved
//        if(tailormades.size()==0){
//
//        }
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(tailorMade);
//        realm.commitTransaction();
//
//        startActivity(intent);
//    }


    public boolean validate() {
        String tmp_tourist = editText_no_of_tourist.getText().toString();
        String tmp_days = editText_no_of_days.getText().toString();
        String fromDate = departDateTextView.getText().toString();
        String toDate = returnDateTextView.getText().toString();
        String destinationlocation = destinationAutoComplete.getText().toString();
        String departlocation = locationAutoComplete.getText().toString();
        if (departlocation == null || departlocation == "" || departlocation.length() < 1) {
            String error = "Enter Depart Location";
            if (!BaseURL.LANGUAGE_ENG) error = "যাত্রা শুরুর স্থান লিখুন";
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (destinationlocation == null || destinationlocation == "" || destinationlocation.length() < 1) {
            String error = "Enter Destination";
            if (!BaseURL.LANGUAGE_ENG) error = "গন্তব্য লিখুন";
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (tmp_tourist.length() == 0 || tmp_tourist == "") {
            String error = "Enter Number of tourist";
            if (!BaseURL.LANGUAGE_ENG) error = "পর্যটক সংখ্যা লিখুন";
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tmp_days.length() == 0 || tmp_days == "") {
            String error = "Enter Number of days";
            if (!BaseURL.LANGUAGE_ENG) error = "দিনের সংখ্যা লিখুন";
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (fromDate.equals("DD-MM-YYYY") || fromDate.equals("দিন-মাস-বসর")) {
            String error = "Enter depart date";
            if (!BaseURL.LANGUAGE_ENG) error = "যাত্রা শুরুর তারিখ লিখুন";
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (toDate.equals("DD-MM-YYYY") || toDate.equals("দিন-মাস-বসর")) {
            String error = "Enter return date";
            if (!BaseURL.LANGUAGE_ENG) error = "ফেরার তারিখ লিখুন";
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // load all districts in spinner
    private void loadLocations() {
        placeCallback.getLocations().enqueue(new CustomCallBack<Place[]>(getActivity()) {
            @Override
            public void onResponse(Call<Place[]> call, Response<Place[]> response) {
                super.onResponse(call, response);
                if (response.body() != null && response.body().length > 0) {
                    places = Arrays.asList(response.body());
                    ArrayAdapter<Place> placeAdapter = new ArrayAdapter<Place>(context, R.layout.layout_spinner, R.id.spinnerText, response.body());
                    locationAutoComplete.setAdapter(placeAdapter);
                    destinationAutoComplete.setAdapter(placeAdapter);
                }
            }
            @Override
            public void onFailure(Call<Place[]> call, Throwable t) {
                super.onFailure(call, t);
                String meesage = "Network Error";
                if (!BaseURL.LANGUAGE_ENG) {
                    meesage = " নেটওয়ার্ক ত্রুটি";
                }
                Toast.makeText(getActivity(), meesage, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), "Locations Could not be loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
