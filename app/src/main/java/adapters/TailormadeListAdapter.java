/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;
import com.olivine.parjatanbichitra.cholodesh.TailorMadeActivity;
import com.olivine.parjatanbichitra.cholodesh.TailorMadeEditActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.DynamicHeight;
import helpers.TailorMadeDataHolder;
import model.HotelDate;
import model.PreviewData;
import model.TailorMadeList;
import userDefinder.TailorMade;
/**
 * Created by Olivine on 6/7/2017.
 */
public class TailormadeListAdapter extends RecyclerView.Adapter<TailorMadeViewholder> {
    private Context mContext;
    private List<TailorMadeList> tailorMades;
    int id ;

    public TailormadeListAdapter(Context mContext, List<TailorMadeList> tailorMades) {
        this.mContext = mContext;
        this.tailorMades = tailorMades;
    }
    @Override
    public TailorMadeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.layout_tailor_made_list_new,parent,false);
        TailorMadeViewholder tailorMadeViewholder=new TailorMadeViewholder(view);
        id=0;
        return tailorMadeViewholder;
    }
    @Override
    public void onBindViewHolder(TailorMadeViewholder holder, int position) {
        final TailorMadeList tailorMade=tailorMades.get(position);
holder.date.setText("Create Date :"+tailorMades.get(position).getCreatedAt());
        DynamicHeight.setHeight(mContext,holder.parent,4,4);
        int id= Integer.parseInt(tailorMade.gettailormade_confirm_status());

        if (id==1){
            holder.active.setText("Confirmed");
        }else if (id==0){
            holder.active.setText("waiting");
        }
        final String url = BaseURL.DESTINATION_IMAGE_BASE_URL+tailorMade.getTailormadeDestinationDistrictImage();
                    Picasso.with(mContext)
                            .load(url)
                            .fit()
                            .placeholder(R.drawable.image_placeholder)
                            .into(holder.imageViewLoad);
        String cost = tailorMade.getTailormadeTotalCost();
        if (!BaseURL.LANGUAGE_ENG){
            cost = BanglaNumberParser.getBangla(cost);
        }
        holder.cost.setText(cost+ " ৳");
        //Toast.makeText(mContext,tailorMade.getTailormadeDays()+" days Trip from "+ tailorMade.getTailormadeDepartDistrictName()+ " to "+tailorMade.getTailormadeDestinationDistrictName(),Toast.LENGTH_SHORT).show();
        holder.dayNight.setText(tailorMade.getTailormadeDays()+" DAYS TRIP FROM "+ tailorMade.getTailormadeDepartDistrictName()+ " TO "+tailorMade.getTailormadeDestinationDistrictName());
        holder.travellers.setText(tailorMade.getTailormadePerson()+ "Traveler(s)");
        holder.dates.setText("Start Date: "+tailorMade.getTailormadeFromDate() + "\n" + "Return Date: "+tailorMade.getTailormadeToDate());
        PreviewData.stratDate=tailorMade.getTailormadeFromDate();
        PreviewData.returnDate=tailorMade.getTailormadeToDate();
        PreviewData.fromPlace= tailorMade.getTailormadeDepartDistrictName()+ " TO "+tailorMade.getTailormadeDestinationDistrictName();

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseURL.dates.clear();
                BaseURL.transportname.clear();
                BaseURL.itinaryItemtailormade.clear();
                BaseURL.routess.clear();
                TailorMadeDataHolder.clearAll();
                TailorMadeDataHolder.tailorMadeId = tailorMade.getTailormadeId();
//                Toast.makeText(mContext, ""+tailorMade.gettailormade_confirm_status(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(mContext, ""+tailorMade.gettailormade_confirm_message(), Toast.LENGTH_LONG).show();
                TailorMadeDataHolder.return_status=tailorMade.gettailormade_confirm_status();
                TailorMadeDataHolder.return_massage= tailorMade.gettailormade_confirm_message();
                TailorMadeDataHolder.Status = tailorMade.status;
                TailorMadeDataHolder.noOfDays = tailorMade.getTailormadeDays();
                TailorMadeDataHolder.noOfTourists = tailorMade.getTailormadePerson();
                TailorMadeDataHolder.departDate = tailorMade.getTailormadeFromDate();
                TailorMadeDataHolder.returnDate = tailorMade.getTailormadeToDate();
                TailorMadeDataHolder.providerName = tailorMade.providerName;
                TailorMadeDataHolder.providerImage = tailorMade.providerImage;
                TailorMadeDataHolder.confDate = tailorMade.confirmationDate;
                TailorMadeDataHolder.biddingAmount = tailorMade.biddingAmount;
                TailorMadeDataHolder.noOfChildren = tailorMade.noOfChildren;
                TailorMadeDataHolder.childrenDesc = tailorMade.childrenDesc;
                PreviewData.days=  tailorMade.getTailormadeDays();
                PreviewData.torrists =tailorMade.getTailormadePerson();
                PreviewData.stratDate =tailorMade.getTailormadeFromDate();
                PreviewData.returnDate=tailorMade.getTailormadeToDate();
                PreviewData.hotelName =tailorMade.providerName;
                PreviewData.hotelName =tailorMade.providerName;
                PreviewData.checkoutDate =tailorMade.check_in_date;
                PreviewData.checkoutDate =tailorMade.check_out_date;
                //provider
                PreviewData.noOfchild=tailorMade.noOfChildren;
                TailorMadeDataHolder.checkin_date = tailorMade.check_in_date;
                TailorMadeDataHolder.checkout_date = tailorMade.check_out_date;

                Toast.makeText(mContext, ""+tailorMade.getlatitude(), Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext, ""+tailorMade.getlongitude(), Toast.LENGTH_SHORT).show();

                //end sazzad
//                TailorMadeDataHolder.makeRoutes(tailorMade.getTailormadeId(),(Activity) mContext);
                Intent intent = new Intent(mContext,TailorMadeActivity.class);
                intent.putExtra("TAILOR_MADE_ID",tailorMade.getTailormadeId()+"");
                intent.putExtra("DESTINATION_IMAGE_PATH",url);
                intent.putExtra("DESTINATION_DISTRICT_NAME",tailorMade.getTailormadeDestinationDistrictName());
                String cost = tailorMade.getTailormadeTotalCost();
                if (!BaseURL.LANGUAGE_ENG){
                    cost = BanglaNumberParser.getBangla(cost);
                }
                intent.putExtra("COST_TOTAL",cost);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tailorMades.size();
    }
}
class TailorMadeViewholder extends RecyclerView.ViewHolder{
    RelativeLayout parent;
    TextView cost;
    TextView dayNight;
    TextView travellers;
    ImageView imageViewLoad;
    TextView dates;
    TextView date;
TextView active;

    public TailorMadeViewholder(View itemView) {
        super(itemView);
        parent= (RelativeLayout) itemView.findViewById(R.id.parent);
        cost= (TextView) itemView.findViewById(R.id.cost);
        dayNight = (TextView) itemView.findViewById(R.id.dayNight);
        travellers= (TextView) itemView.findViewById(R.id.travellers);
        imageViewLoad = (ImageView) itemView.findViewById(R.id.imageViewLoad);
        dates = (TextView) itemView.findViewById(R.id.dates);
        active = (TextView) itemView.findViewById(R.id.active);
        date=(TextView)itemView.findViewById(R.id.date);

    }
}
