package com.olivine.parjatanbichitra.cholodesh;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import adapters.CustomTripPlanTransportAdapter;
import adapters.ItineraryListAdapter;
import adapters.LocalTourAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import callbacks.AuthCallback;
import callbacks.PlaceCallback;
import constants.Travel;
import fragments.TailormadeListFragment;
import fragments.TripPlannerFragment;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomCallBack;
import helpers.CustomTripPlanDataHolder;
import helpers.RecyclerItemClickListener;
import helpers.RetrofitInitializer;
import helpers.TailorMadeDataHolder;
import io.realm.Realm;
import listeners.IteneraryListener;
import listeners.ItineraryListener;
import listeners.TailormadeListener;
import model.AccommodationRoom;
import model.DateStore;
import model.Destination;
import model.Food;
import model.Itenerary;
import model.ListWrapper;
import model.LocalTour;
import model.PreviewData;
import model.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import userDefinder.TailorMade;
import userDefinder.TailormadeSync;

public class ItineraryPlanner extends AppCompatActivity implements IteneraryListener {
    private List<String> indate = new ArrayList<>();
    private List<String> outdate = new ArrayList<>();
    TextView fromtv, itineraryy, startdatetv, returntv, torriststv, daystv, hotelname, hotelcost, checkindatetv, checkoutdatetv, transport, transport2, transportrout, transportrouttwo, transportcost, hotelname2, hotelcost2, checkindatetv2, checkoutdatetv2, hotelname3, hotelcost3, checkindatetv3, checkoutdatetv3;
    TextView itinery, itinarydatetv, itinery2, itinarydatetv2, itinery3, itinarydatetv3, itinerarycost;
    private static final int FINISH_MENU = 1;
    private static final int FOOD_MENU = 3;
    private static final int PREVIEW = 2;
    private static final int REQUEST_FOOD = 1004;
    int day = 0;
    int maxDays;
    int totalItenaryCost = 0;
    int districtID = 0;
    private AuthCallback authCallback;
    private Retrofit retrofit;
    private ItineraryListener itineraryListener;
    private Context context;
    private LocalTour[] localTours;
    private ArrayList<Itenerary> iteneraries = new ArrayList<>();
    // TODO Data storages declear
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Realm realm;
    // Adapters
    private ItineraryListAdapter itineraryListAdapter;
    private LocalTourAdapter localTourAdapter;
    // Callback
    PlaceCallback placeCallback;

    @BindView(R.id.placeHolderImage)
    ImageView placeHolderImage;
    @BindView(R.id.placeHolderText)

    TextView placeHolderText;

    // viewsinitiallization
    @BindView(R.id.spinnerDestinations)
    Spinner spinnerDestinations;
    @BindView(R.id.spinnertourTime)
    Spinner spinnertourTime;
    @BindView(R.id.localtripListView)
    RecyclerView localtripListView;
    @BindView(R.id.itineraryListView)
    RecyclerView itineraryListView;
    @BindView(R.id.itineraryPlaceholderText)
    TextView itineraryPlaceholderText;
    @BindView(R.id.dayplanSelector)
    TextView dayplanSelector;
    @BindView(R.id.totalCostView)
    TextView totalCostView;
    @BindView(R.id.spinnerDistricts)
    Spinner spinnerDistricts;
    @BindView(R.id.totalCost)
    TextView totalCost;
    @BindView(R.id.notifyTxtForLang)
    TextView notifyTxtForLang;
    @BindView(R.id.districtForLang)
    TextView districtForLang;
    @BindView(R.id.nearByPlaceForLang)
    TextView nearByPlaceForLang;
    @BindView(R.id.totalForLang)
    TextView totalForLang;
    @BindView(R.id.estimaforlang)
    TextView estimaforlang;
    @BindView(R.id.selectedItirenaryForLang)
    TextView selectedItirenaryForLang;
    @BindView(R.id.groupSelect)
    CheckBox groupSelect;
    boolean isHalfDayBooked = false;
    boolean isGroup = false;
    private TailorMade tailorMade;
    private int tailormade_id;

