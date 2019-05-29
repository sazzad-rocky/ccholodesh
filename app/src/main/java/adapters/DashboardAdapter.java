package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;
import com.olivine.parjatanbichitra.cholodesh.TailorMadeActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.TailorMadeDataHolder;
import model.TailorMadeList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>{

    private Context context;
    private List<TailorMadeList> lists;

    public  DashboardAdapter(Context context,List<TailorMadeList> tailorMadeLists){
        this.context = context;
        this.lists =tailorMadeLists;
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.dashboard_single_row,parent,false);
        DashboardViewHolder dashboardViewHolder=new DashboardViewHolder(view);
        return dashboardViewHolder;
    }

    @Override
    public void onBindViewHolder(DashboardViewHolder holder, final int position) {
        holder.startdate.setText("Start Date: "+lists.get(position).getTailormadeFromDate() + "\n" + "Return Date: "+lists.get(position).getTailormadeToDate());
        holder.totalcost.setText("Total Cost  "+lists.get(position).getTailormadeTotalCost());

        if (Integer.parseInt(lists.get(position).gettailormade_confirm_status())==1) {
            holder.status.setText("Operator Responced");
        }
        if (Integer.parseInt(lists.get(position).gettailormade_confirm_status())==2){
            holder.status.setText("Confirmed");
        }

        final String url = BaseURL.DESTINATION_IMAGE_BASE_URL+lists.get(position).getTailormadeDestinationDistrictImage();



        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TailorMadeDataHolder.clearAll();
                TailorMadeDataHolder.tailorMadeId = lists.get(position).getTailormadeId();
//                Toast.makeText(mContext, ""+tailorMade.gettailormade_confirm_status(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(mContext, ""+tailorMade.gettailormade_confirm_message(), Toast.LENGTH_LONG).show();

                TailorMadeDataHolder.return_status=lists.get(position).gettailormade_confirm_status();
                TailorMadeDataHolder.return_massage= lists.get(position).gettailormade_confirm_message();
                TailorMadeDataHolder.Status = lists.get(position).status;
                TailorMadeDataHolder.noOfDays = lists.get(position).getTailormadeDays();
                TailorMadeDataHolder.noOfTourists = lists.get(position).getTailormadePerson();
                TailorMadeDataHolder.departDate = lists.get(position).getTailormadeFromDate();
                TailorMadeDataHolder.returnDate = lists.get(position).getTailormadeToDate();
                TailorMadeDataHolder.providerName = lists.get(position).providerName;
                TailorMadeDataHolder.providerImage = lists.get(position).providerImage;
                TailorMadeDataHolder.confDate = lists.get(position).confirmationDate;
                TailorMadeDataHolder.biddingAmount = lists.get(position).biddingAmount;
                TailorMadeDataHolder.noOfChildren = lists.get(position).noOfChildren;
                TailorMadeDataHolder.childrenDesc = lists.get(position).childrenDesc;

                //start sazzad
                TailorMadeDataHolder.checkin_date = lists.get(position).check_in_date;
                TailorMadeDataHolder.checkout_date = lists.get(position).check_out_date;
                Intent intent = new Intent(context,TailorMadeActivity.class);
                intent.putExtra("TAILOR_MADE_ID",lists.get(position).getTailormadeId()+"");
                intent.putExtra("DESTINATION_IMAGE_PATH",url);
                intent.putExtra("DESTINATION_DISTRICT_NAME",lists.get(position).getTailormadeDestinationDistrictName());
                String cost = lists.get(position).getTailormadeTotalCost();
                if (!BaseURL.LANGUAGE_ENG){
                    cost = BanglaNumberParser.getBangla(cost);
                }
                intent.putExtra("COST_TOTAL",cost);
                context.startActivity(intent);


            }
        });


        holder.tripLine.setText(lists.get(position).getTailormadeDays()+" DAYS TRIP FROM "+ lists.get(position).getTailormadeDepartDistrictName()+ " TO "+lists.get(position).getTailormadeDestinationDistrictName());
     //   holder.tripLine.setText(lists.getTailormadeDays()+" DAYS TRIP FROM "+ tailorMade.getTailormadeDepartDistrictName()+ " TO "+tailorMade.getTailormadeDestinationDistrictName());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class DashboardViewHolder extends ViewHolder{

        TextView tripLine,startdate,totalcost,status,details;
        View convertView;
        public DashboardViewHolder(View itemView) {
            super(itemView);
            this.convertView=itemView;

            tripLine =(TextView)convertView.findViewById(R.id.tripLine);
            startdate =(TextView)convertView.findViewById(R.id.startdate);
            totalcost =(TextView)convertView.findViewById(R.id.totalcost);
            status =(TextView)convertView.findViewById(R.id.status);
            details =(TextView)convertView.findViewById(R.id.details);
        }
    }
}
