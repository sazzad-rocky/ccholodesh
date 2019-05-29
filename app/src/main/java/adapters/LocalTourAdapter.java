package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.ArrayList;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.CustomTripPlanDataHolder;
import model.LocalTour;

/**
 * Created by Olivine on 5/9/2017.
 */

public class LocalTourAdapter extends RecyclerView.Adapter<LocalTourListViewHolder>{
    Context mContext;
    private LocalTour []tripList;
    public LocalTourAdapter(Context mContext, LocalTour []tripList) {
        this.mContext = mContext;
        this.tripList = tripList;
        CustomTripPlanDataHolder.localTourCost = new ArrayList<>();
    }
    public LocalTour[] getTripList() {
        return tripList;
    }

    @Override
    public LocalTourListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(mContext).inflate(R.layout.layout_local_tour_list,parent,false);
        LocalTourListViewHolder holder=new LocalTourListViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(LocalTourListViewHolder holder, int position) {

        final  LocalTour localTour=tripList[position];
        String placeName = localTour.getDestinationNearbyPlaceName();
        String grp = "";
        if (localTour.getLocalTourMaxSize() != null && localTour.getLocalTourMaxSize().length() > 0)  grp = "("+localTour.getLocalTourMaxSize()+" Person"+")";
        String duration = localTour.getLocalTourDuration();
        String cost = localTour.getLocalTourPerPersonCost();
        String resType = localTour.getReservationTypeName();
        if (!BaseURL.LANGUAGE_ENG){
            placeName = localTour.placeNameBn;
            duration = BanglaNumberParser.getBangla(duration);
            cost = BanglaNumberParser.getBangla(cost);
            resType = localTour.resTypeBn;
            if (localTour.getLocalTourMaxSize() != null && localTour.getLocalTourMaxSize().length() > 0)  grp = "("+BanglaNumberParser.getBangla(localTour.getLocalTourMaxSize())+" জন  "+")";
            if (duration.equalsIgnoreCase("half day")){
                duration = "অর্ধেক দিন";
            }
            if (duration.equalsIgnoreCase("full day")){
                duration = "সারা দিন";
            }
        }
        holder.textLocation.setText(placeName+" "+grp);
        holder.textTourType.setText(duration);
        holder.tourCost.setText(cost+"৳");
        CustomTripPlanDataHolder.localTourCost.add(localTour);
        holder.textReservationType.setText(resType);
        //Toast.makeText(mContext,localTour.getLocalTourStatus(),Toast.LENGTH_SHORT).show();
        holder.localTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(mContext, "hi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tripList.length;
    }
}
 class LocalTourListViewHolder extends RecyclerView.ViewHolder{
     ImageView localTransport;
     TextView textLocation;
     TextView textTourType;
     TextView textReservationType;
     TextView tourCost;
    public LocalTourListViewHolder(View itemView) {
        super(itemView);
        localTransport= (ImageView) itemView.findViewById(R.id.localTransport);
        textLocation= (TextView) itemView.findViewById(R.id.textLocation);
        textTourType= (TextView) itemView.findViewById(R.id.textTourType);
        tourCost= (TextView) itemView.findViewById(R.id.tourCost);
        textReservationType= (TextView) itemView.findViewById(R.id.textReservationType);

    }
}