    // change day
    @OnClick(R.id.dayplus)
    public void nextDay(View view) {
        if (day == maxDays - 1) {
            return;
        }
        day++;
//        if (!BaseURL.LANGUAGE_ENG)
//        {
//            dayplanSelector.setText("দিন  "+ BanglaNumberParser.getBangla(day+""));
//        }
        try {
            Date current = new SimpleDateFormat("dd-MM-yyyy").parse(BanglaNumberParser.getEnglish(dayplanSelector.getText().toString()));
            Date dayAfter = new Date(current.getTime() + TimeUnit.DAYS.toMillis(1));
            String newstring = new SimpleDateFormat("dd-MM-yyyy").format(dayAfter);
            if (!BaseURL.LANGUAGE_ENG) {
                newstring = BanglaNumberParser.getBangla(newstring);
            }
            dayplanSelector.setText(newstring);
            //filterGroup(isGroup);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.dayminus)
    public void previousDay(View view) {
        if (day == 0) {
            return;
        }
        day--;
//        if (!BaseURL.LANGUAGE_ENG)
//        {
//            dayplanSelector.setText("দিন  "+BanglaNumberParser.getBangla(day+""));
//        }
        //dayplanSelector.setText("Day "+day);

        try {
            Date current = new SimpleDateFormat("dd-MM-yyyy").parse(BanglaNumberParser.getEnglish(dayplanSelector.getText().toString()));
            Date dayAfter = new Date(current.getTime() - TimeUnit.DAYS.toMillis(1));
            String newstring = new SimpleDateFormat("dd-MM-yyyy").format(dayAfter);
            if (!BaseURL.LANGUAGE_ENG) {
                newstring = BanglaNumberParser.getBangla(newstring);
            }
            dayplanSelector.setText(newstring);
            //filterGroup(isGroup);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void filterTrips() {

        List<LocalTour> tmp = new ArrayList<>();
        if (localTours == null || localTours.length == 0) return;
        for (LocalTour l : localTours) {

            if (!isSelected(l)) {
                if (isGroup && l.getLocalTourStatus().equalsIgnoreCase("group")) {
                    l.setLocalTourPerPersonCost(l.localTourGroupCost);
                    tmp.add(l);
                } else if (!isGroup && l.getLocalTourStatus().equalsIgnoreCase("single")) {
                    tmp.add(l);
                }

            }

        }

        LocalTour[] array = tmp.toArray(new LocalTour[tmp.size()]);
        localTourAdapter = new LocalTourAdapter(context, array);
        localtripListView.setAdapter(localTourAdapter);

    }

    private boolean isSelected(LocalTour localTour) {

       // Toast.makeText(context, "oi hala", Toast.LENGTH_SHORT).show();
        for (Itenerary i : iteneraries) {
            if (i.getLocalTourId().equals(localTour.getLocalTourId())) return true;


        }
        return false;
    }

    // end change day
    // If Destination change from spinner load data according to selection
    public void onDestinationSelectedListener() {
        spinnerDestinations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadLocaltour();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // If time change load data according to selection
    private void onTimeSelectedListener() {
        spinnertourTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadLocaltour();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void loadLocaltour() {
        Destination destination = (Destination) spinnerDestinations.getSelectedItem();
        String time = (String) spinnertourTime.getSelectedItem();

        if (!BaseURL.LANGUAGE_ENG) {
            switch (time) {
                case "পূর্বাহ্ণ":
                    time = "AM";
                    break;
                case "অপরাহ্ন":
                    time = "PM";
                    break;
                case "পূর্বাহ্ণ-অপরাহ্ন":
                    time = "AM-PM";
                    break;
            }
        }
        if (destination != null) {
            loadLocaltrips(destination.getDestinationId() + "", time);
            // Toast.makeText(this,destination.getDestinationId()+""+time,Toast.LENGTH_SHORT).show();
        }
    }

    public void replaceLocalTour(LocalTour l) {
        for (int i = 0; i < localTours.length; i++) {
            if (l.getLocalTourId() == localTours[i].getLocalTourId()) {
                localTours[i] = l;
                //Toast.makeText(context,"Rep "+localTours[i].getLocalTourPerPersonCost(),Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_planner);


        ButterKnife.bind(this);
        BaseURL.itinaryItems.clear();

        for (int i = 0; i < BaseURL.dates.size(); i++) {
            indate.add(BaseURL.dates.get(i).getCheckindate());
            outdate.add(BaseURL.dates.get(i).getCheckoutDate());
        }


        int length = CustomTripPlanDataHolder.routes.size();
        int districtToId = 0;
        String fromId = CustomTripPlanDataHolder.routes.get(0).getStartDistrictId()+"";
        BaseURL.districtIdForDestination.add(fromId);
        if (length > 0) {
            for (int i=0;i<length;i++) {
                Route to = CustomTripPlanDataHolder.routes.get(i);
                districtToId = to.getEndDistrictId();
                BaseURL.districtIdForDestination.add(districtToId+"");
            }
        }
//        Toast.makeText(this, ""+BaseURL.datecheckHotel.get(0), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ""+BaseURL.datecheckHotel.get(1), Toast.LENGTH_SHORT).show();
//        localtripListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0) {
//                    Toast.makeText(context, ""+dy, Toast.LENGTH_SHORT).show();
//                    // Scrolling up
//                    localtripListView.setNextFocusDownId(dy);
//                } else {
//                    localtripListView.setNextFocusUpId(dx);
//                    // Scrolling down
//                }
//            }
//        });

        context = getApplicationContext();
        int cost = BaseURL.totalCost + CustomTripPlanDataHolder.transPortcost;
        if (BaseURL.isEdit) {
            cost += TailorMadeDataHolder.locaTourCost;
            totalItenaryCost = TailorMadeDataHolder.locaTourCost;
        }
        String prcCost = cost + " ৳";
        if (!BaseURL.LANGUAGE_ENG) {
            prcCost = BanglaNumberParser.getBangla(prcCost);
        }
        totalCost.setText(prcCost);
        PreviewData.totalcost = prcCost;
        groupSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,groupSelect.isChecked()+"",Toast.LENGTH_SHORT).show();
                isGroup = groupSelect.isChecked();
                filterGroup(groupSelect.isChecked());
            }
        });
        //getSupportActionBar().setTitle("Itinerary Plan");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Toast.makeText(context, ""+CustomTripPlanDataHolder.lastDate, Toast.LENGTH_SHORT).show();
//        String date;
//        if (CustomTripPlanDataHolder.lastDate == "" || CustomTripPlanDataHolder.lastDate == null || CustomTripPlanDataHolder.lastDate.length() < 1) {
//            date = CustomTripPlanDataHolder.startingDate;
//        } else {
//            date = CustomTripPlanDataHolder.lastDate;
//        }
//        if (!BaseURL.LANGUAGE_ENG) {
//            date = BanglaNumberParser.getBangla(date);
//        }
//        dayplanSelector.setText(date);
        String date;

        date = CustomTripPlanDataHolder.startingDate;

        if (!BaseURL.LANGUAGE_ENG) {
            date = BanglaNumberParser.getBangla(date);
        }
        dayplanSelector.setText(date);


        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        groupSelect.setText("Group");
        if (!BaseURL.LANGUAGE_ENG) {
            this.setTitle("ভ্রমণ পরিকল্পনা");
            districtForLang.setText("জেলা");
            nearByPlaceForLang.setText("গন্তব্য সমূহ");
            notifyTxtForLang.setText("গ্রুপ ডাটা দেখার জন্য গ্রুপ বক্সটি চেক করুন");
            totalForLang.setText("মোট");
            estimaforlang.setText("ভ্রমণের খরচ");
            selectedItirenaryForLang.setText("নির্বাচিত ভ্রমণ");
            groupSelect.setText("গ্রুপ");
            itineraryPlaceholderText.setText("ভ্রমণ যোগ করার জন্য  আইটেম ক্লিক করুন");
            totalCostView.setText(BanglaNumberParser.getBangla(totalCostView.getText().toString()));
            spinnerDestinations.setPrompt("একটি গন্তব্য নির্বাচন করুন ");
            spinnerDistricts.setPrompt("একটি গন্তব্য নির্বাচন করুন ");
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.timeBn, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnertourTime.setAdapter(adapter);
        } else
            this.setTitle("Local Tour");
        authCallback = new AuthCallback(this);
        // TODO Storage init
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        //var init
        maxDays = CustomTripPlanDataHolder.noOfDays;
        // Api initialize
        placeCallback = new PlaceCallback(this);
        retrofit = RetrofitInitializer.initNetwork(this);
        itineraryListener = retrofit.create(ItineraryListener.class);
        loadDistrcist();
        onDistrcitSelectedListener();
        onDestinationSelectedListener();
        onTimeSelectedListener();
        //TODO
        // Get current tailormade ID
        tailormade_id = sharedPreferences.getInt(Travel.CURRENT_TAILORMADE_ID, 0);
        tailorMade = realm.where(TailorMade.class).equalTo("tailormade_id", tailormade_id).findFirst();

//        ArrayList realm_planning= (ArrayList) realm.copyFromRealm(tailorMade.iteneraries);
        // Check for previously added plan
//        if(realm_planning!=null && realm_planning.size()>0){
//            iteneraries=realm_planning;
//            itineraryListAdapter=new ItineraryListAdapter(context,realm_planning);
//            itineraryListView.setAdapter(itineraryListAdapter);
//            itineraryPlaceholderText.setVisibility(View.GONE);
//            itineraryListView.setVisibility(View.VISIBLE);
//        }
        if (BaseURL.isEdit) {
            iteneraries = TailorMadeDataHolder.iteneraries;
            ItineraryListAdapter test = new ItineraryListAdapter(context, iteneraries, ItineraryPlanner.this);
            itineraryListView.setAdapter(test);
            itineraryListView.setVisibility(View.VISIBLE);
            itineraryPlaceholderText.setVisibility(View.GONE);
            String costLang = TailorMadeDataHolder.locaTourCost + "";
            if (!BaseURL.LANGUAGE_ENG) {
                costLang = BanglaNumberParser.getBangla(costLang);
            }
            totalCostView.setText(costLang);
        }
        setListListener();
    }

    private void callPreviewDialog() {





        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.itinerary_preview);
        LinearLayout hoteltwo, hotelthree;
        TextView children;
        children = (TextView) dialog.findViewById(R.id.children);
        children.setText(PreviewData.noOfchild);

        Button cancle;
        cancle = (Button) dialog.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        hoteltwo = (LinearLayout) dialog.findViewById(R.id.hoteltwo);
        hotelthree = (LinearLayout) dialog.findViewById(R.id.hotelthree);
        Button saveitinery;

        LinearLayout transportshow = (LinearLayout) dialog.findViewById(R.id.transportshow);
        saveitinery = (Button) dialog.findViewById(R.id.saveitinery);
        TextView tvchildrenn = (TextView) dialog.findViewById(R.id.tvchildrenn);
        TextView tvdays = (TextView) dialog.findViewById(R.id.tvdays);
        TextView tvreturndate = (TextView) dialog.findViewById(R.id.tvreturndate);
        TextView tvtorist = (TextView) dialog.findViewById(R.id.tvtorist);
        TextView tvtranportname = (TextView) dialog.findViewById(R.id.tvtranportname);
        TextView tvtranportname1 = (TextView) dialog.findViewById(R.id.tvtranportname1);
        TextView tvstartdatee = (TextView) dialog.findViewById(R.id.tvstartdatee);
        TextView tvtransportinfo = (TextView) dialog.findViewById(R.id.tvtransportinfo);
        TextView tvhotelinfo = (TextView) dialog.findViewById(R.id.tvhotelinfo);
        TextView tvhotelone = (TextView) dialog.findViewById(R.id.tvhotelone);
        TextView tvhotelonecheckin = (TextView) dialog.findViewById(R.id.tvhotelonecheckin);
        TextView tvhotelonecheckoutdate = (TextView) dialog.findViewById(R.id.tvhotelonecheckoutdate);
        TextView tvhoteltwo = (TextView) dialog.findViewById(R.id.tvhoteltwo);
        TextView tvhoteltwocheckin = (TextView) dialog.findViewById(R.id.tvhoteltwocheckin);
        TextView tvhotelcheckoudatetwo = (TextView) dialog.findViewById(R.id.tvhotelcheckoudatetwo);
        TextView tvhotelthree = (TextView) dialog.findViewById(R.id.tvhotelthree);
        TextView tvcheckinthree = (TextView) dialog.findViewById(R.id.tvcheckinthree);
        TextView tvcheckoutthree = (TextView) dialog.findViewById(R.id.tvcheckoutthree);
        TextView tvnearby = (TextView) dialog.findViewById(R.id.tvnearby);
        TextView tvtransOneDate=(TextView)dialog.findViewById(R.id.tvtransOneDate);
        TextView tvtransTwoDate =(TextView)dialog.findViewById(R.id.tvtransTwoDate);
        TextView tvtransportOne =(TextView)dialog.findViewById(R.id.tvtransportOne);
        TextView tvtransportTwo =(TextView)dialog.findViewById(R.id.tvtransportTwo);
        TextView tvtransportThree =(TextView)dialog.findViewById(R.id.tvtransportThree);

        LinearLayout transportshowthree =(LinearLayout)dialog.findViewById(R.id.transportshowthree);

        TextView tvtransThreeDate =(TextView)dialog.findViewById(R.id.tvtransThreeDate);
        TextView transporttv3 =(TextView)dialog.findViewById(R.id.transporttv3);
        TextView tvformtransportthree =(TextView)dialog.findViewById(R.id.tvformtransportthree);


        TextView tvchildrendes =(TextView)dialog.findViewById(R.id.tvchildrendes);
        TextView childrendescription =(TextView)dialog.findViewById(R.id.childrendescription);

        TextView hotelName4 =(TextView)dialog.findViewById(R.id.hotelName4);
        TextView checkinDatep4 =(TextView)dialog.findViewById(R.id.checkinDatep4);
        TextView CheckoutDate4 =(TextView)dialog.findViewById(R.id.CheckoutDate4);


        TextView tvhotelFour =(TextView)dialog.findViewById(R.id.tvhotelFour);
        TextView tvcheckinFour =(TextView)dialog.findViewById(R.id.tvcheckinFour);
        TextView tvcheckoutFour =(TextView)dialog.findViewById(R.id.tvcheckoutFour);


        LinearLayout hotelFour =(LinearLayout)dialog.findViewById(R.id.hotelFour);
        LinearLayout llhotel =(LinearLayout)dialog.findViewById(R.id.llhotel);
        LinearLayout llitineraryone =(LinearLayout)dialog.findViewById(R.id.llitineraryone);
        LinearLayout llitinerarytwo =(LinearLayout)dialog.findViewById(R.id.llitinerarytwo);
        LinearLayout llitineraryThree =(LinearLayout)dialog.findViewById(R.id.llitineraryThree);

        childrendescription.setText(BaseURL.childagesStr);

        if (BaseURL.transportdateList.size()==1){
            tvtransOneDate.setText(BaseURL.transportdateList.get(0));
        }else if (BaseURL.transportdateList.size()==2){
            tvtransOneDate.setText(BaseURL.transportdateList.get(0));
            tvtransTwoDate.setText(BaseURL.transportdateList.get(1));
        }else if (BaseURL.transportdateList.size()>2){
            transportshowthree.setVisibility(View.VISIBLE);
            tvtransOneDate.setText(BaseURL.transportdateList.get(0));
            tvtransTwoDate.setText(BaseURL.transportdateList.get(1));
            tvtransThreeDate.setText(BaseURL.transportdateList.get(2));

        }

        if (!BaseURL.LANGUAGE_ENG) {
            tvchildrendes.setText("শিশুদের বয়স : ");
            tvtransportThree.setText("তারিখ : ");
            tvtransportTwo.setText("তারিখ : ");
            tvtransportOne.setText("তারিখ : ");
            tvchildrenn.setText("শিশুর সংখ্যা : ");
            tvdays.setText("দিন সংখ্যা : ");
            tvreturndate.setText("ফিরে আসার তারিখ : ");
            tvtorist.setText("পর্যটক সংখ্যা : ");
            tvtranportname.setText("ট্রান্সপোর্ট নাম :");
            tvtranportname1.setText("ট্রান্সপোর্ট নাম :");
            tvstartdatee.setText("শুরুর তারিখ :");
            tvtransportinfo.setText("ট্রান্সপোর্ট ইনফো  :");
            tvhotelinfo.setText("হোটেল ইনফো :");
            tvhotelone.setText("হোটেল : ");
            tvhotelthree.setText("হোটেল : ");
            tvhotelonecheckin.setText("চেক ইন তারিখ : ");
            tvcheckinthree.setText("চেক ইন তারিখ : ");
            tvhotelonecheckoutdate.setText("চেক আউট তারিখ : ");
            tvhoteltwo.setText("হোটেল : ");
            tvhoteltwocheckin.setText("চেক ইন তারিখ : ");
            tvhotelcheckoudatetwo.setText("চেক আউট তারিখ : ");
            tvcheckoutthree.setText("চেক আউট তারিখ : ");
            tvnearby.setText("আশেপাশের এলাকা  : ");

        }
        saveitinery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int tmp_id = tailormade_id;
                String title = "Confirmation";
                String message = "Do You Want to Save your Trip Plan?";
                String yes = "Yes";
                String no = "No";
                if (!BaseURL.LANGUAGE_ENG) {
                    title = "নিশ্চিত করুন ";
                    message = "আপনি আপনার পরিকল্পনাটি  সংরক্ষণ করতে চান?";
                    yes = "হ্যা";
                    no = "না";
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(ItineraryPlanner.this);
                dialog.setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent loginIntent = new Intent(ItineraryPlanner.this, LoginActivity.class);
                        startActivityForResult(loginIntent, tmp_id);
                    }
                });
                dialog.setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Intent intent=new Intent(ItineraryPlanner.this,MainActivity.class);
                        //startActivity(intent);
                    }
                });
                dialog.setMessage(message);
                dialog.setTitle(title);
                dialog.show();
            }
        });
        hotelname2 = (TextView) dialog.findViewById(R.id.hotelName2);
        hotelcost2 = (TextView) dialog.findViewById(R.id.hotelcost2);
        checkindatetv2 = (TextView) dialog.findViewById(R.id.checkinDatep2);
        checkoutdatetv2 = (TextView) dialog.findViewById(R.id.CheckoutDate2);

        hotelname3 = (TextView) dialog.findViewById(R.id.hotelName3);
        hotelcost3 = (TextView) dialog.findViewById(R.id.hotelcost3);
        checkindatetv3 = (TextView) dialog.findViewById(R.id.checkinDatep3);
        checkoutdatetv3 = (TextView) dialog.findViewById(R.id.CheckoutDate3);

        itineraryy = (TextView) dialog.findViewById(R.id.itineraryy);
        fromtv = (TextView) dialog.findViewById(R.id.tvform);
        startdatetv = (TextView) dialog.findViewById(R.id.departuredate);
        returntv = (TextView) dialog.findViewById(R.id.returnDate);
        torriststv = (TextView) dialog.findViewById(R.id.torist);
        daystv = (TextView) dialog.findViewById(R.id.days);
        hotelname = (TextView) dialog.findViewById(R.id.hotelName);
        hotelcost = (TextView) dialog.findViewById(R.id.hotelcost);
        checkindatetv = (TextView) dialog.findViewById(R.id.checkinDatep);
        checkoutdatetv = (TextView) dialog.findViewById(R.id.CheckoutDate);
        transport = (TextView) dialog.findViewById(R.id.transporttv);
        transport2 = (TextView) dialog.findViewById(R.id.transporttv2);
        transportrout = (TextView) dialog.findViewById(R.id.tvformtransport);
        transportrouttwo = (TextView) dialog.findViewById(R.id.tvformtransporttwo);
        transportcost = (TextView) dialog.findViewById(R.id.transportCosttv);
        itinerarycost = (TextView) dialog.findViewById(R.id.itinerarycost);
        itinarydatetv = (TextView) dialog.findViewById(R.id.itinarydate);
        itinery = (TextView) dialog.findViewById(R.id.itinery);
        itinarydatetv2 = (TextView) dialog.findViewById(R.id.itinarydate2);
        itinery2 = (TextView) dialog.findViewById(R.id.itinery2);
        itinarydatetv3 = (TextView) dialog.findViewById(R.id.itinarydate3);
        itinery3 = (TextView) dialog.findViewById(R.id.itinery3);


        if (BaseURL.dates.size() == 1) {
            llhotel.setVisibility(View.VISIBLE);
            hotelname.setText(BaseURL.dates.get(0).getHotelName());
            checkindatetv.setText(BaseURL.dates.get(0).getCheckindate());
            checkoutdatetv.setText(BaseURL.dates.get(0).getCheckoutDate());
        }
        if (BaseURL.dates.size() == 2) {
            llhotel.setVisibility(View.VISIBLE);
            hoteltwo.setVisibility(View.VISIBLE);
            hotelname.setText(BaseURL.dates.get(0).getHotelName());
            checkindatetv.setText(BaseURL.dates.get(0).getCheckindate());
            checkoutdatetv.setText(BaseURL.dates.get(0).getCheckoutDate());
            hotelname2.setText(BaseURL.dates.get(1).getHotelName());
            checkindatetv2.setText(BaseURL.dates.get(1).getCheckindate());
            checkoutdatetv2.setText(BaseURL.dates.get(1).getCheckoutDate());

        }
        if (BaseURL.dates.size() == 3) {
            llhotel.setVisibility(View.VISIBLE);
            hotelthree.setVisibility(View.VISIBLE);
            hoteltwo.setVisibility(View.VISIBLE);
            hotelname.setText(BaseURL.dates.get(0).getHotelName());
            checkindatetv.setText(BaseURL.dates.get(0).getCheckindate());
            checkoutdatetv.setText(BaseURL.dates.get(0).getCheckoutDate());
            hotelname2.setText(BaseURL.dates.get(1).getHotelName());
            checkindatetv2.setText(BaseURL.dates.get(1).getCheckindate());
            checkoutdatetv2.setText(BaseURL.dates.get(1).getCheckoutDate());
            hotelname3.setText(BaseURL.dates.get(2).getHotelName());
            checkindatetv3.setText(BaseURL.dates.get(2).getCheckindate());
            checkoutdatetv3.setText(BaseURL.dates.get(2).getCheckoutDate());
        }

        if (BaseURL.dates.size() > 3) {
            llhotel.setVisibility(View.VISIBLE);
            hotelthree.setVisibility(View.VISIBLE);
            hoteltwo.setVisibility(View.VISIBLE);
            hotelFour.setVisibility(View.VISIBLE);
            hotelname.setText(BaseURL.dates.get(0).getHotelName());
            checkindatetv.setText(BaseURL.dates.get(0).getCheckindate());
            checkoutdatetv.setText(BaseURL.dates.get(0).getCheckoutDate());
            hotelname2.setText(BaseURL.dates.get(1).getHotelName());
            checkindatetv2.setText(BaseURL.dates.get(1).getCheckindate());
            checkoutdatetv2.setText(BaseURL.dates.get(1).getCheckoutDate());
            hotelname3.setText(BaseURL.dates.get(2).getHotelName());
            checkindatetv3.setText(BaseURL.dates.get(2).getCheckindate());
            checkoutdatetv3.setText(BaseURL.dates.get(2).getCheckoutDate());

            hotelName4.setText(BaseURL.dates.get(3).getHotelName());
            checkinDatep4.setText(BaseURL.dates.get(3).getCheckindate());
            CheckoutDate4.setText(BaseURL.dates.get(3).getCheckoutDate());
        }

        if (BaseURL.itinaryItems.size() == 1) {
            llitineraryone.setVisibility(View.VISIBLE);
            itinery.setText(BaseURL.itinaryItems.get(0).getPlaceName());
            itinarydatetv.setText("Date :"+BaseURL.itinaryItems.get(0).getDayplan());
        }
        if (BaseURL.itinaryItems.size() == 2) {
            llitineraryone.setVisibility(View.VISIBLE);
            llitinerarytwo.setVisibility(View.VISIBLE);
            itinery.setText(BaseURL.itinaryItems.get(0).getPlaceName());
            itinarydatetv.setText("Date :"+BaseURL.itinaryItems.get(0).getDayplan());
            itinery2.setText(BaseURL.itinaryItems.get(1).getPlaceName());
            itinarydatetv2.setText("Date :"+BaseURL.itinaryItems.get(1).getDayplan());
        }
        if (BaseURL.itinaryItems.size() > 2) {
            llitineraryone.setVisibility(View.VISIBLE);
            llitinerarytwo.setVisibility(View.VISIBLE);
            llitineraryThree.setVisibility(View.VISIBLE);

            itinery.setText(BaseURL.itinaryItems.get(0).getPlaceName());
            itinarydatetv.setText("Date :"+BaseURL.itinaryItems.get(0).getDayplan());
            itinery2.setText(BaseURL.itinaryItems.get(1).getPlaceName());
            itinarydatetv2.setText("Date :"+BaseURL.itinaryItems.get(1).getDayplan());
            itinery3.setText(BaseURL.itinaryItems.get(2).getPlaceName());
            itinarydatetv3.setText("Date"+BaseURL.itinaryItems.get(2).getDayplan());
        }

        itinerarycost.setText(PreviewData.itinererycost);
