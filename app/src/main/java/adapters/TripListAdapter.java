package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.ArrayList;

import model.Trip;

/**
 * Created by Olivine on 5/6/2017.
 */

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripsViewHolded> {
    private Context mContext;
    private ArrayList<Trip> tripList;

    public TripListAdapter(Context mContext, ArrayList<Trip> tripList) {
        this.mContext = mContext;
        this.tripList=tripList;
    }

    @Override
    public TripsViewHolded onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(mContext).inflate(R.layout.layout_route_list,parent,false);
        TripsViewHolded holder=new TripsViewHolded(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(TripsViewHolded holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class TripsViewHolded extends RecyclerView.ViewHolder {
        TextView textLocation;
        TextView textDate;

        public TripsViewHolded(View itemView) {
            super(itemView);
            textLocation = (TextView) itemView.findViewById(R.id.textLocation);
            textDate = (TextView) itemView.findViewById(R.id.textDate);

        }
    }
}
