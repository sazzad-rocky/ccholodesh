package adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.ItineraryPlanner;
import com.olivine.parjatanbichitra.cholodesh.LoginActivity;
import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.ArrayList;

import helpers.BaseURL;
import listeners.IteneraryListener;
import model.Itenerary;
/**
 * Created by Olivine on 5/10/2017.
 */
public class ItineraryListAdapter extends RecyclerView.Adapter<ItinerayViewHolder> {
    private Context mContext;
    private ArrayList<Itenerary> iteneraryList;
    private IteneraryListener iteneraryListener;

    public ItineraryListAdapter(Context mContext, ArrayList<Itenerary> iteneraryList, IteneraryListener listener) {
        this.mContext = mContext;
        this.iteneraryList = iteneraryList;
        iteneraryListener = listener;
    }
    
    @Override
    public ItinerayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_itinerary_list,parent,false);
        ItinerayViewHolder holder=new ItinerayViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItinerayViewHolder holder, int position) {
        //Toast.makeText(mContext,"get",Toast.LENGTH_SHORT).show();
        final Itenerary itenerary=iteneraryList.get(position);
        holder.dayplan.setText(itenerary.getDayplan());
        holder.placeName.setText(itenerary.getPlaceName());
        holder.costPerperson.setText(itenerary.getPerPersonCost()+"৳");
        holder.time.setText(itenerary.getTime());
//       BaseURL.itinarydate.add(itenerary.getDayplan());
//       BaseURL.itinaryplacename.add(itenerary.getPlaceName());
        BaseURL.itinaryItems.add(itenerary);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iteneraryList.remove(itenerary);
                notifyDataSetChanged();
                iteneraryListener.calculateCost(Integer.parseInt(itenerary.getPerPersonCost()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return iteneraryList.size();
    }
}
class ItinerayViewHolder extends RecyclerView.ViewHolder{
    TextView dayplan;
    TextView placeName;
    TextView costPerperson;
    TextView time;
    LinearLayout Parent;
    TextView delete;
    public ItinerayViewHolder(View itemView) {
        super(itemView);
        dayplan= (TextView) itemView.findViewById(R.id.dayplan);
        placeName= (TextView) itemView.findViewById(R.id.placeName);
        costPerperson= (TextView) itemView.findViewById(R.id.costPerperson);
        time= (TextView) itemView.findViewById(R.id.time);
        Parent = (LinearLayout) itemView.findViewById(R.id.Parent);
        delete = (TextView) itemView.findViewById(R.id.delete);
    }
}