//        itinery.setText(BaseURL.itinarydate + "");
//        itinery.setText(BaseURL.itinarydate + "");
        //  hotelname.setText(PreviewData.hotelName+ "  " +PreviewData.hotelNameTwo);
        transportcost.setText(PreviewData.transportcost);
        transportrout.setText(PreviewData.fromRoute + " - " + PreviewData.toRoute);
        //
        if (CustomTripPlanDataHolder.routes.size() > 1) {
            transportshow.setVisibility(View.VISIBLE);

        } else {
            transportshow.setVisibility(View.GONE);
        }
        for (int i = 0; i < CustomTripPlanDataHolder.routes.size(); i++) {
            if (i == 0) {
                transportrout.setText(CustomTripPlanDataHolder.routes.get(i).getStartDistrictName() + " - " + CustomTripPlanDataHolder.routes.get(i).getEndDistrictName());
            }
            if (i == 1) {
                transportrouttwo.setText(CustomTripPlanDataHolder.routes.get(i).getStartDistrictName() + " - " + CustomTripPlanDataHolder.routes.get(i).getEndDistrictName());
            }
            if (i == 2) {
                tvformtransportthree .setText(CustomTripPlanDataHolder.routes.get(i).getStartDistrictName() + " - " + CustomTripPlanDataHolder.routes.get(i).getEndDistrictName());
            }
        }

        //
