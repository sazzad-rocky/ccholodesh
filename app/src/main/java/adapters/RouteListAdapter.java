package adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;

import callbacks.ProvideCallback;
import helpers.BaseURL;
import helpers.CustomCallBack;
import helpers.ViewAssist;
import listeners.TransportCostListener;
import model.Route;
import model.TransportProvider;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Code Freak Tanveer on 17/02/2017.
 */

public class RouteListAdapter extends RecyclerView.Adapter<RouteViewHolder> {
    private Activity mContext;
    private TransportCostListener transportCostListener;
    private  Route[] routes;
    private ProvideCallback provideCallback;
    private int [] drawables=new int[]{R.drawable.icon_bus_white,R.drawable.icon_airoplane,R.drawable.icon_train,R.drawable.icon_ship};

    int lastPosition=-1;
    public RouteListAdapter(Activity mContext, Route[] routes){
        this.mContext=mContext;
        this.routes=routes;
        provideCallback=new ProvideCallback(mContext);
    }

    public void setTransportCostListener(TransportCostListener transportCostListener) {
        this.transportCostListener = transportCostListener;
        return ;
    }

    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_route_list,parent,false);
        RouteViewHolder promotionViewHolder=new RouteViewHolder(view);
        return promotionViewHolder;
    }

    @Override
    public void onBindViewHolder(final RouteViewHolder holder, final int position) {
        setAnimation(holder.Container, position);
        final Route route=routes[position];
        //if (route.getEndDistrictName() == null || route.getStartDistrictName() == null) return;
        holder.journeyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtView= holder.txtCost;
                String message = "Select Expected date";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    message = "প্রত্যাশিত তারিখ নির্বাচন করুন";
                }
                ViewAssist.setDate(mContext,txtView,message);
            }
        });
        holder.txtRouteName.setText(route.getStartDistrictName()+" To "+route.getEndDistrictName());


        //holder.txtCost.setText("got");
        if(route.getTransportProvider()!=null){
            holder.txtTransport.setText(route.getTransportProvider().getTransportInfoOperatorName());
        }
        //Toast.makeText(mContext,route.getTransportProvider().getTransportInfoOperatorName(),Toast.LENGTH_LONG).show();
        // change tranport for route
        holder.changeTransportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // load route transport
                //Toast.makeText(mContext,route.getStartDistrictId()+" "+route.getEndDistrictId(),Toast.LENGTH_LONG).show();

                provideCallback.getTransportProviders(route.getStartDistrictId(),route.getEndDistrictId()).enqueue(new CustomCallBack<TransportProvider[]>(mContext) {
                    @Override
                    public void onResponse(Call<TransportProvider[]> call, Response<TransportProvider[]> response) {
                        super.onResponse(call,response);
                        //Log.e("Package Url",call.request().url().toString());
                        if(response.body().length==0){
                            Handler handler=new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String message = "No available transport";
                                    if (!BaseURL.LANGUAGE_ENG)
                                    {
                                        message = "কোন পরিবহন ব্যবস্থা নেই";
                                    }
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            });
                            return;
                        }

                        viewTransport(response.body(),holder,position);
                    }

                    @Override
                    public void onFailure(Call<TransportProvider[]> call, Throwable t) {
                        super.onFailure(call,t);
                        String message = "Network Error";
                        if (!BaseURL.LANGUAGE_ENG)
                        {
                            message = "নেটওয়ার্ক ত্রুটি";
                        }
                        Toast.makeText(mContext,message , Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return routes.length;
    }

    private void viewTransport(final TransportProvider []transportProviders, final RouteViewHolder holder, final int adapterPosition){

        TransportListAdapter transportListAdapter=new TransportListAdapter(mContext,transportProviders);
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(mContext);
        builderSingle.setIcon(R.drawable.icon_airoplane);
        String title = "Select Transport";
        if (!BaseURL.LANGUAGE_ENG)
        {
           title = "পরিবহন নির্বাচন করুন";
        }
        builderSingle.setTitle(title);

        builderSingle.setAdapter(transportListAdapter, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TransportProvider transportProvider=transportProviders[i];
                routes[adapterPosition].setTransportProvider(transportProvider);
//
                transportCostListener.addTransportCost(transportProvider);
            }
        });
        builderSingle.show();

    }
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.recyclerview_animation);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}

class RouteViewHolder extends RecyclerView.ViewHolder {
    View Container;
    TextView txtRouteName;
    TextView txtTransport;
    TextView txtCost;
    ImageView transportTypeView;
//    selectable views
    TextView journeyDate;
    TextView transportOption;
    Button changeTransportButton;
    Button transportdate;


    public RouteViewHolder(View itemView) {
        super(itemView);
        Container = itemView.findViewById(R.id.container);
        // Selectable veiw
        journeyDate= (TextView) itemView.findViewById(R.id.journeyDate);
        transportOption= (TextView) itemView.findViewById(R.id.transportOption);
        changeTransportButton= (Button) itemView.findViewById(R.id.changeTransportButton);
        transportdate= (Button) itemView.findViewById(R.id.transportdate);
        // labels
        txtTransport= (TextView) itemView.findViewById(R.id.txtTransport);
        txtCost= (TextView) itemView.findViewById(R.id.txtCost);
        txtRouteName= (TextView) itemView.findViewById(R.id.txtRouteName);
        transportTypeView= (ImageView) itemView.findViewById(R.id.transportTypeView);
        if  (!BaseURL.LANGUAGE_ENG)
        {
            txtTransport.setText("পরিবহন নির্বাচন করুন");
            changeTransportButton.setText("নির্বাচন");
        }

//        promotionHeadingText= (TextView) itemView.findViewById(R.id.promotionHeadingText);
//        promotionStartDate= (TextView) itemView.findViewById(R.id.promotionStartDate);
//        promotionEndDate= (TextView) itemView.findViewById(R.id.promotionEndDate);
//        promotionDetailsText= (TextView) itemView.findViewById(R.id.promotionDetailsText);
    }


}