//        fromtv.setText();
        //Toast.makeText(context, ""+BaseURL.transportname.size(), Toast.LENGTH_SHORT).show();
        if (BaseURL.transportname.size() == 1) {
            transport.setText(BaseURL.transportname.get(0));
        }
        if (BaseURL.transportname.size() ==2) {
            transport.setText(BaseURL.transportname.get(0));
            transport2.setText(BaseURL.transportname.get(1));
        }else if (BaseURL.transportname.size() > 2){
            transport.setText(BaseURL.transportname.get(0));
            transport2.setText(BaseURL.transportname.get(1));
            transporttv3.setText(BaseURL.transportname.get(2));
        }
        //    fromtv.setText(PreviewData.fromRoute+" - "+PreviewData.toRoute);
        fromtv.setText(PreviewData.fromPlace);
        startdatetv.setText(PreviewData.stratDate);
        returntv.setText(PreviewData.returnDate);
        torriststv.setText(PreviewData.torrists);
        daystv.setText(PreviewData.days);
        hotelcost.setText(PreviewData.hotelcost);
//        checkindatetv.setText(CustomTripPlanDataHolder.checkindatee);
//        checkoutdatetv.setText(CustomTripPlanDataHolder.checkoutdatee);


        if (BaseURL.isEdit) {

            transportrout.setText(TailorMadeDataHolder.providerName);


        }
//        if (BaseURL.isEdit) {
//            startdatetv.setText(TailorMadeDataHolder.departDate);
//            returntv.setText(TailorMadeDataHolder.returnDate);
//            torriststv.setText(TailorMadeDataHolder.noOfTourists);
//            daystv.setText(TailorMadeDataHolder.noOfDays);
//            transportrout.setText(TailorMadeDataHolder.providerName);
//            fromtv.setText(TailorMadeDataHolder.ROUTE);
//            transportrout.setText(TailorMadeDataHolder.ROUTE);
//            checkindatetv.setText(TailorMadeDataHolder.checkin_date);
//            checkoutdatetv.setText(TailorMadeDataHolder.checkout_date);
//        }
        // fromtv,totv,startdatetv,returntv,torriststv,daystv,childrentv,avgagetv,checkindatetv,checkoutdatetv;
        dialog.show();
    }

    private void setListListener() {
        // Create Itinerary
        localtripListView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, localtripListView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        LocalTour localTour = localTours[position];
                        //Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
                        String day = dayplanSelector.getText().toString();
                        int localtour_id = localTour.getLocalTourId();
                        // Check for duplicate entry
                        for (Itenerary tmp_itenarery : iteneraries) {
                            //Toast.makeText(context,tmp_itenarery.getTime(),Toast.LENGTH_SHORT).show();
                            String dayPlan = getDayPlan();
                            switch (dayPlan) {
                                case ("Full Day"):
                                    String message = "This Day has already booked for full day";
                                    if (!BaseURL.LANGUAGE_ENG) {
                                        message = "এই দিন ইতিমধ্যে পুরো দিন জন্য নিবন্ধিত";
                                    }
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                    return;

                                case ("Half Day"):
                                    if (localTour.getLocalTourDuration().equalsIgnoreCase("full day")) {
                                        message = "This Day has already booked for half day you can only select half day";
                                        if (!BaseURL.LANGUAGE_ENG) {

                                            message = "এই দিন ইতিমধ্যে অর্ধেক জন্য নিবন্ধিত আপনি শুধুমাত্র অর্ধ দিন নির্বাচন করতে পারেন";
                                        }
                                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                                        return;

                                    } else if (isHalfDayBooked) {
                                        message = "This Day has already booked for full day";
                                        if (!BaseURL.LANGUAGE_ENG) {

                                            message = "এই দিন ইতিমধ্যে পুরো দিন জন্য নিবন্ধিত";
                                        }
                                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                                        return;
                                    }
                                    isHalfDayBooked = true;

                                    break;
                            }

                            if (tmp_itenarery.getLocalTourId() == localtour_id && tmp_itenarery.getDayplan().equals(day)) {

                                String meesage = "You Already planned for this place";
                                if (!BaseURL.LANGUAGE_ENG) {
                                    meesage = " আপনি ইতিমধ্যে এই জায়গার  জন্য পরিকল্পনা করেছেন ";
                                }
                                Toast.makeText(context, meesage, Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                        LocalTour l = CustomTripPlanDataHolder.localTourCost.get(position);
                        // manage Cost
                        int costInd = 0;
                        try {
                            // Toast.makeText(context,localTour.getLocalTourPerPersonCost()+"",Toast.LENGTH_SHORT).show();
                            costInd = Integer.parseInt(l.getLocalTourPerPersonCost()) * CustomTripPlanDataHolder.noOfTourist;

                        } catch (Exception ex) {
                        }
                        totalItenaryCost += costInd;
                        String itirenaryCostLang = "" + totalItenaryCost;

                        if (!BaseURL.LANGUAGE_ENG) {

                            itirenaryCostLang = BanglaNumberParser.getBangla(itirenaryCostLang);
                        }
                        totalCostView.setText(itirenaryCostLang + " ৳");
                        PreviewData.itinererycost = itirenaryCostLang;
                        int cost = BaseURL.totalCost + CustomTripPlanDataHolder.transPortcost;
                        String totalCostLang = cost + totalItenaryCost + "";
                        if (!BaseURL.LANGUAGE_ENG) {
                            totalCostLang = BanglaNumberParser.getBangla(totalCostLang);
                        }
                        totalCost.setText(totalCostLang + " ৳");
                        PreviewData.totalcost = totalCostLang;

                        // Add selected plan to Itenerary list
                        Itenerary itenerary = new Itenerary();
                        //Toast.makeText(ItineraryPlanner.this, ""+day, Toast.LENGTH_SHORT).show();
                        itenerary.setDayplan(day)
                                .setLocalTourId(l.getLocalTourId())
                                .setPlaceName(l.getDestinationNearbyPlaceName())
                                .setPerPersonCost(costInd + "")
                                .setTime(l.getLocalTourDuration())
                                .setTransport(l.getLocalTourTransportType());


                        iteneraries.add(0, itenerary);
                        //filterTrips();
                        // View Itenerary plan to list
                        if (itineraryListAdapter == null) {

                            //

                            itineraryListAdapter = new ItineraryListAdapter(context, iteneraries, ItineraryPlanner.this);
                            itineraryListView.setAdapter(itineraryListAdapter);
                            filterGroup(isGroup);
                        }
                        itineraryListAdapter.notifyDataSetChanged();
                        String meesage = "Added to plan";
                        if (!BaseURL.LANGUAGE_ENG) {
                            meesage = " পরিকল্পনা যোগ করা হয়েছে";

                        }
                        Toast.makeText(context, meesage, Toast.LENGTH_LONG).show();


                        itineraryListView.setVisibility(View.VISIBLE);
                        itineraryPlaceholderText.setVisibility(View.GONE);
                        filterTrips();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

    }

    String getDayPlan() {

        String day = dayplanSelector.getText().toString();

        for (Itenerary itenerary : iteneraries) {

            if (itenerary.getDayplan().equalsIgnoreCase(day)) {

                return itenerary.getTime();
            }


        }
        return "No Plan";

    }

    private void filterGroup(boolean checked) {

        ArrayList<LocalTour> filtered = new ArrayList<>();
        if (localTours == null || localTours.length == 0) return;

        if (checked) {
            for (LocalTour tour : localTours) {
                if (tour.getLocalTourStatus().equalsIgnoreCase("group")) {
                    //tour.setDestinationNearbyPlaceName(tour.getDestinationNearbyPlaceName() + "("+tour.getLocalTourMaxSize()+" Person"+")");
                    //localTours
                    tour.setLocalTourPerPersonCost(tour.localTourGroupCost);
                    //replaceLocalTour(tour);
                    filtered.add(tour);
                }
            }
        } else {
            for (LocalTour tour : localTours) {

                if (tour.getLocalTourStatus().equalsIgnoreCase("single")) {
                    tour.setLocalTourPerPersonCost(tour.getLocalTourPerPersonCost());
                    replaceLocalTour(tour);
                    filtered.add(tour);
                }
            }
        }

        LocalTour[] array = filtered.toArray(new LocalTour[filtered.size()]);
        localTourAdapter = new LocalTourAdapter(context, array);
        localtripListView.setAdapter(localTourAdapter);
    }

    private void onDistrcitSelectedListener() {
        spinnerDistricts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Route route = (Route) spinnerDistricts.getSelectedItem();
                //Toast.makeText(context,route.getEndDistrictName(),Toast.LENGTH_SHORT).show();
                districtID = route.getEndDistrictId();
                loadDestinationsByDistrict(route.getEndDistrictId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadDistrcist() {

        ArrayAdapter<Route> districts = new ArrayAdapter<Route>(context, R.layout.layout_spinner, R.id.spinnerText, CustomTripPlanDataHolder.routes);
        spinnerDistricts.setAdapter(districts);
    }


    private void loadDestinationsByDistrict(int districtId) {

        placeCallback.getDestinations(districtId).enqueue(new CustomCallBack<Destination[]>(this) {
            @Override
            public void onResponse(Call<Destination[]> call, Response<Destination[]> response) {
                super.onResponse(call, response);
                Log.e("Package Url", call.request().url().toString());
                if (response.body() != null && response.body().length > 0) {
                    ArrayAdapter<Destination> destinationAdapter = new ArrayAdapter<Destination>(context, R.layout.layout_spinner, R.id.spinnerText, response.body());
                    spinnerDestinations.setAdapter(destinationAdapter);
                } else if (response.body() != null) {
                    ArrayAdapter<Destination> destinationAdapter = new ArrayAdapter<Destination>(context, R.layout.layout_spinner, R.id.spinnerText, response.body());
                    spinnerDestinations.setAdapter(destinationAdapter);
                    String meesage = "Nothing Found";
                    if (!BaseURL.LANGUAGE_ENG) {
                        meesage = " কিছুই পাওয়া যায়নি";
                    }
                    Toast.makeText(context, meesage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Destination[]> call, Throwable t) {
                super.onFailure(call, t);
                String meesage = "Network Error";
                if (!BaseURL.LANGUAGE_ENG) {
                    meesage = " নেটওয়ার্ক ত্রুটি";
                }
                placeHolderImage.setVisibility(View.VISIBLE);
                placeHolderText.setText(meesage);
                placeHolderText.setVisibility(View.VISIBLE);

            }
        });
    }

    private void loadLocaltrips(String destination, String time) {
        final Call<LocalTour[]> getLocalTour = itineraryListener.getLocalTourList(destination, time);
        //Toast.makeText(this,destination+" " +time, Toast.LENGTH_LONG).show();
        getLocalTour.enqueue(new CustomCallBack<LocalTour[]>(this) {
            @Override
            public void onResponse(Call<LocalTour[]> call, Response<LocalTour[]> response) {
                super.onResponse(call, response);
                Log.e("Package Url", call.request().url().toString());
                if (response.body() != null && response.body().length > 0) {
                    //localTourAdapter=new LocalTourAdapter(context,response.body());
                    localTours = response.body();
                    if (iteneraries.size() > 0) {
                        filterTrips();
                    } else //localtripListView.setAdapter(localTourAdapter);
                        filterGroup(isGroup);
                } else {
                    String meesage = "No trip found";
                    if (!BaseURL.LANGUAGE_ENG) {
                        meesage = " কোন ট্রিপ খুঁজে পাওয়া যায় নি";

                    }
//                    placeHolderImage.setVisibility(View.VISIBLE);
//                    placeHolderText.setText(meesage);
//                    placeHolderText.setVisibility(View.VISIBLE);
                    Toast.makeText(context, meesage, Toast.LENGTH_SHORT).show();

                    localtripListView.setAdapter(null);
                    Log.e("Api url", getLocalTour.request().url().toString());
                }
            }

            @Override
            public void onFailure(Call<LocalTour[]> call, Throwable t) {
                super.onFailure(call, t);

                Log.e("Api url", getLocalTour.request().url().toString());
                Log.e("Api url", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem foodItem = menu.add(Menu.NONE, FOOD_MENU, Menu.NONE, "Food");
//
//
//        foodItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        foodItem.setIcon(R.drawable.icon_restaurent);


        if (!BaseURL.LANGUAGE_ENG) {
            MenuItem preview = menu.add(Menu.NONE, PREVIEW, Menu.NONE, "প্রিভিউ");
            preview.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else {
            MenuItem preview = menu.add(Menu.NONE, PREVIEW, Menu.NONE, "PREVIEW");
            preview.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }


        String menuName = "Save";
        if (!BaseURL.LANGUAGE_ENG) {
            menuName = "সংরক্ষণ";
        }
        MenuItem finishItem = menu.add(Menu.NONE, FINISH_MENU, Menu.NONE, menuName);
        finishItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
        //

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case FINISH_MENU:
                // TODO Save Itinerary
                // realm.beginTransaction();
                // tailorMade.iteneraries.clear();
                // tailorMade.itineraryCost=totalItenaryCost;
                //for(Itenerary itenerary:iteneraries){
                //     tailorMade.iteneraries.add(itenerary);
                // }
                //realm.commitTransaction();

                final int tmp_id = tailormade_id;
                String title = "Confirmation";
                String message = "Do You Want to Save your Trip Plan?";
                String yes = "Yes";
                String no = "No";

                if (!BaseURL.LANGUAGE_ENG) {
                    title = "নিশ্চিত করুন ";
                    message = "আপনি আপনার পরিকল্পনাটি  সংরক্ষণ করতে চান?";
                    yes = "হ্যা";
                    no = "না";
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent loginIntent = new Intent(ItineraryPlanner.this, LoginActivity.class);
                        startActivityForResult(loginIntent, tmp_id);
                    }
                });
                dialog.setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Intent intent=new Intent(ItineraryPlanner.this,MainActivity.class);
                        //startActivity(intent);
                    }
                });
                dialog.setMessage(message);
                dialog.setTitle(title);
                dialog.show();
//                tailormade_id++;
//                editor.putInt(Travel.CURRENT_TAILORMADE_ID,tailormade_id);
//                editor.commit();

                //===========================================================

                break;
//            case FOOD_MENU:
//                Intent foodIntent = new Intent(ItineraryPlanner.this, FoodListActivity.class);
//                foodIntent.putExtra("DISTRICT_ID", districtID + "");
//                startActivityForResult(foodIntent, REQUEST_FOOD);
//                break;
            case PREVIEW:
                callPreviewDialog();
                break;

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOOD) {
//            for(Food food:){
//                tailorMade.iteneraries.add(itenerary);
//            }
            String json = "";
            try {
                json = data.getExtras().getString("FOOD_LIST", "");
                //Toast.makeText(this, ""+json, Toast.LENGTH_SHORT).show();
                ListWrapper listWrapper = new Gson().fromJson(json, ListWrapper.class);
                List<Food> selectedFoods = listWrapper.getFoods();
                for (int i = 0; i < selectedFoods.size(); i++) {
                    totalItenaryCost += Integer.parseInt(selectedFoods.get(i).getDestinationFoodServiceProviderPriceRange());
                }
            } catch (Exception e) {
            }
            return;
        }
        //Toast.makeText(context,requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();
        if (resultCode != 200) return;
        int tailormade_id = requestCode;
        TailorMade tailorMade = new TailorMade();
        saveTailormadeToServer(tailorMade);
        Intent intent = new Intent(ItineraryPlanner.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        String meesage = "Your Trip Plan has been Saved. Please Check \"My Trips\" for Details.";
        if (!BaseURL.LANGUAGE_ENG) {
            meesage = " আপনার পরিকল্পনাটি সংরক্ষিত হয়েছে।  বিস্তারিত জানতে \"আমার পরিকল্পনা\" অপশন দেখুন ";
        }
        intent.putExtra("Message", meesage);
        startActivity(intent);
    }

    private void saveTailormadeToServer(TailorMade tailorMade) {
        String user = sharedPreferences.getString(Travel.USER_EMAIL, null);
        int length = CustomTripPlanDataHolder.routes.size();
        String distritctFrom = "";
        String districtTo = "";
        int districtFromId = 0;
        int districtToId = 0;
        if (length > 0) {
            Route from = CustomTripPlanDataHolder.routes.get(0);
            Route to = CustomTripPlanDataHolder.routes.get(length - 1);
            distritctFrom = from.getStartDistrictName();
            districtTo = to.getEndDistrictName();
            districtFromId = from.getStartDistrictId();
            districtToId = to.getEndDistrictId();
            Toast.makeText(context, ""+districtToId, Toast.LENGTH_SHORT).show();
        }
        removeDuplicate();
        if (BaseURL.accommodationRooms.size() > 0) {
            for (AccommodationRoom temp : BaseURL.accommodationRooms) {
                try {
                    if (temp.getAccommodationCategoryId() == 0) {
                        temp.setAccommodationRoomId(null);
                        temp.setAccommodationCategoryName(null);
                        temp.setAccommodationCategoryId(null);
                    }

                } catch (Exception e) {
                }
            }

        }

        DateStore dateStore = new DateStore();
        final TailormadeSync testtailormade = new TailormadeSync();
        testtailormade.setCustomerId(user)
                .setTailormade_id(TailorMadeDataHolder.tailorMadeId)
                .setDeapartDistrictId(districtFromId)
                .setDepartDistrictName(distritctFrom)
                .setDestinationDistrictId(districtToId)
                .setDestinationDistrictName(districtTo)
                .setDepartDate(BanglaNumberParser.getEnglish(CustomTripPlanDataHolder.startingDate))
                .setReturnDate(BanglaNumberParser.getEnglish(CustomTripPlanDataHolder.returnDate))
                .setNumberOFDays(CustomTripPlanDataHolder.noOfDays)
                .setNumberOFTourists(CustomTripPlanDataHolder.noOfTourist)

                //start sazzad
//                .setCheckin(CustomTripPlanDataHolder.checkindatee)
//                .setCheckOut(CustomTripPlanDataHolder.checkoutdatee)
                .setCheckin(indate)
                .setCheckOut(outdate)
                .settailormade_all_district_id(BaseURL.districtIdForDestination)

                .setTransport_date(BaseURL.transportdateList)
                .settailormadeChildDescription(BaseURL.childagesStr)
                //end sazzad
                .setTotalCost(CustomTripPlanDataHolder.transPortcost + BaseURL.totalCost + totalItenaryCost + "");
        testtailormade.tailormadeNumberOfChild = PreviewData.noOfchild + "";

        if (BaseURL.isEdit)
            testtailormade.requestType = "edit";
        testtailormade.tailorMadeTailorMadeId = TailorMadeDataHolder.tailorMadeId;

        if (!BaseURL.LANGUAGE_ENG) {
            testtailormade.requestVersion = "bn";
        }
        List<Route> realm_transport_provider = CustomTripPlanDataHolder.routes;
        //List<AccommodationRoom> realm_acc_provider=;
        List<Itenerary> realm_itinerary = iteneraries;
        testtailormade.setTransportProviders(realm_transport_provider);
        testtailormade.setAccommodationProviders(BaseURL.accommodationRooms);
//        testtailormade.setCheckin(BaseURL.checkindates);
//        testtailormade.setCheckOut(BaseURL.checkoutdates);

        testtailormade.setIteneraries(realm_itinerary);
        Retrofit retrofit = RetrofitInitializer.initNetwork(this);
        TailormadeListener tailormadeListener = retrofit.create(TailormadeListener.class);
        Gson gson = new Gson();
        String json = gson.toJson(testtailormade);
        //  Toast.makeText(this, ""+json, Toast.LENGTH_SHORT).show();
        Log.e("Url", "Null");
        Log.e("JSON", json);
        TailormadeSync test = new TailormadeSync();
        authCallback.SendTailorMade(testtailormade).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.e("Url",response.body());
                Gson gson = new Gson();
                String json = gson.toJson(testtailormade);
                Log.e("Url", "Null");
                Log.e("JSON", json);

                String meesage = "Your Trip Plan has been Saved. Please Check \"My Trips\" for Details.";
                if (!BaseURL.LANGUAGE_ENG) {
                    meesage = " আপনার ট্রিপ প্ল্যান টি সংরক্ষিত হয়েছে।  বিস্তারিত জানতে \"আমার পরিকল্পনা\" অপশন দেখুন ";
                }
                // Toast.makeText(ItineraryPlanner.this,meesage,Toast.LENGTH_LONG).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(ItineraryPlanner.this);
                builder.setMessage(meesage);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });
               // finish();

//
//                TripPlannerFragment tripplannerFragment = TripPlannerFragment.newInstance();
//                loadFragment(tripplannerFragment);


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
//        realm.beginTransaction();
//        this.tailorMade.accommodationRooms = null;
//        realm.close();

//        tailormadeListener.getReturn(testtailormade).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                        if (response.body() == null)
//                        {
//                            Gson gson = new Gson();
//                            String json = gson.toJson(testtailormade);
//                            Log.e("Url","Null");
//                            Log.e("JSON",json );
//                        }
//                        else
//                        {
//                            Log.e("Url","!Null");
//                        }
//                Log.e("Url",call.request().url().toString());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//               // Log.e("error",t.getMessage());
//            }
//        });
        Intent intent = new Intent(ItineraryPlanner.this, MainActivity.class);
        startActivity(intent);

    }


    public void loadFragment (Fragment fragment)
    {
       FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.listFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(context,"Resume",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void calculateCost(int cost) {
        int total = BaseURL.totalCost + CustomTripPlanDataHolder.transPortcost + totalItenaryCost;
        totalItenaryCost -= cost;
        total -= cost;
        String totalLang = total + "";
        String totalItirenary = totalItenaryCost + "";

        if (!BaseURL.LANGUAGE_ENG) {
            totalLang = BanglaNumberParser.getBangla(totalLang);
            totalItirenary = BanglaNumberParser.getBangla(totalItirenary);
        }
        totalCost.setText(totalLang + "৳");
        totalCostView.setText(totalItirenary + "৳");
        PreviewData.totalcost = totalLang;


    }

    ArrayList<AccommodationRoom> test = new ArrayList<>();

    public void removeDuplicate() {
        List<AccommodationRoom> filter = new ArrayList<>();
        for (AccommodationRoom a : BaseURL.accommodationRooms) {
            if (!doesExist(a)) {
                test.add(a);
            }
        }
        BaseURL.accommodationRooms = new ArrayList<>();
        BaseURL.accommodationRooms = test;
    }

    boolean doesExist(AccommodationRoom a) {

        if (test.size() > 0) {
            for (AccommodationRoom acc : test) {
                if (a.getAccommodationRoomId() == acc.getAccommodationRoomId() && acc.getAccommodationRoomId() != 0)
                    return true;
            }
        }
        return false;
    }



}
